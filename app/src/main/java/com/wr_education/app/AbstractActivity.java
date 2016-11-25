package com.wr_education.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wr_education.R;

import java.lang.reflect.Field;

import butterknife.ButterKnife;


public abstract class AbstractActivity extends FragmentActivity implements View.OnClickListener{
	protected final static int LOADER_INITDATA = 0x100;
	protected String tag = this.getClass().getName();
	public final int LOADING = 0, LOADSUCCESS = 1, LOADFAILURE = -1;
	public Context mContext;
	protected int theme = 0;
	protected View mProgressView;
	protected Dialog pDialogView;
	protected Dialog webDialog;
	protected Dialog toastDialog;
	protected Button btn_pop;
	protected View mPopView;
	protected RelativeLayout mCanversLayout;
	protected PopupWindow mPopupWindow;
	protected ImageView mFavor;
	protected LinearLayout mHome_lay, mCar_lay, mShare_lay, mMine_lay;
	protected TextView pop_addFavor_text;
	boolean result = false;
	private InputMethodManager imm;
	
	public View getProgressView() {
		return mProgressView;
	}
	
	
	/**
	 * 等待菊花提示框
	 */
	public void showWaitLoad(Context context, String msg){
		try {
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
			LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
			ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
			TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
			// 加载动画
			Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
					context, R.anim.loading_animation);
			// 使用ImageView显示动画
			spaceshipImage.startAnimation(hyperspaceJumpAnimation);
			tipTextView.setText(msg);// 设置加载信息
			if(webDialog==null)
				webDialog = new Dialog(context, R.style.dialog_popup);// 创建自定义样式dialog
			webDialog.setCanceledOnTouchOutside(false);//点击外部不可以取消关闭窗体
			webDialog.setCancelable(true);// 不可以用“返回键”取消
			Window window = webDialog.getWindow();
			WindowManager.LayoutParams lp = window.getAttributes();
			lp.dimAmount = 0f;
			window.setAttributes(lp);
			webDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
			webDialog.show();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
	public void DismissDialog() {
		if(webDialog!=null){
			webDialog.dismiss();
			webDialog=null;
		}
	}
	/**
	 * 类似与Toast提示弹窗页面中间显示提示
	 * @param context
	 * @param msg
	 */
	public void showToastLoad(Context context, String msg){
		try {
			if(msg != null && !msg.equals("")){
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
	/**
	 * 类似与Toast提示弹窗页面中间显示提示
	 * @param context 
	 * @param msg 展示内容
	 * @param time 弹窗显示时间
	 */
	public void showToastLoad(Context context, String msg, final int time){
		try {
			if(msg != null && !msg.equals("")){
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
	/**
	 * 类似与Toast提示弹窗页面中间显示提示
	 * 用于没有网络连接时提示
	 * @param context
	 * @param msg
	 */
	public String NetworkPrompt="没有网络，请检查连接！"; 
	public void showToastLoad(Context context){
		
	}
	
	public void DismissToastDialog() {
		if(toastDialog!=null){
			toastDialog.dismiss();
			toastDialog=null;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		if(CreditHXSDKHelper.getInstance().getNotifier()!=null)
//			CreditHXSDKHelper.getInstance().getNotifier().reset();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("theme", theme);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (android.os.Build.VERSION.SDK_INT > 8 && true) {
			/*
			 * 线程监测
			 */
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			/*
			 * 虚拟机监�?			 */
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects() // 探测SQLite数据库操�?.penaltyLog() // 打印logcat
					.penaltyDeath().build());
		}
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		setTheme(theme);
		MyApplication.getInstance().setActivity(this);
		super.onCreate(savedInstanceState);
		forceShowActionBarOverflowMenu();

		//初始化布局
		initLayout();
		//添加ButterKife框架
		ButterKnife.bind(this);
		//初始化变量
		initVariables();
		//添加监听时间
		aadListenter();

	//	setStatusBar();
	}
	private void forceShowActionBarOverflowMenu() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception ignored) {

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(pDialogView!=null)
			pDialogView=null;
		if(webDialog!=null)
			webDialog=null;
		if(toastDialog!=null)
			toastDialog=null;
		ButterKnife.unbind(this);
	}
	
	/**
	 * 
	 */
	protected void saveEditTextAndCloseIMM() {
		/** 隐藏软键盘 **/
		View view = getWindow().peekDecorView();
		if (view != null) {
			imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
		return;
	}

	protected abstract void aadListenter();

	protected abstract void initVariables();

	protected abstract void initLayout();

	@Override
	public void onClick(View v) {

	}

	public void onBack(View view) {
		finish();
	}

//	public void setStatusBar() {
//		StatusBarUtil.setColorDiff(this, getResources().getColor(R.color.syllabus_btn_bg_green));
//	}
}
