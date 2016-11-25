package com.wr_education.activity;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.adapter.MyMessageRecyclerAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.MyMessageListBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 我的消息页面
 * Created by Administrator on 2016/4/14.
 */
public class MyMessageActivity extends AbstractActivity {
    @Bind(R.id.mymessage_imgbtn_back)
    ImageButton mymessageImgbtnBack;
    @Bind(R.id.organization_recyclerview)
    XRecyclerView mRecyclerView;
    private MyMessageListBean bean;
    private ArrayList<MyMessageListBean.DataBean> list;
    private MyMessageRecyclerAdapter adapter;


    @Override
    protected void initLayout() {
        setContentView(R.layout.mymessage_layout);
    }

    @Override
    protected void aadListenter() {
        mymessageImgbtnBack.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mRecyclerView.setLoadingListener(loadingListener);
        list = new ArrayList<MyMessageListBean.DataBean>();
        updata();
        mRecyclerView.setRefreshing(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mymessage_imgbtn_back:
                finish();
                break;
        }
    }

    private class GetMyMessageTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
         //   showWaitLoad(MyMessageActivity.this, "数据加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(MyMessageActivity.this).GetMyMessage(strings[0]);
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
                     //   DismissDialog();
                        list.clear();
                        list.addAll(bean.getData());
                        adapter = new MyMessageRecyclerAdapter(MyMessageActivity.this, list);
                        adapter.setOnItemClickLitener(new MyMessageRecyclerAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
//                                Snackbar.make(view, "点击的是" + position + "个", Snackbar.LENGTH_LONG)
//                                        .setAction("Action", new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//
//                                            }
//                                        }).show();
                            }
                        });
                        mRecyclerView.setAdapter(adapter);
                    } else {
                     //   DismissDialog();
                        Utility.showToast(bean.getMsg(), MyMessageActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", MyMessageActivity.this);
                 //   DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
             //   DismissDialog();
                e.getStackTrace();
            }
        }
    }

    private void updata(){
        if (Utility.NetworkAvailable(MyMessageActivity.this)) {
            if (MyApplication.getInstance().getLoginBean().getData().getId() != null) {
                new GetMyMessageTask().execute(MyApplication.getInstance().getLoginBean().getData().getId());
            } else {
                Utility.showToast("没有获取到您的id", MyMessageActivity.this);
            }
        } else {
            Utility.showToastNoNetWork(MyMessageActivity.this);
        }
    }

    XRecyclerView.LoadingListener loadingListener = new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if(adapter!=null){
                        updata();
                        adapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }
                }

            }, 1000);            //refresh data here
        }

        @Override
        public void onLoadMore() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if(adapter!=null){
                        mRecyclerView.loadMoreComplete();
                        adapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }
            }, 1000);
        }
    };
}
