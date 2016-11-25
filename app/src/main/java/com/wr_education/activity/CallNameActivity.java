package com.wr_education.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.adapter.CallNameGridViewAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.CallNameBean;
import com.wr_education.bean.PublicBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 课堂点名页面
 * Created by Administrator on 2016/4/15.
 */
public class CallNameActivity extends AbstractActivity {
    @Bind(R.id.callname_imgbtn_back)
    ImageButton callnameImgbtnBack;
    @Bind(R.id.callname_tv_title)
    TextView callnameTvTitle;
    @Bind(R.id.callname_tv_commit)
    TextView callnameTvCommit;
    @Bind(R.id.callname_gridview)
    GridView callnameGridview;
    private CallNameBean bean;
    private ArrayList<CallNameBean.DataBean> list;
    private CallNameGridViewAdapter adapter;
    private String id; //用来存放当前课程的id
    private String second_call; //用来存放当前应该进行点名还是补点名操作。  0--点名   1--补点名
    private String[] present;//点名到场的学生id，格式为数组，如[1,2,3]
    private String[] absent;//点名未到的学生id，格式为数组，如[4,5,6]，没有则传空数组
    private PublicBean publicBean;

    private AlertDialog.Builder alert;

    @Override
    protected void initLayout() {
        setContentView(R.layout.callname_layout);
    }

    @Override
    protected void aadListenter() {
        callnameTvCommit.setOnClickListener(this);
        callnameImgbtnBack.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        list = new ArrayList<CallNameBean.DataBean>();
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
            second_call = getIntent().getStringExtra("second_call");
            if (second_call.equals("1")) { //进行点名操作
                callnameTvTitle.setText("补点名");
            }
        }
        if (Utility.NetworkAvailable(CallNameActivity.this)) {
            new GetCallNameTask().execute(id);
        } else {
            Utility.showToastNoNetWork(CallNameActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.callname_imgbtn_back:
                finish();
                break;
            case R.id.callname_tv_commit:
                if (bean.getData() != null) {
                    if (bean.getData().size() != 0) {
                        int presentnumber = 0; //在
                        int absentnumber = 0; //缺席
                        for (int i = 0; i < bean.getData().size(); i++) {
                            if (bean.getData().get(i).getCalled().equals("0")) {
                                absentnumber++;
                            } else {
                                presentnumber++;
                            }
                        }
                        present = new String[presentnumber];
                        absent = new String[absentnumber];
                        presentnumber = 0;
                        absentnumber = 0;//缺席
                        for (int i = 0; i < bean.getData().size(); i++) {
                            if (bean.getData().get(i).getCalled().equals("0")) {
                                absent[absentnumber] = bean.getData().get(i).getId();
                                absentnumber++;
                            } else {
                                present[presentnumber] = bean.getData().get(i).getId();
                                presentnumber++;
                            }
                        }
                        if (Utility.NetworkAvailable(CallNameActivity.this)) {
                            if (presentnumber != 0) {
                                if (absentnumber == 0) {//如果未到学生为0则直接提交

                                    new SetCallNameTask().execute(id, converToString(present), converToString(absent));
                                } else {
                                    commitDialog("还有" + absentnumber + "名未到学生,是否提交?");
                                }


                            } else {
                                if (second_call.equals("1")) {//如果是补点名
                                    Utility.showToast("请选择至少一名需要补点名的学生", CallNameActivity.this);
                                } else if (second_call.equals("0")) {
                                    Utility.showToast("至少有一名上课学生才能上课", CallNameActivity.this);
                                }
                            }
                        } else {
                            Utility.showToastNoNetWork(CallNameActivity.this);
                        }
                    } else {
                        Utility.showToast("没有上课学生可以点名", CallNameActivity.this);
                    }
                } else {
                    Utility.showToast("没有上课学生可以点名", CallNameActivity.this);
                }

                break;
        }
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (list.get(position).getCalled().equals("0")) {//如果未到
                list.get(position).setCalled("1");//选中已到
                view.setBackgroundResource(R.drawable.change_border_select);


            } else if (list.get(position).getCalled().equals("1")) {
                list.get(position).setCalled("0");//选中未到
                view.setBackgroundResource(R.drawable.change_border);

            }
        }
    };

    //获取点名信息
    private class GetCallNameTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(CallNameActivity.this, "数据获取中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            if (second_call.equals("0")) { //进行点名操作

                bean = HttpRequest.getInstance(CallNameActivity.this).GetCallNameState(strings[0]);
            } else if (second_call.equals("1")) {//进行补点名操作

                bean = HttpRequest.getInstance(CallNameActivity.this).GetSecondCallNameState(strings[0]);
            }

            // 请求成功返回数据
            if (bean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (bean.getCode() == 0) {
                        DismissDialog();
                        list.clear();
                        list.addAll(bean.getData());
                        adapter = new CallNameGridViewAdapter(CallNameActivity.this, list, second_call);
                        callnameGridview.setAdapter(adapter);
                        callnameGridview.setOnItemClickListener(onItemClickListener);

                    } else {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), CallNameActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", CallNameActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }

    //上传点名信息
    private class SetCallNameTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(CallNameActivity.this, "上传中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            publicBean = HttpRequest.getInstance(CallNameActivity.this).SetCallNameState(strings[0], strings[1], strings[2]);
            // 请求成功返回数据
            if (publicBean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (publicBean.getCode() == 0) {
                        DismissDialog();
                        Utility.showToast(publicBean.getMsg(), CallNameActivity.this);
                        finish();
                        saveEditTextAndCloseIMM();
                    } else {
                        DismissDialog();
                        Utility.showToast(publicBean.getMsg(), CallNameActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", CallNameActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }


    /**
     * @Description:把数组转换为一个用逗号分隔的字符串 ，以便于用in+String 查询
     */
    public static String converToString(String[] ig) {
        String str = "";
        if (ig != null && ig.length > 0) {
            for (int i = 0; i < ig.length; i++) {
                str += ig[i] + ",";
            }
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private void commitDialog(String msg) {
        alert = new AlertDialog.Builder(this);
        alert.setTitle("温馨提示").setMessage(msg).setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new SetCallNameTask().execute(id, converToString(present), converToString(absent));
                    }
                }).setNegativeButton("取消", null);
        alert.create();
        alert.show();
    }

}