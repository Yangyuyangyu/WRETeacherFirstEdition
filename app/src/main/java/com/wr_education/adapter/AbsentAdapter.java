package com.wr_education.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.bean.AbsentBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/15.
 */
public class AbsentAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<AbsentBean.DataBean> list;

    public AbsentAdapter(Context context,ArrayList<AbsentBean.DataBean> list){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.recycleview_absent, null);
            holderView = new HolderView();
            holderView.name=(TextView)convertView.findViewById(R.id.recylerview_absentteacher_tv_name);
            holderView.phone=(TextView)convertView.findViewById(R.id.recylerview_absentteacher_tv_phone);
            holderView.checkBox=(CheckBox)convertView.findViewById(R.id.recylerview_absentteacher_cb);
            holderView.item=(LinearLayout)convertView.findViewById(R.id.recylerview_absentteacher_item);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }
        holderView.name.setText(list.get(position).getName());
        holderView.phone.setText(list.get(position).getPhone());
        if(list.get(position).getIscheck()){
            holderView.checkBox.setChecked(true);
        }else{
            holderView.checkBox.setChecked(false);
        }
        return convertView;
    }



    class HolderView {
        TextView name;
        TextView phone;
        LinearLayout item;
        CheckBox checkBox;
    }
//    private Context context;
//    private ArrayList<AbsentBean.DataBean> list;
//    private ViewHolder holder;
//    public AbsentAdapter(Context context, ArrayList<AbsentBean.DataBean> list){
//        super();
//        this.context = context;
//        this.list = list;
//    }
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
//        View view = View.inflate(parent.getContext(), R.layout.recycleview_absent, null);
//        // 创建一个ViewHolder
//        holder = new ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, final int position) {
//   //     holder.ratingBar.setRating(list.get(position).getCode());
//        holder.name.setText(list.get(position).getName());
//        holder.phone.setText(list.get(position).getPhone());
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView name; //名字
//        public CheckBox checkBox;
//        public TextView phone;//电话号码
//        public LinearLayout item;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.recylerview_absentteacher_tv_name);
//            checkBox=(CheckBox) itemView.findViewById(R.id.recylerview_absentteacher_cb);
//            phone = (TextView) itemView.findViewById(R.id.recylerview_absentteacher_tv_phone);
//            item=(LinearLayout) itemView.findViewById(R.id.recylerview_absentteacher_item);
//        }
//    }

}
