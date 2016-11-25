package com.wr_education.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.Bimp;
import com.wr_education.util.AlbumHelper;
import com.wr_education.view.ViewPagerFixed;
import com.wr_education.view.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个是用于进行图片浏览时的界面
 *
 *
 */
public class GalleryActivity extends AbstractActivity {
	private Intent intent;
    // 返回按钮
    private TextView back_bt;
	// 发送按钮
	private TextView send_bt;
	//删除按钮
	private Button del_bt;

	//获取前一个activity传过来的position
	private int position;
	//当前的位置
	private int location = 0;
	
	private ArrayList<View> listViews = null;
	private ViewPagerFixed pager;
	private MyPageAdapter adapter;

	public List<Bitmap> bmp = new ArrayList<Bitmap>();
	public List<String> drr = new ArrayList<String>();
	public List<String> del = new ArrayList<String>();
	
	private Context mContext;

	RelativeLayout photo_relativeLayout;

	@Override
	protected void aadListenter() {

	}

	@Override
	protected void initVariables() {
		MyApplication.getInstance().addActivity(this);
		mContext = this;
		back_bt = (TextView) findViewById(R.id.select_photo);
		send_bt = (TextView) findViewById(R.id.select_wancheng);
		del_bt = (Button)findViewById(R.id.gallery_del);

		back_bt.setOnClickListener(new BackListener());
		send_bt.setOnClickListener(new GallerySendListener());
		del_bt.setOnClickListener(new DelListener());

		intent = getIntent();
		Bundle bundle = intent.getExtras();
		position = Integer.parseInt(intent.getStringExtra("position"));
		isShowOkBt();
		// 为发送按钮设置文字
		pager = (ViewPagerFixed) findViewById(R.id.gallery01);
		pager.setOnPageChangeListener(pageChangeListener);
		for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {

			initListViews(i);
		}

		adapter = new MyPageAdapter(listViews);
		pager.setAdapter(adapter);
		pager.setPageMargin(10);
		int id = intent.getIntExtra("ID", 0);
		pager.setCurrentItem(id);
	}

	@Override
	protected void initLayout() {
		setContentView(R.layout.plugin_camera_gallery);// 切屏到主界面
	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {
			location = arg0;
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageScrollStateChanged(int arg0) {

		}
	};
	
	private void initListViews(int i) {
		if (listViews == null)
			listViews = new ArrayList<View>();
		PhotoView img = new PhotoView(this);
		img.setBackgroundColor(0xff000000);
		if(Bimp.tempSelectBitmap.size()!=0){
//			if(Bimp.tempSelectBitmap.get(i).isNetwork_img_flag()){
//				MyApplication.getInstance().gxdisplay(Bimp.tempSelectBitmap.get(i).getImagePath(),img, R.drawable.plugin_camera_no_pictures);
//				img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
//						LayoutParams.MATCH_PARENT));
//			}
//			else{
//				Log.e("ss", "ss");
//			MyApplication.getInstance().displayfile(Bimp.tempSelectBitmap.get(i).getImagePath(),img, R.drawable.plugin_camera_no_pictures);
//		img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
//				LayoutParams.MATCH_PARENT));
//			}
			MyApplication.getInstance().displayfile(Bimp.tempSelectBitmap.get(i).getImagePath(),img, R.mipmap.plugin_camera_no_pictures);
			img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
		listViews.add(img);
		}
	
	}
	
	// 返回按钮添加的监听器
	private class BackListener implements OnClickListener {

		public void onClick(View v) {
			if(AlbumActivity.contentList==null){
				AlbumHelper helper = AlbumHelper.getHelper();
				helper.init(getApplicationContext());
				AlbumActivity.contentList = helper.getImagesBucketList(false);
			}
			Intent xc_intnt=new Intent(GalleryActivity.this,ImageFile.class);
			startActivity(xc_intnt);
			finish();
		}
	}
	
	// 删除按钮添加的监听器
	private class DelListener implements OnClickListener {

		public void onClick(View v) {
			if (listViews.size() == 1) {
				
				Bimp.tempSelectBitmap.clear();
				Bimp.max = 0;
				send_bt.setText("完成"+"(" + Bimp.tempSelectBitmap.size() + "/"+Bimp.count+")");
				Intent intent = new Intent("data.broadcast.action");  
                sendBroadcast(intent);  
				finish();
			} else {
				//回收当前的Bitmap
				
				Bimp.tempSelectBitmap.remove(location);
				Bimp.max--;
				pager.removeAllViews();
				listViews.remove(location);
				adapter.setListViews(listViews);
				send_bt.setText("完成"+"(" + Bimp.tempSelectBitmap.size() + "/"+Bimp.count+")");
				adapter.notifyDataSetChanged();
			}
		}
	}

	// 完成按钮的监听
	private class GallerySendListener implements OnClickListener {
		public void onClick(View v) {
			
//			Intent intent=new Intent(GalleryActivity.this,ReleaseDynamics_Activity.class);
//			startActivity(intent);
			finish();
		}

	}

	public void isShowOkBt() {
		if (Bimp.tempSelectBitmap.size() > 0) {
			send_bt.setText("完成"+"(" + Bimp.tempSelectBitmap.size() + "/"+Bimp.count+")");;
			send_bt.setPressed(true);
			send_bt.setClickable(true);
			send_bt.setTextColor(Color.WHITE);
		} else {
			send_bt.setPressed(false);
			send_bt.setClickable(false);
			send_bt.setTextColor(Color.parseColor("#E1E0DE"));
		}
	}

//	/**
//	 * 监听返回按钮
//	 */
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			if(position==1){
//				this.finish();
//				intent.setClass(GalleryActivity.this, AlbumActivity.class);
//				startActivity(intent);
//			}else if(position==2){
//				this.finish();
//				intent.setClass(GalleryActivity.this, ShowAllPhoto.class);
//				startActivity(intent);
//			}
//		}
//		return true;
//	}
	
	
	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;

		private int size;
		public MyPageAdapter(ArrayList<View> listViews) {
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public void setListViews(ArrayList<View> listViews) {
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public int getCount() {
			return size;
		}

		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPagerFixed) arg0).removeView(listViews.get(arg1 % size));
		}

		public void finishUpdate(View arg0) {
		}

		public Object instantiateItem(View arg0, int arg1) {
			try {
				((ViewPagerFixed) arg0).addView(listViews.get(arg1 % size), 0);

			} catch (Exception e) {
			}
			return listViews.get(arg1 % size);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
}
