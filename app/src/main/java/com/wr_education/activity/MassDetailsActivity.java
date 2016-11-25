package com.wr_education.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.adapter.MassDetailsListAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.MassDetailsBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;
import com.wr_education.view.MyListView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 社团详情页
 * Created by Administrator on 2016/4/14.
 */
public class MassDetailsActivity extends AbstractActivity {
    @Bind(R.id.massdetails_imgbtn_back)
    ImageButton massdetailsImgbtnBack;
    @Bind(R.id.massdetails_tv_name)
    TextView massdetailsTvName;
    @Bind(R.id.massdetails_tv_teacher)
    TextView massdetailsTvTeacher;
    @Bind(R.id.massdetails_tv_details)
    TextView massdetailsTvDetails;
    @Bind(R.id.massdetails_tv_nodata)
    TextView massdetailsTvNoData;
    @Bind(R.id.massdetails_tv_organization)
    TextView massdetailsTvOrganization;
    @Bind(R.id.massdetails_list_activity)
    MyListView massdetailsList;
    @Bind(R.id.massdetails_rl_regime)
    RelativeLayout massdetailsRlRegime;
    @Bind(R.id.massdetails_rl_Build)
    RelativeLayout massdetailsRlBuild;
    @Bind(R.id.massdetails_rl_plan)
    RelativeLayout massdetailsRlPlan;
    private ArrayList<MassDetailsBean.DataBean.NewsBean> list;
    private MassDetailsListAdapter adapter;
    private String id;

    private MassDetailsBean bean;


    @Override
    protected void initLayout() {
        setContentView(R.layout.massdetails_layout);
    }
    @Override
    protected void aadListenter() {
        massdetailsImgbtnBack.setOnClickListener(this);
        massdetailsRlRegime.setOnClickListener(this);
        massdetailsRlPlan.setOnClickListener(this);
        massdetailsRlBuild.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        list=new ArrayList<MassDetailsBean.DataBean.NewsBean>();

//        for(int i=0;i<2;i++){
//            listBean=new MassDetailsListBean();
//            listBean.setName("活动报名");
//            listBean.setContent("成都市管乐团比赛报名");
//            list.add(listBean);
//        }

        if(getIntent()!=null){
            id=getIntent().getStringExtra("id");
        }
        Updata();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.massdetails_imgbtn_back: //返回
                finish();
                saveEditTextAndCloseIMM();
                break;
            case R.id.massdetails_rl_regime://社团管理制度
                Intent regimeintent=new Intent(MassDetailsActivity.this,MassRegimetActivity.class);
                regimeintent.putExtra("id",bean.getData().getId());
                startActivity(regimeintent);
                break;
            case R.id.massdetails_rl_plan://课程规划
                Intent planintent=new Intent(MassDetailsActivity.this,CoursePlanActivity.class);
                planintent.putExtra("id", bean.getData().getId());
                startActivity(planintent);
                break;
            case R.id.massdetails_rl_Build://社团建设表
                Intent buildintent=new Intent(MassDetailsActivity.this,MassBuidActivity.class);
                buildintent.putExtra("id", bean.getData().getId());
                startActivity(buildintent);
                break;
        }
    }

    private class GetMassDetailsTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(MassDetailsActivity.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean= HttpRequest.getInstance(MassDetailsActivity.this).GetMassDetail(strings[0]);
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
                    if (bean.getCode()==0){
                        DismissDialog();
                        //设置名字
                        massdetailsTvName.setText(bean.getData().getName());
                        //设置管理员
                        massdetailsTvTeacher.setText(bean.getData().getAdmins());
                        //设置简介
                        massdetailsTvDetails.setText(bean.getData().getBrief());
                        //设置所属机构
                        massdetailsTvOrganization.setText(bean.getData().getAgency_name());
                        list.clear();
                        if(bean.getData().getNews().size()!=0){
                            massdetailsTvNoData.setVisibility(View.GONE);
                            massdetailsList.setVisibility(View.VISIBLE);
                            list.addAll(bean.getData().getNews());
                            adapter=new MassDetailsListAdapter(MassDetailsActivity.this,list);
                            massdetailsList.setAdapter(adapter);
                        }else{
                            massdetailsList.setVisibility(View.GONE);
                            massdetailsTvNoData.setVisibility(View.VISIBLE);
                        }
                    }else{
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), MassDetailsActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", MassDetailsActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }

    private void Updata(){
        if(Utility.NetworkAvailable(MassDetailsActivity.this)){
            if(MyApplication.getInstance().getLoginBean().getData().getId()!=null){
                new GetMassDetailsTask().execute(id);
            }else{
                Utility.showToast("没有获取到您的id",MassDetailsActivity.this);
            }
        }else{
            Utility.showToastNoNetWork(MassDetailsActivity.this);
        }
    }
}
