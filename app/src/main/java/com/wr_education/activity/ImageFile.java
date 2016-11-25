package com.wr_education.activity;


import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.adapter.FolderAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;


/**
 *  显示所有相册
 * @author 小男孩
 *
 */
public class ImageFile extends AbstractActivity {

	private FolderAdapter folderAdapter;
	private TextView bt_cancel;
	private Context mContext;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plugin_camera_image_file);
		MyApplication.getInstance().addActivity(this);
		mContext = this;
		bt_cancel = (TextView) findViewById(R.id.select_quxiao);
		bt_cancel.setOnClickListener(new CancelListener());
		GridView gridView = (GridView) findViewById(R.id.fileGridView);
		folderAdapter = new FolderAdapter(this);
		gridView.setAdapter(folderAdapter);
	}

	@Override
	protected void aadListenter() {

	}

	@Override
	protected void initVariables() {

	}

	@Override
	protected void initLayout() {

	}

	private class CancelListener implements OnClickListener {// 取消按钮的监听
		public void onClick(View v) {
			//清空选择的图片
			finish();
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		
		return true;
	}
	
  @Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	finish();
}
}
