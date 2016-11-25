package com.wr_education.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.wr_education.R;
import com.wr_education.adapter.AlbumGridViewAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.Bimp;
import com.wr_education.bean.ImageBucket;
import com.wr_education.bean.ImageItem;
import com.wr_education.util.AlbumHelper;

import java.util.ArrayList;
import java.util.List;


/**
 *  相册选择界面
 * @author 小男孩
 *
 */
public class AlbumActivity extends AbstractActivity {
	//相册容器
	private GridView gridView;
	private TextView select_photo;
	private TextView select_quxiao;
	private TextView select_yulan;
	private TextView select_wancheng;
	private TextView select_nophoto;
	
	private AlbumGridViewAdapter gridImageAdapter;
	private Context mContext;
	private ArrayList<ImageItem> dataList;
	private AlbumHelper helper;
	public static List<ImageBucket> contentList;
	private TextView title_text;
	private int newindex=-1;
	private int oldindex=-1;
	private ArrayList<ImageItem> select_imgs= new ArrayList<ImageItem>();//图片集合
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_photo_layout);
		MyApplication.getInstance().addActivity(this);
		//注册一个广播，这个广播主要是用于在GalleryActivity进行预览时，防止当所有图片都删除完后，再回到该页面时被取消选中的图片仍处于选中状态
		IntentFilter filter = new IntentFilter("data.broadcast.action");
		registerReceiver(broadcastReceiver, filter); 
		//初始化控件
		initview();
		initlitser();
		isShowOkBt();

	}

    private OnClickListener mClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			
			case R.id.select_al_quxiao:
				//如果图片下标不为-1就要判断用户有没有进行选择操作 取消只清楚当前选择的图片
//				if(oldindex!=-1){
//					
//					for(int i=oldindex+1;i<newindex+1;i++){
//					    Bimp.tempSelectBitmap.remove(i);
//					}
//				}
//				else{
//				Bimp.tempSelectBitmap.clear();
//				}
				finish();
				
				break;
				
			case R.id.select_al_photo:
				Bimp.tempSelectBitmap=select_imgs;
				Intent intent4=new Intent(AlbumActivity.this,ImageFile.class);
				startActivity(intent4);
				finish();
				
				break;
		   
			case R.id.select_al_wancheng:
//				AlbumActivity.this.showWaitLoad(AlbumActivity.this, "图片处理中...");
				Bimp.tempSelectBitmap=select_imgs;
				finish();
				
				break;
				
			case R.id.select_al_yulian:
				if (select_imgs.size() > 0) {
					Bimp.tempSelectBitmap=select_imgs;
					Intent intent3=new Intent(AlbumActivity.this,GalleryActivity.class);
					intent3.putExtra("position", "1");
					startActivity(intent3);
					finish();
				}
				
				
				break;
			}
			
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
				if (select_imgs.size() >= Bimp.count) {
					toggleButton.setChecked(false);
					chooseBt.setVisibility(View.GONE);
					if (!removeOneData(dataList.get(position))) {
						Toast.makeText(AlbumActivity.this, "只能选"+Bimp.count+"张图片哦亲",Toast.LENGTH_SHORT).show();
					}
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
					newindex--;
					chooseBt.setVisibility(View.GONE);
					select_wancheng.setText("完成"+"(" + select_imgs.size() + "/"+Bimp.count+")");
				}
				isShowOkBt();
			}
		});
		
		
	}
	
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
	
	private boolean removeOneData(ImageItem imageItem) {
		if (select_imgs.contains(imageItem)) {
			select_imgs.remove(imageItem);
			newindex--;
			select_wancheng.setText("完成"+"(" +select_imgs.size() + "/"+Bimp.count+")");
			return true;
		}
	return false;
}
	
	private void initview() {
		// TODO Auto-generated method stub
		gridView=findviewbyid(R.id.photo_grivew);
		select_photo=findviewbyid(R.id.select_al_photo);
		select_quxiao=findviewbyid(R.id.select_al_quxiao);
		select_wancheng=findviewbyid(R.id.select_al_wancheng);
		select_nophoto=findviewbyid(R.id.select_nophoto);
		select_yulan=findviewbyid(R.id.select_al_yulian);
		title_text=findviewbyid(R.id.title_text);
		title_text.setText("最近照片");
		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());
		contentList = helper.getImagesBucketList(false);
		dataList = new ArrayList<ImageItem>();
		int max_count=0;
		int max_index=0;
		if(Bimp.tempSelectBitmap.size()>0){
			select_imgs.addAll(Bimp.tempSelectBitmap);
		}
		for(int i = 0; i<contentList.size(); i++){
		if(max_count<contentList.get(i).count){
			max_count=contentList.get(i).count;
			max_index=i;
		}
		}	
		for(int k=0;k<(contentList.get(max_index).imageList.size());k++){
			if(k>=30)
			break;
			dataList.add(contentList.get(max_index).imageList.get(k));		
		}
		gridImageAdapter = new AlbumGridViewAdapter(this,dataList,select_imgs);
		gridView.setAdapter(gridImageAdapter);
		gridView.setEmptyView(select_nophoto);
		select_wancheng.setText("完成"+"(" +select_imgs.size() + "/"+Bimp.count+")");
		gridView.setOnScrollListener(new PauseOnScrollListener(MyApplication.getInstance().imageLoader, true, true));
	
		
	}
	
	@SuppressWarnings("unchecked")
	private <T extends View> T findviewbyid(int viewId)  
    {  
          
       return (T) findViewById(viewId);
    } 
	
	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {  
		  
        @Override  
        public void onReceive(Context context, Intent intent) {  
        	//mContext.unregisterReceiver(this);
            // TODO Auto-generated method stub  
        	gridImageAdapter.notifyDataSetChanged();
        }  
    }; 
    
    protected void onDestroy() {
    	unregisterReceiver(broadcastReceiver);
    	super.onDestroy();
    	
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

	;

}
