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
import com.wr_education.activity.OrganizationHomeActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.RecommendOrganizationBean;
import com.wr_education.util.Utility;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/6.
 */
public class OrganizationAddXRecyClerViewAdapter extends RecyclerView.Adapter<OrganizationAddXRecyClerViewAdapter.ViewHolder>{
    private Context context;
    private ArrayList<RecommendOrganizationBean.DataBean> list;

    public OrganizationAddXRecyClerViewAdapter(Context context, ArrayList<RecommendOrganizationBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.organizationaddlistadapter_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderView,final int position) {
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
        MyApplication.getInstance().displayHeadImage(list.get(position).getImg(), holderView.head, R.mipmap.img_default, null);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView head;
        public  TextView name;
        public  TextView teacher;
        public TextView location;
        public  RelativeLayout item;
        public ViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.organizationaddlistadapter_tv_name);
            teacher = (TextView) view.findViewById(R.id.organizationaddlistadapter_tv_teacher);
            location = (TextView) view.findViewById(R.id.organizationaddlistadapter_tv_location);
            head = (ImageView) view.findViewById(R.id.organizationaddlistadapter_image_head);
            item = (RelativeLayout) view.findViewById(R.id.organizationaddlistadapter_rl);
        }
    }
}
