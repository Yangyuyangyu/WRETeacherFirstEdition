package com.wr_education.activity;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.AboutUsBean;
import com.wr_education.dialog.CallPhoneDialog;
import com.wr_education.util.Utility;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/14.
 */
public class AboutUsActivity extends AbstractActivity {
    @Bind(R.id.aboutus_imgbtn_back)
    ImageButton aboutusImgbtnBack;
    @Bind(R.id.aboutus_img_head)
    ImageView aboutusImgHead;
    @Bind(R.id.aboutus_tv_name)
    TextView aboutusTvName;
    @Bind(R.id.aboutus_tv_content)
    WebView aboutusTvContent;
    @Bind(R.id.aboutus_tv_copyright)
    TextView aboutusTvCopyright;
    @Bind(R.id.aboutus_tv_company)
    TextView aboutusTvCompany;
    @Bind(R.id.aboutus_rl_callme)
    RelativeLayout aboutusRlCallme;
    @Bind(R.id.aboutus_tv_phone)
    TextView aboutusTvPhone;

    private CallPhoneDialog callPhoneDialog;
    private AboutUsBean bean;

    @Override
    protected void aadListenter() {
        aboutusImgbtnBack.setOnClickListener(this);
        aboutusRlCallme.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        if(getIntent()!=null){
            bean=(AboutUsBean)getIntent().getSerializableExtra("bean");
        }
        if(bean.getData()!=null){
            aboutusTvPhone.setText(bean.getData().getTelephone());
            aboutusTvContent.loadDataWithBaseURL(null, bean.getData().getDetail()+"", "text/html", "utf-8", null);
        }
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.aboutus_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aboutus_imgbtn_back:
                finish();
                break;
            case R.id.aboutus_rl_callme:
                if(aboutusTvPhone.getText().toString()!=null){
                    callPhoneDialog = new CallPhoneDialog(AboutUsActivity.this, "是否拨打电话：" + aboutusTvPhone.getText().toString(), aboutusTvPhone.getText().toString());
                    callPhoneDialog.showDialog();
                }else{
                    Utility.showToast("没有获取到客服电话", AboutUsActivity.this);
                }

                break;
        }
    }
}
