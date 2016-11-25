package com.wr_education.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.wr_education.R;

/**
 * Created by Administrator on 2016/4/25.
 */
public class UpdataDialog extends Dialog{
    private ImageView img;
    private TextView textView;
    private Window window;

    public UpdataDialog(Context context,String content) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loading_dialog);
        img=(ImageView)findViewById(R.id.img);
        textView=(TextView)findViewById(R.id.tipTextView);
        textView.setText(content);
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.anim.loading_animation);
        // 使用ImageView显示动画
        img.startAnimation(hyperspaceJumpAnimation);
    }

    public void showDialog() {
        window = getWindow(); // 得到对话框
        window.setWindowAnimations(R.style.dialog_popup); // 设置窗口弹出动画
        window.setBackgroundDrawableResource(android.R.color.transparent); // 设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wl);
        this.setCanceledOnTouchOutside(true);
        show();
    }

    public void dim() {
        dismiss();
    }
}
