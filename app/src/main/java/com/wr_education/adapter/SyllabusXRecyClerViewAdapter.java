package com.wr_education.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.activity.AbsentTeacherActivity;
import com.wr_education.activity.ClassDetailsActivity;
import com.wr_education.activity.LeaveActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.SyllabusBean;
import com.wr_education.bean.SyllabusUpDataBean;
import com.wr_education.dialog.UpdataDialog;
import com.wr_education.util.Utility;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/6.
 */
public class SyllabusXRecyClerViewAdapter extends RecyclerView.Adapter<SyllabusXRecyClerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SyllabusBean.DataBean.CourseBean> list;
    private UpdataDialog dialog;
    private SyllabusUpDataBean bean;
    private MyClickListener mListener;
    private int intenttoday;//传过来的星期几
    private int today; //存放今天是星期几
    private String starttime;//课程开始时间

    public SyllabusXRecyClerViewAdapter(Context context, ArrayList<SyllabusBean.DataBean.CourseBean> list, MyClickListener listener, int intenttoday) {
        this.context = context;
        this.list = list;
        this.mListener = listener;
        this.intenttoday = intenttoday;
        dialog = new UpdataDialog(context, "提交中....");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.syllabuslistadapter_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderView, final int position) {
        holderView.cancel.setTag(position);
        holderView.btn.setTag(position);
        holderView.callnamebtn.setTag(position);
        holderView.gradebtn.setTag(position);
        holderView.check.setVisibility(View.GONE);
        holderView.name.setText(list.get(position).getName());
        MyApplication.getInstance().displayHeadImage(list.get(position).getImg(), holderView.head, R.mipmap.img_default, null);
        holderView.time.setText(list.get(position).getClass_time());
        if (list.get(position).getType().equals("1")) {  //如果是大课
            holderView.bigandsmallname.setText("(大课)");
            if (list.get(position).getLeave().equals("1")) {  //-1-没有请假申请,0-请假核审中，1-已请假,2-申请被拒绝
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("老师已请假");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
                holderView.rl_callname.setVisibility(View.GONE);
                holderView.rl_grade.setVisibility(View.GONE);
            } else if (list.get(position).getLeave().equals("0")) {
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("请假审核中");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
                holderView.rl_callname.setVisibility(View.GONE);
                holderView.rl_grade.setVisibility(View.GONE);
            } else if (list.get(position).getReplace().equals("1")) {
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("老师已代课");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
                holderView.rl_callname.setVisibility(View.GONE);
                holderView.rl_grade.setVisibility(View.GONE);
            } else if (list.get(position).getReplace().equals("0")) {
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("代课审核中");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
                holderView.rl_callname.setVisibility(View.GONE);
                holderView.rl_grade.setVisibility(View.GONE);
            } else {
                if (list.get(position).getStatus().equals("0")) {
                    holderView.btn.setText("签到");
                    try {
                        today = Utility.dayForWeek(list.get(position).getDate());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    if(today==(intenttoday+1)){ //判断是否是今天,intenttoday是从前面传过来的，只传一次就是今天的值
                    starttime = list.get(position).getDate() + " " + list.get(position).getStart();//拼接课程开始时间
                    Long starttimenumber = Utility.getStringToDate(starttime); //将拼接的日期转换成毫秒
                    Long nowtimenumber = Utility.getStringToDate(Utility.getCurrentTimeDate());//现在的时间转换成毫秒
                    if ((starttimenumber - 5400000) < nowtimenumber) { //判断是否是课程开始前90分钟
                        holderView.btn.setEnabled(true);
                        holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_yellow));
                        holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_yellow);
                    } else { //如果没有到上课时间则签到不能点
                        holderView.btn.setEnabled(false);
                        holderView.btn.setTextColor(context.getResources().getColor(R.color.gray_hint));
                        holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_gary);
                    }
//                    }else{
//                        holderView.btn.setEnabled(false);
//                        holderView.btn.setTextColor(context.getResources().getColor(R.color.gray_hint));
//                        holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_gary);
//                    }
                    holderView.rl_classteacher.setVisibility(View.VISIBLE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.rl_callname.setVisibility(View.GONE);
                    holderView.rl_grade.setVisibility(View.GONE);
                } else if (list.get(position).getStatus().equals("1")) {
                    holderView.btn.setText("开始上课");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_orange));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_orange);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                    holderView.rl_callname.setVisibility(View.GONE);
                    holderView.rl_grade.setVisibility(View.GONE);
                } else if (list.get(position).getStatus().equals("2")) {
                    holderView.btn.setText("点名");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_green));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_green);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                    holderView.rl_callname.setVisibility(View.GONE);
                    holderView.rl_grade.setVisibility(View.GONE);
                } else if (list.get(position).getStatus().equals("3")) {
                    //在这里进行判断能不能补点名
                    if (list.get(position).getSecond_call().equals("0")) {
                        holderView.rl_callname.setVisibility(View.GONE);
                    } else if (list.get(position).getSecond_call().equals("1")) {
                        holderView.rl_callname.setVisibility(View.VISIBLE);
                    }
                    if (list.get(position).getReport().equals("-1")) {
                        holderView.check.setVisibility(View.VISIBLE);
                    } else {
                        holderView.check.setVisibility(View.GONE);
                    }
                    holderView.rl_grade.setVisibility(View.VISIBLE);
                    holderView.btn.setText("提交记录");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_blue));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_blue);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                }
