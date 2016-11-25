package com.wr_education.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.PublicBean;
import com.wr_education.bean.VerficationBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;
import com.wr_education.view.TimeButton;

import butterknife.Bind;

/**
 * 注册页面
 * Created by Administrator on 2016/4/12.
 */
public class RegisterActivity extends AbstractActivity implements View.OnClickListener {
    @Bind(R.id.register_et_username)
    EditText registerEtUsername;
    @Bind(R.id.register_et_verificationcode)
    EditText registerEtVerificationcode;
    @Bind(R.id.register_btn_time)
    TimeButton registerBtnTime;
    @Bind(R.id.register_et_passworld)
    EditText registerEtPassworld;
    @Bind(R.id.register_img_seepassworld)
    ImageView registerImgSeepassworld;
    @Bind(R.id.register_btn)
    Button registerBtn;
    @Bind(R.id.register_imgbtn_back)
    ImageButton registerImgbtnBack;
    @Bind(R.id.register_cb_choose)
    CheckBox Choose;
    @Bind(R.id.register_tv_rule)
    TextView Rule;
    @Bind(R.id.register_tv_ruleprotect)
    TextView RuleProtect;
    private boolean isHidden = true;
    private VerficationBean bean;
    private PublicBean publicBean;

    @Override
    protected void aadListenter() {
        registerBtn.setOnClickListener(this);
        registerBtnTime.setOnClickListener(this);
        registerImgSeepassworld.setOnClickListener(this);
        registerImgbtnBack.setOnClickListener(this);
        Rule.setOnClickListener(this);
        RuleProtect.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        registerBtnTime.setTextAfter("秒后重新获取").setTextBefore("获取验证码")
                .setLenght(60 * 1000);

        Choose.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.register_layout);
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
            if (arg1) {
                Choose.setChecked(true);
            } else {
                Choose.setChecked(false);
            }
        }
    };

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.register_btn: //注册按钮的点击事件
                if (Utility.StringIsNull(registerEtUsername.getText().toString())) {
                    Utility.showToast("手机号码不能为空", RegisterActivity.this);
                } else if (!Utility.isPhoneNum(registerEtUsername.getText().toString())) {
                    Utility.showToast("手机号码有误", RegisterActivity.this);
                } else if (Utility.StringIsNull(registerEtVerificationcode.getText().toString())) {
                    Utility.showToast("验证码不能为空", RegisterActivity.this);
                } else if (registerEtVerificationcode.getText().length() < 6) {
                    Utility.showToast("验证码为6位数字", RegisterActivity.this);
                } else if (Utility.StringIsNull(registerEtPassworld.getText().toString())) {
                    Utility.showToast("密码不能为空", RegisterActivity.this);
                } else if (registerEtPassworld.getText().length() < 6 || registerEtPassworld.getText().length() > 18) {
                    Utility.showToast("密码必须是6-18位", RegisterActivity.this);
                } else if (bean == null) {
                    Utility.showToast("您还没有获取过验证码", RegisterActivity.this);
                }else if(!Choose.isChecked()){
                    Utility.showToast("请接受服务协议",RegisterActivity.this);
                } else if (Utility.NetworkAvailable(RegisterActivity.this)) {
                    new GetRegisterTask().execute(registerEtUsername.getText().toString(), registerEtPassworld.getText().toString(), registerEtVerificationcode.getText().toString(), String.valueOf(bean.getLog_id()));
                } else {
                    Utility.showToastNoNetWork(RegisterActivity.this);
                }
                break;
            case R.id.register_btn_time:
                if (Utility.StringIsNull(registerEtUsername.getText().toString())) {
                    Utility.showToast("手机号码不能为空", RegisterActivity.this);
                } else if (!Utility.isPhoneNum(registerEtUsername.getText().toString())) {
                    Utility.showToast("手机号码有误", RegisterActivity.this);
                } else if (Utility.NetworkAvailable(RegisterActivity.this)) {
                    new GetVerificationCodeTask().execute(registerEtUsername.getText().toString());
                } else {
                    Utility.showToastNoNetWork(RegisterActivity.this);
                }
                break;
            case R.id.register_img_seepassworld:
                if (isHidden) {
                    //设置EditText文本为可见的
                    registerImgSeepassworld.setBackgroundResource(R.mipmap.registerseepassworldtwo);
                    registerEtPassworld.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    registerImgSeepassworld.setBackgroundResource(R.mipmap.registerseepassworld);
                    registerEtPassworld.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isHidden = !isHidden;
                registerEtPassworld.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = registerEtPassworld.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
                break;

            case R.id.register_imgbtn_back:
                startActivity(new Intent(RegisterActivity.this, StartActivity.class));
                finish();
                saveEditTextAndCloseIMM();
                break;

            case R.id.register_tv_rule: //服务协议
                intent=new Intent(RegisterActivity.this, RuleActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;

            case R.id.register_tv_ruleprotect: //保护条款
                intent=new Intent(RegisterActivity.this, RuleActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);
                break;
        }
    }


    /**
     * 验证码数据请求
     */
    private class GetVerificationCodeTask extends
            AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            bean = HttpRequest.getInstance(RegisterActivity.this).GetVerificationCode(params[0]);
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
                        registerBtnTime.TimeGo();
                        Utility.showToast(bean.getMsg(), RegisterActivity.this);
                    } else {
                        Utility.showToast(bean.getMsg(), RegisterActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", RegisterActivity.this);
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }

    private class GetRegisterTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(RegisterActivity.this, "注册中...");
        }

        @Override
        protected String doInBackground(String... params) {
            publicBean = HttpRequest.getInstance(RegisterActivity.this).SetRegisterCode(params[0], params[1], params[2], params[3]);
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
                    if (publicBean.getCode() == 0) {//注册成功
                        DismissDialog();
                        Utility.showToast(publicBean.getMsg(), RegisterActivity.this);
                        finish();
                        saveEditTextAndCloseIMM();
                    } else {
                        DismissDialog();
                        Utility.showToast(publicBean.getMsg(), RegisterActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", RegisterActivity.this);
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
