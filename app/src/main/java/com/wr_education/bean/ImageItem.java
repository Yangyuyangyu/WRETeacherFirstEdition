package com.wr_education.bean;

import java.io.Serializable;
import java.util.ArrayList;


public class ImageItem implements Serializable {
	public String imageId;
	public String thumbnailPath;
	public String imagePath;
	public int ysflag=0;
	private ArrayList<ImageItem> list;
	public boolean network_img_flag=false;//是否为网络图片 网络图片为true
	
	
	public ArrayList<ImageItem> getList() {
		return list;
	}
	
	public boolean isNetwork_img_flag() {
		return network_img_flag;
	}

	public void setNetwork_img_flag(boolean network_img_flag) {
		this.network_img_flag = network_img_flag;
	}

	public void setList(ArrayList<ImageItem> list) {
		this.list = list;
	}
	public int getYsflag() {
		return ysflag;
	}
	public void setYsflag(int ysflag) {
		this.ysflag = ysflag;
	}
	public boolean isSelected = false;
	
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getThumbnailPath() {
		return thumbnailPath;
	}
	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
	
	
}
