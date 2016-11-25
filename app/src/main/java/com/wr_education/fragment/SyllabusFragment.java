package com.wr_education.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.activity.CallNameActivity;
import com.wr_education.activity.CommitReportActivity;
import com.wr_education.activity.SignInActivity;
import com.wr_education.activity.StudentGradeActivity;
import com.wr_education.adapter.SyllabusXRecyClerViewAdapter;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.SyllabusBean;
import com.wr_education.bean.SyllabusUpDataBean;
import com.wr_education.dialog.UpdataDialog;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 课程表页面
 * Created by Administrator on 2016/4/12.
 */
public class SyllabusFragment extends Fragment implements View.OnClickListener {
    private Button[] mTabs;
    private ArrayList<SyllabusBean.DataBean.CourseBean> list;
    private View v;
    private int currentTabIndex = 0;
    private int index; //判断当前是哪个fragment显示
    private LinearLayout circle;//转菊花
    private SyllabusBean bean;
    private int myposition;
    private UpdataDialog dialog;
    private SyllabusUpDataBean upDataBean;
    private XRecyclerView mRecyclerView;
    private TextView up; //上一页
    private TextView down; //下一页
    private TextView title; //标题
    private SyllabusXRecyClerViewAdapter recyClerViewAdapter;
    private RelativeLayout syllabus_rl;
    private Timer timer; //计时器
    private long newtime;
    private int today;
    private int week = 0; //本周传0，上一周减一，下一周加一

