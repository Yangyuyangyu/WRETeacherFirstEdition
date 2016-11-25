package com.wr_education.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.adapter.HomePageTwoXRecyClerViewAdapter;
import com.wr_education.app.BaseFragment;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.HomePageTwoListBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/12.
 */
public class HomePageTwoFragment extends BaseFragment{

//    @Bind(R.id.homepagetwo_rl_class_history)
//    RelativeLayout homepagetwoRlClassHistory;
    @Bind(R.id.homepagetwo_recyclerview)
    XRecyclerView mRecyclerView;
    private View v;
    private HomePageTwoListBean bean;
    private ArrayList<HomePageTwoListBean.DataBean> list;
    private HomePageTwoXRecyClerViewAdapter clerViewAdapter;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return v = inflater.inflate(R.layout.viewpager_homepage_two, null);
    }

    @Override
    protected void initVariables() {
        init();
    }

    @Override
    protected void addListener() {
        mRecyclerView.setLoadingListener(loadingListener);
    //    homepagetwoRlClassHistory.setOnClickListener(this);
    }

    private void init() {
        list = new ArrayList<HomePageTwoListBean.DataBean>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);

//        View header =   LayoutInflater.from(getActivity()).inflate(R.layout.homepage_two_head_layout, (ViewGroup) v.findViewById(android.R.id.content),false);
//        mRecyclerView.addHeaderView(header);
        updata();

    }

    private class GetHomeCourseTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(getActivity()).GetHomeCourse(strings[0]);
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
                        list.clear();
                        list.addAll(bean.getData());
                        clerViewAdapter=new HomePageTwoXRecyClerViewAdapter(list,getActivity());
                        mRecyclerView.setAdapter(clerViewAdapter);
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
                new GetHomeCourseTask().execute(MyApplication.getInstance().getLoginBean().getData().getId());
            } else {
                Utility.showToast("没有获取到您的id", getActivity());
            }
        } else {
            Utility.showToastNoNetWork(getActivity());
        }
    }

    XRecyclerView.LoadingListener loadingListener=new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    updata();
                    clerViewAdapter.notifyDataSetChanged();
                    mRecyclerView.refreshComplete();
                }

            }, 1000);            //refresh data here
        }

        @Override
        public void onLoadMore() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    clerViewAdapter.notifyDataSetChanged();
                    mRecyclerView.loadMoreComplete();
                }

            }, 1000);
        }
    };

//    @Override
//    public void onClick(View v) {
//        super.onClick(v);
//        switch (v.getId()){
//            case R.id.homepagetwo_rl_class_history://查看所有
//                getActivity().startActivity(new Intent(getActivity(), HistoryClassActivity.class));
//                break;
//        }
//    }
}
