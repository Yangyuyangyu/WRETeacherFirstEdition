package com.wr_education.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.app.BaseFragment;
import com.wr_education.bean.ClassDetailsBean;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/18.
 */
public class ClassDetailsThreeFragment extends BaseFragment {
    @Bind(R.id.classdetailsthree_tv_suitcrowd)
    TextView classdetailsthreeTvSuitcrowd;
    @Bind(R.id.classdetailsthree_tv_teachingobjectives)
    TextView classdetailsthreeTvTeachingobjectives;
    @Bind(R.id.classdetailsthree_tv_exitclassrule)
    TextView classdetailsthreeTvExitclassrule;
    @Bind(R.id.classdetailsthree_tv_insertclassrule)
    TextView classdetailsthreeTvInsertclassrule;
    private ClassDetailsBean bean;

    public void ClassDetailsThreeFragment(ClassDetailsBean bean){
        this.bean=bean;
    }
    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.classdetailsthree_layout, null);
    }

    @Override
    protected void initVariables() {
        classdetailsthreeTvSuitcrowd.setText(bean.getData().getInfo().getFit_crowd());
        classdetailsthreeTvTeachingobjectives.setText(bean.getData().getInfo().getGoal());
        classdetailsthreeTvExitclassrule.setText(bean.getData().getInfo().getQuit_rule());
        classdetailsthreeTvInsertclassrule.setText(bean.getData().getInfo().getJoin_rule());
    }

    @Override
    protected void addListener() {

    }
}
