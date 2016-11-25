package com.wr_education.activity;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.MassRegimetBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import butterknife.Bind;

/**
 * 社团管理制度页
 * Created by Administrator on 2016/4/15.
 */
public class MassRegimetActivity extends AbstractActivity {
    @Bind(R.id.massregime_imgbtn_back)
    ImageButton massregimeImgbtnBack;
    @Bind(R.id.massregime_tv_contenttitle)
    TextView massregimeTvContenttitle;
    @Bind(R.id.massregime_tv_contenttime)
    TextView massregimeTvContenttime;
    @Bind(R.id.massregimet_wv_webview)
    WebView massregimetWvWebview;

    private MassRegimetBean bean;
    private String id;

    @Override
    protected void initLayout() {
        setContentView(R.layout.massregime_layout);
    }

    @Override
    protected void aadListenter() {
        massregimeImgbtnBack.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        if (Utility.NetworkAvailable(MassRegimetActivity.this)) {
            new GetMassRegimetTask().execute(id);
        } else {
            Utility.showToastNoNetWork(MassRegimetActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.massregime_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
        }
    }

    private class GetMassRegimetTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(MassRegimetActivity.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(MassRegimetActivity.this).GetMasssRegimet(strings[0]);
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
                        massregimeTvContenttitle.setText(bean.getData().getTitle());
                        massregimeTvContenttime.setText(bean.getData().getTime());
                        massregimetWvWebview.loadDataWithBaseURL(null, Utility.Webhtml(bean.getData().getDetail()), "text/html", "utf-8", null);
                    } else {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), MassRegimetActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", MassRegimetActivity.this);
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
