package com.wr_education.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.activity.GradeActiviy;
import com.wr_education.bean.StudentGradeBean;

import java.util.ArrayList;

/**
 * Created by baoyz on 2014/6/29.
 */
public class StudentGradeAdapter extends RecyclerView.Adapter<StudentGradeAdapter.ViewHolder>{
    private Context context;
    // 数据集
    private ArrayList<StudentGradeBean.DataBean.StudentBean> list;
    private StudentGradeBean bean;
    private String title;

    public StudentGradeAdapter(Context context,ArrayList<StudentGradeBean.DataBean.StudentBean> list,StudentGradeBean bean,String title) {
        super();
        this.context=context;
        this.list = list;
        this.bean=bean;
        this.title=title;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.studentgradelistadapter_layout, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        if(!list.get(position).getScore().equals("")){
            holder.grade.setText(list.get(position).getScore()+ "分");
        }
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, GradeActiviy.class);
                intent.putExtra("courseId",bean.getData().getCourseInfo().getClass_id());
                intent.putExtra("studentId",list.get(position).getId());
                intent.putExtra("score_id",list.get(position).getScore_id());
                intent.putExtra("title",title);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name; //名字
        public TextView grade;// 分数
        public RelativeLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.studentadapter_tv_name);
            grade = (TextView) itemView.findViewById(R.id.studentadapter_tv_grade);
            item = (RelativeLayout) itemView.findViewById(R.id.studentadapter_rl_item);
        }
    }
}
