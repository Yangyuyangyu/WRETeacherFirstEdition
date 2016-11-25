package com.wr_education.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.wr_education.R;

public class HomeGalleryAdapter extends BaseAdapter {
	private Context context;
	private ImageView imageView;
	private BitmapFactory.Options options;
	public Bitmap bitmap;
	int img[] = { R.mipmap.meishitupian01, R.mipmap.meishitupian02,
			R.mipmap.meishitupian03, R.mipmap.meishitupian04,
			R.mipmap.meishitupian05 };

	public HomeGalleryAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return img[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		imageView = new ImageView(context);
		imageView.setAdjustViewBounds(true);
		imageView.setScaleType(ScaleType.FIT_XY);
		options = new BitmapFactory.Options();
		options.inSampleSize = 2;
		bitmap = BitmapFactory.decodeResource(context.getResources(),
				img[position % img.length], options);
		imageView.setImageBitmap(bitmap);
		return imageView;
	}

}
