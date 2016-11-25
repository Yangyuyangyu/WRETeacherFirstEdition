package com.wr_education.dialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.app.AbstractDialog;
import com.wr_education.util.LogHelper;
import com.wr_education.util.Utility;

/**
 * Created by Administrator on 2016/4/16.
 */
public class CallPhoneDialog extends AbstractDialog{
    private Button btn_confirm;
    private Button btn_cancel;
    private TextView tv_content;
    private Context context;
    private String content;
    private String phone;

    public CallPhoneDialog(Context context,String content,String phone) {
        super(context);
        setContentView(R.layout.dilog_layout);
        this.context=context;
        this.content=content;
        this.phone=phone;
        init();
    }
    private void init(){
        tv_content=(TextView)findViewById(R.id.active_dilog_content);
        btn_confirm = (Button)findViewById(R.id.active_dilog_btn_confirm);
        btn_cancel = (Button) findViewById(R.id.active_dilog_btn_cancel);
        btn_confirm.setText("确定");
        btn_cancel.setText("取消");
        tv_content.setText(content);
        btn_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                try {
                    context.startActivity(intent);
                } catch (Exception e) {
                    LogHelper.i(e.getMessage());
                    Utility.showToast("拨打电话的权限未开启，请检查您的权限",context);
                }
                dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {// 取消
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });
    }
}
