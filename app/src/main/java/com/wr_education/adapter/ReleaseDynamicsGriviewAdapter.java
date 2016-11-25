package com.wr_education.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.wr_education.R;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.Bimp;


/**
 * 发布动态照片墙的适配器
 * 
 * @author 小男孩
 * 
 */
public class ReleaseDynamicsGriviewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private int selectedPosition = -1;
	private boolean shape;

	public boolean isShape() {
		return shape;
	}

	public void setShape(boolean shape) {
		this.shape = shape;
	}

	public ReleaseDynamicsGriviewAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		if (Bimp.tempSelectBitmap.size() == Bimp.count) {
			return Bimp.count;
		}
		// return Bimp.tempSelectBitmap.size();
		return (Bimp.tempSelectBitmap.size() + 1);
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	public void setSelectedPosition(int position) {
		selectedPosition = position;
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_published_grida,
					parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.item_grida_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (parent.getChildCount() == position) {
			if (position == Bimp.tempSelectBitmap.size()) {
				holder.image.setImageResource(R.mipmap.addimg);
				if (position == Bimp.count) {
					holder.image.setVisibility(View.GONE);
				}
			} else {
				MyApplication.getInstance().displayfile(
						Bimp.tempSelectBitmap.get(position).getImagePath(),
						holder.image, R.mipmap.img_default);
//				if(Bimp.tempSelectBitmap.get(position).isNetwork_img_flag()){
//					Log.e("网络图片", "地址："+Bimp.tempSelectBitmap.get(position).getImagePath());
//					MyApplication.getInstance().gxdisplay(
//							Bimp.tempSelectBitmap.get(position).getImagePath(),
//							holder.image, R.drawable.img_default);
//				}else{
//
//				}
			}
		}
		return convertView;
	}

	public class ViewHolder {
		public ImageView image;
	}

}
