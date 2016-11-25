package com.wr_education.activity.change;


import android.content.Intent;
import android.os.AsyncTask;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.baidu.android.pushservice.PushManager;
import com.wr_education.R;
import com.wr_education.activity.StartActivity;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.change.FindPassword;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import butterknife.OnClick;

public class ChangePassword extends AbstractActivity {
    @Bind(R.id.change_oldEdit)
    EditText changeOldEdit;
    @Bind(R.id.change_newEdit)
    EditText changeNewEdit;
    @Bind(R.id.change_newOkEdit)
    EditText changeNewOkEdit;
    @Bind(R.id.change_btn_ok)
    Button changeBtnOk;
    @Bind(R.id.setting_imgbtn_back)
    ImageButton settingImgbtnBack;


    private FindPassword findPassword;

    @Override
    protected void aadListenter() {

    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_change_password);
    }


    @OnClick({R.id.setting_imgbtn_back, R.id.change_btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_imgbtn_back:
                finish();
                break;
            case R.id.change_btn_ok:
                if (Utility.StringIsNull(changeOldEdit.getText().toString()) || Utility.StringIsNull(changeNewEdit.getText().toString()) ||
                        Utility.StringIsNull(changeNewOkEdit.getText().toString())) {
                    Utility.showToast("密码不能为空", ChangePassword.this);
                    return;
                } else if (!changeNewEdit.getText().toString().equals(changeNewOkEdit.getText().toString())) {
                    Utility.showToast("两次新密码不一致", ChangePassword.this);
                    return;
                } else if (Utility.NetworkAvailable(ChangePassword.this)) {
                    new GetChangePwdTask().execute(MyApplication.getInstance().getLoginBean().getData().getId(), changeOldEdit.getText().toString(), changeNewOkEdit.getText().toString());
                } else {
                    Utility.showToast("修改密码失败", ChangePassword.this);
                }
                break;
        }
    }


    private class GetChangePwdTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(ChangePassword.this, "正在处理...");
        }

        @Override
        protected String doInBackground(String... params) {
            findPassword = HttpRequest.getInstance(ChangePassword.this).FindPassword(params[0], params[1], params[2]);
            // 请求成功返回数据
            if (findPassword != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (findPassword.getCode() == 0) {//注册成功
                        DismissDialog();
                        Utility.showToast(findPassword.getMsg(), ChangePassword.this);
                        logoutDialogWindow();
                    } else {
                        DismissDialog();
                        Utility.showToast(findPassword.getMsg(), ChangePassword.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", ChangePassword.this);
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
     * 退出登录的
     *
     * @param // * @param content 提示内容
     */

    public void logoutDialogWindow() {
        MyApplication.getInstance().exit();
        MyApplication.getInstance().ClearReportDraft();
        //删除推送标签
        List<String> tag = new ArrayList<>();
        tag.add(String.valueOf(MyApplication.getInstance().getLoginBean().getData().getId()));
        PushManager.delTags(getApplicationContext(), tag);

        //关闭地图定位
        MyApplication.mLocationClient.stop();

        MyApplication.isLogin = false;
        startActivity(new Intent(ChangePassword.this, StartActivity.class));
        finish();
    }
}
