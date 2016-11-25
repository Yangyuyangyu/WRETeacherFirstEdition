//package com.wr_education.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//
//import com.wr_education.R;
//import com.wr_education.bean.GradeBean;
//
//import java.util.ArrayList;
//
///**
// * Created by Administrator on 2016/4/19.
// */
//public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.ViewHolder> {
//    private Context context;
//    private ArrayList<GradeBean> list;
//    private ViewHolder holder;
//    private TextView[] mTabs;
//
//    public GradeAdapter(Context context, ArrayList<GradeBean> list) {
//        super();
//        this.context = context;
//        this.list = list;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
//        View view = View.inflate(parent.getContext(), R.layout.gradeadapter_layout, null);
//        // 创建一个ViewHolder
//        holder = new ViewHolder(view);
//        return holder;
//    }
//
//    public void change_List(ArrayList<GradeBean> list) {
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        if(list.get(position).getCode()!=0){
//            holder.grade.setText(list.get(position).getCode()+"分");
//        }else{
//            holder.grade.setText("");
//        }
//        holder.name.setText(list.get(position).getName());
//        switch (list.get(position).getCode()){
//            case 1:
//                list.get(position).setOne(true);
//                break;
//            case 2:
//                list.get(position).setTwo(true);
//                break;
//            case 3:
//                list.get(position).setThree(true);
//                break;
//            case 4:
//                list.get(position).setFour(true);
//                break;
//            case 5:
//                list.get(position).setFive(true);
//                break;
//            case 6:
//                list.get(position).setSix(true);
//                break;
//            case 7:
//                list.get(position).setSeven(true);
//                break;
//            case 8:
//                list.get(position).setEight(true);
//                break;
//            case 9:
//                list.get(position).setNine(true);
//                break;
//            case 10:
//                list.get(position).setTen(true);
//                break;
//        }
//        holder.one.setSelected(list.get(position).isOne());
//        holder.two.setSelected(list.get(position).isTwo());
//        holder.three.setSelected(list.get(position).isThree());
//        holder.four.setSelected(list.get(position).isFour());
//        holder.five.setSelected(list.get(position).isFive());
//        holder.six.setSelected(list.get(position).isSix());
//        holder.seven.setSelected(list.get(position).isSeven());
//        holder.eight.setSelected(list.get(position).isEight());
//        holder.nine.setSelected(list.get(position).isNine());
//        holder.ten.setSelected(list.get(position).isTen());
//        mTabs = new TextView[10];
//        mTabs[0] = holder.one;
//        mTabs[1] = holder.two;
//        mTabs[2] = holder.three;
//        mTabs[3] = holder.four;
//        mTabs[4] = holder.five;
//        mTabs[5] = holder.six;
//        mTabs[6] = holder.seven;
//        mTabs[7] = holder.eight;
//        mTabs[8] = holder.nine;
//        mTabs[9] = holder.ten;
//
//
//        View.OnClickListener onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.gradeadpter_tv_one:
//                        list.get(position).setCode(Integer.valueOf(holder.one.getText().toString()));
//                        holder.grade.setText(list.get(position).getCode() + "分");
//                        list.get(position).setOne(true);
//                        list.get(position).setTwo(false);
//                        list.get(position).setThree(false);
//                        list.get(position).setFour(false);
//                        list.get(position).setFive(false);
//                        list.get(position).setSix(false);
//                        list.get(position).setSeven(false);
//                        list.get(position).setEight(false);
//                        list.get(position).setNine(false);
//                        list.get(position).setTen(false);
//                        break;
//                    case R.id.gradeadpter_tv_two:
//                        list.get(position).setCode(Integer.valueOf(holder.two.getText().toString()));
//                        holder.grade.setText(list.get(position).getCode() + "分");
//                        list.get(position).setOne(false);
//                        list.get(position).setTwo(true);
//                        list.get(position).setThree(false);
//                        list.get(position).setFour(false);
//                        list.get(position).setFive(false);
//                        list.get(position).setSix(false);
//                        list.get(position).setSeven(false);
//                        list.get(position).setEight(false);
//                        list.get(position).setNine(false);
//                        list.get(position).setTen(false);
//                        break;
//                    case R.id.gradeadpter_tv_three:
//                        list.get(position).setCode(Integer.valueOf(holder.three.getText().toString()));
//                        holder.grade.setText(list.get(position).getCode() + "分");
//                        list.get(position).setOne(false);
//                        list.get(position).setTwo(false);
//                        list.get(position).setThree(true);
//                        list.get(position).setFour(false);
//                        list.get(position).setFive(false);
//                        list.get(position).setSix(false);
//                        list.get(position).setSeven(false);
//                        list.get(position).setEight(false);
//                        list.get(position).setNine(false);
//                        list.get(position).setTen(false);
//                        break;
//                    case R.id.gradeadpter_tv_four:
//                        list.get(position).setCode(Integer.valueOf(holder.four.getText().toString()));
//                        holder.grade.setText(list.get(position).getCode() + "分");
//                        list.get(position).setOne(false);
//                        list.get(position).setTwo(false);
//                        list.get(position).setThree(false);
//                        list.get(position).setFour(true);
//                        list.get(position).setFive(false);
//                        list.get(position).setSix(false);
//                        list.get(position).setSeven(false);
//                        list.get(position).setEight(false);
//                        list.get(position).setNine(false);
//                        list.get(position).setTen(false);
//                        break;
//                    case R.id.gradeadpter_tv_five:
//                        list.get(position).setCode(Integer.valueOf(holder.five.getText().toString()));
//                        holder.grade.setText(list.get(position).getCode() + "分");
//
//                        list.get(position).setOne(false);
//                        list.get(position).setTwo(false);
//                        list.get(position).setThree(false);
//                        list.get(position).setFour(false);
//                        list.get(position).setFive(true);
//                        list.get(position).setSix(false);
//                        list.get(position).setSeven(false);
//                        list.get(position).setEight(false);
//                        list.get(position).setNine(false);
//                        list.get(position).setTen(false);
//                        break;
//                    case R.id.gradeadpter_tv_six:
//                        list.get(position).setCode(Integer.valueOf(holder.six.getText().toString()));
//                        holder.grade.setText(list.get(position).getCode() + "分");
//                        list.get(position).setOne(false);
//                        list.get(position).setTwo(false);
//                        list.get(position).setThree(false);
//                        list.get(position).setFour(false);
//                        list.get(position).setFive(false);
//                        list.get(position).setSix(true);
//                        list.get(position).setSeven(false);
//                        list.get(position).setEight(false);
//                        list.get(position).setNine(false);
//                        list.get(position).setTen(false);
//                        break;
//                    case R.id.gradeadpter_tv_seven:
//                        list.get(position).setCode(Integer.valueOf(holder.seven.getText().toString()));
//                        holder.grade.setText(list.get(position).getCode() + "分");
//
//                        list.get(position).setOne(false);
//                        list.get(position).setTwo(false);
//                        list.get(position).setThree(false);
//                        list.get(position).setFour(false);
//                        list.get(position).setFive(false);
//                        list.get(position).setSix(false);
//                        list.get(position).setSeven(true);
//                        list.get(position).setEight(false);
//                        list.get(position).setNine(false);
//                        list.get(position).setTen(false);
//                        break;
//                    case R.id.gradeadpter_tv_eight:
//                        list.get(position).setCode(Integer.valueOf(holder.eight.getText().toString()));
//                        holder.grade.setText(list.get(position).getCode() + "分");
//
//                        list.get(position).setTwo(false);
//                        list.get(position).setThree(false);
//                        list.get(position).setFour(false);
//                        list.get(position).setFive(false);
//                        list.get(position).setSix(false);
//                        list.get(position).setSeven(false);
//                        list.get(position).setEight(true);
//                        list.get(position).setNine(false);
//                        list.get(position).setTen(false);
//                        break;
//                    case R.id.gradeadpter_tv_nine:
//                        list.get(position).setCode(Integer.valueOf(holder.nine.getText().toString()));
//                        holder.grade.setText(list.get(position).getCode() + "分");
//                        list.get(position).setOne(false);
//                        list.get(position).setTwo(false);
//                        list.get(position).setThree(false);
//                        list.get(position).setFour(false);
//                        list.get(position).setFive(false);
//                        list.get(position).setSix(false);
//                        list.get(position).setSeven(false);
//                        list.get(position).setEight(false);
//                        list.get(position).setNine(true);
//                        list.get(position).setTen(false);
//                        break;
//                    case R.id.gradeadpter_tv_ten:
//                        list.get(position).setCode(Integer.valueOf(holder.ten.getText().toString()));
//                        holder.grade.setText(list.get(position).getCode() + "分");
//                        list.get(position).setOne(false);
//                        list.get(position).setTwo(false);
//                        list.get(position).setThree(false);
//                        list.get(position).setFour(false);
//                        list.get(position).setFive(false);
//                        list.get(position).setSix(false);
//                        list.get(position).setSeven(false);
//                        list.get(position).setEight(false);
//                        list.get(position).setNine(false);
//                        list.get(position).setTen(true);
//                        break;
//                }
//                holder.one.setSelected(list.get(position).isOne());
//                holder.two.setSelected(list.get(position).isTwo());
//                holder.three.setSelected(list.get(position).isThree());
//                holder.four.setSelected(list.get(position).isFour());
//                holder.five.setSelected(list.get(position).isFive());
//                holder.six.setSelected(list.get(position).isSix());
//                holder.seven.setSelected(list.get(position).isSeven());
//                holder.eight.setSelected(list.get(position).isEight());
//                holder.nine.setSelected(list.get(position).isNine());
//                holder.ten.setSelected(list.get(position).isTen());
//            }
//        };
//
//
//        for (int i = 0; i < mTabs.length; i++) {
//            mTabs[i].setOnClickListener(onClickListener);
//        }
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView name; //名字
//        public TextView grade;// 分数
//        public RadioGroup radioGroup;
//        public TextView one;
//        public TextView two;
//        public TextView three;
//        public TextView four;
//        public TextView five;
//        public TextView six;
//        public TextView seven;
//        public TextView eight;
//        public TextView nine;
//        public TextView ten;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.gradeadpter_tv_name);
//            grade = (TextView) itemView.findViewById(R.id.gradeadpter_tv_grade);
//            radioGroup = (RadioGroup) itemView.findViewById(R.id.gradeadpter_radiopgrpup);
//            one = (TextView) itemView.findViewById(R.id.gradeadpter_tv_one);
//            two = (TextView) itemView.findViewById(R.id.gradeadpter_tv_two);
//            three = (TextView) itemView.findViewById(R.id.gradeadpter_tv_three);
//            four = (TextView) itemView.findViewById(R.id.gradeadpter_tv_four);
//            five = (TextView) itemView.findViewById(R.id.gradeadpter_tv_five);
//            six = (TextView) itemView.findViewById(R.id.gradeadpter_tv_six);
//            seven = (TextView) itemView.findViewById(R.id.gradeadpter_tv_seven);
//            eight = (TextView) itemView.findViewById(R.id.gradeadpter_tv_eight);
//            nine = (TextView) itemView.findViewById(R.id.gradeadpter_tv_nine);
//            ten = (TextView) itemView.findViewById(R.id.gradeadpter_tv_ten);
//        }
//    }
//
//    public ArrayList<GradeBean> GetList() {
//        return list;
//    }
//
//}
