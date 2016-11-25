package com.wr_education.activity;

import android.view.KeyEvent;
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
 * 教育经历页面
 * Created by Administrator on 2016/4/14.
 */
public class EducationExperienceActivity extends AbstractActivity {
    @Bind(R.id.educationexperience_imgbtn_back)
    ImageButton educationexperienceImgbtnBack; //返回
    @Bind(R.id.educationexperience_tv_commit)
    TextView educationexperienceTvCommit; //提交
    @Bind(R.id.educationexperience_et)
    EditText educationexperienceEt;
    private int enter_count = 0;
    private int max_enter_count = 10;
    private boolean isDeleteEnter = false;
    private PublicBean bean;


    @Override
    protected void initLayout() {
        setContentView(R.layout.educationexperience_layout);
    }
    @Override
    protected void aadListenter() {
        educationexperienceImgbtnBack.setOnClickListener(this);
        educationexperienceTvCommit.setOnClickListener(this);
        educationexperienceEt.setOnKeyListener(onKeyListener);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        educationexperienceEt.setText(MyApplication.getInstance().getLoginBean().getData().getEdu_exp());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.educationexperience_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM(); //隐藏软键盘
                break;
            case R.id.educationexperience_tv_commit:
                if(Utility.StringIsNull(educationexperienceEt.getText().toString())){
                    Utility.showToast("教育经历不能为空",EducationExperienceActivity.this);
                }else if(Utility.NetworkAvailable(EducationExperienceActivity.this)){
                    if(MyApplication.getInstance().getLoginBean().getData().getId()!=null){
                        new GetEducationTask().execute(MyApplication.getInstance().getLoginBean().getData().getId(),educationexperienceEt.getText().toString());
                    }else{
                        Utility.showToast("没有获取到您的id",EducationExperienceActivity.this);
                    }
                }else{
                    Utility.showToastNoNetWork(EducationExperienceActivity.this);
                }
                break;
        }
    }

    View.OnKeyListener onKeyListener=new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                isDeleteEnter = false;
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // 回车按钮判断
                    if (enter_count >= max_enter_count) {
                        Utility.showToast("最多手动" + max_enter_count + "个换行",
                                EducationExperienceActivity.this);
                        return true;
                    } else {
                        enter_count = enter_count + 1;
                    }
                } else if (keyCode == KeyEvent.KEYCODE_DEL) {
                    isDeleteEnter = true;
                }
            }
            return false;
        }
    };

    private class GetEducationTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(EducationExperienceActivity.this, "上传中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean=HttpRequest.getInstance(EducationExperienceActivity.this).SetEducationExperience(strings[0],strings[1]);
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
                    if (bean.getCode()==0){//上传教育经历成功
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), EducationExperienceActivity.this);
                        finish();
                        saveEditTextAndCloseIMM();
                    }else{
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), EducationExperienceActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", EducationExperienceActivity.this);
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
