package com.wr_education.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.wr_education.R;
import com.wr_education.adapter.AlbumGridViewAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.Bimp;
import com.wr_education.bean.ImageItem;

import java.util.ArrayList;


/**
 * 这个是显示一个文件夹里面的所有图片时的界面
 *
 * @author king
 * @QQ:595163260
 * @version 2014年10月18日  下午11:49:10
 */
public class ShowAllPhoto extends AbstractActivity {
	private GridView gridView;
	private TextView select_photo;
	private TextView select_quxiao;
	private TextView select_yulan;
	private TextView select_wancheng;
	private TextView select_nophoto;
	
	private TextView headTitle;
	private Intent intent;
	private Context mContext;
	private AlbumGridViewAdapter gridImageAdapter;
	private String folderName="";
	private ArrayList<ImageItem> select_imgs= new ArrayList<ImageItem>();//图片集合
	private int newindex=-1;
	private int oldindex=0;
	public static ArrayList<ImageItem> dataList = new ArrayList<ImageItem>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_photo_layout);
		MyApplication.getInstance().addActivity(this);
		mContext = this;
		
		this.intent = getIntent();
		folderName = intent.getStringExtra("folderName");
		if (folderName.length() > 8) {
			folderName = folderName.substring(0, 9) + "...";
		}
		initview();
		initlitser();
		isShowOkBt();
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

	private void initview() {
		// TODO Auto-generated method stub
		gridView=findviewbyid(R.id.photo_grivew);
		select_photo=findviewbyid(R.id.select_al_photo);
		select_quxiao=findviewbyid(R.id.select_al_quxiao);
		select_wancheng=findviewbyid(R.id.select_al_wancheng);
		select_nophoto=findviewbyid(R.id.select_nophoto);
		select_yulan=findviewbyid(R.id.select_al_yulian);
		headTitle=findviewbyid(R.id.title_text);
		headTitle.setText(folderName);
		if(dataList.size()>0){
			select_nophoto.setVisibility(View.GONE);
		}
		if(Bimp.tempSelectBitmap.size()>0){
			select_imgs.addAll(Bimp.tempSelectBitmap);
		}
		IntentFilter filter = new IntentFilter("data.broadcast.action");  
		registerReceiver(broadcastReceiver, filter);  
		gridImageAdapter = new AlbumGridViewAdapter(this,dataList,
				select_imgs);
		gridView.setAdapter(gridImageAdapter);
		
	}
	@SuppressWarnings("unchecked")
	private <T extends View> T findviewbyid(int viewId)  
    {  
          
       return (T) findViewById(viewId);
    } 

	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {  
		  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            // TODO Auto-generated method stub  
        	gridImageAdapter.notifyDataSetChanged();
        }  
    };  


	private void initlitser() {
		// TODO Auto-generated method stub
		select_photo.setOnClickListener(mClickListener);
		select_quxiao.setOnClickListener(mClickListener);
		select_wancheng.setOnClickListener(mClickListener);
		select_yulan.setOnClickListener(mClickListener);
		
		gridImageAdapter
		.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

			@Override
			public void onItemClick(final ToggleButton toggleButton,
					int position, boolean isChecked,Button chooseBt) {
				if (select_imgs.size() >= Bimp.count&&isChecked) {
					chooseBt.setVisibility(View.GONE);
					toggleButton.setChecked(false);
					Toast.makeText(ShowAllPhoto.this,"只能选"+Bimp.count+"张图片哦亲" , Toast.LENGTH_SHORT).show();
					return;
				}

				if (isChecked) {
					chooseBt.setVisibility(View.VISIBLE);
					dataList.get(position).setNetwork_img_flag(false);
					select_imgs.add(dataList.get(position));
					newindex++;
					select_wancheng.setText("完成"+"(" + select_imgs.size()
							+ "/"+Bimp.count+")");
				} else {
					select_imgs.remove(dataList.get(position));
					chooseBt.setVisibility(View.GONE);
					newindex--;
					select_wancheng.setText("完成"+"(" + select_imgs.size() + "/"+Bimp.count+")");
				}
				isShowOkBt();
			}
		});
		
		
	}
	
    private OnClickListener mClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			
			case R.id.select_al_quxiao:
				finish();
				
				break;
				
			case R.id.select_al_photo:
				Bimp.tempSelectBitmap=select_imgs;
				Intent intent4=new Intent(ShowAllPhoto.this,ImageFile.class);
				startActivity(intent4);
				finish();
				break;
		   
			case R.id.select_al_wancheng:
//				ShowAllPhoto.this.showWaitLoad(ShowAllPhoto.this, "图片处理中...");
				Bimp.tempSelectBitmap=select_imgs;
				finish();

				
				break;
				
			case R.id.select_al_yulian:
				if (select_imgs.size() > 0) {
					Bimp.tempSelectBitmap=select_imgs;
					Intent intent3=new Intent(ShowAllPhoto.this,GalleryActivity.class);
					intent3.putExtra("position", "1");
					startActivity(intent3);
					finish();
				}
				
				
				break;
			}
			
		}
	};

	public void isShowOkBt() {
		if (select_imgs.size() > 0) {
			select_wancheng.setText("完成"+"(" + select_imgs.size() + "/"+Bimp.count+")");
		    
			select_yulan.setClickable(true);
			select_wancheng.setClickable(true);
		} else {
            select_wancheng.setText("完成"+"(" + select_imgs.size() + "/"+Bimp.count+")");
			select_yulan.setClickable(false);
			select_wancheng.setClickable(false);
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			intent.setClass(ShowAllPhoto.this, ImageFile.class);
			startActivity(intent);
			finish();
		}

		return false;

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		isShowOkBt();
		super.onRestart();
	}

}
