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
import com.wr_education.activity.MassContentActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.MassDetailsBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/14.
 */
public class MassDetailsListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<MassDetailsBean.DataBean.NewsBean> list;
    public MassDetailsListAdapter(Context context,ArrayList<MassDetailsBean.DataBean.NewsBean> list){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.massdetailslistadapter_layout, null);
            holderView = new HolderView();
            holderView.content=(TextView)convertView.findViewById(R.id.messdetailslistadapter_tv_content);
            holderView.name=(TextView)convertView.findViewById(R.id.messdetailslistadapter_tv_name);
            holderView.head=(ImageView)convertView.findViewById(R.id.messdetailslistadapter_img_head);
            holderView.item=(RelativeLayout)convertView.findViewById(R.id.messdetailslistadapter_rl_item);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }
        holderView.content.setText(list.get(position).getBrief());
        holderView.name.setText(list.get(position).getName());
        MyApplication.getInstance().displayHeadImage(list.get(position).getImg(),holderView.head,R.mipmap.img_3,null);
        holderView.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MassContentActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class HolderView {
        TextView name;
        TextView content;
        ImageView head;
        RelativeLayout item;
    }
}
