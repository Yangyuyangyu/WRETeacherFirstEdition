package com.wr_education.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.adapter.AbsentAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.AbsentBean;
import com.wr_education.bean.PublicBean;
import com.wr_education.bean.VerficationBean;
import com.wr_education.http.HttpPath;
import com.wr_education.http.HttpRequest;
import com.wr_education.http.HttpUtility;
import com.wr_education.util.Utility;
import com.wr_education.view.ClearEditText;
import com.wr_education.view.MyListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * 代课页面
 * Created by Administrator on 2016/4/15.
 */
public class AbsentTeacherActivity extends AbstractActivity {
    @Bind(R.id.abasentteacher_imgbtn_back)
    ImageButton abasentteacherImgbtnBack;
    @Bind(R.id.abasentteacher_tv_commit)
    TextView abasentteacherTvCommit;
    //    @Bind(R.id.abasentteacher_et_username)
//    EditText abasentteacherEtUsername;
//    @Bind(R.id.abasentteacher_et_passworld)
//    EditText abasentteacherEtPassworld;
    @Bind(R.id.abasentteacher_tv_reason)
    TextView abasentteacherTvReason;
    @Bind(R.id.abasentteacher_et_content)
    EditText abasentteacherEtContent;
    @Bind(R.id.abasentteacher_rl_reson)
    RelativeLayout abasentteacherRlReson;
    @Bind(R.id.absentteacher_et_name)
    ClearEditText EtName; //名字搜索框
    @Bind(R.id.absentteacher_et_phone)
    ClearEditText EtPhone; //电话号码搜索框
    @Bind(R.id.absentteacher_btn_find)
    Button BtnFind; //搜索按钮
    @Bind(R.id.absentteacher_list)
    MyListView listView;

    protected View mPopView; // 右上角弹窗
    private LinearLayout popid; //popwindow的布局id
    protected PopupWindow mPopupWindow;
    protected TextView pop_tv_sickleave, pop_tv_personalaffairs, pop_tv_otherleave;//病假，事假，其他

    private PublicBean bean;
    private String id;
    private String tid; //选中的代课老师的id
    private Boolean ischock = false; //用于判断有没有选中代课老师

    private StringRequest stringRequest;
    private ArrayList<AbsentBean.DataBean> list;
    private AbsentAdapter adapter;

    @Override
    protected void aadListenter() {
        abasentteacherImgbtnBack.setOnClickListener(this);
        abasentteacherTvCommit.setOnClickListener(this);
        abasentteacherRlReson.setOnClickListener(this);
        BtnFind.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        initpop();//初始化popwindow
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");

        }

        list = new ArrayList<AbsentBean.DataBean>();
        adapter = new AbsentAdapter(AbsentTeacherActivity.this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //清空所有的选中，重新设置选中项
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setIscheck(false);
                }
                list.get(position).setIscheck(true);
                adapter.notifyDataSetChanged();
            }
        });
        if(MyApplication.getInstance().getLoginBean().getData().getId() != null){
            //获取推荐的代课老师的数据
            if (Utility.NetworkAvailable(AbsentTeacherActivity.this)) {
                showWaitLoad(AbsentTeacherActivity.this, "数据获取中...");





                stringRequest = new StringRequest(Request.Method.POST, HttpPath.absentUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Log.d("whm", result);
                        Gson gson = new Gson();
                        AbsentBean bean = new AbsentBean();
                        bean = gson.fromJson(result, bean.getClass());
                        if (bean.getCode() == 0) {
                            DismissDialog();
                            list.clear();
                            list.addAll(bean.getData());
                            adapter.notifyDataSetChanged();
                        } else {
                            DismissDialog();
                            Utility.showToast(bean.getMsg(), AbsentTeacherActivity.this);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), AbsentTeacherActivity.this);
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("id", MyApplication.getInstance().getLoginBean().getData().getId());
                        return map;
                    }
                };
                stringRequest.setTag("volleyget");
                MyApplication.getHttpQueue().add(stringRequest);
            } else {
                Utility.showToastNoNetWork(AbsentTeacherActivity.this);
            }
        }else{
            Utility.showToast("没有获取到您的id", AbsentTeacherActivity.this);
        }

    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.absentteacher_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.abasentteacher_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
            case R.id.abasentteacher_tv_commit: //提交
