package com.wr_education.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.activity.ClassDetailsActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.HomePageTwoListBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/13.
 */
public class HomePageTwoListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<HomePageTwoListBean.DataBean> list;
    public HomePageTwoListAdapter(Context context,ArrayList<HomePageTwoListBean.DataBean> list){
        this.context=context;
        this.list=list;
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
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.homepagetwolistadapter_layout, null);
            holderView = new HolderView();
            holderView.name=(TextView)convertView.findViewById(R.id.homepagetwolist_tv_name);
            holderView.time=(TextView)convertView.findViewById(R.id.homepagetwolist_tv_time);
            holderView.img=(ImageView)convertView.findViewById(R.id.homepagetwolist_image_head);
            holderView.item=(RelativeLayout)convertView.findViewById(R.id.homepagetwo_rl_item);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }
        holderView.name.setText(list.get(position).getName());
        holderView.time.setText(list.get(position).getClass_time());
        MyApplication.getInstance().displayHeadImage(list.get(position).getImg(),holderView.img,R.mipmap.ceshi_img1,null);
        holderView.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ClassDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class HolderView {
        TextView name;
        TextView time;
        ImageView img;
        RelativeLayout item;
    }
}
