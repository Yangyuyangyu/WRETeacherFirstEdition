package com.wr_education.activity;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wr_education.R;
import com.wr_education.adapter.HistoryClassAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.HistoryClassBean;
import com.wr_education.http.HttpPath;
import com.wr_education.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 历史课程页面
 * Created by Administrator on 2016/5/10.
 */
public class HistoryClassActivity extends AbstractActivity implements View.OnClickListener{
    @Bind(R.id.historyclass_imgbtn_back)
    ImageButton historyclassImgbtnBack;
    @Bind(R.id.historyclass_recyclerview)
    XRecyclerView mRecyclerView;

    private StringRequest stringRequest;
    private HistoryClassBean bean;
    private ArrayList<HistoryClassBean.DataBean> list;
    private HistoryClassAdapter adapter;

    @Override
    protected void aadListenter() {
        historyclassImgbtnBack.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        list=new ArrayList<HistoryClassBean.DataBean>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mRecyclerView.setLoadingListener(loadingListener);

        updata();
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.historyclass_layout);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.historyclass_imgbtn_back:
                finish();
                break;
        }
    }

    XRecyclerView.LoadingListener loadingListener=new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    updata();
                    adapter.notifyDataSetChanged();
                    mRecyclerView.refreshComplete();
                }

            }, 1000);
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


    private void updata(){
        if (Utility.NetworkAvailable(HistoryClassActivity.this)) {
            if (MyApplication.getInstance().getLoginBean().getData().getId() != null) {
                showWaitLoad(HistoryClassActivity.this, "获取数据中...");
                String url=HttpPath.finishedClassUrl+"?id="+MyApplication.getInstance().getLoginBean().getData().getId();
                stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Log.i("result",result);
                        Gson gson = new Gson();
                        HistoryClassBean bean = new HistoryClassBean();
                        bean = gson.fromJson(result, bean.getClass());
                        if (bean.getCode() == 0) {
                            DismissDialog();
                            list.clear();
                            list.addAll(bean.getData());
                            adapter=new HistoryClassAdapter(list,HistoryClassActivity.this);
                            mRecyclerView.setAdapter(adapter);
                        } else {
                            DismissDialog();
                            Utility.showToast(bean.getMsg(), HistoryClassActivity.this);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("请求失败：" + volleyError.getMessage());
                        Utility.showToast("请求失败，请重试！", HistoryClassActivity.this);
                        DismissDialog();
                    }
                });
                stringRequest.setTag("volleyget");
                MyApplication.getHttpQueue().add(stringRequest);
            }else{
                Utility.showToast("没有获取到您的id", HistoryClassActivity.this);
            }
        }else{
            Utility.showToastNoNetWork(HistoryClassActivity.this);
        }
    }
}