//                else if(list.get(position).getStatus().equals("4")){
//                    holderView.btn.setVisibility(View.GONE);
//                    holderView.type.setVisibility(View.VISIBLE);
//                    holderView.type.setText("待学生确认");
//                    holderView.rl_classteacher.setVisibility(View.GONE);
//                    holderView.rl_cancel.setVisibility(View.GONE);
//                    holderView.rl_right.setVisibility(View.VISIBLE);
//                }

                //改了把评分放在课程报告那   4现在是课程结束
//                else if (list.get(position).getStatus().equals("4")) {
//                    holderView.btn.setText("评分");
//                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_pink));
//                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_pink);
//                    holderView.rl_classteacher.setVisibility(View.GONE);
//                    holderView.rl_cancel.setVisibility(View.GONE);
//                    holderView.rl_right.setVisibility(View.VISIBLE);
//                    holderView.type.setVisibility(View.GONE);
//                    holderView.btn.setVisibility(View.VISIBLE);
//                    holderView.rl_callname.setVisibility(View.GONE);
//                }
                else if (list.get(position).getStatus().equals("4")) {
                    if (list.get(position).getReport().equals("1")) {
                        holderView.type.setText("课程已结束");
                    } else if (list.get(position).getReport().equals("0")) {
                        holderView.type.setText("记录审核中");
                    }
                    holderView.type.setVisibility(View.VISIBLE);
                    holderView.btn.setVisibility(View.GONE);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.rl_callname.setVisibility(View.GONE);
                    holderView.rl_grade.setVisibility(View.GONE);
                }
