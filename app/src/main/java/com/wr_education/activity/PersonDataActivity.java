package com.wr_education.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.PublicBean;
import com.wr_education.dialog.BirthDayDialog;
import com.wr_education.dialog.SexWheelDialog;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;
import com.wr_education.view.ClearEditText;
import com.wr_education.view.RoundImageView;

import butterknife.Bind;

/**
 * 个人资料页面
 * Created by Administrator on 2016/4/14.
 */
public class PersonDataActivity extends AbstractActivity {
    @Bind(R.id.persondata_imgbtn_back)
    ImageButton persondataImgbtnBack; //返回
    @Bind(R.id.persondata_tv_center)
    TextView persondataTvCenter; //确认
    @Bind(R.id.persondata_img_head)
    RoundImageView persondataImgHead; //头像
    @Bind(R.id.persondata_tv_phone)
    TextView persondataTvPhone; //电话
    @Bind(R.id.persondata_tv_sex)
    TextView persondataTvSex; //性别
    @Bind(R.id.persondata_tv_birthdate)
    TextView persondataTvBirthdate; //出生日期
    //    @Bind(R.id.persondata_et_homeaddress)
//    ClearEditText persondataEtHomeaddress; //家庭住址
    @Bind(R.id.persondata_rl_head)
    RelativeLayout persondataRlHead; //头像修改
    @Bind(R.id.persondata_et_name)
    ClearEditText persondataEtName; //名字输入
    private String[] sex = {"男", "女"};
    private SexWheelDialog sexWheelDialog;
    private BirthDayDialog birthDayDialog;
    private PublicBean bean;
    private int sexupdata = 0;
    private String img;

