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
import com.wr_education.bean.OrganizationListBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/6.
 */
public class OrganizationXRecyClerViewAdapter extends RecyclerView.Adapter<OrganizationXRecyClerViewAdapter.ViewHolder> {
    private ArrayList<OrganizationListBean.DataBean> datas;
    private Context context;

    public OrganizationXRecyClerViewAdapter(Context context,ArrayList<OrganizationListBean.DataBean> datas) {
        this.datas = datas;
        this.context=context;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public OrganizationXRecyClerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.organizationlistadapter_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
        if(datas.get(position).getStatus().equals("1")){ //通过
            viewHolder.check.setVisibility(View.GONE);
        }else if(datas.get(position).getStatus().equals("0")){ //审核中
            viewHolder.check.setBackgroundResource(R.mipmap.audit_ico);
            viewHolder.check.setVisibility(View.VISIBLE);
        }else if(datas.get(position).getStatus().equals("2")){ //已拒绝
            viewHolder.check.setBackgroundResource(R.mipmap.jujue);
            viewHolder.check.setVisibility(View.VISIBLE);
        }
        viewHolder.name.setText(datas.get(position).getAgencyInfo().getName());
        viewHolder.teacher.setText(datas.get(position).getAgencyInfo().getTeacherNum()+"位老师");
        viewHolder.location.setText(datas.get(position).getAgencyInfo().getLocation());
        MyApplication.getInstance().displayHeadImage(datas.get(position).getAgencyInfo().getImg(), viewHolder.head, R.mipmap.img_default, null);
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OrganizationHomeActivity.class);
                intent.putExtra("id",datas.get(position).getAgencyInfo().getId());
                context.startActivity(intent);
            }
        });
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView head;
        public TextView name;
        public TextView teacher;
        public TextView location;
        public RelativeLayout item;
        public ImageView check;
        public ViewHolder(View view){
            super(view);
            name=(TextView)view.findViewById(R.id.organizationlistadapter_tv_name);
            teacher=(TextView)view.findViewById(R.id.organizationlistadapter_tv_teacher);
            location=(TextView)view.findViewById(R.id.organizationlistadapter_tv_location);
            item=(RelativeLayout)view.findViewById(R.id.organizationlistadapter_rl);
            check=(ImageView)view.findViewById(R.id.organizationlistadapter_img_check);
            head=(ImageView)view.findViewById(R.id.organizationlistadapter_image_head);
        }
    }
}
