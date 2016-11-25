package com.wr_education.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.wr_education.bean.LoginBean;
import com.wr_education.http.HttpPath;
import com.wr_education.util.DisplayImageOptionsUnits;
import com.wr_education.util.ShareReferenceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MyApplication extends Application {
    public static Context applicationContext;
    private static MyApplication instance;
    private Activity activity = null;
    private List<Activity> mList = new LinkedList<Activity>();
    public static DisplayImageOptions imageOptions = null;
    public static ImageLoader imageLoader = null;
    private static String LOGIN_USER_MESSAGE = "LoginUserMessage";
    public final static String PREF_USERNAME = "username";
    private static final String PREF_PWD = "pwd";
    private static final String PREF_ChannelID = "ChannelId";
    private static LoginBean loginBean;
    private static String lat;
    private static String lon;
    private static String addr;
    private static String ChannelId; //推送的id
    private static HashMap<String,ArrayList<String>> ReportDraft; //报告草稿

    public static boolean isLogin = false; //是否登录过
    public static boolean isMyMessage = false; //是否是推送来消息进来

    public static RequestQueue queue;
    //定位
    public static LocationClient mLocationClient = null;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        applicationContext = this;
        instance = this;
        initImageLoader(getApplicationContext());
        imageLoader = ImageLoader.getInstance();

        queue = Volley.newRequestQueue(applicationContext);//使用全局上下文

        ShareReferenceUtils.init(getApplicationContext());
        //百度地图初始化
        SDKInitializer.initialize(getApplicationContext());

        //定位
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类

        ReportDraft=new HashMap<String,ArrayList<String>>();
    }

    public static MyApplication getInstanceContent() {
        return instance;
    }

    public static RequestQueue getHttpQueue() {
        return queue;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public HashMap<String,ArrayList<String>> getReportDraft() {
        return ReportDraft;
    }

    //添加数据key传id
    public void addReportDraft(String key,ArrayList<String> list) {
        if(ReportDraft.containsKey(key)){
            ReportDraft.remove(key);
        }
        ReportDraft.put(key,list);
    }

    //清空hashmap
    public void ClearReportDraft(){
        ReportDraft.clear();
    }
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //杀掉后台进程
//            System.exit(0); 
        }
    }

    public void exitAll() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);   //返回值为0代表正常退出
        }
    }

    public static boolean isMyMessage() {
        return isMyMessage;
    }

    public static void setIsMyMessage(boolean isMyMessage) {
        MyApplication.isMyMessage = isMyMessage;
    }

    public static String getLat() {
        return lat;
    }

    public static void setLat(String lat) {
        MyApplication.lat = lat;
    }

    public static String getLon() {
        return lon;
    }

    public static void setLon(String lon) {
        MyApplication.lon = lon;
    }

    public static String getAddr() {
        return addr;
    }

    public static void setAddr(String addr) {
        MyApplication.addr = addr;
    }

    //    /**
//     * 获取用户的身份证号码
//     *
//     * @return
//     */
//    public String getLoginIDCard() {
//        SharedPreferences share = instance.getSharedPreferences(LOGIN_USER_MESSAGE, Activity.MODE_PRIVATE);
//        String IDCard = share.getString(PREF_ID_CARD, "");
//        return IDCard;
//    }

