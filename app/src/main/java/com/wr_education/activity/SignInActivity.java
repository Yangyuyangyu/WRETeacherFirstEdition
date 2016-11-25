package com.wr_education.activity;

import android.content.Context;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.PublicBean;
import com.wr_education.http.HttpPath;
import com.wr_education.util.ShareReferenceUtils;
import com.wr_education.util.Utility;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.Bind;

/**
 * 签到页面
 * Created by Administrator on 2016/5/10.
 */
public class SignInActivity extends AbstractActivity implements View.OnClickListener {
    @Bind(R.id.sign_imgbtn_back)
    ImageButton signImgbtnBack;
    @Bind(R.id.sign_mapview)
    MapView bmapView;
    @Bind(R.id.sign_btn)
    Button signBtn;
    @Bind(R.id.sign_tv_location)
    TextView signTvLocation;
    private BaiduMap mBaiduMap;
    private PublicBean bean;
    private StringRequest stringRequest;
    private String id; //上课记录id，从课程表传过来
    private String DEVICE_ID;//手机标识码
    private String time;//当前时间


    @Override
    protected void initLayout() {
        setContentView(R.layout.sign_in_layout);
    }

    @Override
    protected void aadListenter() {
        signImgbtnBack.setOnClickListener(this);
        signBtn.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        try {
            //获取手机唯一标识码,根据不同的手机设备返回IMEI，MEID或者ESN码
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            DEVICE_ID = tm.getDeviceId();

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (DEVICE_ID == null) {
            DEVICE_ID = getDeviceId(getApplicationContext());
        }
        Log.e("手机标识码--------", DEVICE_ID + "");
        time = Utility.getCurrentTimeDate();//获取当前时间

        mBaiduMap = bmapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //定义Maker坐标点
        mBaiduMap.setTrafficEnabled(false);
        mBaiduMap.setMyLocationEnabled(true);
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(16.0f);//设置地图的缩放比例
        mBaiduMap.setMapStatus(msu);//将前面的参数交给BaiduMap类
        bmapView.showScaleControl(true);//是否显示比例尺控件
        bmapView.showZoomControls(true);//是否显示缩放控件
        bmapView.removeViewAt(1);
        if (id != null) {
            if (MyApplication.getLat() != null && MyApplication.getLon() != null && MyApplication.getAddr() != null) {
                signTvLocation.setText(MyApplication.getAddr()); //设置位置
                LatLng ll = new LatLng(Double.valueOf(MyApplication.getLat()), Double.valueOf(MyApplication.getLon()));
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
                //自己的定位
                LatLng point = new LatLng(Double.valueOf(MyApplication.getLat()), Double.valueOf(MyApplication.getLon()));
                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.icon_marka);
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(point)
                        .icon(bitmap);
                //在地图上添加Marker，并显示
                mBaiduMap.addOverlay(option);
            } else {
                Utility.showToast("没有获取到当前位置信息", SignInActivity.this);
            }
        } else {
            Utility.showToast("没有获取到课堂信息", SignInActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.sign_imgbtn_back:
                finish();
                break;

            case R.id.sign_btn: //签到按钮
                if (Utility.StringIsNull(DEVICE_ID)) {
                    Utility.showToast("没有获取到当前手机的标识码", SignInActivity.this);

                } else if (Utility.StringIsNull(time)) {
                    Utility.showToast("没有获取到当前时间", SignInActivity.this);
                } else if (Utility.NetworkAvailable(SignInActivity.this)) {
                    showWaitLoad(SignInActivity.this, "签到中...");
                    stringRequest = new StringRequest(Request.Method.POST, HttpPath.signUpUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String result) {
                            Log.i("result", result);
                            Gson gson = new Gson();
                            PublicBean bean = new PublicBean();
                            bean = gson.fromJson(result, bean.getClass());
                            if (bean.getCode() == 0) {
                                DismissDialog();
                                Utility.showToast(bean.getMsg(), SignInActivity.this);
                                finish();
                                saveEditTextAndCloseIMM();
                            } else {
                                DismissDialog();
                                Utility.showToast(bean.getMsg(), SignInActivity.this);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Utility.showToast("请求失败，请重试！", SignInActivity.this);
                            DismissDialog();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("class_id", id);
                            map.put("lng", MyApplication.getLon()); //经度
                            map.put("lat", MyApplication.getLat()); //伟度
                            map.put("location", MyApplication.getAddr());//当前在哪个位置
                            map.put("unique_code", DEVICE_ID);//手机唯一标识符
                            map.put("time", time);//当前时间
                            Log.e("签到map",map.toString());
                            return map;
                        }
                    };
                    stringRequest.setTag("volleyget");
                    MyApplication.getHttpQueue().add(stringRequest);
                } else {
                    Utility.showToastNoNetWork(SignInActivity.this);
                }
                break;
        }
    }

    public static String getDeviceId(Context context) {
        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        deviceId.append("a");
        try {
            //wifi mac地址
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();
            if (!Utility.StringIsNull(wifiMac)) {
                deviceId.append("wifi");
                deviceId.append(wifiMac);

                return deviceId.toString();
            }
            //IMEI（imei）
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            if (!Utility.StringIsNull(imei)) {
                deviceId.append("imei");
                deviceId.append(imei);
                return deviceId.toString();
            }
            //序列号（sn）
            String sn = tm.getSimSerialNumber();
            if (!Utility.StringIsNull(sn)) {
                deviceId.append("sn");
                deviceId.append(sn);
                return deviceId.toString();
            }
            //如果上面都没有， 则生成一个id：随机码
            String uuid = getUUID(context);
            if (!Utility.StringIsNull(uuid)) {
                deviceId.append("id");
                deviceId.append(uuid);
                return deviceId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            deviceId.append("id").append(getUUID(context));
        }
        return deviceId.toString();
    }

    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context) {
        String uuid = null;
        String mShare = ShareReferenceUtils.getValue("sysCacheMap");
        if (mShare == null) {
            uuid = UUID.randomUUID().toString();
            ShareReferenceUtils.putValue("sysCacheMap", uuid);
        }


        return uuid;
    }
}
