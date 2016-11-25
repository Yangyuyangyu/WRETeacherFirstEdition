package com.wr_education.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.activity.EducationExperienceActivity;
import com.wr_education.activity.MyMessageActivity;
import com.wr_education.activity.PersonDataActivity;
import com.wr_education.activity.SettingActivity;
import com.wr_education.activity.ShareFruitActivity;
import com.wr_education.app.BaseFragment;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.LoginBean;
import com.wr_education.bean.TeacherBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;
import com.wr_education.view.RoundImageView;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/12.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.mine_img_find)
    RelativeLayout mineImgFind;
    @Bind(R.id.mine_rl_ziliao)
    RelativeLayout mineRlZiliao;
    @Bind(R.id.mine_rl_education)
    RelativeLayout mineRlEducation;
    @Bind(R.id.mine_rl_share)
    RelativeLayout mineRlShare;
    @Bind(R.id.mine_ll_setting)
    LinearLayout mineLlSetting;
    @Bind(R.id.mine_tv_name)
    TextView mineTvName;
    @Bind(R.id.mine_tv_phone)
    TextView mineTvPhone;
    @Bind(R.id.mine_img_head)
    RoundImageView mineImgHead;
    @Bind(R.id.mine_rl_title)
    RelativeLayout mineRlTitle;
    @Bind(R.id.mine_sl_title)
    ScrollView mineSlTitle;
    @Bind(R.id.mine_imageview)
    ImageView mineimageview;
    private LoginBean loginBean;
    private TeacherBean bean;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mine_layout, null);
    }

    @Override
    protected void initVariables() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //获取标题栏高度
//            mineSlTitle.setPadding(0, Utility.getStatusHeight(getActivity()), 0, 0);
//        }
        if (MyApplication.getInstance().getLoginBean() != null) {
            loginBean = MyApplication.getInstance().getLoginBean();
            mineTvName.setText(loginBean.getData().getName());
            mineTvPhone.setText(loginBean.getData().getPhone());
            MyApplication.getInstance().displayHeadImage(MyApplication.getInstance().getLoginBean().getData().getHead(), mineImgHead, R.mipmap.morentouxiang2, null);
            if(MyApplication.getInstance().getLoginBean().getData().getHas_message().equals("0")){//没有新消息
                mineimageview.setImageDrawable(getResources().getDrawable(R.mipmap.mine_lingdang));
            }else if(MyApplication.getInstance().getLoginBean().getData().getHas_message().equals("1")){//有新消息
                mineimageview.setImageDrawable(getResources().getDrawable(R.mipmap.mine_lingdangtwo));
            }
        }

    }

    @Override
    protected void addListener() {
        mineImgFind.setOnClickListener(this);
        mineRlZiliao.setOnClickListener(this);
        mineRlEducation.setOnClickListener(this);
        mineRlShare.setOnClickListener(this);
        mineLlSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.mine_img_find:
                startActivity(new Intent(getActivity(), MyMessageActivity.class));
                break;
            case R.id.mine_rl_ziliao:
                startActivity(new Intent(getActivity(), PersonDataActivity.class));
                break;
            case R.id.mine_rl_education:
                startActivity(new Intent(getActivity(), EducationExperienceActivity.class));
                break;
            case R.id.mine_rl_share:
                startActivity(new Intent(getActivity(), ShareFruitActivity.class));
                break;
            case R.id.mine_ll_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mineTvName.setText(loginBean.getData().getName());
        mineTvPhone.setText(loginBean.getData().getPhone());
        if (MyApplication.getInstance().getLoginBean().getData().getHead() != null) {
            MyApplication.getInstance().displayHeadImage(MyApplication.getInstance().getLoginBean().getData().getHead(), mineImgHead, R.mipmap.morentouxiang2, null);
        }
        updata();
    }
    private class GetHomePageOneTask extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(getActivity()).GetTeacherInfo(strings[0]);
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
                    if(bean.getData().getInfo().getHas_message().equals("0")){
                        mineimageview.setImageDrawable(getResources().getDrawable(R.mipmap.mine_lingdang));
                    }else if(bean.getData().getInfo().getHas_message().equals("1")){
                        mineimageview.setImageDrawable(getResources().getDrawable(R.mipmap.mine_lingdangtwo));
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", getActivity());
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.getStackTrace();
            }
        }
    }

    private void updata(){
        if(Utility.NetworkAvailable(getActivity())){
            new GetHomePageOneTask().execute(MyApplication.getInstance().getLoginBean().getData().getId());
        }else{
            Utility.showToastNoNetWork(getActivity());
        }
    }
}
