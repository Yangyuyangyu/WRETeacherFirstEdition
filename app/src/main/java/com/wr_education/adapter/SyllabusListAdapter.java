package com.wr_education.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/13.
 */
public class SyllabusListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SyllabusBean.DataBean.CourseBean> list;
    private UpdataDialog dialog;
    private SyllabusUpDataBean bean;
    private MyClickListener mListener;

    public SyllabusListAdapter(Context context, ArrayList<SyllabusBean.DataBean.CourseBean> list, MyClickListener listener) {
        this.context = context;
        this.list = list;
        this.mListener = listener;
        dialog = new UpdataDialog(context, "提交中....");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.syllabuslistadapter_layout, null);
            holderView = new HolderView();
            holderView.name = (TextView) convertView.findViewById(R.id.syllabuslist_tv_name);
            holderView.bigandsmallname = (TextView) convertView.findViewById(R.id.syllabuslist_tv_bigandsmallname);
            holderView.time = (TextView) convertView.findViewById(R.id.syllabuslist_tv_time);
            holderView.leave = (TextView) convertView.findViewById(R.id.syllabuslist_tv_leave);
            holderView.type = (TextView) convertView.findViewById(R.id.syllabuslist_tv_type);
            holderView.classteacher = (TextView) convertView.findViewById(R.id.syllabuslist_tv_classteacher);
            holderView.cancel = (TextView) convertView.findViewById(R.id.syllabuslist_tv_cancel);
            holderView.head = (ImageView) convertView.findViewById(R.id.syllabuslist_img_head);
            holderView.btn = (Button) convertView.findViewById(R.id.syllabuslist_btn);
            holderView.rl_classteacher = (RelativeLayout) convertView.findViewById(R.id.syllabuslist_rl_leave);
            holderView.rl_cancel = (RelativeLayout) convertView.findViewById(R.id.syllabuslist_rl_cancel);
            holderView.rl_item = (RelativeLayout) convertView.findViewById(R.id.syllabuslist_rl_item);
            holderView.rl_right=(RelativeLayout)convertView.findViewById(R.id.syllabuslist_rl_right);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        holderView.cancel.setTag(position);
        holderView.btn.setTag(position);
        holderView.name.setText(list.get(position).getName());
        MyApplication.getInstance().displayHeadImage(list.get(position).getImg(),holderView.head,R.mipmap.ceshi_img1,null);
        holderView.time.setText(list.get(position).getClass_time());
        if (list.get(position).getType().equals("1")) {  //如果是大课
            holderView.bigandsmallname.setText("(大课)");
            if(list.get(position).getLeave().equals("1")){  //-1-没有请假申请,0-请假核审中，1-已请假,2-申请被拒绝
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("老师已请假");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
            }else if(list.get(position).getLeave().equals("0")){
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("请假审核中");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
            }else if(list.get(position).getReplace().equals("1")){
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("老师已代课");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
            }else if(list.get(position).getReplace().equals("0")){
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("代课审核中");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
            }else{
                if (list.get(position).getStatus().equals("0")) {
                    holderView.btn.setText("签到");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_yellow));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_yellow);
                    holderView.rl_classteacher.setVisibility(View.VISIBLE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                } else if (list.get(position).getStatus().equals("1")) {
                    holderView.btn.setText("开始上课");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_orange));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_orange);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                } else if (list.get(position).getStatus().equals("2")) {
                    holderView.btn.setText("点名");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_green));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_green);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                } else if (list.get(position).getStatus().equals("3")) {
                    holderView.btn.setText("提交报告");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_blue));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_blue);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                } else if(list.get(position).getStatus().equals("4")){
                    holderView.btn.setVisibility(View.GONE);
                    holderView.type.setVisibility(View.VISIBLE);
                    holderView.type.setText("待学生确认");
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                }else if (list.get(position).getStatus().equals("5")) {
                    holderView.btn.setText("评分");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_pink));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_pink);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                }else if(list.get(position).getStatus().equals("6")){
                    holderView.type.setVisibility(View.VISIBLE);
                    holderView.btn.setVisibility(View.GONE);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.type.setText("课程已结束");
                    holderView.rl_right.setVisibility(View.VISIBLE);
                }else if(list.get(position).getStatus().equals("7")){
                    holderView.type.setVisibility(View.VISIBLE);
                    holderView.btn.setVisibility(View.GONE);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.type.setText("老师已代课");
                    holderView.rl_right.setVisibility(View.VISIBLE);
                }else{
                    holderView.rl_right.setVisibility(View.GONE);
                }
            }
        } else if (list.get(position).getType().equals("2")) { //如果是小课
            holderView.bigandsmallname.setText("(小课)");
            if(list.get(position).getLeave().equals("1")){  //0-未请假，1-已请假
                holderView.btn.setVisibility(View.GONE);
                holderView.type.setVisibility(View.VISIBLE);
                holderView.type.setText("老师已请假");
                holderView.rl_classteacher.setVisibility(View.GONE);
                holderView.rl_cancel.setVisibility(View.GONE);
                holderView.rl_right.setVisibility(View.VISIBLE);
            }else{
                if (list.get(position).getStatus().equals("0")) {
                    holderView.btn.setText("确认预约");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_orange));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_orange);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.VISIBLE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                } else if (list.get(position).getStatus().equals("1")) {
                    holderView.btn.setText("签到");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_yellow));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_yellow);
                    holderView.rl_classteacher.setVisibility(View.VISIBLE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                } else if (list.get(position).getStatus().equals("2")) {
                    holderView.btn.setText("开始上课");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_orange));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_orange);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                } else if (list.get(position).getStatus().equals("3")) {
                    holderView.btn.setText("提交报告");
                    holderView.btn.setTextColor(context.getResources().getColor(R.color.syllabus_btn_bg_blue));
                    holderView.btn.setBackgroundResource(R.drawable.shape_syllabus_btn_bg_blue);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                    holderView.type.setVisibility(View.GONE);
                    holderView.btn.setVisibility(View.VISIBLE);
                }else if(list.get(position).getStatus().equals("4")){
                    holderView.btn.setVisibility(View.GONE);
                    holderView.type.setVisibility(View.VISIBLE);
                    holderView.type.setText("待学生确认");
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.rl_right.setVisibility(View.VISIBLE);
                }else if(list.get(position).getStatus().equals("5")){
                    holderView.type.setVisibility(View.VISIBLE);
                    holderView.btn.setVisibility(View.GONE);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.type.setText("课程已结束");
                    holderView.rl_right.setVisibility(View.VISIBLE);
                }else if(list.get(position).getStatus().equals("7")){
                    holderView.type.setVisibility(View.VISIBLE);
                    holderView.btn.setVisibility(View.GONE);
                    holderView.rl_classteacher.setVisibility(View.GONE);
                    holderView.rl_cancel.setVisibility(View.GONE);
                    holderView.type.setText("老师已代课");
                    holderView.rl_right.setVisibility(View.VISIBLE);
                }else{
                    holderView.rl_right.setVisibility(View.GONE);
                }
            }

        }
        holderView.cancel.setOnClickListener(mListener);
        holderView.btn.setOnClickListener(mListener);

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
        return convertView;
    }

    public final class HolderView {
        ImageView head;
        TextView name;
        TextView time;
        TextView bigandsmallname;
        Button btn;
        TextView classteacher;
        TextView leave;
        TextView cancel;
        TextView type;
        RelativeLayout rl_classteacher;
        RelativeLayout rl_cancel;
        RelativeLayout rl_item;
        RelativeLayout rl_right;
    }

    /**
     * 用于回调的抽象类
     *
     * @author Ivan Xu
     *         2014-11-26
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
