package com.wr_education.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.adapter.MyFragmentPagerAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.OrganizationHomeBean;
import com.wr_education.bean.PublicBean;
import com.wr_education.fragment.MyMassFragment;
import com.wr_education.fragment.OrganizationSynopsisFragment;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;
import com.wr_education.view.RoundImageView;
import com.wr_education.view.TabLineIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

/**
 * 机构主页
 * Created by Administrator on 2016/4/14.
 */
public class OrganizationHomeActivity extends AbstractActivity implements TabLineIndicator.OnTabReselectedListener {
    @Bind(R.id.organizationhome_imgbtn_back)
    ImageButton organizationhomeImgbtnBack;
    @Bind(R.id.organizationhome_img_head)
    RoundImageView organizationhomeImgHead;
    @Bind(R.id.organizationhome_btn_add)
    Button organizationhomeBtnAdd;
    @Bind(R.id.organizationhome_maintype)
    TabLineIndicator organizationhomeMaintype;
    @Bind(R.id.organizationhome_rl_addsbaner)
    RelativeLayout organizationhomeRlAddsbaner;
    @Bind(R.id.organizationhome_vPager)
    ViewPager organizationhomeVPager;
//    @Bind(R.id.organizationhome_tv_title)
//    TextView organizationhomeTvTitle;
    @Bind(R.id.organizationhome_tv_name)
    TextView organizationhomeTvName;
    @Bind(R.id.organizationhome_rl_waititle)
    RelativeLayout organizationhome_rl_waititle;

    private int ispage; // 1--主页 2--机构社团
    private OrganizationSynopsisFragment synopsisFragment;
    private MyMassFragment myMassFragment;

    private ImageView imageView;// 动画图片
    private ArrayList<Fragment> views;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private View view1, view2;// 各个页卡

    private List<View> tablist; // Tab页面列表
    private ArrayList<HashMap<String, String>> maps = null;

    private OrganizationHomeBean bean;
    private String id;//上个页面传过来的机构id
    private PublicBean publicBean;

    @Override
    protected void initLayout() {
        setContentView(R.layout.organizationhome_layout);
    }