    // 定义Handler
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isrun = true;
//            Log.d("debug", "handleMessage方法所在的线程："
//                    + Thread.currentThread().getName());
            if (list.size() != 0) {
                long[] Time = new long[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    Time[i] = Utility.getStringToDate(list.get(i).getDate() + " " + list.get(i).getStart());
                }
                if ((long) msg.obj > newtime) {//进页面取一次时间，开线程，持续12个小时
                    // 结束Timer计时器
                    timer.cancel();
                }
                for (int i = 0; i < Time.length; i++) {
                    if ((long) msg.obj > (Time[i] - 5400000) && (long) msg.obj < (Time[i] - 5395000)) {
                        recyClerViewAdapter.notifyDataSetChanged();
                    }
//                    Log.d("whm", "当前时间是:" + msg.obj + "  课程时间是:" + Time[i]);
                }

            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.syllabus_layout, null);
        initView();  //初始化控件
        return v;
    }

    private void initView() {
        up = (TextView) v.findViewById(R.id.syllabus_tv_up);
        down = (TextView) v.findViewById(R.id.syllabus_tv_down);
        title = (TextView) v.findViewById(R.id.syllabus_tv_title);
        circle = (LinearLayout) v.findViewById(R.id.syllabus_ll_circle);
        //用于设置透明状态栏
        syllabus_rl = (RelativeLayout) v.findViewById(R.id.syllabus_rl);

        dialog = new UpdataDialog(getActivity(), "提交中....");
        newtime = Utility.getStringToDate(Utility.getCurrentTimeDate()) + 43200000l;
        mTabs = new Button[7];
        mTabs[0] = (Button) v.findViewById(R.id.syllabus_btn_one);
        mTabs[1] = (Button) v.findViewById(R.id.syllabus_btn_two);
        mTabs[2] = (Button) v.findViewById(R.id.syllabus_btn_three);
        mTabs[3] = (Button) v.findViewById(R.id.syllabus_btn_four);
        mTabs[4] = (Button) v.findViewById(R.id.syllabus_btn_five);
        mTabs[5] = (Button) v.findViewById(R.id.syllabus_btn_six);
        mTabs[6] = (Button) v.findViewById(R.id.syllabus_btn_seven);
        switch (getWeekOfDate()) {
            case "1"://星期天
                mTabs[6].setSelected(true);
                index = 6;
                currentTabIndex = 6;
                break;
            case "2"://星期一
                mTabs[0].setSelected(true);
                index = 0;
                currentTabIndex = 0;
                break;
            case "3"://星期二
                mTabs[1].setSelected(true);
                index = 1;
                currentTabIndex = 1;
                break;
            case "4"://星期三
                mTabs[2].setSelected(true);
                index = 2;
                currentTabIndex = 2;
                break;
            case "5"://星期四
                mTabs[3].setSelected(true);
                index = 3;
                currentTabIndex = 3;
                break;
            case "6"://星期五
                mTabs[4].setSelected(true);
                index = 4;
                currentTabIndex = 4;
                break;
            case "7"://星期六
                mTabs[5].setSelected(true);
                index = 5;
                currentTabIndex = 5;
                break;

        }
        today = index;
        mRecyclerView = (XRecyclerView) v.findViewById(R.id.syllabus_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mRecyclerView.setLoadingListener(loadingListener);

        list = new ArrayList<SyllabusBean.DataBean.CourseBean>();

        updata();

        mTabs[0].setOnClickListener(this);
        mTabs[1].setOnClickListener(this);
        mTabs[2].setOnClickListener(this);
        mTabs[3].setOnClickListener(this);
        mTabs[4].setOnClickListener(this);
        mTabs[5].setOnClickListener(this);
        mTabs[6].setOnClickListener(this);
        up.setOnClickListener(onClickListener);
        down.setOnClickListener(onClickListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.syllabus_btn_one:
                index = 0;
                break;
            case R.id.syllabus_btn_two:
                index = 1;
                break;
            case R.id.syllabus_btn_three:
                index = 2;
                break;
            case R.id.syllabus_btn_four:
                index = 3;
                break;
            case R.id.syllabus_btn_five:
                index = 4;
                break;
            case R.id.syllabus_btn_six:
                index = 5;
                break;
            case R.id.syllabus_btn_seven:
                index = 6;
                break;
        }
        list.clear();
        if (bean.getData() != null) {
            list.addAll(bean.getData().get(index).getCourse());
        }
        //    listAdapter.notifyDataSetChanged(); //刷新列表
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
        if (list.size() != 0) {
            recyClerViewAdapter.notifyDataSetChanged();
        }

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.syllabus_tv_up: //上一页
                    week = week - 1;
                    if (week > -4 && week < 4) {
                        updata();
                    } else {
                        week = week + 1;
                        Utility.showToast("只能查看前三周的课程和后三周的课程", getActivity());
                    }
                    break;
                case R.id.syllabus_tv_down: //下一页
                    week = week + 1;
                    if (week > -4 && week < 4) {
                        updata();
                    } else {
                        week = week - 1;
                        Utility.showToast("只能查看前三周的课程和后三周的课程", getActivity());
                    }
                    break;
            }

            switch (week) {
                case 0:
                    title.setText("课程表(本周)");
                    //      Utility.showToast("当前查看的是本周课程表",getActivity());
                    break;
                case -1:
                    title.setText("课程表(前一周)");
                    //     Utility.showToast("当前查看的是前一周",getActivity());
                    break;
                case -2:
                    title.setText("课程表(前二周)");
                    //      Utility.showToast("当前查看的是前二周",getActivity());
                    break;
                case -3:
                    title.setText("课程表(前三周)");
                    //      Utility.showToast("当前查看的是前三周",getActivity());
                    break;
                case 1:
                    title.setText("课程表(后一周)");
                    //       Utility.showToast("当前查看的是后一周",getActivity());
                    break;
                case 2:
                    title.setText("课程表(后二周)");
                    //       Utility.showToast("当前查看的是后二周",getActivity());
                    break;
                case 3:
                    title.setText("课程表(后三周)");
                    //       Utility.showToast("当前查看的是后三周",getActivity());
                    break;
            }
        }
    };

    XRecyclerView.LoadingListener loadingListener = new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    updata();
                    recyClerViewAdapter.notifyDataSetChanged();
                    mRecyclerView.refreshComplete();
                }

            }, 1000);
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

    private class GetSyllabusTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(getActivity()).GetSyllabus(strings[0], strings[1]);
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
                        circle.setVisibility(View.GONE);
                        list.clear();
                        list.addAll(bean.getData().get(index).getCourse());
                        //   listAdapter = new SyllabusListAdapter(getActivity(), list, mListener);
                        recyClerViewAdapter = new SyllabusXRecyClerViewAdapter(getActivity(), list, mListener, today);
                        mRecyclerView.setAdapter(recyClerViewAdapter);
                        initTimer(); //计时器
                        //    xListView.setAdapter(listAdapter);

                    } else {
                        circle.setVisibility(View.GONE);
                        list.clear();
                        recyClerViewAdapter.notifyDataSetChanged();
                        Utility.showToast(bean.getMsg(), getActivity());
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", getActivity());
                    circle.setVisibility(View.GONE);
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
                new GetSyllabusTask().execute(MyApplication.getInstance().getLoginBean().getData().getId(), week + "");
            } else {
                Utility.showToast("没有获取到机构id", getActivity());
            }
        } else {
            Utility.showToastNoNetWork(getActivity());
        }
    }

    public static String getWeekOfDate() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        //获取当前是星期几
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        return mWay;

    }

    @Override
    public void onResume() {
        super.onResume();
        updata();
        initTimer();
    }


    /**
     * 实现类，响应按钮点击事件
     */
    private SyllabusXRecyClerViewAdapter.MyClickListener mListener = new SyllabusXRecyClerViewAdapter.MyClickListener() {
        @Override
        public void myOnClick(int position, View v) {
            myposition = position;
            if (list.get(position).getType().equals("1")) { //如果是大课
                if (v.getId() == R.id.syllabuslist_btn_callname) {  //如果点击的是点名
                    Intent callintent = new Intent(getActivity(), CallNameActivity.class);
                    callintent.putExtra("id", list.get(position).getId());
                    callintent.putExtra("second_call", list.get(position).getSecond_call());
                    getActivity().startActivity(callintent);
                } else if (v.getId() == R.id.syllabuslist_btn_grade) {//如果点击的是评分
                    Intent gradeintent = new Intent(getActivity(), StudentGradeActivity.class);
                    gradeintent.putExtra("id", list.get(position).getId());
                    getActivity().startActivity(gradeintent);
                } else {
                    switch (list.get(position).getStatus()) {
                        case "0": //签到
                            Intent signintent = new Intent(getActivity(), SignInActivity.class);
                            signintent.putExtra("id", list.get(position).getId());
                            getActivity().startActivity(signintent);
//                        if (Utility.NetworkAvailable(getActivity())) {
//                            new SetUpdataTypeTask().execute("1", list.get(position).getId());
//                        } else {
//                            Utility.showToastNoNetWork(getActivity());
//                        }
                            break;
                        case "1"://开始上课
                            if (Utility.NetworkAvailable(getActivity())) {
                                new SetUpdataTypeTask().execute("2", list.get(position).getId());
                            } else {
                                Utility.showToastNoNetWork(getActivity());
                            }
                            break;
                        case "2"://点名
                            Intent callintent = new Intent(getActivity(), CallNameActivity.class);
                            callintent.putExtra("id", list.get(position).getId());
                            callintent.putExtra("second_call", list.get(position).getSecond_call());
                            getActivity().startActivity(callintent);
                            break;
                        case "3"://提交报告
                            Intent commitintent = new Intent(getActivity(), CommitReportActivity.class);
                            commitintent.putExtra("id", list.get(position).getId());
                            commitintent.putExtra("name", list.get(position).getName());
                            commitintent.putExtra("time", list.get(position).getClass_time());
                            getActivity().startActivity(commitintent);
                            break;
//                        case "4": //评分
//                            Intent gradeintent=new Intent(getActivity(),StudentGradeActivity.class);
//                            gradeintent.putExtra("id", list.get(position).getId());
//                            getActivity().startActivity(gradeintent);
//                            break;
                    }
                }
            } else if (list.get(position).getType().equals("2")) { //如果是小课
                if (v.getId() == R.id.syllabuslist_tv_cancel) {
                    if (Utility.NetworkAvailable(getActivity())) {
                        new SetUpdataTypeTwoTask().execute("-1", list.get(position).getId());
                    } else {
                        Utility.showToastNoNetWork(getActivity());
                    }
                } else if (v.getId() == R.id.syllabuslist_btn_grade) {//如果点击的是评分
                    Intent gradeintent = new Intent(getActivity(), StudentGradeActivity.class);
                    gradeintent.putExtra("id", list.get(position).getId());
                    getActivity().startActivity(gradeintent);
                } else {
                    switch (list.get(position).getStatus()) {
                        case "0": //确认预约
                            if (Utility.NetworkAvailable(getActivity())) {
                                new SetUpdataTypeTask().execute("1", list.get(position).getId());
                            } else {
                                Utility.showToastNoNetWork(getActivity());
                            }
                            break;
                        case "1"://签到
                            Intent signintent = new Intent(getActivity(), SignInActivity.class);
                            signintent.putExtra("id", list.get(position).getId());
                            getActivity().startActivity(signintent);
//                            if (Utility.NetworkAvailable(getActivity())) {
//                                new SetUpdataTypeTask().execute("2", list.get(position).getId());
//                            } else {
//                                Utility.showToastNoNetWork(getActivity());
//                            }
                            break;
                        case "2"://开始上课
                            if (Utility.NetworkAvailable(getActivity())) {
                                new SetUpdataTypeTask().execute("3", list.get(position).getId());
                            } else {
                                Utility.showToastNoNetWork(getActivity());
                            }
                            break;
                        case "3"://提交报告
                            Intent commitintent = new Intent(getActivity(), CommitReportActivity.class);
                            commitintent.putExtra("id", list.get(position).getId());
                            commitintent.putExtra("name", list.get(position).getName());
                            commitintent.putExtra("time", list.get(position).getClass_time());
                            getActivity().startActivity(commitintent);
                            break;
//                        case "4": //评分
//                            Intent gradeintent=new Intent(getActivity(),StudentGradeActivity.class);
//                            gradeintent.putExtra("id", list.get(position).getId());
//                            getActivity().startActivity(gradeintent);
//                            break;
                    }
                }
            }
        }
    };

    private class SetUpdataTypeTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.showDialog();
        }

        @Override
        protected String doInBackground(String... strings) {
            upDataBean = HttpRequest.getInstance(getActivity()).GetCourseState(strings[0], strings[1]);
            // 请求成功返回数据
            if (upDataBean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {

                    if (upDataBean.getCode() == 0) { //有数据
                        dialog.dim();
                        list.get(myposition).setStatus(upDataBean.getData());
                        //     listAdapter.notifyDataSetChanged();
                        recyClerViewAdapter.notifyDataSetChanged();
                    } else {
                        dialog.dim();
                        Utility.showToast(bean.getMsg(), getActivity());
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", getActivity());
                    dialog.dim();
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.getStackTrace();
                dialog.dim();
            }
        }
    }

    //取消预约
    private class SetUpdataTypeTwoTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.showDialog();
        }

        @Override
        protected String doInBackground(String... strings) {
            upDataBean = HttpRequest.getInstance(getActivity()).GetCourseState(strings[0], strings[1]);
            // 请求成功返回数据
            if (upDataBean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (upDataBean.getCode() == 0) { //有数据
                        dialog.dim();
                        updata(); //直接重新拉一次列表
                        //  list.remove(myposition);
                        //  list.get(myposition).setStatus("-1");//将状态给改变一下。。
                        //   listAdapter.notifyDataSetChanged();
                        recyClerViewAdapter.notifyDataSetChanged();
                    } else {
                        dialog.dim();
                        Utility.showToast(bean.getMsg(), getActivity());
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", getActivity());
                    dialog.dim();
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.getStackTrace();
                dialog.dim();
            }
        }
    }

    private boolean isrun = false;  //判断定时器是否开启

    public void initTimer() {
        if (!isrun) {
            // 按钮按下时创建一个Timer定时器
            timer = new Timer();
            // 创建一个TimerTask
            // TimerTask是个抽象类,实现了Runnable接口，所以TimerTask就是一个子线程
            TimerTask timerTask = new TimerTask() {
                // 开始时间
                long i = Utility.getStringToDate(Utility.getCurrentTimeDate());

                @Override
                public void run() {
//                    Log.d("debug", "run方法所在的线程："
//                            + Thread.currentThread().getName());
                    // 定义一个消息传过去
                    Message msg = new Message();
                    i = i + 1000;
                    msg.obj = i;
                    handler.sendMessage(msg);
                }
            };
            // 定义计划任务，根据参数的不同可以完成以下种类的工作：
            // １．schedule(TimerTask task, Date when)　ー＞　在固定时间执行某任务
            // ２．schedule(TimerTask task, Date when, long period)　ー＞　在固定时间开始重复执行某任务，重复时间间隔可控
            // ３．schedule(TimerTask task, long delay)　ー＞　在延迟多久后执行某任务
            // ４．schedule(TimerTask task, long delay, long period)　ー＞　在延迟多久后重复执行某任务，重复时间间隔可控
            timer.schedule(timerTask, 0, 1000);// 0秒后开始计时，间隔为1秒
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
        isrun = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        isrun = false;
    }
}