//    /**
//     * 保存用户的身份证号码
//     *
//     * @param IDCard
//     */
//    public void setLoginIDCard(String IDCard) {
//        if (IDCard != null && !IDCard.equals("")) {
//            SharedPreferences share = instance.getSharedPreferences(LOGIN_USER_MESSAGE, Activity.MODE_PRIVATE);
//            SharedPreferences.Editor editor = share.edit();
//            editor.putString(PREF_ID_CARD, IDCard);
//            editor.commit();
//        }
//    }


    public void initImageLoader(Context context) {
        //缓存文件的目录
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "WR_EducationApp/imageloader");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(800, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3) //线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加密
                .memoryCache(new LruMemoryCache(5 * 1024 * 1024)) // You can pass your own memory cache implementation/您可以通过自己的内存缓存实现
                .memoryCacheSize(10 * 1024 * 1024) // 内存缓存的最大值
                .diskCacheSize(128 * 1024 * 1024)  // 50 Mb sd卡(本地)缓存的最大值
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                        // 由原先的discCache -> diskCache
                .diskCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();
        //全局初始化此配置
        ImageLoader.getInstance().init(config);

    }


    /**
     * 清除内存缓存
     *
     * @param view
     */
    public void onClearMemoryClick(View view) {
//    	Toast.makeText(this, "清除内存缓存成功", Toast.LENGTH_SHORT).show();
        ImageLoader.getInstance().clearMemoryCache();  // 清除内存缓存
    }

    /**
     * 清除本地缓存
     *
     * @param view
     */
    public void onClearDiskClick(View view) {
//    	Toast.makeText(this, "清除本地缓存成功", Toast.LENGTH_SHORT).show();
        ImageLoader.getInstance().clearDiskCache();  // 清除本地缓存
    }

    /**
     * 加载图片
     *
     * @param imgurl
     * @param imageView
     * @param defaultPicId
     */
    public void displayfile(String imgurl, ImageView imageView,int defaultPicId){
        imageLoader.displayImage("file://"+imgurl, imageView, DisplayImageOptionsUnits.getIns().displayImageOptions(defaultPicId),new SimpleImageLoadingListener(){
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                // TODO Auto-generated method stub
                DiskCacheUtils.removeFromCache(imageUri, imageLoader.getDiskCache());
                MemoryCacheUtils.removeFromCache(imageUri, imageLoader.getMemoryCache());
            }
        });
    }


    public static MyApplication getInstance() {
        return instance;
    }


    /**
     * 获取当前登陆的用户名（我们的服务器的用户名）电话号码
     *
     * @return
     */
    public String getLoginUserName() {
        SharedPreferences share = instance.getSharedPreferences(LOGIN_USER_MESSAGE, Activity.MODE_PRIVATE);
        String userName = share.getString(PREF_USERNAME, "");
        return userName;
    }

    /**
     * 获取密码
     *
     * @return
     */
    public String getLoginPassword() {
        SharedPreferences share = instance.getSharedPreferences(LOGIN_USER_MESSAGE, Activity.MODE_PRIVATE);
        String password = share.getString(PREF_PWD, "");
        return password;
    }

    /**
     * 获取推送id
     *
     * @return
     */
    public String getChannelId() {
        SharedPreferences share = instance.getSharedPreferences(LOGIN_USER_MESSAGE, Activity.MODE_PRIVATE);
        String ChannelId = share.getString(PREF_ChannelID, "");
        return ChannelId;
    }

    /**
     * 设置当前登录的用户名（我们的服务器的用户名）
     *
     * @param
     */
    public void setLoginUserName(String username) {
        if (username != null && !username.equals("")) {
            SharedPreferences share = instance.getSharedPreferences(LOGIN_USER_MESSAGE, Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = share.edit();
            editor.putString(PREF_USERNAME, username);
            editor.commit();
        }
    }

    /**
     * 设置密码
     *
     * @param pwd
     */
    public void setLoginPassword(String pwd) {
        if (pwd != null) {
            SharedPreferences share = instance.getSharedPreferences(LOGIN_USER_MESSAGE, Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = share.edit();
            editor.putString(PREF_PWD, pwd);
            editor.commit();
        }
    }
    /**
     * 设置推送id
     *
     * @param ChannelId
     */
    public void setChannelId(String ChannelId) {
        if (ChannelId != null) {
            SharedPreferences share = instance.getSharedPreferences(LOGIN_USER_MESSAGE, Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = share.edit();
            editor.putString(PREF_ChannelID, ChannelId);
            editor.commit();
        }
    }

    public void setLoginBean(LoginBean loginBean){
        MyApplication.loginBean=loginBean;
    }

    public LoginBean getLoginBean(){
        return loginBean;
    }
    //头像加载
    public void displayHeadImage(String imgurl, ImageView imageView, int defaultPicId, ImageLoadingListener listener) {
        imageLoader.displayImage(imgurl, imageView, DisplayImageOptionsUnits.getIns().displayImageOptions(defaultPicId), listener);
    }
    public void displayCallNameImage(String imgurl, ImageView imageView, int defaultPicId, ImageLoadingListener listener) {
        imageLoader.displayImage(HttpPath.ImgHOST+imgurl, imageView, DisplayImageOptionsUnits.getIns().displayImageOptions(defaultPicId), listener);
    }
}
