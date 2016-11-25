package com.wr_education.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wr_education.R;
import com.wr_education.adapter.MyFragmentPagerAdapter;
import com.wr_education.callbackutils.CallBack;
import com.wr_education.view.TabLineIndicator;
import com.wr_education.view.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/4/12.
 */
public class HomePageFragment extends Fragment implements TabLineIndicator.OnTabReselectedListener, View.OnClickListener, XListView.IXListViewListener, CallBack {
    private View v;
//    private RoundImageView head; //主页的老师头像
//    private TextView name; //主页的老师名字

    private int ispage; // 1--主页 2--课程  3--学生

   // private HomePageOneFragment oneFragment;
    private HomePageTwoFragment twoFragment;
    private HomePageThreeFragment threeFragment;
    private ViewPager viewPager; // 对应的viewPager
    private ImageView imageView;// 动画图片
    private ArrayList<Fragment> views;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private View view1, view2, view3;// 各个页卡

    private List<View> tablist; // Tab页面列表
    private TabLineIndicator mMainType;
    private ArrayList<HashMap<String, String>> maps = null;
    private ImageButton back;
    private MyFragmentPagerAdapter adapter;
    private RelativeLayout homepage_rl_img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.homepage_layout, null);
        //       initView();  //初始化控件
        InitViewPager();
        initTab();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
//        {
//            // 透明状态栏
//            getActivity().getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            homepage_rl_img.setPadding(0, Utility.getStatusHeight(getActivity()),0,0);
//        }
        return v;
    }
//    private void initView() {
//        head=(RoundImageView)v.findViewById(R.id.homepage_img_head);
//        name=(TextView)v.findViewById(R.id.homepage_tv_name);
//        homepage_rl_img=(RelativeLayout)v.findViewById(R.id.homepage_rl_img);
//        if(MyApplication.getInstance().getLoginBean().getData().getHead()!=null){
//            MyApplication.getInstance().displayHeadImage(MyApplication.getInstance().getLoginBean().getData().getHead(),head,R.mipmap.ic_launcher,null);
//        }
//        name.setText(MyApplication.getInstance().getLoginBean().getData().getName());
//    }

    private void InitViewPager() {
        viewPager = (ViewPager) v.findViewById(R.id.homepage_vPager);
        views = new ArrayList<Fragment>();
     //   oneFragment = new HomePageOneFragment();
        twoFragment = new HomePageTwoFragment();
        threeFragment = new HomePageThreeFragment();
     //   views.add(oneFragment);
        views.add(twoFragment);
        views.add(threeFragment);
        adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), views);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        ispage = 1;// 初始化默认是需求信息页面
    }


    private void initTab() {
        mMainType = (TabLineIndicator) v.findViewById(R.id.homepage_maintype);
        mMainType.setOnTabReselectedListener(this);
        maps = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 2; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            if (i == 0)
                //   map.put("name", "主页");
                map.put("name", "课程");
            else if (i == 1)
                map.put("name", "学生");
            maps.add(map);
        }
        mMainType.addAdapter(maps);
        mMainType.setPos(currIndex);
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
                    mMainType.setPos(position);
                    currIndex = position;
                    ispage = 1;
                    break;
                case 1:
                    mMainType.setPos(position);
                    currIndex = position;
                    ispage = 2;
                    break;
//                case 2:
//                    mMainType.setPos(position);
//                    currIndex = position;
//                    ispage = 3;
//                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    @Override
    public void onTabReselected(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


    @Override
    public void putInfo(int i) {
        mMainType.setPos(i);
        viewPager.setCurrentItem(i);
    }

    @Override
    public void onResume() {
        super.onResume();
//        if(MyApplication.getInstance().getLoginBean().getData().getHead()!=null){
//            MyApplication.getInstance().displayHeadImage(MyApplication.getInstance().getLoginBean().getData().getHead(),head,R.mipmap.morentouxiang2,null);
//            name.setText(MyApplication.getInstance().getLoginBean().getData().getName());
//        }
    }
}
