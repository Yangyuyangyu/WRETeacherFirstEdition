package com.wr_education.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Mr-fang on 2016/1/13.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initLayout(inflater, container, savedInstanceState);

      //  setStatusBar();
        //娣诲姞缁勪欢娉ㄨВ
        ButterKnife.bind(this, view);
        //鍒濆鍖栧彉閲�
        initVariables();
        //娣诲姞鐩戝惉浜嬩欢
        addListener();

        return view;
    }

    /**
     * 鍒濆鍖栧竷灞�
     */
    protected abstract View initLayout( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState );

    /**
     * 鍒濆鍖栧彉閲�
     */
    protected abstract void initVariables();

    /**
     * 娣诲姞鐩戝惉浜嬩欢
     */
    protected abstract void addListener();

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

//    public void setStatusBar() {
//        StatusBarUtil.setColorDiff(getActivity(), getResources().getColor(R.color.syllabus_btn_bg_green));//
//    }

}