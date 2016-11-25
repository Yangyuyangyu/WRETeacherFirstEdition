package com.wr_education.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wr_education.R;
import com.wr_education.adapter.ClassDetailsTwoAdapter;
import com.wr_education.app.BaseFragment;
import com.wr_education.bean.ClassDetailsBean;
import com.wr_education.view.MyListView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 课程评论页面
 * Created by Administrator on 2016/4/16.
 */
public class ClassDetailsTwoFragment extends BaseFragment {
    @Bind(R.id.classtwo_list)
    MyListView classtwoList;
    private ArrayList<ClassDetailsBean.DataBean.CommentBean> list;
    private ClassDetailsTwoAdapter adapter;
    private ClassDetailsBean bean;

    public void ClassDetailsTwoFragment(ClassDetailsBean bean) {
        this.bean = bean;
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.classdetailstwo_layout, null);
    }

    @Override
    protected void initVariables() {
        list = new ArrayList<ClassDetailsBean.DataBean.CommentBean>();
        if (bean.getData().getComment() != null) {
            classtwoList.setVisibility(View.VISIBLE);
            list.addAll(bean.getData().getComment());
            adapter = new ClassDetailsTwoAdapter(getActivity(), list);
            classtwoList.setAdapter(adapter);
        }
    }

    @Override
    protected void addListener() {

    }

}
