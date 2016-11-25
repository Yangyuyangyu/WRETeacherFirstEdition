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
import com.wr_education.activity.OrganizationHomeActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.RecommendOrganizationBean;
import com.wr_education.util.Utility;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/14.
 */
public class OrganizationAddListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<RecommendOrganizationBean.DataBean> list;

    public OrganizationAddListAdapter(Context context, ArrayList<RecommendOrganizationBean.DataBean> list) {
        this.context = context;
        this.list = list;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.organizationaddlistadapter_layout, null);
            holderView = new HolderView();
            holderView.name = (TextView) convertView.findViewById(R.id.organizationaddlistadapter_tv_name);
            holderView.teacher = (TextView) convertView.findViewById(R.id.organizationaddlistadapter_tv_teacher);
            holderView.location = (TextView) convertView.findViewById(R.id.organizationaddlistadapter_tv_location);
            holderView.head = (ImageView) convertView.findViewById(R.id.organizationaddlistadapter_image_head);
            holderView.item = (RelativeLayout) convertView.findViewById(R.id.organizationaddlistadapter_rl);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        holderView.name.setText(list.get(position).getName());
        holderView.teacher.setText(list.get(position).getTeacherNum() + "位老师");
        if (!Utility.StringIsNull(list.get(position).getLocation())) {
            holderView.location.setText(list.get(position).getLocation());
        }
        holderView.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OrganizationHomeActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);
            }
        });
        MyApplication.getInstance().displayHeadImage(list.get(position).getImg(), holderView.head, R.mipmap.img_sousuo, null);
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
