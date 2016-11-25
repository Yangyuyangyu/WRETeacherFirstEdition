package com.wr_education.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.PublicBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import butterknife.Bind;

/**
 * 成果分享页面
 * Created by Administrator on 2016/4/14.
 */
public class ShareFruitActivity extends AbstractActivity {
    @Bind(R.id.sharefruit_imgbtn_back)
    ImageButton sharefruitImgbtnBack;
    @Bind(R.id.sharefruit_tv_commit)
    TextView sharefruitTvCommit;
    @Bind(R.id.sharefruit_et)
    EditText sharefruitEt;
    private PublicBean bean;

    @Override
    protected void aadListenter() {
        sharefruitImgbtnBack.setOnClickListener(this);
        sharefruitTvCommit.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        sharefruitEt.setText(MyApplication.getInstance().getLoginBean().getData().getRes_share());
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.sharefruit_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sharefruit_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
            case R.id.sharefruit_tv_commit:
                if(Utility.StringIsNull(sharefruitEt.getText().toString())){
                    Utility.showToast("成果分享不能为空",ShareFruitActivity.this);
                }else if(Utility.NetworkAvailable(ShareFruitActivity.this)){
                    if(MyApplication.getInstance().getLoginBean().getData().getId()!=null){
                        new GetShareFruitTask().execute(MyApplication.getInstance().getLoginBean().getData().getId(),sharefruitEt.getText().toString());
                    }else{
                        Utility.showToast("没有获取到您的id",ShareFruitActivity.this);
                    }
                }else{
                    Utility.showToastNoNetWork(ShareFruitActivity.this);
                }
                break;
        }
    }

    private class GetShareFruitTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(ShareFruitActivity.this, "上传中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean= HttpRequest.getInstance(ShareFruitActivity.this).SetShareFruitData(strings[0],strings[1]);
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
                    if (bean.getCode()==0){//上传成果分享成功
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), ShareFruitActivity.this);
                        finish();
                        saveEditTextAndCloseIMM();
                    }else{
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), ShareFruitActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", ShareFruitActivity.this);
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
