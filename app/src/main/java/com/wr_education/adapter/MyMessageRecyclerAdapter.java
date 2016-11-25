package com.wr_education.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.bean.MyMessageListBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/12.
 */
public class MyMessageRecyclerAdapter extends RecyclerView.Adapter<MyMessageRecyclerAdapter.ViewHolder>{

    private ArrayList<MyMessageListBean.DataBean> list;
    private Context context;
    private OnItemClickLitener mOnItemClickLitener;

    public MyMessageRecyclerAdapter(Context context,ArrayList<MyMessageListBean.DataBean> list) {
        this.list = list;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mymessagelistadapter_layout, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        holder.content.setText(list.get(position).getContent());
        holder.time.setText(list.get(position).getTime());

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView content;
        public ViewHolder(View view){
            super(view);
            content=(TextView)view.findViewById(R.id.mymessageadapter_tv_content);
            time=(TextView)view.findViewById(R.id.mymessageadapter_tv_time);
        }
    }


    /**
     * ItemClick的回调接口
     * @author zhy
     *
     */
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
