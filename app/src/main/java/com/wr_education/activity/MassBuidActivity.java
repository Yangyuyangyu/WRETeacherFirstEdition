package com.wr_education.activity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.MassBuidBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import butterknife.Bind;

/**
 * 社团建设表
 * Created by Administrator on 2016/4/15.
 */
public class MassBuidActivity extends AbstractActivity {
    @Bind(R.id.massbuid_imgbtn_back)
    ImageButton massbuidImgbtnBack;
    @Bind(R.id.massbuid_tv_name)
    TextView massbuidTvName;
    @Bind(R.id.massbuid_tv_time)
    TextView massbuidTvTime;
    @Bind(R.id.massbuid_tv_studentnumber)
    TextView massbuidTvStudentnumber;
    @Bind(R.id.massbuid_tv_controlnumber)
    TextView massbuidTvControlnumber;
    @Bind(R.id.massbuid_tv_subject)
    TextView massbuidTvSubject;

    private MassBuidBean bean;
    private String id;
    @Override
    protected void aadListenter() {
        massbuidImgbtnBack.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        if(getIntent()!=null){
            id=getIntent().getStringExtra("id");
        }

        if(Utility.NetworkAvailable(MassBuidActivity.this)){
            new GetMassBuidTask().execute(id);
        }else{
            Utility.showToastNoNetWork(MassBuidActivity.this);
        }
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.massbuid_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.massbuid_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
        }
    }
    private class GetMassBuidTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(MassBuidActivity.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean= HttpRequest.getInstance(MassBuidActivity.this).GetMassBuildDetail(strings[0]);
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
                    if (bean.getCode()==0){
                        DismissDialog();
                        massbuidTvName.setText(bean.getData().getName());
                        massbuidTvTime.setText(bean.getData().getCreate_time());
                        massbuidTvStudentnumber.setText(bean.getData().getStudentNum()+"人");
                        massbuidTvControlnumber.setText(bean.getData().getAdminNum()+"人");
                        massbuidTvSubject.setText(bean.getData().getSubjectNum());
                    }else{
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), MassBuidActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", MassBuidActivity.this);
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
