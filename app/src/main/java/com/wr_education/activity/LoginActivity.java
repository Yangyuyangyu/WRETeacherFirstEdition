package com.wr_education.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.LoginBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import butterknife.Bind;

/**
 * 登录页面
 * Created by Administrator on 2016/4/12.
 */
public class LoginActivity extends AbstractActivity {
    @Bind(R.id.login_et_username)
    EditText et_username; //手机号码
    @Bind(R.id.login_et_passworld)
    EditText et_passworld; //密码
    @Bind(R.id.login_btn)
    Button btn_login; //登录按钮
    @Bind(R.id.login_imgbtn_back)
    ImageButton loginImgbtnBack;
    private LoginBean loginBean;


    public void initLayout() {
        setContentView(R.layout.login_layout);
    }

    public void aadListenter() {
        btn_login.setOnClickListener(this);
        loginImgbtnBack.setOnClickListener(this);
    }

    public void initVariables() {
        MyApplication.getInstance().addActivity(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                if (Utility.StringIsNull(et_username.getText().toString())) {
                //    et_username.setError("手机号码不能为空");
                    Utility.showToast("手机号码不能为空", LoginActivity.this);
                } else if (!Utility.isPhoneNum(et_username.getText().toString())) {
                //    et_username.setError("手机号码有误");
                    Utility.showToast("手机号码有误", LoginActivity.this);
                } else if (Utility.StringIsNull(et_passworld.getText().toString())) {
                //    et_passworld.setError("密码不能为空");
                    Utility.showToast("密码不能为空", LoginActivity.this);
                } else if (Utility.NetworkAvailable(LoginActivity.this)) {
                    new GetLoginTask().execute(et_username.getText().toString(),et_passworld.getText().toString());
                } else {
                    Utility.showToastNoNetWork(LoginActivity.this);
                }
                break;
            case R.id.login_imgbtn_back:
                startActivity(new Intent(LoginActivity.this,StartActivity.class));
                finish();
                saveEditTextAndCloseIMM();
                break;
        }
    }

    private class GetLoginTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            showWaitLoad(LoginActivity.this, "登录中...");
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            loginBean=HttpRequest.getInstance(LoginActivity.this).setLogin(params[0],params[1]);
            // 请求成功返回数据
            if (loginBean != null) {
                return "success";
            }
            return "fail";
        }


        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (loginBean.getCode()==0){//请求成功
                        DismissDialog();
                        MyApplication.isLogin=true;
                        //将用户名和密码保存下来以便后面做自动登录
                        MyApplication.getInstance().setLoginUserName(et_username.getText().toString());
                        MyApplication.getInstance().setLoginPassword(et_passworld.getText().toString());

                        //将登陆返回的数据保存下来
                        MyApplication.getInstance().setLoginBean(loginBean);

                        //将activity的list清空，准备跳转到主页面
                        MyApplication.getInstance().exit();

                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                        saveEditTextAndCloseIMM();
                    } else{
                        DismissDialog();
                        Utility.showToast(loginBean.getMsg(), LoginActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", LoginActivity.this);
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
