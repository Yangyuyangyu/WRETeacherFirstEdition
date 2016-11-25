package com.wr_education.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wr_education.R;
import com.wr_education.adapter.OrganizationAddXRecyClerViewAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.RecommendOrganizationBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;
import com.wr_education.view.ClearEditText;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 机构搜索页面
 * Created by Administrator on 2016/4/14.
 */
public class OrganizationAddActivity extends AbstractActivity {
    @Bind(R.id.organization_rl_jigou)
    RelativeLayout organizationRlJigou;
    @Bind(R.id.organization_et_find)
    ClearEditText organizationEtFind;
    @Bind(R.id.organizationgadd_tv_cencle)
    TextView organizationgaddTvCencle;
    @Bind(R.id.organizationgadd_tv_jigou)
    TextView organizationgaddTvJigou;
    @Bind(R.id.organizationadd_rl_outtitle)
    RelativeLayout organizationaddRlOuttitle;
    @Bind(R.id.organizationadd_ll_recommend)
    LinearLayout organizationgaddLlRecommend;
    @Bind(R.id.organizationadd_recyclerview)
    XRecyclerView mRecyclerView;
    private RecommendOrganizationBean bean;
    private ArrayList<RecommendOrganizationBean.DataBean> list;
    private OrganizationAddXRecyClerViewAdapter recyClerViewAdapter;

    protected View mPopView; // 右上角弹窗
    private LinearLayout popid; //popwindow的布局id
    protected PopupWindow mPopupWindow;
    protected TextView pop_tv_organization, pop_tv_school;
    private boolean isdata = false; //用于判断当前请求是推荐的还是搜索的
    private int page=0;
    private boolean isupordown=false;//判断是上拉还是下拉，true--上拉


    @Override
    protected void initLayout() {
        setContentView(R.layout.organizationadd_layout);
    }

    @Override
    protected void aadListenter() {
        organizationgaddTvCencle.setOnClickListener(this);
        organizationRlJigou.setOnClickListener(this);
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

        list = new ArrayList<RecommendOrganizationBean.DataBean>();
        recyClerViewAdapter=new OrganizationAddXRecyClerViewAdapter(OrganizationAddActivity.this, list);
        mRecyclerView.setAdapter(recyClerViewAdapter);
        initpop();//初始化popwindow

        organizationEtFind.setOnKeyListener(onKeyListener);

        if (Utility.NetworkAvailable(OrganizationAddActivity.this)) {
            new GetFindOrganizationTask().execute("0", "","0");
        } else {
            Utility.showToastNoNetWork(OrganizationAddActivity.this);
        }
    }

    private void initpop() {
        // 弹出选项
        mPopView = LayoutInflater.from(OrganizationAddActivity.this).inflate(
                R.layout.popwindow_layout, null);
        popid = (LinearLayout) mPopView.findViewById(R.id.pop_id);
        mPopupWindow = new PopupWindow(mPopView, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop_tv_organization = (TextView) mPopView.findViewById(R.id.pop_tv_orgnization);
        pop_tv_school = (TextView) mPopView.findViewById(R.id.pop_tv_school);
        pop_tv_organization.setOnClickListener(onClickListener);
        pop_tv_school.setOnClickListener(onClickListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.organizationgadd_tv_cencle:
                finish();
                saveEditTextAndCloseIMM();
                break;
            case R.id.organization_rl_jigou:
                showPopLay();
                break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pop_tv_orgnization:
                    mPopupWindow.dismiss();
                    organizationgaddTvJigou.setText("机构");
                    page=0;
                    isupordown=false;
                    updata("1", page);
                    break;
                case R.id.pop_tv_school:
                    mPopupWindow.dismiss();
                    organizationgaddTvJigou.setText("学校");
                    page=0;
                    isupordown=false;
                    updata("2", page);
                    break;
            }
        }
    };

