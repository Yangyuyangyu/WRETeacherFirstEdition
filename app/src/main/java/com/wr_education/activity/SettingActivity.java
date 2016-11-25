package com.wr_education.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.android.pushservice.PushManager;
import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.activity.change.ChangePassword;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.AboutUsBean;
import com.wr_education.dialog.CallPhoneDialog;
import com.wr_education.dialog.CleanDialog;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.Utility;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/14.
 */
public class SettingActivity extends AbstractActivity {
    @Bind(R.id.setting_imgbtn_back)
    ImageButton settingImgbtnBack;
    @Bind(R.id.setting_rl_clean)
    RelativeLayout settingRlClean;
    @Bind(R.id.setting_rl_feedback)
    RelativeLayout settingRlFeedback;
    @Bind(R.id.setting_rl_customerservicephone)
    RelativeLayout settingRlCustomerservicephone;
    @Bind(R.id.setting_rl_aboutus)
    RelativeLayout settingRlAboutus;
    @Bind(R.id.setting_btn_exit)
    Button settingBtnExit;
    @Bind(R.id.setting_tv_phone)
    TextView settingTvPhone;
    @Bind(R.id.setting_tv_clean)
    TextView settingTvClean;
    @Bind(R.id.setting_rl_changePassword)
    RelativeLayout settingRlChangePassword;

    private CallPhoneDialog callPhoneDialog;
    private CleanDialog cleanDialog;
    private AboutUsBean bean;

    @Override
    protected void aadListenter() {
        settingImgbtnBack.setOnClickListener(this);
        settingRlClean.setOnClickListener(this);
        settingRlFeedback.setOnClickListener(this);
        settingRlCustomerservicephone.setOnClickListener(this);
        settingRlAboutus.setOnClickListener(this);
        settingBtnExit.setOnClickListener(this);
        settingRlChangePassword.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        getCacheSize();
        if (Utility.NetworkAvailable(SettingActivity.this)) {
            new GetAboutUsTask().execute();
        } else {
            Utility.showToastNoNetWork(SettingActivity.this);
        }
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.setting_layout);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.setting_rl_changePassword:
                startActivity(new Intent(SettingActivity.this, ChangePassword.class));

                break;

            case R.id.setting_btn_exit:
                logoutDialogWindow(SettingActivity.this);
                break;
            case R.id.setting_rl_aboutus:
                intent = new Intent(SettingActivity.this, AboutUsActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);
                //    startActivity(new Intent(SettingActivity.this, AboutUsActivity.class));
                break;
            case R.id.setting_rl_feedback:
                startActivity(new Intent(SettingActivity.this, FeedBackActivity.class));
                break;
            case R.id.setting_rl_clean:
                cleanDialog = new CleanDialog(SettingActivity.this, "是否清理缓存?", onClickListener);
                cleanDialog.showDialog();
                break;
            case R.id.setting_rl_customerservicephone:
                if (!Utility.StringIsNull(settingTvPhone.getText().toString())) {
                    callPhoneDialog = new CallPhoneDialog(SettingActivity.this, "是否拨打电话：" + settingTvPhone.getText().toString(), settingTvPhone.getText().toString());
                    callPhoneDialog.showDialog();
                } else {
                    Utility.showToast("没有获取到客服电话", SettingActivity.this);
                }
                break;
            case R.id.setting_imgbtn_back:
                finish();
                saveEditTextAndCloseIMM();
                break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        MyApplication.getInstance().onClearMemoryClick(null);
                        MyApplication.getInstance().onClearDiskClick(null);
                        final String filePath = Environment.getExternalStorageDirectory()
                                + File.separator + "WR_EducationApp/imageloader";
                        File file = new File(filePath);
                        delete(file);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                getCacheSize();
                                cleanDialog.dismiss();
                                MyApplication.getInstance().ClearReportDraft();
                                Utility.showToast("清理成功", SettingActivity.this);
                            }
                        });
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private Dialog dialogWindow = null;

    /**
     * 退出登录的
     *
     * @param context 当前上下文对象
     *                //    * @param content 提示内容
     */
    public void logoutDialogWindow(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.exitdialog_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        params.setMargins(50, 0, 50, 0);
        dialogWindow = new Dialog(context, R.style.dialog_popup);
        dialogWindow.setContentView(view, params);
        Button btn_confirm = (Button) view.findViewById(R.id.exitdilog_btn_confirm);
        Button btn_cancel = (Button) view.findViewById(R.id.exitdilog_btn_cancel);
        btn_confirm.setText("确定");
        btn_cancel.setText("取消");
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (dialogWindow != null)
                    dialogWindow.dismiss();
                dialogWindow = null;
                MyApplication.getInstance().exit();
                MyApplication.getInstance().ClearReportDraft();
                //删除推送标签
                List<String> tag = new ArrayList<>();
                tag.add(String.valueOf(MyApplication.getInstance().getLoginBean().getData().getId()));
                PushManager.delTags(getApplicationContext(), tag);

                //关闭地图定位
                MyApplication.mLocationClient.stop();

                MyApplication.isLogin = false;
                startActivity(new Intent(SettingActivity.this, StartActivity.class));
                finish();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {//取消
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (dialogWindow != null)
                    dialogWindow.dismiss();
                dialogWindow = null;
            }
        });
        dialogWindow.setCanceledOnTouchOutside(true);// 禁止点击对话框外部取消对话框显示
        dialogWindow.setCancelable(true);// 阻止“返回键”关闭Dialog
        dialogWindow.show();
    }

    /**
     * @param file
     */
    public void delete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }
            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            file.delete();
        }
    }

    /***
     * 获取文件夹大小
     ***/
    public long getFileSize(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    /***
     * 转换文件大小单位(b/kb/mb/gb)
     ***/
    public String FormetFileSize(long fileS) {// 转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = 0.00 + "M"; //df.format((double) fileS)
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取指定文件夹大小
     */
    public void getCacheSize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // TODO Auto-generated method stub
                    final String filePath = Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "WR_EducationApp/imageloader";
                    File file = new File(filePath);
                    long fileSize = getFileSize(file);
                    final String hcSize = FormetFileSize(fileSize);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            settingTvClean.setText(hcSize);
                        }
                    });
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            settingTvClean.setText("0.00M");
                        }
                    });
                }
            }
        }).start();
    }




    private class GetAboutUsTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //    showWaitLoad(SettingActivity.this,"数据加载中....");
        }

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(SettingActivity.this).SetAboutUsData();
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
                    if (bean.getCode() == 0) {
                        DismissDialog();
                        settingTvPhone.setText(bean.getData().getTelephone());
                    } else {
                        DismissDialog();
                        //      Utility.showToast(bean.getMsg(), SettingActivity.this);
                    }
                } else {
                    //      Utility.showToast("请求失败，请重试！", SettingActivity.this);
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
