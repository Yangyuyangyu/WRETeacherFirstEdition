package com.wr_education.activity;

import android.content.Intent;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.LoginBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

/**
 * 启动页面
 * Created by Administrator on 2016/5/12.
 */
public class LoadingActivity extends AbstractActivity{
    private LoginBean loginBean;
    @Override
    protected void aadListenter() {

    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        try {
            if(MyApplication.getInstance().getLoginUserName()!=null && !MyApplication.getInstance().getLoginUserName().equals("")
                    && MyApplication.getInstance().getLoginPassword()!=null && !MyApplication.getInstance().getLoginPassword().equals("")) {//存在账号和密码
                if(Utility.NetworkAvailable(LoadingActivity.this)){
                    new GetLoginTask().execute(MyApplication.getInstance().getLoginUserName(),MyApplication.getInstance().getLoginPassword());
                }else{
                    startActivity(new Intent(LoadingActivity.this,StartActivity.class));
                    finish();
                }
            }else{
                startActivity(new Intent(LoadingActivity.this,StartActivity.class));
                finish();
            }
        }catch (Exception e){
            e.printStackTrace();
            startActivity(new Intent(LoadingActivity.this, StartActivity.class));
            finish();
        }
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.loading_layout);
    }

    private class GetLoginTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            loginBean= HttpRequest.getInstance(LoadingActivity.this).setLogin(params[0],params[1]);
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
                try {
                    Thread.sleep(1500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if (result.equals("success")) {
                    if (loginBean.getCode()==0){//请求成功
                        //将登陆返回的数据保存下来
                        MyApplication.isLogin=true;
                        MyApplication.getInstance().setLoginBean(loginBean);
                        startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                        finish();
                    } else{
                        startActivity(new Intent(LoadingActivity.this,StartActivity.class));
                        finish();
                    }
                } else {
                    startActivity(new Intent(LoadingActivity.this, StartActivity.class));
                    finish();
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.getStackTrace();
            }
        }
    }
}
