package com.wr_education.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.PublicBean;
import com.wr_education.bean.VerficationBean;
import com.wr_education.http.HttpPath;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;
import com.wr_education.view.TimeButton;

import butterknife.Bind;

/**
 * 找回密码页面
 * Created by Administrator on 2016/4/12.
 */
public class FindPassWorldAcitvity extends AbstractActivity implements View.OnClickListener {
    @Bind(R.id.findpassworld_et_username)
    EditText findpassworldEtUsername;
    @Bind(R.id.findpassworld_et_verificationcode)
    EditText findpassworldEtVerificationcode;
    @Bind(R.id.findpassworld_btn_time)
    TimeButton findpassworldBtnTime;
    @Bind(R.id.findpassworld_et_passworld)
    EditText findpassworldEtPassworld;
    @Bind(R.id.findpassworld_et_passworldtwo)
    EditText findpassworldEtPassworldtwo;
    @Bind(R.id.findpassworld_btn)
    Button findpassworldBtn;
    @Bind(R.id.findpassworld_imgbtn_back)
    ImageButton findpassworldImgbtnBack;
    @Bind(R.id.findpassworld_img_passworldtwo)
    ImageView findpassworldImgPassworldtwo;
    private PublicBean publicBean;
    private VerficationBean bean;
    private StringRequest stringRequest;

    @Override
    protected void aadListenter() {
        findpassworldBtn.setOnClickListener(this);
        findpassworldBtnTime.setOnClickListener(this);
        findpassworldImgPassworldtwo.setOnClickListener(this);
        findpassworldImgbtnBack.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        findpassworldBtnTime.setTextAfter("秒后重新获取").setTextBefore("获取验证码")
                .setLenght(60 * 1000);
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.findpassworld_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findpassworld_btn: //确认按钮监听
                if (Utility.StringIsNull(findpassworldEtUsername.getText().toString())) {
                    Utility.showToast("手机号码不能为空", FindPassWorldAcitvity.this);
                } else if (!Utility.isPhoneNum(findpassworldEtUsername.getText().toString())) {
                    Utility.showToast("手机号码有误", FindPassWorldAcitvity.this);
                } else if (Utility.StringIsNull(findpassworldEtVerificationcode.getText().toString())) {
                    Utility.showToast("验证码不能为空", FindPassWorldAcitvity.this);
                } else if (findpassworldEtVerificationcode.getText().length()<6) {
                    Utility.showToast("验证码为6位数字", FindPassWorldAcitvity.this);
                }else if (Utility.StringIsNull(findpassworldEtPassworld.getText().toString())) {
                    Utility.showToast("密码不能为空", FindPassWorldAcitvity.this);
                } else if (findpassworldEtPassworld.getText().length() < 6 || findpassworldEtPassworld.getText().length() > 18) {
                    Utility.showToast("密码必须是6-18位", FindPassWorldAcitvity.this);
                } else if (Utility.StringIsNull(findpassworldEtPassworldtwo.getText().toString())) {
                    Utility.showToast("确认密码不能为空", FindPassWorldAcitvity.this);
                } else if (!findpassworldEtPassworld.getText().toString().equals(findpassworldEtPassworldtwo.getText().toString())) {
                    Utility.showToast("两次密码不正确", FindPassWorldAcitvity.this);
                } else if(bean==null){
                    Utility.showToast("您还没有获取过验证码",FindPassWorldAcitvity.this);
                }else if (Utility.NetworkAvailable(FindPassWorldAcitvity.this)) {
                        new GetFindPassWorldTask().execute(findpassworldEtUsername.getText().toString(),findpassworldEtPassworld.getText().toString(),findpassworldEtVerificationcode.getText().toString(),String.valueOf(bean.getLog_id()));
                   }
                else {
                    Utility.showToastNoNetWork(FindPassWorldAcitvity.this);
                }
                break;
            case R.id.findpassworld_btn_time:  //验证码监听
                if(Utility.StringIsNull(findpassworldEtUsername.getText().toString())){
                    Utility.showToast("手机号码不能为空", FindPassWorldAcitvity.this);
                }else if (!Utility.isPhoneNum(findpassworldEtUsername.getText().toString())) {
                    Utility.showToast("手机号码有误", FindPassWorldAcitvity.this);
                }else if (Utility.NetworkAvailable(FindPassWorldAcitvity.this)) {
                  //  new GetFindVerificationCodeTask().execute(findpassworldEtUsername.getText().toString());
                    showWaitLoad(FindPassWorldAcitvity.this, "获取数据中...");
                    stringRequest = new StringRequest(Request.Method.GET, HttpPath.SendFindCodeUrl+"?mobile="+findpassworldEtUsername.getText().toString(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String result) {
                            Gson gson = new Gson();
                            bean = new VerficationBean();
                            bean = gson.fromJson(result, bean.getClass());
                            if (bean.getCode() == 0) {
                                findpassworldBtnTime.TimeGo();
                                DismissDialog();
                                Utility.showToast(bean.getMsg(), FindPassWorldAcitvity.this);
                            } else {
                                DismissDialog();
                                Utility.showToast(bean.getMsg(), FindPassWorldAcitvity.this);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Utility.showToast("请求失败，请重试！", FindPassWorldAcitvity.this);
                            DismissDialog();
                        }
                    });
                    stringRequest.setTag("volleyget");
                    MyApplication.getHttpQueue().add(stringRequest);
                } else {
                    Utility.showToastNoNetWork(FindPassWorldAcitvity.this);
                }
                break;
            case R.id.findpassworld_imgbtn_back:
                startActivity(new Intent(FindPassWorldAcitvity.this,StartActivity.class));
                finish();
                saveEditTextAndCloseIMM();
                break;
        }
    }

//    /**
//     * 获取验证码数据请求
//     */
//    private class GetFindVerificationCodeTask extends
//            AsyncTask<String, String, String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            verficationBean=HttpRequest.getInstance(FindPassWorldAcitvity.this).GetVerificationCode(params[0]);
//            // 请求成功返回数据
//            if (verficationBean != null) {
//                return "success";
//            }
//            return "fail";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            try {
//                if (result.equals("success")) {
//                    if (verficationBean.getCode()==0){
//                        findpassworldBtnTime.TimeGo();
//                        Utility.showToast(verficationBean.getMsg(),FindPassWorldAcitvity.this);
//                    }else{
//                        Utility.showToast(verficationBean.getMsg(),FindPassWorldAcitvity.this);
//                    }
//                } else {
//                    Utility.showToast("请求失败，请重试！",FindPassWorldAcitvity.this);
//                }
//            } catch (Exception e) {
//                // TODO: handle exception
//                DismissDialog();
//                e.getStackTrace();
//            }
//        }
//    }


    /**
     * 找回密码数据请求
     */
    private class GetFindPassWorldTask extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(FindPassWorldAcitvity.this, "注册中...");
        }

        @Override
        protected String doInBackground(String... params) {
            publicBean= HttpRequest.getInstance(FindPassWorldAcitvity.this).FindPassWorld(params[0],params[1],params[2],params[3]);
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
                    if (publicBean.getCode()==0||publicBean.getCode()==-3){//找回密码成功,如果是未注册的账号则注册成功
                        DismissDialog();
                        Utility.showToast(publicBean.getMsg(), FindPassWorldAcitvity.this);
                        finish();
                        saveEditTextAndCloseIMM();
                    }else{
                        DismissDialog();
                        Utility.showToast(publicBean.getMsg(), FindPassWorldAcitvity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", FindPassWorldAcitvity.this);
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
