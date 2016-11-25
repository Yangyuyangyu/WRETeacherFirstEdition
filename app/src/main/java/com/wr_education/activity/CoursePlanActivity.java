package com.wr_education.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.MassPlanBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 课程规划页面
 * Created by Administrator on 2016/4/15.
 */
public class CoursePlanActivity extends AbstractActivity {
    @Bind(R.id.courseplan_imgbtn_back)
    ImageButton courseplanImgbtnBack;
    @Bind(R.id.courseplan_webview)
    WebView courseplanWebview;

    private MassPlanBean bean;
    private String id;

    @Override
    protected void aadListenter() {
        courseplanImgbtnBack.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }

        if (Utility.NetworkAvailable(CoursePlanActivity.this)) {
            new GetCoursePlanTask().execute(id);
        } else {
            Utility.showToastNoNetWork(CoursePlanActivity.this);
        }
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.courseplan_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.courseplan_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
        }
    }

    private class GetCoursePlanTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(CoursePlanActivity.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(CoursePlanActivity.this).GetCoursePlan(strings[0]);
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
                        courseplanWebview.loadDataWithBaseURL(null, bean.getData().getPlan() + "", "text/html", "utf-8", null);
                    } else {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), CoursePlanActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", CoursePlanActivity.this);
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
