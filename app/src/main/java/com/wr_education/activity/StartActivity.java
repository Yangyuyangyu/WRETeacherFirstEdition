package com.wr_education.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/15.
 */
public class StartActivity extends AbstractActivity {
    @Bind(R.id.start_btn_login)
    Button startBtnLogin;
    @Bind(R.id.start_btn_register)
    Button startBtnRegister;
    @Bind(R.id.start_tv_findpassworld)
    TextView startTvFindpassworld;

    @Override
    protected void aadListenter() {
        startBtnLogin.setOnClickListener(this);
        startBtnRegister.setOnClickListener(this);
        startTvFindpassworld.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.start_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn_login:
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
                break;
            case R.id.start_btn_register:
                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
                break;
            case R.id.start_tv_findpassworld:
                startActivity(new Intent(StartActivity.this, FindPassWorldAcitvity.class));
                break;
        }
    }

}
