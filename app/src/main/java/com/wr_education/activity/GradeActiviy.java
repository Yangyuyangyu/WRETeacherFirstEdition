package com.wr_education.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.adapter.GradeAdapterTwo;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.GradeBean;
import com.wr_education.bean.PublicBean;
import com.wr_education.bean.ScoreItemBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 评分页面
 * Created by Administrator on 2016/4/19.
 */
public class GradeActiviy extends AbstractActivity {
    @Bind(R.id.grade_imgbtn_back)
    ImageButton gradeImgbtnBack;
    @Bind(R.id.grade_tv_title)
    TextView gradeTvTitle;
    @Bind(R.id.grade_recycler)
    RecyclerView gradeRecycler;
    @Bind(R.id.grade_btn_center)
    Button gradeBtnCenter;
    @Bind(R.id.grade_btn_cancel)
    Button gradeBtnCancel;
    private GradeBean bean;
    private ArrayList<GradeBean> list;
    private GradeAdapterTwo adapter;
    private boolean ishas=false;
    private PublicBean publicBean;
    private String courseId;
    private String studentId;
    private String score_id;
    private String title;
    private ScoreItemBean itemBean;

    @Override
    protected void initLayout() {
        setContentView(R.layout.grade_layout);
    }

    @Override
    protected void aadListenter() {
        gradeImgbtnBack.setOnClickListener(this);
        gradeBtnCenter.setOnClickListener(this);
        gradeBtnCancel.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        MyApplication.getInstance().addActivity(this);
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        gradeRecycler.setLayoutManager(layoutManager);

        list=new ArrayList<GradeBean>();

        if(getIntent()!=null){
            courseId=getIntent().getStringExtra("courseId");
            studentId=getIntent().getStringExtra("studentId");
            score_id=getIntent().getStringExtra("score_id");
            title=getIntent().getStringExtra("title");
        }
        if(Utility.NetworkAvailable(GradeActiviy.this)){
            new GetScoreTask().execute(courseId,studentId);
        }else{
            Utility.showToastNoNetWork(GradeActiviy.this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.grade_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
            case R.id.grade_btn_center:
                list=adapter.GetList();
                for (int i=0;i<list.size();i++){
                    if(list.get(i).getCode()==0){
                        ishas=true;
                        break;
                    }
                }
                if(!ishas){
                    String[] id=new String[list.size()];
                    String[] code=new String[list.size()];

                    for (int i=0;i<list.size();i++){
                        id[i]=itemBean.getData().get(i).getId();
                        code[i]=list.get(i).getCode()+"";
                    }
                    String item=Utility.converToString(id); //评分项id，以逗号连接的字符串
                    String score=Utility.converToString(code); //分数，以逗号连接的字符串
                    if(Utility.NetworkAvailable(GradeActiviy.this)){
                        new GetSaveScoreTask().execute(studentId,courseId,item,score);
                    }else{
                        Utility.showToastNoNetWork(GradeActiviy.this);
                    }
                }else{
                    ishas=false;
                    Utility.showToast("还有没有评分的选项哦!",GradeActiviy.this);
                }
                break;
            case R.id.grade_btn_cancel:
                finish();
                saveEditTextAndCloseIMM();
                break;
        }
    }

    //查询打分项
    private class GetScoreTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(GradeActiviy.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            itemBean= HttpRequest.getInstance(GradeActiviy.this).GetScore(strings[0],strings[1]);
            // 请求成功返回数据
            if (itemBean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (itemBean.getCode()==0){
                        DismissDialog();
                        gradeBtnCenter.setVisibility(View.VISIBLE);
                        gradeBtnCancel.setVisibility(View.VISIBLE);
                        for (int i=0;i<itemBean.getData().size();i++){
                            bean=new GradeBean();
                            bean.setName(itemBean.getData().get(i).getName());
                            bean.setCode(Float.valueOf(itemBean.getData().get(i).getScore()));
                            list.add(bean);
                        }
                        gradeTvTitle.setText(title);
                        adapter=new GradeAdapterTwo(GradeActiviy.this,list);
                        gradeRecycler.setAdapter(adapter);
                    }else{
                        DismissDialog();
                        Utility.showToast(itemBean.getMsg(), GradeActiviy.this);

                    }
                } else {
                    Utility.showToast("请求失败，请重试！", GradeActiviy.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }


    //保存评分信息
    private class GetSaveScoreTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(GradeActiviy.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            publicBean= HttpRequest.getInstance(GradeActiviy.this).GetSaveScore(strings[0],strings[1],strings[2],strings[3]);
            // 请求成功返回数据
            if (publicBean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (publicBean.getCode()==0){
                        DismissDialog();
                        Utility.showToast(publicBean.getMsg(), GradeActiviy.this);
                        finish();
                    }else{
                        DismissDialog();
                        Utility.showToast(publicBean.getMsg(), GradeActiviy.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", GradeActiviy.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }
}