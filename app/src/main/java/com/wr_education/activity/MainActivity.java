package com.wr_education.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.google.gson.Gson;
import com.wr_education.R;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.PublicBean;
import com.wr_education.fragment.HomePageFragment;
import com.wr_education.fragment.MineFragment;
import com.wr_education.fragment.OrganizationFragment;
import com.wr_education.fragment.SyllabusFragment;
import com.wr_education.http.HttpPath;
import com.wr_education.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Tab主页
 */
public class MainActivity extends FragmentActivity{
    private Button[] mTabs; //主界面下面4个按钮
    private int index=0; //判断当前是哪个fragment显示
    private RelativeLayout[] tab_containers;
    // 当前fragment的index
    private int currentTabIndex = 0;
    private HomePageFragment homePageFragment;
    private MineFragment mineFragment;
    private OrganizationFragment organizationFragment;
    private SyllabusFragment syllabusFragment;
    private Fragment[] fragments; //fragment集合
    public static Boolean ishas=false;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    //经纬度
    private String lat;
    private String lon;
    //位置
    private String addr;
    //用来装推送的标签
    private ArrayList<String> idlist;
    //推送的数据请求
    private StringRequest stringRequest;
    //判断推送的消息是否上传成功
    private boolean isup=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //百度推送
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "zHB37ZkCDuB6x9lH4GPuhd4fA2tQjCO3");
        idlist=new ArrayList<String>();
        idlist.add(MyApplication.getInstance().getLoginBean().getData().getId());
        PushManager.setTags(this, idlist);
        MyApplication.getInstance().addActivity(this);
        initView();
        initLocation(); //定位初始化
        PushMassage();//消息推送
        if(MyApplication.getInstance().isMyMessage()){
            MyApplication.setIsMyMessage(false);
            startActivity(new Intent(this, MyMessageActivity.class));
        }
    }


    private void initView() {
        mTabs = new Button[4];
        mTabs[0] = (Button) findViewById(R.id.mainactivity_btn_homepage);
        mTabs[1] = (Button) findViewById(R.id.mainactivity_btn_organization);
        mTabs[2] = (Button) findViewById(R.id.mainactivity_btn_syllabus);
        mTabs[3] = (Button) findViewById(R.id.mainactivity_btn_mine);
        mTabs[0].setSelected(true);

        //初始化5个fragment
        homePageFragment=new HomePageFragment();
        mineFragment=new MineFragment();
        organizationFragment=new OrganizationFragment();
        syllabusFragment=new SyllabusFragment();
        fragments = new Fragment[] {syllabusFragment,organizationFragment,homePageFragment,mineFragment};
        // 添加显示第一个fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainactivity_fragment_container, syllabusFragment)
                .hide(syllabusFragment).show(syllabusFragment).commit();
    }
    /**
     * button点击事件
     *
     * @param view
     */
    public void onTabClicked(View view) {
        switch (view.getId()){
            case R.id.mainactivity_btn_homepage:
                index = 0;
                break;
            case R.id.mainactivity_btn_organization:
                index = 1;
                break;
            case R.id.mainactivity_btn_syllabus:
                index = 2;
                break;
            case R.id.mainactivity_btn_mine:
                index = 3;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.mainactivity_fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Utility.showToast("再按一次退出程序", MainActivity.this);
                exitTime = System.currentTimeMillis();
            } else {
//                MyApplication.isLogin=false;
//                MyApplication.fb_flag=true;
//                MyApplication.tip_count=1;
                moveTaskToBack(false);
                finish();
      //          MyApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ishas){
            mTabs[currentTabIndex].setSelected(false);
            // 把当前tab设为选中状态
            mTabs[1].setSelected(true);
            currentTabIndex = 1;
        }
        initLocation();
//        if(!isup){
//            PushMassage();//消息推送
//        }
    }


    private void initLocation(){
      //  mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient=MyApplication.mLocationClient;
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000*30;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            lat=location.getLatitude()+"";
            lon=location.getLongitude()+"";
            addr=location.getAddrStr();
            MyApplication.setLat(lat);
            MyApplication.setLon(lon);
            MyApplication.setAddr(addr);
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }

    public void PushMassage(){
        if (Utility.NetworkAvailable(MainActivity.this)) {
            if (!TextUtils.isEmpty(MyApplication.getInstance().getChannelId())) {
                String url= HttpPath.pushMessageUrl+"?channelId="+MyApplication.getInstance().getChannelId();
                Log.e("推送", "请求的地址是:"+url);
                Log.e("推送", "请求的参数是是:"+MyApplication.getInstance().getChannelId());
                stringRequest = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Log.e("推送", "请求的结果是:"+result);
                        Gson gson = new Gson();
                        PublicBean bean = new PublicBean();
                        bean = gson.fromJson(result, bean.getClass());
                        if (bean.getCode() == 0) {
                            isup=true;
                        } else {
                            Utility.showToast(bean.getMsg(), MainActivity.this);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("推送失败", error.getMessage(), error);
                        byte[] htmlBodyBytes = error.networkResponse.data;
                        Log.e("推送失败", new String(htmlBodyBytes), error);
                    }
                });
                stringRequest.setTag("volleyget");
                MyApplication.getHttpQueue().add(stringRequest);
            }else{
                Utility.showToast("没有获取到您的推送id", MainActivity.this);
            }
        }else{
            Utility.showToastNoNetWork(MainActivity.this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationClient.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }
}
