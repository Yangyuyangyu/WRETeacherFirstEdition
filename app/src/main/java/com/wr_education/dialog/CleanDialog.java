package com.wr_education.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.app.AbstractDialog;

/**
 * Created by Administrator on 2016/5/7.
 */
public class CleanDialog extends AbstractDialog {
    private Button btn_confirm;
    private Button btn_cancel;
    private TextView tv_content;
    private Context context;
    private String content;
    private String phone;
    private View.OnClickListener onClickListener;
    public CleanDialog(Context context,String content,View.OnClickListener onClickListener) {
        super(context);
        setContentView(R.layout.dilog_layout);
        this.context=context;
        this.content=content;
        this.onClickListener=onClickListener;
        init();
    }

    private void init(){
        tv_content=(TextView)findViewById(R.id.active_dilog_content);
        btn_confirm = (Button)findViewById(R.id.active_dilog_btn_confirm);
        btn_cancel = (Button) findViewById(R.id.active_dilog_btn_cancel);
        btn_confirm.setText("确定");
        btn_cancel.setText("取消");
        tv_content.setText(content);
        btn_confirm.setOnClickListener(onClickListener);


        btn_cancel.setOnClickListener(new View.OnClickListener() {// 取消
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });
    }


    public void Dis(){
        dismiss();
    }
}
