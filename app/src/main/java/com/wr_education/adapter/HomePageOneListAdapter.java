package com.wr_education.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.TeacherBean;
import com.wr_education.view.RoundImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/12.
 */
public class HomePageOneListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<TeacherBean.DataBean.CommentBean> list;
    public HomePageOneListAdapter(Context context,ArrayList<TeacherBean.DataBean.CommentBean> list){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.homepageonelistadapter_layout, null);
            holderView = new HolderView();
            holderView.content=(TextView)convertView.findViewById(R.id.homepageonelist_tv_content);
            holderView.name=(TextView)convertView.findViewById(R.id.homepageonelist_tv_name);
            holderView.time=(TextView)convertView.findViewById(R.id.homepageonelist_tv_time);
            holderView.head=(RoundImageView)convertView.findViewById(R.id.homepageonelist_image_head);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }
        holderView.content.setText(list.get(position).getContent());
        holderView.name.setText(list.get(position).getUser_name());
        holderView.time.setText(list.get(position).getTime());
        MyApplication.getInstance().displayHeadImage(list.get(position).getUser_img(), holderView.head, R.mipmap.touxiang, null);

        return convertView;
    }

    class HolderView {
        TextView name;
        TextView time;
        TextView content;
        RoundImageView head;
    }

}