//                else if(list.get(position).getStatus().equals("7")){
//                    holderView.type.setVisibility(View.VISIBLE);
//                    holderView.btn.setVisibility(View.GONE);
//                    holderView.rl_classteacher.setVisibility(View.GONE);
//                    holderView.rl_cancel.setVisibility(View.GONE);
//                    holderView.type.setText("老师已代课");
//                    holderView.rl_right.setVisibility(View.VISIBLE);
//                }
                else {
                    holderView.rl_right.setVisibility(View.GONE);
                }
            }
        } else if (list.get(position).getType().equals("2")) { //如果是小课
            holderView.bigandsmallname.setText("(小课)");
            if (list.get(position).getLeave().equals("1")) {  //0-未请假，1-已请假
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("老师已请假");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
                holderView.rl_callname.setVisibility(View.GONE);
                holderView.rl_grade.setVisibility(View.GONE);
            } else if (list.get(position).getLeave().equals("0")) {
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("请假审核中");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
                holderView.rl_callname.setVisibility(View.GONE);
                holderView.rl_grade.setVisibility(View.GONE);
            } else if (list.get(position).getReplace().equals("1")) {
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("老师已代课");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
                holderView.rl_callname.setVisibility(View.GONE);
                holderView.rl_grade.setVisibility(View.GONE);
            } else if (list.get(position).getReplace().equals("0")) {
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("代课审核中");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
                holderView.rl_callname.setVisibility(View.GONE);
                holderView.rl_grade.setVisibility(View.GONE);
            } else {
                if (list.get(position).getStatus().equals("0")) {
                    holderView.btn.setText("确认预约");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_orange));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_orange);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.VISIBLE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                    holderView.rl_callname.setVisibility(View.GONE);
                    holderView.rl_grade.setVisibility(View.GONE);
                } else if (list.get(position).getStatus().equals("1")) {
                    holderView.btn.setText("签到");
//                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_yellow));
//                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_yellow);
                    try {
                        today = Utility.dayForWeek(list.get(position).getDate());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (today == (intenttoday + 1)) { //判断是否是今天
                        starttime = list.get(position).getDate() + " " + list.get(position).getStart();//拼接课程开始时间
                        Long starttimenumber = Utility.getStringToDate(starttime); //将拼接的日期转换成毫秒
                        Long nowtimenumber = Utility.getStringToDate(Utility.getCurrentTimeDate());//现在的时间转换成毫秒
                        if ((starttimenumber - 5400000) < nowtimenumber) { //判断是否是课程开始前90分钟
                            holderView.btn.setEnabled(true);
                            holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_yellow));
                            holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_yellow);
                        } else {
                            holderView.btn.setEnabled(false);
                            holderView.btn.setTextColor(context.getResources().getColor(R.color.gray_hint));
                            holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_gary);
                        }
                    } else {
                        holderView.btn.setEnabled(false);
                        holderView.btn.setTextColor(context.getResources().getColor(R.color.gray_hint));
                        holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_gary);
                    }
                    holderView.rl_classteacher.setVisibility(View.VISIBLE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                    holderView.rl_callname.setVisibility(View.GONE);
                    holderView.rl_grade.setVisibility(View.GONE);
                } else if (list.get(position).getStatus().equals("2")) {
                    holderView.btn.setText("开始上课");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_orange));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_orange);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                    holderView.rl_callname.setVisibility(View.GONE);
                    holderView.rl_grade.setVisibility(View.GONE);
                } else if (list.get(position).getStatus().equals("3")) {
                    if (list.get(position).getReport().equals("-1")) {
                        holderView.check.setVisibility(View.VISIBLE);
                    } else {
                        holderView.check.setVisibility(View.GONE);
                    }
                    holderView.btn.setText("提交记录");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_blue));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_blue);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                    holderView.rl_callname.setVisibility(View.GONE);
                    holderView.rl_grade.setVisibility(View.VISIBLE);
                }
//                else if(list.get(position).getStatus().equals("4")){
//                    holderView.btn.setVisibility(View.GONE);
//                    holderView.type.setVisibility(View.VISIBLE);
//                    holderView.type.setText("待学生确认");
//                    holderView.rl_classteacher.setVisibility(View.GONE);
//                    holderView.rl_cancel.setVisibility(View.GONE);
//                    holderView.rl_right.setVisibility(View.VISIBLE);
//                }

                //改了把评分放在课程报告那   4现在是课程结束
//                else if (list.get(position).getStatus().equals("4")) {
//                    holderView.btn.setText("评分");
//                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_pink));
//                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_pink);
//                    holderView.rl_classteacher.setVisibility(View.GONE);
//                    holderView.rl_cancel.setVisibility(View.GONE);
//                    holderView.rl_right.setVisibility(View.VISIBLE);
//                    holderView.type.setVisibility(View.GONE);
//                    holderView.btn.setVisibility(View.VISIBLE);
//                    holderView.rl_callname.setVisibility(View.GONE);
//                }
                else if (list.get(position).getStatus().equals("4")) {
                    if (list.get(position).getReport().equals("1")) {
                        holderView.type.setText("课程已结束");
                    } else if (list.get(position).getReport().equals("0")) {
                        holderView.type.setText("记录审核中");
                    }
                    holderView.type.setVisibility(View.VISIBLE);
                    holderView.btn.setVisibility(View.GONE);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.rl_callname.setVisibility(View.GONE);
                    holderView.rl_grade.setVisibility(View.GONE);
                }
