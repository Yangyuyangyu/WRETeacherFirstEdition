package com.wr_education.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.wr_education.R;
import com.wr_education.bean.ImageItem;
import com.wr_education.util.BasicImageAsynLoader;
import com.wr_education.util.Utility;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 这个是显示一个文件夹里面的所有图片时用的适配器
 *
 * @author king
 * @QQ:595163260
 * @version 2014年10月18日  下午11:49:35
 */
public class AlbumGridViewAdapter extends BaseAdapter{
	final String TAG = getClass().getSimpleName();
	private Context mContext;
	private ArrayList<ImageItem> dataList;
	private ArrayList<ImageItem> selectedDataList;
	
	private BasicImageAsynLoader asynLoader;
	ExecutorService executorService = Executors.newCachedThreadPool();
	
	
	public AlbumGridViewAdapter(Context c, ArrayList<ImageItem> dataList,
			ArrayList<ImageItem> selectedDataList) {
		mContext = c;
		this.dataList = dataList;
		this.selectedDataList = selectedDataList;
		asynLoader = new BasicImageAsynLoader(mAsynHandler,
				Utility.DipToPixels(c, 300));;
	}
	
	public Handler mAsynHandler = new Handler();

	public int getCount() {
		return dataList.size();
	}

	public Object getItem(int position) {
		return dataList.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}


	/**
	 * 存放列表项控件句柄
	 */
	private class ViewHolder {
		public ImageView imageView;
		public ToggleButton toggleButton;
		public Button choosetoggle;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView=LayoutInflater.from(mContext).inflate(R.layout.plugin_camera_select_imageview, null);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.image_view);
			viewHolder.toggleButton = (ToggleButton) convertView
					.findViewById(R.id.toggle_button);
			viewHolder.choosetoggle = (Button) convertView
					.findViewById(R.id.choosedbt);
			convertView.setTag(viewHolder);
		}
		 else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
		if (dataList != null && dataList.size() > position){
			//判断图片是否已经加载过
   	     		getThumb(viewHolder.imageView, dataList.get(position).getImagePath());
	
		}
		

		viewHolder.toggleButton.setTag(position);
		viewHolder.choosetoggle.setTag(position);
		viewHolder.toggleButton.setOnClickListener(new ToggleClickListener(viewHolder.choosetoggle));
		if (selectedDataList.contains(dataList.get(position))) {
			viewHolder.toggleButton.setChecked(true);
			viewHolder.choosetoggle.setVisibility(View.VISIBLE);
		} else {
			viewHolder.toggleButton.setChecked(false);
			viewHolder.choosetoggle.setVisibility(View.GONE);
		}


		return convertView;
	}
	
	private void getThumb(final ImageView itemImage, String strPath) {
		itemImage.setTag(strPath);

		asynLoader.loadBitmap(itemImage, true, strPath, executorService);
	}
	
	private class ToggleClickListener implements OnClickListener{
		Button chooseBt;
		public ToggleClickListener(Button choosebt){
			this.chooseBt = choosebt;
		}
		
		@Override
		public void onClick(View view) {
			if (view instanceof ToggleButton) {
				ToggleButton toggleButton = (ToggleButton) view;
				int position = (Integer) toggleButton.getTag();
				if (dataList != null && mOnItemClickListener != null
						&& position < dataList.size()) {
					mOnItemClickListener.onItemClick(toggleButton, position, toggleButton.isChecked(),chooseBt);
				}
			}
		}
	}
	

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener(OnItemClickListener l) {
		mOnItemClickListener = l;
	}

	public interface OnItemClickListener {
		public void onItemClick(ToggleButton view, int position,
								boolean isChecked, Button chooseBt);
	}
    


}
