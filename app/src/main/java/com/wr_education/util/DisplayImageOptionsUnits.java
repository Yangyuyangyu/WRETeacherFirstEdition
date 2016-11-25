package com.wr_education.util;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;


/**
 * @类名:DisplayImageOptionsUnits
 * @描述:TODO(获取请求图片的配置信息)
 */
public class DisplayImageOptionsUnits {
    public static DisplayImageOptionsUnits displayImageOptionsUnits = new DisplayImageOptionsUnits();
    DisplayImageOptions options = null;
    int defaultid = 0;

    public static DisplayImageOptionsUnits getIns() {
        return displayImageOptionsUnits;
    }

    public DisplayImageOptions displayImageOptions(int resId) {
        //当options对象不为空并且defaultid与resid一致时候 且返回options对象
        if (null != options && defaultid == resId) {
            return options;
        }
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(resId) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(resId) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(resId) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED) //设置图片以如何的编码方式显示
                .delayBeforeLoading(100)
                .resetViewBeforeLoading(false)
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new FadeInBitmapDisplayer(1500)) //是否图片加载好后渐入的动画时间
                .build(); // 构建完成
        defaultid = resId;
        return options;
    }

    /**
     * 加载本地图片
     *
     * @param resId
     * @return
     */
    public DisplayImageOptions displayImageOptions_File(int resId) {
        //当options对象不为空并且defaultid与resid一致时候 且返回options对象
        DisplayImageOptions options_1 = new DisplayImageOptions.Builder()
                .showImageOnLoading(resId) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(resId) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(resId) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(false) // 设置下载的图片是否缓存在内存中
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .delayBeforeLoading(100)
                .resetViewBeforeLoading(false)
                .cacheOnDisk(false) // 设置下载的图片是否缓存在SD卡中
                .build(); // 构建完成
        return options_1;
    }
}