    /**
     * // * 右上角菜单选项 // * @param context // * @param msg //
     */
    public void showPopLay() {
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#00000000")));
        mPopupWindow.showAsDropDown(organizationaddRlOuttitle, 0, 0);
        mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.update();
    }


    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                //判断输入框有没有值，有值的话按回车就隐藏软键盘
                if (Utility.StringIsNull(organizationEtFind.getText().toString())) {
                      /*隐藏软键盘*/
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputMethodManager.isActive()) {
                        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                } else {
                      /*隐藏软键盘*/
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputMethodManager.isActive()) {
                        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    if (Utility.NetworkAvailable(OrganizationAddActivity.this)) {
                        if (organizationgaddTvJigou.getText().toString().equals("机构")) {
                            isdata = true;
                            new GetFindOrganizationTask().execute("1", organizationEtFind.getText().toString(),"0");
                        } else if (organizationgaddTvJigou.getText().toString().equals("学校")) {
                            isdata = true;
                            new GetFindOrganizationTask().execute("2", organizationEtFind.getText().toString(),"0");
                        }
                    } else {
                        Utility.showToastNoNetWork(OrganizationAddActivity.this);
                    }
                }


            }
            return false;
        }
    };

    XRecyclerView.LoadingListener loadingListener=new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    isupordown=false;
                    page=0;
                    if(organizationgaddTvJigou.getText().equals("机构")){
                        updata("1",0);
                    }else if(organizationgaddTvJigou.getText().equals("学校")){
                        updata("2",0);
                    }
                    recyClerViewAdapter.notifyDataSetChanged();
                    mRecyclerView.refreshComplete();
                }

            }, 1000);
        }

        @Override
        public void onLoadMore() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    isupordown=true;
                    ++page;
                    if(organizationgaddTvJigou.getText().equals("机构")){
                        updata("1",page);
                    }else if(organizationgaddTvJigou.getText().equals("学校")){
                        updata("2",page);
                    }
                    recyClerViewAdapter.notifyDataSetChanged();
                    mRecyclerView.loadMoreComplete();
                }

            }, 1000);
        }
    };

    private void updata(String isschool,int page){
        if (Utility.NetworkAvailable(OrganizationAddActivity.this)) {
            new GetFindOrganizationUpdataTask().execute(isschool, "",""+page);
        } else {
            Utility.showToastNoNetWork(OrganizationAddActivity.this);
        }
    }


    /**
     * 搜索和推荐的
     */
    private class GetFindOrganizationTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(OrganizationAddActivity.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... params) {
            bean = HttpRequest.getInstance(OrganizationAddActivity.this).GetFindDataOrganization(params[0], params[1],params[2]);
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
                        DismissDialog();
                        if (isdata) {
                            organizationgaddLlRecommend.setVisibility(View.GONE);
                        } else {
                            organizationgaddLlRecommend.setVisibility(View.VISIBLE);
                        }
                        list.clear();
                        list.addAll(bean.getData());
                        recyClerViewAdapter.notifyDataSetChanged();
                       // mRecyclerView.setAdapter(recyClerViewAdapter);
                    } else {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), OrganizationAddActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", OrganizationAddActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }


    /**
     * 下拉刷新和上拉加载的
     */
    private class GetFindOrganizationUpdataTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
       //     showWaitLoad(OrganizationAddActivity.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... params) {
            bean = HttpRequest.getInstance(OrganizationAddActivity.this).GetFindDataOrganization(params[0], params[1],params[2]);
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
                    //    DismissDialog();
                        if(isupordown) { //上拉
                            organizationgaddLlRecommend.setVisibility(View.VISIBLE);
                            list.addAll(bean.getData());
                            recyClerViewAdapter.notifyDataSetChanged();
                        }else{
                            organizationgaddLlRecommend.setVisibility(View.VISIBLE);
                            list.clear();
                            list.addAll(bean.getData());
                            recyClerViewAdapter.notifyDataSetChanged();
                        }
                    //    mRecyclerView.setAdapter(recyClerViewAdapter);
                    } else if(bean.getCode()==-1){
                        if(isupordown){ //上拉
                      //      DismissDialog();
                            page--;
                            Utility.showToast("暂无数据", OrganizationAddActivity.this);
                            organizationgaddLlRecommend.setVisibility(View.VISIBLE);
                            //recyClerViewAdapter=new OrganizationAddXRecyClerViewAdapter(OrganizationAddActivity.this, list);
                            recyClerViewAdapter.notifyDataSetChanged();
                        //    mRecyclerView.setAdapter(recyClerViewAdapter);
                        }else{ //下拉
                      //      DismissDialog();
                            Utility.showToast("暂无数据", OrganizationAddActivity.this);
                            organizationgaddLlRecommend.setVisibility(View.VISIBLE);
                            list.clear();
                            //recyClerViewAdapter=new OrganizationAddXRecyClerViewAdapter(OrganizationAddActivity.this, list);
                            recyClerViewAdapter.notifyDataSetChanged();
                        //    mRecyclerView.setAdapter(recyClerViewAdapter);
                        }
                    }else {
                     //   DismissDialog();
                        Utility.showToast(bean.getMsg(), OrganizationAddActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", OrganizationAddActivity.this);
                 //   DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
            //    DismissDialog();
                e.getStackTrace();
            }
        }
    }

}
