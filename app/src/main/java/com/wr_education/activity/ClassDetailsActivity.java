package com.wr_education.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.adapter.HomeGalleryAdapter;
import com.wr_education.adapter.MyFragmentPagerAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.ClassDetailsBean;
import com.wr_education.fragment.ClassDetailsOneFragment;
import com.wr_education.fragment.ClassDetailsThreeFragment;
import com.wr_education.fragment.ClassDetailsTwoFragment;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;
import com.wr_education.view.NoScrollViewPager;
import com.wr_education.view.RoundImageView;
import com.wr_education.view.TabLineIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

/**
 * 课程详情页面
 * Created by Administrator on 2016/4/15.
 */
public class ClassDetailsActivity extends AbstractActivity implements TabLineIndicator.OnTabReselectedListener {
    @Bind(R.id.classdetails_imgbtn_back)
    ImageButton classdetailsImgbtnBack;
    //    @Bind(R.id.viewflow)
//    ViewFlow viewflow;
//    @Bind(R.id.viewflowindic)
//    CircleFlowIndicator viewflowindic;
    @Bind(R.id.classdetails_tv_name)
    TextView classdetailsTvName;
    @Bind(R.id.classdetails_tv_number)
    TextView classdetailsTvNumber;
    @Bind(R.id.classdetails_tv_content)
    TextView classdetailsTvContent;
    @Bind(R.id.classdetails_rl_location)
    RelativeLayout classdetailsRlLocation;
    @Bind(R.id.classdetails_rl_organization)
    RelativeLayout classdetailsRlOrganization;
    @Bind(R.id.classdetails_img_classteacher)
    RoundImageView classdetailsImgClassteacher;
    @Bind(R.id.classdetails_tv_teachername)
    TextView classdetailsTvTeachername;
    @Bind(R.id.classdetails_tv_teacherinterest)
    TextView classdetailsTvTeacherinterest;
    //    @Bind(R.id.classdetails_tv_suitcrowd)
//    TextView classdetailsTvSuitcrowd;
//    @Bind(R.id.classdetails_tv_teachingobjectives)
//    TextView classdetailsTvTeachingobjectives;
//    @Bind(R.id.classdetails_tv_exitclassrule)
//    TextView classdetailsTvExitclassrule;
//    @Bind(R.id.classdetails_tv_insertclassrule)
//    TextView classdetailsTvInsertclassrule;
    @Bind(R.id.homepage_maintype)
    TabLineIndicator homepageMaintype;
    @Bind(R.id.homepage_rl_addsbaner)
    RelativeLayout homepageRlAddsbaner;
    @Bind(R.id.homepage_vPager)
    NoScrollViewPager homepageVPager;
    @Bind(R.id.classdetails_tv_location)
    TextView classdetailsTvLocation;
    @Bind(R.id.classdetails_tv_organization)
    TextView classdetailsTvOrganization;
    @Bind(R.id.classdetails_img_title)
    ImageView classdetailsImgtitle;
    @Bind(R.id.classdetails_sl)
    ScrollView classdetailsSl;
    @Bind(R.id.classdetails_fl)
    FrameLayout classdetailsFl;
    @Bind(R.id.classdetails_tv_title)
    TextView classdetailsTvTitle;
    @Bind(R.id.classdetails_rl)
    RelativeLayout classdetails_rl;

    private HomeGalleryAdapter adapter;

    private ClassDetailsOneFragment classDetailsOneFragment;
    private ClassDetailsTwoFragment classDetailsTwoFragment;
    private ClassDetailsThreeFragment classDetailsThreeFragment;
    private ImageView imageView;// 动画图片
    private ArrayList<Fragment> views;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private View view1, view2, view3;// 各个页卡

    private List<View> tablist; // Tab页面列表
    private ArrayList<HashMap<String, String>> maps = null;

    private ClassDetailsBean bean;
    private String id;

    @Override
    protected void initLayout() {
        setContentView(R.layout.classdetails_layout);
    }

