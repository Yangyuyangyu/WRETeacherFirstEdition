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
import com.wr_education.activity.ClassDetailsActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.HomePageTwoListBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/6.
 */
public class HomePageTwoXRecyClerViewAdapter extends RecyclerView.Adapter<HomePageTwoXRecyClerViewAdapter.ViewHolder>{
    private ArrayList<HomePageTwoListBean.DataBean> list;
    private Context context;

    public HomePageTwoXRecyClerViewAdapter(ArrayList<HomePageTwoListBean.DataBean> list,Context context) {
        this.list = list;
        this.context=context;
    }
    @Override
    public HomePageTwoXRecyClerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.homepagetwolistadapter_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderView,final int position) {
        holderView.name.setText(list.get(position).getName());
        holderView.time.setText(list.get(position).getClass_time());
        MyApplication.getInstance().displayHeadImage(list.get(position).getImg(),holderView.img,R.mipmap.img_default,null);
        holderView.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ClassDetailsActivity.class);
                intent.putExtra("id",list.get(position).getCid());
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
        public TextView name;
        public TextView time;
        public ImageView img;
        public RelativeLayout item;
        public ViewHolder(View view){
            super(view);
            name=(TextView)view.findViewById(R.id.homepagetwolist_tv_name);
            time=(TextView)view.findViewById(R.id.homepagetwolist_tv_time);
            img=(ImageView)view.findViewById(R.id.homepagetwolist_image_head);
            item=(RelativeLayout)view.findViewById(R.id.homepagetwo_rl_item);
        }
    }
}
