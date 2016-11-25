package com.wr_education.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.activity.MyMessageActivity;
import com.wr_education.adapter.MyMassRecyclerAdapter;
import com.wr_education.app.BaseFragment;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.MyMassListBean;
import com.wr_education.bean.OrganizationHomeBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/14.
 */
public class MyMassFragment extends BaseFragment{
    @Bind(R.id.organization_ll_circle)
    LinearLayout organizationLlCircle;
    @Bind(R.id.mymass_recyclerview)
    XRecyclerView mRecyclerView;
    private MyMassListBean listBean;
    private ArrayList<MyMassListBean.DataBean> list;
 //   private MyMassListAdapter adapter;
    private MyMassRecyclerAdapter adapter;
    private OrganizationHomeBean homeBean;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mymass_layout, null);
    }

    @Override
    protected void initVariables() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mRecyclerView.setLoadingListener(loadingListener);

        list = new ArrayList<MyMassListBean.DataBean>();
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            homeBean = (OrganizationHomeBean) bundle.getSerializable("bean");
        }
        updata();
    }

    @Override
    protected void addListener() {

    }

    private class GetMyMassTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            listBean = HttpRequest.getInstance(getActivity()).GetMyMass(strings[0]);
            // 请求成功返回数据
            if (listBean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (listBean.getCode() == 0) { //有数据
                        organizationLlCircle.setVisibility(View.GONE);
                        list.clear();
                        list.addAll(listBean.getData());
                        adapter = new MyMassRecyclerAdapter(getActivity(), list);
                        mRecyclerView.setAdapter(adapter);
                    } else {
                        organizationLlCircle.setVisibility(View.GONE);
                    }
                } else {
                    //          Utility.showToast("该机构没有社团", getActivity());
                    organizationLlCircle.setVisibility(View.GONE);
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
                new GetMyMassTask().execute(homeBean.getData().getId());
            } else {
                Utility.showToast("没有获取到机构id", getActivity());
            }
        } else {
            Utility.showToastNoNetWork(getActivity());
        }
    }

    XRecyclerView.LoadingListener loadingListener = new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    updata();
                    adapter.notifyDataSetChanged();
                    mRecyclerView.refreshComplete();
                }

            }, 1000);            //refresh data here
        }

        @Override
        public void onLoadMore() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    adapter.notifyDataSetChanged();
                    mRecyclerView.loadMoreComplete();
                }
            }, 1000);
        }
    };
}