    @Override
    protected void aadListenter() {
        classdetailsImgbtnBack.setOnClickListener(this);
        classdetailsRlOrganization.setOnClickListener(this);
        classdetailsRlLocation.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        InitViewPager();
        initTab();
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }

        if (Utility.NetworkAvailable(ClassDetailsActivity.this)) {
            new GetClassDetailsTask().execute(id);
        } else {
            Utility.showToastNoNetWork(ClassDetailsActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.classdetails_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
            case R.id.classdetails_rl_organization:
                Intent intent = new Intent(ClassDetailsActivity.this, OrganizationHomeActivity.class);
                intent.putExtra("id", bean.getData().getInfo().getAgency_id());
                startActivity(intent);
                break;
            case R.id.classdetails_rl_location:
                //暂时不需要跳转
             //   startActivity(new Intent(ClassDetailsActivity.this, BaiDuMapActivity.class));
                break;
        }
    }

    private void InitViewPager() {
        views = new ArrayList<Fragment>();
        classDetailsThreeFragment = new ClassDetailsThreeFragment();
        classDetailsOneFragment = new ClassDetailsOneFragment();
        classDetailsTwoFragment = new ClassDetailsTwoFragment();

        views.add(classDetailsThreeFragment);
        views.add(classDetailsOneFragment);
        views.add(classDetailsTwoFragment);

        homepageVPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initTab() {
        homepageMaintype.setOnTabReselectedListener(this);
        maps = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 3; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            if (i == 0)
                map.put("name", "课程说明");
            else if (i == 1)
                map.put("name", "课程详情");
            else if (i == 2) {
                map.put("name", "课程评价");
            }
            maps.add(map);
        }
        homepageMaintype.addAdapter(maps);
        homepageMaintype.setPos(currIndex);
    }

    @Override
    public void onTabReselected(int position) {
        homepageVPager.setCurrentItem(position);
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
                    homepageMaintype.setPos(position);
                    currIndex = position;
                    break;
                case 1:
                    homepageMaintype.setPos(position);
                    currIndex = position;
                    break;
                case 2:
                    homepageMaintype.setPos(position);
                    currIndex = position;
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class GetClassDetailsTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(ClassDetailsActivity.this, "数据加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(ClassDetailsActivity.this).GetCourseInfo(strings[0]);
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
                        if(bean.getData().getInfo().getType().equals("1")){
                            classdetailsTvNumber.setVisibility(View.GONE);
                        }else if(bean.getData().getInfo().getType().equals("2")){
                            classdetailsTvNumber.setVisibility(View.VISIBLE);
                            classdetailsTvNumber.setText(bean.getData().getInfo().getCourse_num() + "节"); //设置节数
                        }
                        MyApplication.getInstance().displayHeadImage(bean.getData().getInfo().getImg(), classdetailsImgtitle, R.mipmap.meishitupian01, null);//设置课程图片
                        classdetailsTvName.setText(bean.getData().getInfo().getName()); //设置名称
                        classdetailsTvContent.setText(bean.getData().getInfo().getFit_crowd());//设置试学人群

                        classdetailsTvLocation.setText(bean.getData().getInfo().getAddress()); //设置位置
                        classdetailsTvOrganization.setText(bean.getData().getInfo().getAgency()); //设置机构
                        MyApplication.getInstance().displayHeadImage(bean.getData().getTInfo().getHead(), classdetailsImgClassteacher, R.mipmap.morentouxiang2, null); //设置老师头像
                        classdetailsTvTeachername.setText(bean.getData().getTInfo().getName()); //设置老师名字
                        classdetailsTvTeacherinterest.setText(bean.getData().getTInfo().getLabel_admin());//设置老师爱好

                        classDetailsThreeFragment.ClassDetailsThreeFragment(bean);
                        classDetailsOneFragment.ClassDetailsOneFragment(bean);
                        classDetailsTwoFragment.ClassDetailsTwoFragment(bean);
                        homepageVPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), views));
                        homepageVPager.setCurrentItem(0);
                    } else {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), ClassDetailsActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", ClassDetailsActivity.this);
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
