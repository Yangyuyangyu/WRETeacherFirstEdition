package com.wr_education.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.activity.ClassDetailsActivity;
import com.wr_education.adapter.HomePageOneListAdapter;
import com.wr_education.app.BaseFragment;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.HomePageOneListBean;
import com.wr_education.bean.TeacherBean;
import com.wr_education.callbackutils.CallBack;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;
import com.wr_education.view.MyListView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/12.
 */
public class HomePageOneFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.homepage_tv_location)
    TextView homepageTvLocation;
    @Bind(R.id.homepage_tv_aptitude)
    TextView homepageTvAptitude;
    @Bind(R.id.homepage_tv_specialty)
    TextView homepageTvSpecialty;
    @Bind(R.id.homepage_rl_class)
    RelativeLayout homepageRlClass;
    @Bind(R.id.homepage_one_list)
    MyListView homepageOneList;
    @Bind(R.id.homepage_tv_class_content)
    TextView homepageTvClassContent;
    @Bind(R.id.homepage_img_class)
    ImageView homepageImgClass;
    @Bind(R.id.homepage_tv_class_name)
    TextView homepageTvClassName;
    @Bind(R.id.homepage_tv_class_time)
    TextView homepageTvClassTime;
    @Bind(R.id.homepage_tv_educationexperience)
    TextView homepageTvEducationexperience;
    @Bind(R.id.homepage_tv_sharefruit)
    TextView homepageTvSharefruit;
    @Bind(R.id.homepage_rl_classcontent)
    RelativeLayout homepageRlClasscontent;
    @Bind(R.id.homepage_tv_evaluate)
    TextView homepageTvEvaluate;
    private View v;
    private HomePageOneListBean oneListBean;
    private ArrayList<TeacherBean.DataBean.CommentBean> list;
    private HomePageOneListAdapter adapter;
    private CallBack callback;
    private TeacherBean bean;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return v = inflater.inflate(R.layout.viewpager_homepage_one, null);
    }

    @Override
    protected void initVariables() {
        init();
    }

    @Override
    protected void addListener() {
        homepageRlClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.putInfo(1);
            }
        });
        homepageRlClasscontent.setOnClickListener(this);
    }

    private void init() {
        homepageOneList = (MyListView) v.findViewById(R.id.homepage_one_list);
        homepageRlClass = (RelativeLayout) v.findViewById(R.id.homepage_rl_class);
        list = new ArrayList<TeacherBean.DataBean.CommentBean>();
        updata(); //更新数据
    }

    public void setCallback(CallBack callback) {
        this.callback = callback;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.homepage_rl_classcontent:
                if(bean.getData().getCourse().size()!=0){
                    Intent intent = new Intent(getActivity(), ClassDetailsActivity.class);
                    intent.putExtra("id", bean.getData().getCourse().get(0).getId());
                    getActivity().startActivity(intent);
                }
                break;
        }
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
                    if (bean.getCode() == 0) { //有数据
                        if(!Utility.StringIsNull(bean.getData().getInfo().getAddress())){
                            //设置位置
                            homepageTvLocation.setText(bean.getData().getInfo().getAddress());
                        }
                        if(!Utility.StringIsNull(bean.getData().getInfo().getQualification())){
                            //设置资质
                            homepageTvAptitude.setText(bean.getData().getInfo().getQualification());
                        }
                        if(Utility.StringIsNull(bean.getData().getInfo().getLabel_admin())){
                            //设置特点
                            homepageTvSpecialty.setText(bean.getData().getInfo().getLabel_admin());
                        }
                        if(bean.getData().getCourseNum()!=0){
                            //设置课程
                            homepageTvClassContent.setText(Integer.valueOf(bean.getData().getCourseNum()) + "个");
                            //设置课程名字
                            homepageTvClassName.setText(bean.getData().getCourse().get(0).getName());
                            //设置课程头像
                            MyApplication.getInstance().displayHeadImage(bean.getData().getCourse().get(0).getImg(), homepageImgClass, R.mipmap.ceshi_img1, null);
                        }else{
                            homepageTvClassContent.setText("0个");
                        }

                        //设置教育经历
                        if (Utility.StringIsNull(bean.getData().getInfo().getEdu_exp())) {
                            homepageTvEducationexperience.setText("您还没有设置您的教育经历");
                        } else {
                            homepageTvEducationexperience.setText(bean.getData().getInfo().getEdu_exp());
                        }
                        //设置成果分享
                        if (Utility.StringIsNull(bean.getData().getInfo().getRes_share())) {
                            homepageTvSharefruit.setText("您还没有设置您的成果分享");
                        } else {
                            homepageTvSharefruit.setText(bean.getData().getInfo().getRes_share());
                        }

                        if(bean.getData().getComment().size()!=0) {
                            homepageOneList.setVisibility(View.VISIBLE);
                            homepageTvEvaluate.setVisibility(View.GONE);
                            //设置学生评价
                            list.clear();
                            list.addAll(bean.getData().getComment());
                            adapter = new HomePageOneListAdapter(getActivity(), list);
                            homepageOneList.setAdapter(adapter);
                        }else{
                            homepageOneList.setVisibility(View.GONE);
                            homepageTvEvaluate.setVisibility(View.VISIBLE);
                        }
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

    private void updata() {
        if (Utility.NetworkAvailable(getActivity())) {
            if (MyApplication.getInstance().getLoginBean().getData().getId() != null) {
                new GetHomePageOneTask().execute(MyApplication.getInstance().getLoginBean().getData().getId());
            } else {
                Utility.showToast("没有获取到您的id", getActivity());
            }
        } else {
            Utility.showToastNoNetWork(getActivity());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updata();

    }

}
