package com.wr_education.activity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.PublicBean;
import com.wr_education.http.HttpPath;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * 意见反馈页面
 * Created by Administrator on 2016/4/14.
 */
public class FeedBackActivity extends AbstractActivity {
    @Bind(R.id.feedback_imgbtn_back)
    ImageButton feedbackImgbtnBack;
    @Bind(R.id.feedback_tv_commit)
    TextView feedbackTvCommit;
    @Bind(R.id.feedback_et)
    EditText feedbackEt;
    private PublicBean bean;
    private StringRequest stringRequest;

    @Override
    protected void aadListenter() {
        feedbackImgbtnBack.setOnClickListener(this);
        feedbackTvCommit.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.feedback_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feedback_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
            case R.id.feedback_tv_commit:
                if (Utility.StringIsNull(feedbackEt.getText().toString())) {
                    Utility.showToast("意见反馈不能为空", FeedBackActivity.this);
                } else if (Utility.NetworkAvailable(FeedBackActivity.this)) {
                    if (MyApplication.getInstance().getLoginBean().getData().getId() != null) {
                        showWaitLoad(FeedBackActivity.this, "上传中...");
                        stringRequest = new StringRequest(Request.Method.POST, HttpPath.FeedBackUrl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String result) {
                                Gson gson = new Gson();
                                PublicBean bean = new PublicBean();
                                bean = gson.fromJson(result, bean.getClass());
                                if (bean.getCode() == 0) {
                                    DismissDialog();
                                    Utility.showToast(bean.getMsg(), FeedBackActivity.this);
                                    finish();
                                    saveEditTextAndCloseIMM();
                                } else {
                                    DismissDialog();
                                    Utility.showToast(bean.getMsg(), FeedBackActivity.this);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Utility.showToast("请求失败，请重试！", FeedBackActivity.this);
                                DismissDialog();
                                Log.e("LOGIN_ERROR", error.getMessage(), error);
                                byte[] htmlBodyBytes = error.networkResponse.data;
                                Log.e("LOGIN_ERROR", new String(htmlBodyBytes), error);
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("id", MyApplication.getInstance().getLoginBean().getData().getId());
                                map.put("content", feedbackEt.getText().toString());
                                return map;
                            }

                        };
                        stringRequest.setTag("volleyget");
                        MyApplication.getHttpQueue().add(stringRequest);
                        //这个是以前的方法请求
                        //  new GetFeedBackTask().execute(MyApplication.getInstance().getLoginBean().getData().getId(),feedbackEt.getText().toString());
                    } else {
                        Utility.showToast("没有获取到您的id", FeedBackActivity.this);
                    }
                } else {
                    Utility.showToastNoNetWork(FeedBackActivity.this);
                }
                break;
        }
    }

    private class GetFeedBackTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(FeedBackActivity.this, "上传中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(FeedBackActivity.this).SetFeedBackData(strings[0], strings[1]);
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
                        Utility.showToast(bean.getMsg(), FeedBackActivity.this);
                        finish();
                        saveEditTextAndCloseIMM();
                    } else {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), FeedBackActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", FeedBackActivity.this);
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
    protected void onStop() {
        super.onStop();
       MyApplication.getHttpQueue().cancelAll("volleyget");
    }
}