//                else if(list.get(position).getStatus().equals("7")){
//                    holderView.type.setVisibility(View.VISIBLE);
//                    holderView.btn.setVisibility(View.GONE);
//                    holderView.rl_classteacher.setVisibility(View.GONE);
//                    holderView.rl_cancel.setVisibility(View.GONE);
//                    holderView.type.setText("老师已代课");
//                    holderView.rl_right.setVisibility(View.VISIBLE);
//                }
                else {
                    holderView.rl_right.setVisibility(View.GONE);
                }
            }

        }
        holderView.cancel.setOnClickListener(mListener);
        holderView.btn.setOnClickListener(mListener);
        holderView.callnamebtn.setOnClickListener(mListener);
        holderView.gradebtn.setOnClickListener(mListener);

        //请假点击事件
        holderView.leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leaveintent = new Intent(context, LeaveActivity.class);
                leaveintent.putExtra("id", list.get(position).getId());
                context.startActivity(leaveintent);
            }
        });

        //代课点击事件
        holderView.classteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent absentintent = new Intent(context, AbsentTeacherActivity.class);
                absentintent.putExtra("id", list.get(position).getId());
                context.startActivity(absentintent);
            }
        });

        holderView.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsintent = new Intent(context, ClassDetailsActivity.class);
                detailsintent.putExtra("id", list.get(position).getCid());
                context.startActivity(detailsintent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView head;
        public ImageView check;
        public TextView name;
        public TextView time;
        public TextView bigandsmallname;
        public Button btn;
        public Button callnamebtn;
        public Button gradebtn;
        public TextView classteacher;
        public TextView leave;
        public TextView cancel;
        public TextView type;
        public RelativeLayout rl_classteacher;
        public RelativeLayout rl_cancel;
        public RelativeLayout rl_item;
        public RelativeLayout rl_right;
        public RelativeLayout rl_callname;
        public RelativeLayout rl_grade;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.syllabuslist_tv_name);
            bigandsmallname = (TextView) view.findViewById(R.id.syllabuslist_tv_bigandsmallname);
            time = (TextView) view.findViewById(R.id.syllabuslist_tv_time);
            leave = (TextView) view.findViewById(R.id.syllabuslist_tv_leave);
            type = (TextView) view.findViewById(R.id.syllabuslist_tv_type);
            classteacher = (TextView) view.findViewById(R.id.syllabuslist_tv_classteacher);
            cancel = (TextView) view.findViewById(R.id.syllabuslist_tv_cancel);
            head = (ImageView) view.findViewById(R.id.syllabuslist_img_head);
            check = (ImageView) view.findViewById(R.id.syllabuslist_img_check);
            btn = (Button) view.findViewById(R.id.syllabuslist_btn);
            callnamebtn = (Button) view.findViewById(R.id.syllabuslist_btn_callname);
            gradebtn = (Button) view.findViewById(R.id.syllabuslist_btn_grade);
            rl_classteacher = (RelativeLayout) view.findViewById(R.id.syllabuslist_rl_leave);
            rl_cancel = (RelativeLayout) view.findViewById(R.id.syllabuslist_rl_cancel);
            rl_item = (RelativeLayout) view.findViewById(R.id.syllabuslist_rl_item);
            rl_right = (RelativeLayout) view.findViewById(R.id.syllabuslist_rl_right);
            rl_callname = (RelativeLayout) view.findViewById(R.id.syllabuslist_rl_callname);
            rl_grade = (RelativeLayout) view.findViewById(R.id.syllabuslist_rl_grade);
        }
    }

    /**
     * 用于回调的抽象类
     */
    public static abstract class MyClickListener implements View.OnClickListener {
        /**
         * 基类的onClick方法
         */
        @Override
        public void onClick(View v) {
            myOnClick((Integer) v.getTag(), v);
        }

        public abstract void myOnClick(int position, View v);
    }
}
