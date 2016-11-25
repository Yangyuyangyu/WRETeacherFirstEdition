package com.wr_education.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.activity.OrganizationAddActivity;
import com.wr_education.adapter.OrganizationXRecyClerViewAdapter;
import com.wr_education.app.BaseFragment;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.OrganizationListBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/12.
 */
public class OrganizationFragment extends BaseFragment {
    @Bind(R.id.organization_recyclerview)
    XRecyclerView mRecyclerView;
    @Bind(R.id.organization_rl)
    RelativeLayout organizationRl;
    private TextView add;
    private RelativeLayout rl_Noadd;
    private LinearLayout organization_ll_circle;
    private OrganizationListBean listBean;
    private ArrayList<OrganizationListBean.DataBean> list;
    private OrganizationXRecyClerViewAdapter recyClerViewAdapter;
    private View v;
    private boolean isupdate=true; //判断是否需要在onResume中刷新数据


    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return v = inflater.inflate(R.layout.organization_layout, null);
    }

    @Override
    protected void initVariables() {
        initView();  //初始化控件
    }

    @Override
    protected void addListener() {

    }

    private void initView() {
        add = (TextView) v.findViewById(R.id.organization_tv_add);
        rl_Noadd = (RelativeLayout) v.findViewById(R.id.organization_rl_noadd);
        organization_ll_circle = (LinearLayout) v.findViewById(R.id.organization_ll_circle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mRecyclerView.setLoadingListener(loadingListener);

        add.setOnClickListener(this);

        list = new ArrayList<OrganizationListBean.DataBean>();
        updata(); //更新数据
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.organization_tv_add:
                startActivity(new Intent(getActivity(), OrganizationAddActivity.class));
                break;
        }
    }

    private class GetOrganizationTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            listBean = HttpRequest.getInstance(getActivity()).SetOrganization(strings[0]);
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
                    if (listBean.getCode() == -1) {//没有数据
                        organization_ll_circle.setVisibility(View.GONE);
                        rl_Noadd.setVisibility(View.VISIBLE);
                    } else if (listBean.getCode() == 0) { //有数据
                        isupdate=false;
                        organization_ll_circle.setVisibility(View.GONE);
                        rl_Noadd.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        list.clear();
                        list.addAll(listBean.getData());
                        recyClerViewAdapter = new OrganizationXRecyClerViewAdapter(getActivity(), list);
                        mRecyclerView.setAdapter(recyClerViewAdapter);
                        //  mRecyclerView.setRefreshing(true);
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
                new GetOrganizationTask().execute(MyApplication.getInstance().getLoginBean().getData().getId());
            } else {
                Utility.showToast("没有获取到您的id", getActivity());
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
                    recyClerViewAdapter.notifyDataSetChanged();
                    mRecyclerView.refreshComplete();
                }

            }, 1000);            //refresh data here
        }

        @Override
        public void onLoadMore() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    recyClerViewAdapter.notifyDataSetChanged();
                    mRecyclerView.loadMoreComplete();
                }
            }, 1000);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        //如果刷新过一次了就不再onResume中进行刷新了
        if(isupdate){
            updata();
        }
    }
}
