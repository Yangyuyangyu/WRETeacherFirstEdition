package com.wr_education.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.adapter.StudentGradeAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.PublicBean;
import com.wr_education.bean.StudentGradeBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 评分选择学生页面
 * Created by Administrator on 2016/4/19.
 */
public class StudentGradeActivity extends AbstractActivity {
    @Bind(R.id.studentgrade_imgbtn_back)
    ImageButton studentgradeImgbtnBack;
    @Bind(R.id.studentgrade_tv_title)
    TextView studentgradeTvTitle;
//    @Bind(R.id.studentgrade_btn_center)
//    Button studentgradeBtnCenter;
//    @Bind(R.id.studentgrade_btn_cancel)
//    Button studentgradeBtnCancel;
    @Bind(R.id.studentgrade_recycler)
    RecyclerView studentgradeRecycler;
    private ArrayList<StudentGradeBean.DataBean.StudentBean> list;
    private StudentGradeAdapter adapter;
    private StudentGradeBean studentGradeBean;
    private PublicBean publicBean;

    private String id;
    private Boolean isover=false;//用来判断是否给每个同学都评分了

    @Override
    protected void initLayout() {
        setContentView(R.layout.studentgrade_layout);
    }

    @Override
    protected void aadListenter() {
        studentgradeImgbtnBack.setOnClickListener(this);
//        studentgradeBtnCenter.setOnClickListener(this);
//        studentgradeBtnCancel.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        studentgradeRecycler.setLayoutManager(layoutManager);

        list=new ArrayList<StudentGradeBean.DataBean.StudentBean>();

        if(getIntent()!=null){
            id=getIntent().getStringExtra("id");
        }
        updata();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.studentgrade_imgbtn_back://返回
                finish();
                saveEditTextAndCloseIMM();
                break;
//            case R.id.studentgrade_btn_center://确认
//                if(studentGradeBean.getData().getStudent().size()!=0){
//                    for (int i = 0; i <studentGradeBean.getData().getStudent().size(); i++) {
//                        if(Utility.StringIsNull(studentGradeBean.getData().getStudent().get(i).getScore())){
//                            isover=true;
//                            break;
//                        }
//                    }
//                    if(!isover){
//                        if(Utility.NetworkAvailable(StudentGradeActivity.this)){
//                            new GetScoreFinishTask().execute(studentGradeBean.getData().getCourseInfo().getClass_id());
//                        }else{
//                            Utility.showToastNoNetWork(StudentGradeActivity.this);
//                        }
//                    }else{
//                        Utility.showToast("还有同学没有完成评分",StudentGradeActivity.this);
//                    }
//                }else{
//                    Utility.showToast("没有可以评分的学生",StudentGradeActivity.this);
//                }
//                break;
//            case R.id.studentgrade_btn_cancel://取消
//                finish();
//                saveEditTextAndCloseIMM();
//                break;
        }
    }




    //查询上课学生
    private class GetGradeTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(StudentGradeActivity.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            studentGradeBean= HttpRequest.getInstance(StudentGradeActivity.this).GetStudentOfCourse(strings[0]);
            // 请求成功返回数据
            if (studentGradeBean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (studentGradeBean.getCode()==0){
                        DismissDialog();
                        studentgradeTvTitle.setText(studentGradeBean.getData().getCourseInfo().getName());
                        isover=false;
                        list.clear();
                        list.addAll(studentGradeBean.getData().getStudent());
                        adapter=new StudentGradeAdapter(StudentGradeActivity.this,list,studentGradeBean,studentGradeBean.getData().getCourseInfo().getName());
                        studentgradeRecycler.setAdapter(adapter);
                    }else{
                        DismissDialog();
                        Utility.showToast(studentGradeBean.getMsg(), StudentGradeActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", StudentGradeActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }

    //修改状态
    private class GetScoreFinishTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(StudentGradeActivity.this, "加载中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            publicBean= HttpRequest.getInstance(StudentGradeActivity.this).GetScoreFinish(strings[0]);
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
                        finish();
                        saveEditTextAndCloseIMM();
                    }else{
                        DismissDialog();
                        Utility.showToast(publicBean.getMsg(), StudentGradeActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", StudentGradeActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }

    private void updata(){
        if(Utility.NetworkAvailable(StudentGradeActivity.this)){
            new GetGradeTask().execute(id);
        }else{
            Utility.showToastNoNetWork(StudentGradeActivity.this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updata();
    }
}