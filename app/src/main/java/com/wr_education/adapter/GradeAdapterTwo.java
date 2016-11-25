package com.wr_education.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.bean.GradeBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/10.
 */
public class GradeAdapterTwo extends RecyclerView.Adapter<GradeAdapterTwo.ViewHolder> {
    private Context context;
    private ArrayList<GradeBean> list;
    private ViewHolder holder;
    public GradeAdapterTwo(Context context, ArrayList<GradeBean> list){
        super();
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(parent.getContext(), R.layout.gradeadaptertwo_layout, null);
        // 创建一个ViewHolder
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.ratingBar.setRating(list.get(position).getCode());
        if(list.get(position).getCode()!=0){
            holder.grade.setText(list.get(position).getCode()+"分");
        }else{
            holder.grade.setText("");
        }
        holder.name.setText(list.get(position).getName());
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                holder.grade.setText(rating + "分");
                list.get(position).setCode(rating);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name; //名字
        public RatingBar ratingBar;
        public TextView grade;//分数

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.gradeadptertwo_tv_name);
            ratingBar=(RatingBar) itemView.findViewById(R.id.gradeadptertwo_ratingbar);
            grade = (TextView) itemView.findViewById(R.id.gradeadptertwo_tv_grade);

        }
    }

    public ArrayList<GradeBean> GetList() {
        return list;
    }
}
