package com.wr_education.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.CallNameBean;
import com.wr_education.view.RoundImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/15.
 */
public class CallNameGridViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CallNameBean.DataBean> list;
    private String second_call;

    public CallNameGridViewAdapter(Context context, ArrayList<CallNameBean.DataBean> list, String second_call) {
        this.context = context;
        this.list = list;
        this.second_call = second_call;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.callnamegridviewadapter_layout, null);
            holderView = new HolderView();
            holderView.name = (TextView) convertView.findViewById(R.id.callnamegridview_tv_name);
            holderView.sex = (TextView) convertView.findViewById(R.id.callnamegridview_tv_sex);
            holderView.item = (RelativeLayout) convertView.findViewById(R.id.callnamegridview_rl_item);
            holderView.head = (RoundImageView) convertView.findViewById(R.id.callnamegridview_img_head);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        holderView.name.setText(list.get(position).getName());
        if (list != null) {
            if (list.get(position).getSex().equals("1")) {
                holderView.sex.setText("男");
            } else if (list.get(position).getSex().equals("2")) {
                holderView.sex.setText("女");
            } else {
                holderView.sex.setText("");
            }
            MyApplication.getInstance().displayHeadImage(list.get(position).getHead(), holderView.head, R.mipmap.no_loading, null);
            if (second_call.equals("0")) {

                if(list.get(position).getLeave()!=null||!list.get(position).getLeave().equals("")){
                    if (list.get(position).getLeave().equals("1")) { //已请假
                        holderView.item.setBackgroundResource(R.drawable.change_border_blue);
                    } else {
//                    holderView.item.setBackgroundResource(R.drawable.change_border);
                    }
                }

            }
//            if (list.get(position).getCalled().equals("0")) { //未到
//                holderView.item.setBackgroundResource(R.color.app_while_but_no_color);
//            } else {
//                holderView.item.setBackgroundResource(R.color.syllabus_btn_bg_green);
//            }
        }
        return convertView;
    }

    class HolderView {
        RoundImageView head;
        TextView name;
        TextView sex;
        RelativeLayout item;
    }
}
