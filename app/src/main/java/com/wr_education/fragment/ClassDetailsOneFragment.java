package com.wr_education.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.wr_education.R;
import com.wr_education.app.BaseFragment;
import com.wr_education.bean.ClassDetailsBean;
import com.wr_education.util.Utility;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/16.
 */
public class ClassDetailsOneFragment extends BaseFragment {

    @Bind(R.id.classone_webview)
    WebView classoneWebview;
    private ClassDetailsBean bean;

    public void ClassDetailsOneFragment(ClassDetailsBean bean) {
        this.bean = bean;
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.classdetailsone_layout, null);
    }

    @Override
    protected void initVariables() {
        String content=Utility.Webhtml(bean.getData().getInfo().getDetail());
        classoneWebview.loadDataWithBaseURL(null, content + "", "text/html", "utf-8", null);
    }

    @Override
    protected void addListener() {

    }
}
