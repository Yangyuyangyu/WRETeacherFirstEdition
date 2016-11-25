package com.wr_education.activity;

import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.util.com.baidu.mapapi.overlayutil.DrivingRouteOverlay;

import butterknife.Bind;

/**
 * 百度地图页面
 * Created by Administrator on 2016/5/4.
 */
public class BaiDuMapActivity extends AbstractActivity {
    @Bind(R.id.bmapView)
    MapView bmapView;
    private BaiduMap mBaiduMap;

    @Override
    protected void initLayout() {
        setContentView(R.layout.baidumap_layout);
    }

    @Override
    protected void aadListenter() {
    }

    @Override
    protected void initVariables() {
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

        if(MyApplication.getLat()!=null&&MyApplication.getLon()!=null){
            LatLng ll = new LatLng(Double.valueOf(MyApplication.getLat()), Double.valueOf(MyApplication.getLon()));
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(u);
            //自己的定位
            LatLng point = new LatLng(Double.valueOf(MyApplication.getLat()), Double.valueOf(MyApplication.getLon()));
//            //构建Marker图标
//            BitmapDescriptor bitmap = BitmapDescriptorFactory
//                    .fromResource(R.mipmap.icon_marka);
//            //构建MarkerOption，用于在地图上添加Marker
//            OverlayOptions option = new MarkerOptions()
//                    .position(point)
//                    .icon(bitmap);
//            //在地图上添加Marker，并显示
//            mBaiduMap.addOverlay(option);

            //要去的地方的定位
            LatLng point2 = new LatLng(30.6995310000,104.0201040000);
            RoutePlanSearch mSearch = RoutePlanSearch.newInstance();
            PlanNode stNode = PlanNode.withLocation(point);
            PlanNode enNode = PlanNode.withLocation(point2);
            OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
                @Override
                public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                    //获取步行线路规划结果
                }

                @Override
                public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
                    //获取公交换乘路径规划结果
                }

                @Override
                public void onGetDrivingRouteResult(DrivingRouteResult result) {
                    //获取驾车线路规划结果

                    if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                        Toast.makeText(BaiDuMapActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                    }
                    if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                        //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                        //result.getSuggestAddrInfo()
                        return;
                    }
                    if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                        DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                                mBaiduMap);
                        drivingRouteOverlay.setData(result.getRouteLines()
                                .get(0));// 设置一条驾车路线方案
                        mBaiduMap.setOnMarkerClickListener(drivingRouteOverlay);
                        drivingRouteOverlay.addToMap();
                        drivingRouteOverlay.zoomToSpan();
                    }
                }

                @Override
                public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

                }
            };
            mSearch.setOnGetRoutePlanResultListener(listener);

            mSearch.drivingSearch(new DrivingRoutePlanOption().from(stNode).to(enNode));

        }
    }




}
