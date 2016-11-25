package com.wr_education.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.bean.MyMessageListBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/14.
 */
public class MyMessageListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<MyMessageListBean.DataBean> list;
    public MyMessageListAdapter(Context context,ArrayList<MyMessageListBean.DataBean> list){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.mymessagelistadapter_layout, null);
            holderView = new HolderView();
            holderView.content=(TextView)convertView.findViewById(R.id.mymessageadapter_tv_content);
     //       holderView.name=(TextView)convertView.findViewById(R.id.mymessageadapter_tv_name);
            holderView.time=(TextView)convertView.findViewById(R.id.mymessageadapter_tv_time);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }
        holderView.content.setText(list.get(position).getContent());
    //    holderView.name.setText(list.get(position).getNeme());
        holderView.time.setText(list.get(position).getTime());
        return convertView;
    }

    class HolderView {
    //    TextView name;
        TextView time;
        TextView content;
    }
}
