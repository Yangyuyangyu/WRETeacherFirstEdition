package com.wr_education.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.PublicBean;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import butterknife.Bind;

/**
 * 请假页面
 * Created by Administrator on 2016/4/15.
 */
public class LeaveActivity extends AbstractActivity {
    @Bind(R.id.leave_imgbtn_back)
    ImageButton leaveImgbtnBack;
    @Bind(R.id.leave_tv_commit)
    TextView leaveTvCommit;
    @Bind(R.id.leave_rl_reason)
    RelativeLayout leaveRlReason;
    @Bind(R.id.leave_et)
    EditText leaveEt;
    @Bind(R.id.leave_tv_reason)
    TextView leaveTvReason;
    private PublicBean bean;
    private String id;

    protected View mPopView; // 右上角弹窗
    private LinearLayout popid; //popwindow的布局id
    protected PopupWindow mPopupWindow;
    protected TextView pop_tv_sickleave, pop_tv_personalaffairs, pop_tv_otherleave;//病假，事假，其他

    @Override
    protected void aadListenter() {
        leaveImgbtnBack.setOnClickListener(this);
        leaveTvCommit.setOnClickListener(this);
        leaveRlReason.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        initpop();//初始化popwindow
        if(getIntent()!=null){
            id=getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.leave_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leave_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
            case R.id.leave_tv_commit:
                if(Utility.StringIsNull(leaveTvReason.getText().toString())){
                    Utility.showToast("您还没有选择您的请假原因",LeaveActivity.this);
                }else if(Utility.StringIsNull(leaveEt.getText().toString())){
                    Utility.showToast("备注不能为空",LeaveActivity.this);
                }else if(Utility.NetworkAvailable(LeaveActivity.this)){
                    if(MyApplication.getInstance().getLoginBean().getData().getId()!=null){
                        new SetLeaveTask().execute(MyApplication.getInstance().getLoginBean().getData().getId(),id,leaveTvReason.getText().toString(),leaveEt.getText().toString());
                    }else{
                        Utility.showToast("没有获取到您的id",LeaveActivity.this);
                    }
                }else{
                    Utility.showToastNoNetWork(LeaveActivity.this);
                }
                break;
            case R.id.leave_rl_reason:
                showPopLay();
                break;
        }
    }

    private void initpop() {
        // 弹出选项
        mPopView = LayoutInflater.from(LeaveActivity.this).inflate(
                R.layout.popwindow_leave_layout, null);
        popid = (LinearLayout) mPopView.findViewById(R.id.pop_leave_id);
        mPopupWindow = new PopupWindow(mPopView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop_tv_sickleave = (TextView) mPopView.findViewById(R.id.pop_tv_sickleave); //病假
        pop_tv_personalaffairs = (TextView) mPopView.findViewById(R.id.pop_tv_personalaffairs);//事假
        pop_tv_otherleave = (TextView) mPopView.findViewById(R.id.pop_tv_otherleave);//其他
        pop_tv_sickleave.setOnClickListener(onClickListener);
        pop_tv_personalaffairs.setOnClickListener(onClickListener);
        pop_tv_otherleave.setOnClickListener(onClickListener);
    }

    /**
     * // * 右上角菜单选项 // * @param context // * @param msg //
     */
    public void showPopLay() {
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#00000000")));
        mPopupWindow.showAsDropDown(leaveRlReason, 0, 0);
        mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.update();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pop_tv_sickleave: //病假
                    mPopupWindow.dismiss();
                    leaveTvReason.setText("病假");
                    break;
                case R.id.pop_tv_personalaffairs: //事假
                    mPopupWindow.dismiss();
                    leaveTvReason.setText("事假");
                    break;
                case R.id.pop_tv_otherleave: //其他
                    mPopupWindow.dismiss();
                    leaveTvReason.setText("其他");
                    break;
            }
        }
    };

    private class SetLeaveTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(LeaveActivity.this, "上传中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean= HttpRequest.getInstance(LeaveActivity.this).SetLeave(strings[0], strings[1], strings[2],strings[3]);
            // 请求成功返回数据
            if (bean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (bean.getCode()==0){//上传教育经历成功
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), LeaveActivity.this);
                        finish();
                        saveEditTextAndCloseIMM();
                    }else{
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), LeaveActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", LeaveActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }
}
