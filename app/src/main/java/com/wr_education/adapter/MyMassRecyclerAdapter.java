package com.wr_education.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.activity.MassDetailsActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.MyMassListBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/12.
 */
public class MyMassRecyclerAdapter extends RecyclerView.Adapter<MyMassRecyclerAdapter.ViewHolder>{
    private ArrayList<MyMassListBean.DataBean> list;
    private Context context;


    public MyMassRecyclerAdapter(Context context,ArrayList<MyMassListBean.DataBean> list) {
        this.list = list;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mymasslistadapter_layout, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.name.setText(list.get(position).getName());
        holder.teacher.setText(list.get(position).getAdmins());
        holder.location.setText("简介:"+list.get(position).getBrief());
        MyApplication.getInstance().displayHeadImage(list.get(position).getImg(),holder.head,R.mipmap.img_default,null);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MassDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);
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
        public TextView name;
        public TextView teacher;
        public TextView location;
        public RelativeLayout item;
        public ViewHolder(View view){
            super(view);
            name=(TextView)view.findViewById(R.id.mymasslistadapter_tv_name);
            teacher=(TextView)view.findViewById(R.id.mymasslistadapter_tv_teacher);
            location=(TextView)view.findViewById(R.id.mymasslistadapter_tv_location);
            item=(RelativeLayout)view.findViewById(R.id.mymasslistadapter_rl);
            head=(ImageView)view.findViewById(R.id.mymasslistadapter_image_head);
        }
    }


}