    @Override
    protected void aadListenter() {
        persondataImgbtnBack.setOnClickListener(this);
        persondataTvCenter.setOnClickListener(this);
        persondataTvSex.setOnClickListener(this);
        persondataTvBirthdate.setOnClickListener(this);
        persondataRlHead.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        persondataTvPhone.setText(MyApplication.getInstance().getLoginBean().getData().getPhone());
        persondataTvBirthdate.setText(MyApplication.getInstance().getLoginBean().getData().getBirthday());
        persondataEtName.setText(MyApplication.getInstance().getLoginBean().getData().getName());
        if (MyApplication.getInstance().getLoginBean().getData().getHead() != null) {
            MyApplication.getInstance().displayHeadImage(MyApplication.getInstance().getLoginBean().getData().getHead(), persondataImgHead, R.mipmap.morentouxiang2, null);
        }
        if (MyApplication.getInstance().getLoginBean().getData().getSex() == 1) {
            persondataTvSex.setText("男");
        } else if (MyApplication.getInstance().getLoginBean().getData().getSex() == 2) {
            persondataTvSex.setText("女");
        }
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.persondata_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.persondata_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();//隐藏软键盘
                break;
            case R.id.persondata_tv_center:
                if (Utility.StringIsNull(persondataEtName.getText().toString())) {
                    Utility.showToast("您还没有输入您的姓名", PersonDataActivity.this);
                } else if (Utility.StringIsNull(persondataTvSex.getText().toString())) {
                    Utility.showToast("您还没有选择您的性别", PersonDataActivity.this);
                } else if (Utility.StringIsNull(persondataTvBirthdate.getText().toString())) {
                    Utility.showToast("您还没有选择您的出生日期", PersonDataActivity.this);
                } else if (Utility.NetworkAvailable(PersonDataActivity.this)) {
                    if (persondataTvSex.getText().toString().equals("男")) {
                        sexupdata = 1;
                    } else if (persondataTvSex.getText().toString().equals("女")) {
                        sexupdata = 2;
                    }
                    if (MyApplication.getInstance().getLoginBean().getData().getHead() != null) {
                        if (img != null) {
                            new SetPerSonDataTask().execute(img, persondataEtName.getText().toString(), sexupdata + "", persondataTvBirthdate.getText().toString(), MyApplication.getInstance().getLoginBean().getData().getId());
                        } else {
                            new SetPerSonDataTask().execute(MyApplication.getInstance().getLoginBean().getData().getHead(), persondataEtName.getText().toString(), sexupdata + "", persondataTvBirthdate.getText().toString(), MyApplication.getInstance().getLoginBean().getData().getId());
                        }
                    } else {
                        Utility.showToast("没有获取到您的id", PersonDataActivity.this);
                    }

                } else {
                    Utility.showToastNoNetWork(PersonDataActivity.this);
                }
                break;
            case R.id.persondata_rl_head: //修改头像
                startActivityForResult(new Intent(PersonDataActivity.this, HeadPortratitActivity.class), HeadPortratitActivity.PZ_FLAG);
                break;
            case R.id.persondata_tv_sex: //性别
                sexWheelDialog = new SexWheelDialog(PersonDataActivity.this, sex, persondataTvSex.getText().toString(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!Utility.StringIsNull(sexWheelDialog.GetText())) {
                            persondataTvSex.setText(sexWheelDialog.GetText());
                        }
                        sexWheelDialog.dismiss();
                    }
                });
                sexWheelDialog.showDialog();
                break;
            case R.id.persondata_tv_birthdate: //出生日期
                if (Utility.StringIsNull(persondataTvBirthdate.getText().toString())) {
                    String []array={"1950","01","01"};
                    birthDayDialog = new BirthDayDialog(PersonDataActivity.this,array, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!Utility.StringIsNull(birthDayDialog.GetText())) {
                                persondataTvBirthdate.setText(birthDayDialog.GetText());
                            }else{
                                persondataTvBirthdate.setText("1950-01-01");
                            }
                            birthDayDialog.dismiss();
                        }
                    });
                    birthDayDialog.showDialog();
                }else{
                    birthDayDialog = new BirthDayDialog(PersonDataActivity.this, convertStrToArray(persondataTvBirthdate.getText().toString()), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!Utility.StringIsNull(birthDayDialog.GetText())) {
                                persondataTvBirthdate.setText(birthDayDialog.GetText());
                            }
                            birthDayDialog.dismiss();
                        }
                    });
                    birthDayDialog.showDialog();
                }
                break;
        }
    }

    //使用String的split 方法
    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split("-"); //拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    }

    private class SetPerSonDataTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(PersonDataActivity.this, "上传中...");
        }

        @Override
        protected String doInBackground(String... params) {
            bean = HttpRequest.getInstance(PersonDataActivity.this).SetPersonData(params[0], params[1], params[2], params[3], params[4]);
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
                    if (bean.getCode() == 0) {//上传成功成功
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), PersonDataActivity.this);
                        MyApplication.getInstance().getLoginBean().getData().setName(persondataEtName.getText().toString());
                        MyApplication.getInstance().getLoginBean().getData().setBirthday(persondataTvBirthdate.getText().toString());
                        if (persondataTvSex.getText().toString().equals("男")) {
                            MyApplication.getInstance().getLoginBean().getData().setSex(1);
                        } else if (persondataTvSex.getText().toString().equals("女")) {
                            MyApplication.getInstance().getLoginBean().getData().setSex(2);
                        }
                        if(img!=null){
                            MyApplication.getInstance().getLoginBean().getData().setHead(img);
                        }

                        finish();
                        saveEditTextAndCloseIMM();
                    } else {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), PersonDataActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", PersonDataActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(MyApplication.getInstance().getLoginBean().getData().getHead()!=null){
//            MyApplication.getInstance().displayHeadImage(MyApplication.getInstance().getLoginBean().getData().getHead(),persondataImgHead,R.mipmap.morentouxiang2,null);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HeadPortratitActivity.PZ_FLAG && HeadPortratitActivity.PZ_FLAG == requestCode) {
            if(data!=null){
                img = data.getStringExtra("img");
                MyApplication.getInstance().displayHeadImage(img, persondataImgHead, R.mipmap.morentouxiang2, null);
            }
        }
    }
}