    @Override
    protected void aadListenter() {
        organizationhomeImgbtnBack.setOnClickListener(this);
        organizationhomeBtnAdd.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            organizationhome_rl_waititle.setPadding(0, Utility.getStatusHeight(OrganizationHomeActivity.this), 0, 0);
//        }

        views = new ArrayList<Fragment>();

        synopsisFragment = new OrganizationSynopsisFragment();
        myMassFragment = new MyMassFragment();
        views.add(synopsisFragment);
        views.add(myMassFragment);
        organizationhomeVPager.setCurrentItem(0);
        organizationhomeVPager.setOnPageChangeListener(new MyOnPageChangeListener());
        ispage = 1;// 初始化默认是需求信息页面

        organizationhomeMaintype.setOnTabReselectedListener(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        maps = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 2; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            if (i == 0)
                map.put("name", "主页");
            else if (i == 1)
                map.put("name", "机构社团");
            maps.add(map);
        }
        organizationhomeMaintype.addAdapter(maps);
        organizationhomeMaintype.setPos(currIndex);

        Updata();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.organizationhome_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
            case R.id.organizationhome_btn_add:
                if (Utility.NetworkAvailable(OrganizationHomeActivity.this)) {
                    if(MyApplication.getInstance().getLoginBean().getData().getId()!=null){
                        new SetAddOrganizationTask().execute(MyApplication.getInstance().getLoginBean().getData().getId(),id);
                    }else{
                        Utility.showToast("没有获取到您的id",OrganizationHomeActivity.this);
                    }
                } else {
                    Utility.showToastNoNetWork(OrganizationHomeActivity.this);
                }
                break;
        }
    }

    @Override
    public void onTabReselected(int position) {
        organizationhomeVPager.setCurrentItem(position);
    }


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) {
            currIndex = position;
            switch (position) {
                case 0:
                    organizationhomeMaintype.setPos(position);
                    currIndex = position;
                    ispage = 1;
                    break;
                case 1:
                    organizationhomeMaintype.setPos(position);
                    currIndex = position;
                    ispage = 2;
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    //获取机构详情数据
    private class GetOrganizationHomeTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(OrganizationHomeActivity.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(OrganizationHomeActivity.this).GetOrganizationDetail(strings[0],strings[1]);
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
                //        organizationhomeTvTitle.setText(bean.getData().getName());
                        organizationhomeTvName.setText(bean.getData().getName());
                        MyApplication.getInstance().displayHeadImage(bean.getData().getImg(), organizationhomeImgHead, R.mipmap.morentouxiang2, null);
                        if(bean.getData().getJoined().equals("0")){ //-1未加入，0申请审核中，1通过，2拒绝
                            organizationhomeBtnAdd.setBackgroundResource(R.color.app_while_but_pass_color);
                            organizationhomeBtnAdd.setSelected(false);
                            organizationhomeBtnAdd.setEnabled(false);
                            organizationhomeBtnAdd.setText("已申请，正在审核中....");
                            organizationhomeBtnAdd.setVisibility(View.VISIBLE);
                        }else if(bean.getData().getJoined().equals("2")){
//                            organizationhomeBtnAdd.setBackgroundResource(R.color.app_while_but_pass_color);
//                            organizationhomeBtnAdd.setSelected(false);
//                            organizationhomeBtnAdd.setEnabled(false);
//                            organizationhomeBtnAdd.setText("该机构以拒绝您加入....");
                            organizationhomeBtnAdd.setText("再次申请");
                            organizationhomeBtnAdd.setVisibility(View.VISIBLE);
                        }else if(bean.getData().getJoined().equals("1")){
                            organizationhomeBtnAdd.setBackgroundResource(R.color.app_while_but_pass_color);
                            organizationhomeBtnAdd.setSelected(false);
                            organizationhomeBtnAdd.setEnabled(false);
                            organizationhomeBtnAdd.setText("您已经加入了该机构");
                            organizationhomeBtnAdd.setVisibility(View.VISIBLE);
                        }else if(bean.getData().getJoined().equals("-1")){
                            organizationhomeBtnAdd.setText("申请加入");
                            organizationhomeBtnAdd.setVisibility(View.VISIBLE);
                        }
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("bean", bean);
                        synopsisFragment.setArguments(bundle);
                        Bundle bundle1=new Bundle();
                        bundle1.putSerializable("bean", bean);
                        myMassFragment.setArguments(bundle1);
                        organizationhomeVPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), views));
                    } else {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), OrganizationHomeActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", OrganizationHomeActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }

    private void Updata() {
        if (Utility.NetworkAvailable(OrganizationHomeActivity.this)) {
            if(MyApplication.getInstance().getLoginBean().getData().getId()!=null){
                new GetOrganizationHomeTask().execute(id,MyApplication.getInstance().getLoginBean().getData().getId());
            }else{
                Utility.showToast("没有获取到您的id",OrganizationHomeActivity.this);
            }
        } else {
            Utility.showToastNoNetWork(OrganizationHomeActivity.this);
        }
    }

    //申请加入机构
    private class SetAddOrganizationTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(OrganizationHomeActivity.this, "申请中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            publicBean=HttpRequest.getInstance(OrganizationHomeActivity.this).SetAddOrganization(strings[0], strings[1]);
            // 请求成功返回数据
            if (publicBean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (publicBean.getCode()==0){//上传成功
                        DismissDialog();
                        Utility.showToast("申请成功", OrganizationHomeActivity.this);
                        organizationhomeBtnAdd.setBackgroundResource(R.color.app_while_but_pass_color);
                        organizationhomeBtnAdd.setSelected(false);
                        organizationhomeBtnAdd.setEnabled(false);
                        organizationhomeBtnAdd.setText("已申请加入");
                    }else{
                        DismissDialog();
                        Utility.showToast(publicBean.getMsg(), OrganizationHomeActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", OrganizationHomeActivity.this);
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
