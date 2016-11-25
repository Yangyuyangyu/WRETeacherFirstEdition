package com.wr_education.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.app.BaseFragment;
import com.wr_education.bean.OrganizationHomeBean;
import com.wr_education.util.Utility;

import butterknife.Bind;

/**
 * 机构详情主页Fragment
 * Created by Administrator on 2016/4/14.
 */
public class OrganizationSynopsisFragment extends BaseFragment {
    @Bind(R.id.organizationsynopsis_tv_location)
    TextView organizationsynopsisTvLocation;
//    @Bind(R.id.organizationsynopsis_tv_aptitude)
//    TextView organizationsynopsisTvAptitude;
    @Bind(R.id.organizationsynopsis_tv_specialty)
    TextView organizationsynopsisTvSpecialty;
    @Bind(R.id.organizationsynopsis_tv_synopsis)
    TextView organizationsynopsisTvSynopsis;
    @Bind(R.id.organizationsynopsis_tv_no)
    TextView organizationsynopsisTvNo;
    @Bind(R.id.organizationsynopsis_ll_no)
    LinearLayout organizationsynopsisLlNo;
    private OrganizationHomeBean bean;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.organizationsynopsis_layout, null);
    }

    @Override
    protected void initVariables() {
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            bean = (OrganizationHomeBean) bundle.getSerializable("bean");
            //设置位置
            organizationsynopsisTvLocation.setText(bean.getData().getLocation());
            //设置资质
      //      organizationsynopsisTvAptitude.setText(bean.getData().getQualification());
            //设置理念
            organizationsynopsisTvSpecialty.setText(bean.getData().getFeature());
            //设置简介
            organizationsynopsisTvSynopsis.setText(bean.getData().getBrief());
            if(Utility.StringIsNull(bean.getData().getRefuse())){
                organizationsynopsisLlNo.setVisibility(View.GONE);
            }else{
                organizationsynopsisLlNo.setVisibility(View.VISIBLE);
                organizationsynopsisTvNo.setText(bean.getData().getRefuse());
            }
        }

    }

    @Override
    protected void addListener() {

    }
}