//                if(Utility.StringIsNull(abasentteacherEtUsername.getText().toString())){
//                    Utility.showToast("代课账号不能为空",AbsentTeacherActivity.this);
//                }else if(Utility.StringIsNull(abasentteacherEtPassworld.getText().toString())){
//                    Utility.showToast("代课密码不能为空",AbsentTeacherActivity.this);
//                }else if(abasentteacherEtPassworld.getText().length() < 6 || abasentteacherEtPassworld.getText().length() > 18){
//                    Utility.showToast("密码必须是6-18位", AbsentTeacherActivity.this);
//                }else
                //判断有没有选中某个代课老师
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getIscheck()) {
                        ischock = true;
                        tid = list.get(i).getId();
                    }
                }
                if (!ischock) {
                    Utility.showToast("您还没有选择代课老师", AbsentTeacherActivity.this);
                } else if (Utility.StringIsNull(abasentteacherTvReason.getText().toString())) {
                    Utility.showToast("您还没有选择您的代课原因", AbsentTeacherActivity.this);
                } else if (Utility.StringIsNull(abasentteacherEtContent.getText().toString())) {
                    Utility.showToast("备注不能为空", AbsentTeacherActivity.this);
                } else if (Utility.NetworkAvailable(AbsentTeacherActivity.this)) {
                    if (MyApplication.getInstance().getLoginBean().getData().getId() != null) {
                        new SetAbsentTeacherTask().execute(MyApplication.getInstance().getLoginBean().getData().getId(), id, tid, abasentteacherTvReason.getText().toString(), abasentteacherEtContent.getText().toString());
                    } else {
                        Utility.showToast("没有获取到您的id", AbsentTeacherActivity.this);
                    }
                } else {
                    Utility.showToastNoNetWork(AbsentTeacherActivity.this);
                }
                break;
            case R.id.abasentteacher_rl_reson:
                showPopLay();
                break;
            case R.id.absentteacher_btn_find: //搜索按钮
                //通过姓名和手机号搜索老师
                if (Utility.StringIsNull(EtName.getText().toString()) && Utility.StringIsNull(EtPhone.getText().toString())) {
                    Utility.showToast("请输入姓名或手机号", AbsentTeacherActivity.this);
                }else if(!Utility.StringIsNull(EtPhone.getText().toString())){

                }  else if (Utility.NetworkAvailable(AbsentTeacherActivity.this)) {
                    showWaitLoad(AbsentTeacherActivity.this, "数据获取中...");
                    stringRequest = new StringRequest(Request.Method.POST, HttpPath.absentUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String result) {
                            Log.d("whm", result);
                            Gson gson = new Gson();
                            AbsentBean bean = new AbsentBean();
                            bean = gson.fromJson(result, bean.getClass());
                            if (bean.getCode() == 0) {
                                DismissDialog();
                                list.clear();
                                list.addAll(bean.getData());
                                adapter.notifyDataSetChanged();
                            } else {
                                DismissDialog();
                                Utility.showToast(bean.getMsg(), AbsentTeacherActivity.this);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            DismissDialog();
                            Utility.showToast(bean.getMsg(), AbsentTeacherActivity.this);
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("name", EtName.getText().toString());
                            map.put("mobile", EtPhone.getText().toString());
                            return map;
                        }
                    };
                    stringRequest.setTag("volleyget");
                    MyApplication.getHttpQueue().add(stringRequest);
                } else {
                    Utility.showToastNoNetWork(AbsentTeacherActivity.this);
                }
                break;
        }
    }

    private void initpop() {
        // 弹出选项
        mPopView = LayoutInflater.from(AbsentTeacherActivity.this).inflate(
                R.layout.popwindow_leave_layout, null);
        popid = (LinearLayout) mPopView.findViewById(R.id.pop_leave_id);
        mPopupWindow = new PopupWindow(mPopView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop_tv_sickleave = (TextView) mPopView.findViewById(R.id.pop_tv_sickleave); //病假
        pop_tv_personalaffairs = (TextView) mPopView.findViewById(R.id.pop_tv_personalaffairs);//事假
        pop_tv_otherleave = (TextView) mPopView.findViewById(R.id.pop_tv_otherleave);//其他
        pop_tv_sickleave.setOnClickListener(onClickListener);
        pop_tv_personalaffairs.setOnClickListener(onClickListener);
        pop_tv_otherleave.setOnClickListener(onClickListener);
    }

    /**
     * // * 右上角菜单选项 // * @param context // * @param msg //
     */
    public void showPopLay() {
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#00000000")));
        mPopupWindow.showAsDropDown(abasentteacherRlReson, 0, 0);
        mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.update();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pop_tv_sickleave: //病假
                    mPopupWindow.dismiss();
                    abasentteacherTvReason.setText("病假");
                    break;
                case R.id.pop_tv_personalaffairs: //事假
                    mPopupWindow.dismiss();
                    abasentteacherTvReason.setText("事假");
                    break;
                case R.id.pop_tv_otherleave: //其他
                    mPopupWindow.dismiss();
                    abasentteacherTvReason.setText("其他");
                    break;
            }
        }
    };

    private class SetAbsentTeacherTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(AbsentTeacherActivity.this, "上传中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(AbsentTeacherActivity.this).SetAbsentTeacher(strings[0], strings[1], strings[2], strings[3], strings[4]);
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
                        Utility.showToast(bean.getMsg(), AbsentTeacherActivity.this);
                        finish();
                        saveEditTextAndCloseIMM();
                    } else {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), AbsentTeacherActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", AbsentTeacherActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }

}
