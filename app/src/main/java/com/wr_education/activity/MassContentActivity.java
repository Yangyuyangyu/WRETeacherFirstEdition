package com.wr_education.activity;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.NewsDetailsBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import butterknife.Bind;

/**
 * 社团活动内容页
 * Created by Administrator on 2016/4/15.
 */
public class MassContentActivity extends AbstractActivity {
    @Bind(R.id.masscontent_imgbtn_back)
    ImageButton masscontentImgbtnBack;
    @Bind(R.id.masscontent_tv_title)
    TextView masscontentTvTitle;
    @Bind(R.id.masscontent_tv_contenttitle)
    TextView masscontentTvContenttitle;
    @Bind(R.id.masscontent_tv_contenttime)
    TextView masscontentTvContenttime;
    @Bind(R.id.masscontent_tv_smalltitle)
    TextView masscontentTvSmalltitle;
    @Bind(R.id.masscontent_img_pic)
    ImageView masscontentImgPic;
    @Bind(R.id.masscontent_webview)
    WebView masscontentWebview;

    private String id;
    private NewsDetailsBean bean;

    @Override
    protected void aadListenter() {
        masscontentImgbtnBack.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        if (Utility.NetworkAvailable(MassContentActivity.this)) {
            if (MyApplication.getInstance().getLoginBean().getData().getId() != null) {
                new GetMassContentTask().execute(id);
            } else {
                Utility.showToast("没有获取到您的id", MassContentActivity.this);
            }
        } else {
            Utility.showToastNoNetWork(MassContentActivity.this);
        }
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.masscontent_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.masscontent_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
        }
    }

    private class GetMassContentTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(MassContentActivity.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(MassContentActivity.this).GetnewsDetails(strings[0]);
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
                        masscontentTvTitle.setText(bean.getData().getGroup_name());
                        masscontentTvContenttitle.setText(bean.getData().getName());
                        masscontentTvContenttime.setText(bean.getData().getTime());
                        masscontentTvSmalltitle.setText(bean.getData().getGroup_name());
                        masscontentWebview.loadDataWithBaseURL(null, Utility.Webhtml(bean.getData().getDetail()), "text/html", "utf-8", null);
                        MyApplication.getInstance().displayHeadImage(bean.getData().getImg(), masscontentImgPic, R.mipmap.contentpic, null);
                    } else {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), MassContentActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", MassContentActivity.this);
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
