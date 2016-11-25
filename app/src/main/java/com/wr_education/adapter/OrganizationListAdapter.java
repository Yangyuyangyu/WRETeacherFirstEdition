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
import com.wr_education.bean.OrganizationListBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/13.
 */
public class OrganizationListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<OrganizationListBean.DataBean> list;
    public OrganizationListAdapter(Context context,ArrayList<OrganizationListBean.DataBean> list){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.organizationlistadapter_layout, null);
            holderView = new HolderView();
            holderView.name=(TextView)convertView.findViewById(R.id.organizationlistadapter_tv_name);
            holderView.teacher=(TextView)convertView.findViewById(R.id.organizationlistadapter_tv_teacher);
            holderView.location=(TextView)convertView.findViewById(R.id.organizationlistadapter_tv_location);
            holderView.item=(RelativeLayout)convertView.findViewById(R.id.organizationlistadapter_rl);
            holderView.check=(ImageView)convertView.findViewById(R.id.organizationlistadapter_img_check);
            holderView.head=(ImageView)convertView.findViewById(R.id.organizationlistadapter_image_head);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }
        if(list.get(position).getStatus().equals("1")){
            holderView.check.setVisibility(View.GONE);
        }else if(list.get(position).getStatus().equals("0")){
            holderView.check.setVisibility(View.VISIBLE);
        }
        holderView.name.setText(list.get(position).getAgencyInfo().getName());
        holderView.teacher.setText(list.get(position).getAgencyInfo().getTeacherNum()+"位老师");
        holderView.location.setText(list.get(position).getAgencyInfo().getLocation());
        MyApplication.getInstance().displayHeadImage(list.get(position).getAgencyInfo().getImg(), holderView.head, R.mipmap.img_sousuo, null);
        holderView.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OrganizationHomeActivity.class);
                intent.putExtra("id",list.get(position).getAgencyInfo().getId());
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
        ImageView check;
    }
}
