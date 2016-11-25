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
import com.wr_education.activity.MassDetailsActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.MyMassListBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/14.
 */
public class MyMassListAdapter extends BaseAdapter{
    private ArrayList<MyMassListBean.DataBean> list;
    private Context context;

    public MyMassListAdapter(Context context,ArrayList<MyMassListBean.DataBean> list){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.mymasslistadapter_layout, null);
            holderView = new HolderView();
            holderView.name=(TextView)convertView.findViewById(R.id.mymasslistadapter_tv_name);
            holderView.teacher=(TextView)convertView.findViewById(R.id.mymasslistadapter_tv_teacher);
            holderView.location=(TextView)convertView.findViewById(R.id.mymasslistadapter_tv_location);
            holderView.item=(RelativeLayout)convertView.findViewById(R.id.mymasslistadapter_rl);
            holderView.head=(ImageView)convertView.findViewById(R.id.mymasslistadapter_image_head);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }
        holderView.name.setText(list.get(position).getName());
        holderView.teacher.setText(list.get(position).getAdmins());
        holderView.location.setText("简介:"+list.get(position).getBrief());
        MyApplication.getInstance().displayHeadImage(list.get(position).getImg(),holderView.head,R.mipmap.img_3,null);
        holderView.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MassDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class HolderView {
        ImageView head;
        TextView name;
        TextView teacher;
        TextView location;
        RelativeLayout item;
    }
}
