//package com.android.homework.bean;
//
//import android.view.View;
//
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.LocationClient;
//import com.baidu.location.LocationClientOption;
//import com.baidu.mapapi.map.MapStatus;
//import com.baidu.mapapi.map.MapStatusUpdateFactory;
//import com.baidu.mapapi.map.MapView;
//import com.baidu.mapapi.map.MarkerOptions;
//import com.baidu.mapapi.map.MyLocationConfiguration;
//import com.baidu.mapapi.map.MyLocationData;
//import com.baidu.mapapi.map.Polyline;
//import com.baidu.mapapi.map.PolylineOptions;
//import com.baidu.mapapi.model.LatLng;
//import com.baidu.mapapi.utils.DistanceUtil;
//
///**
// * @auther 19062
// * @date 2023/8/23
// * @time 18:36.
// */
//
//
//public class kkk {
//
//
//    //伪代码
//    public void onCreate() {
//        mMapView = (MapView) findViewById(R.id.bmapView);
//        mBaiduMap = mMapView.getMap();
//        // 开启定位图层
//        mBaiduMap.setMyLocationEnabled(true);
//        /**添加地图缩放状态变化监听，当手动放大或缩小地图时，拿到缩放后的比例，
//        然后获取到下次定位，*  给地图重新设置缩放比例，否则地图会重新回到默认的mCurrentZoom缩放比例*/
//        mCurrentZoom = 18;
//        mBaiduMap.setOnMapStatusChangeListener(new OnMapStatusChangeListener() {
//            @Overridepublic
//            void onMapStatusChangeStart(MapStatus arg0) {
//
//            }
//            @Overridepublic
//            void onMapStatusChangeFinish(MapStatus arg0) {
//                mCurrentZoom = arg0.zoom;
//                //获取手指缩放地图后的值
//                 }
//                 @Overridepublic
//                 void onMapStatusChange(MapStatus arg0) {
//
//                 }});
//        //设置定位图标类型为跟随模式
//        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.FOLLOWING, true, null));
//        // 定位初始化
//        mLocClient = new LocationClient(this);
//        mLocClient.registerLocationListener(myListener);
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationMode.Device_Sensors);
//        //只接受gps位置
//        option.setOpenGps(true);
//        // 允许gps定位
//        option.setCoorType("bd09ll");
//        // 设置坐标类型
//        option.setScanSpan(1000);
//        //一秒一个gps
//        mLocClient.setLocOption(option);
//    }//开始获取位置点
//
//    public void onStart() {
//        start.setOnClickListener(new OnClickListener() {
//            @Overridepublic
//            void onClick(View v) {
//                if (mLocClient != null && !mLocClient.isStarted()) {
//                    mLocClient.start();
//                }
//            }
//        });
//    }//位置回调，取点很重要
//
//    public class MyLocationListenner implements BDLocationListener {
//        @Overridepublic
//        void onReceiveLocation(final BDLocation location) {
//            if (location == null || mMapView == null) {
//                return;
//            }
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {
//                //只要gps点
//                if (isFirstLoc) {
//                    //首次定位
//                    /**第一个点很重要，决定了轨迹的效果，gps刚接收到信号时返回的一些点精度不高，
//                     * * 尽量选一个精度相对较高的起始点，这个过程大概从gps刚接收到信号后5-10秒就可以完成，不影响效果。
//                     * * 注：gps接收卫星信号少则十几秒钟，多则几分钟，* 如果长时间手机收不到gps，
//                     * 退出，重启手机再试，这是硬件的原因*/
//                    LatLng ll = null;
//                    //选一个精度相对较高的起始点
//                    ll = getMostAccuracyLocation(location);
//                    if (ll == null) {
//                        return;
//                    }
//                    isFirstLoc = false;
//                    points.add(ll);
//                    //加入集合
//                    last = ll;
//                    //显示当前定位点，缩放地图
//                    locateAndZoom(location, ll);
//                    //标记起点图层位置
//                    MarkerOptions oStart = new MarkerOptions();
//                    // 地图标记覆盖物参数配置类
//                    oStart.position(points.get(0));
//                    // 覆盖物位置点，第一个点为起点
//                    oStart.icon(startBD);
//                    // 设置覆盖物图片
//                    mBaiduMap.addOverlay(oStart);
//                    // 在地图上添加此图层
//                    return;
//                    //画轨迹最少得2个点，首地定位到这里就可以返回了
//                }
//                //从第二个点开始
//                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
//                //sdk回调gps位置的频率是1秒1个，位置点太近动态画在图上不是很明显，可以设置点之间距离大于为5米才添加到集合中
//                if (DistanceUtil.getDistance(last, ll) < 5) {
//                    return;
//                }
//                points.add(ll);
//                //如果要运动完成后画整个轨迹，位置点都在这个集合中
//                last = ll;
//                //显示当前定位点，缩放地图
//                locateAndZoom(location, ll);
//                //清除上一次轨迹，避免重叠绘画
//                mMapView.getMap().clear();
//                //起始点图层也会被清除，重新绘画
//                MarkerOptions oStart = new MarkerOptions();
//                oStart.position(points.get(0));
//                oStart.icon(startBD);
//                mBaiduMap.addOverlay(oStart);
//                //将points集合中的点绘制轨迹线条图层，显示在地图
//                上OverlayOptions ooPolyline = new PolylineOptions().width(13).color(0xAAFF0000).points(points);
//                mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
//            }
//
//    }
//
//}//首次定位很重要，选一个精度相对较高的起始点
//
//    private LatLng getMostAccuracyLocation(final BDLocation location) {
//        if (location.getRadius() > 25) {
//            //gps位置精度大于25米的点直接弃用
//            return null;
//        }
//        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
//        if (DistanceUtil.getDistance(last, ll) > 5) {
//            last = ll;
//            points.clear();
//            //有两点位置大于5，重新来过
//            return null;
//        }
//        points.add(ll);
//        last = ll;
//        //有5个连续的点之间的距离小于5，认为gps已稳定，以最新的点为起始点
//        if (points.size() >= 5) {
//            points.clear();
//            return ll;
//        }
//        return null;
//    }
//}
//
////显示当前定位点，缩放地图
//private void locateAndZoom(BDLocation location,LatLng ll){/*** 记录当前经纬度，当位置不变，手机转动，取得方向传感器的方向，给地图重新设置位置参数，在跟随模式下可使地图箭头随手机转动而转动*/
//        mCurrentLat=location.getLatitude();
//        mCurrentLon=location.getLongitude();
//        locData=new MyLocationData.Builder().accuracy(0);// 去掉精度圈
//        // 此mCurrentDirection为自己获取到的手机传感器方向信息，顺时针0-360.
//        direction(mCurrentDirection).latitude(location.getLatitude())
//        .longitude(location.getLongitude()).build();
//        mBaiduMap.setMyLocationData(locData);
//        //显示当前定位位置点//给地图设置缩放中心点，和缩放比例值b
//        uilder=new MapStatus.Builder();
//        builder.target(ll).zoom(mCurrentZoom);
//        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//        }//运动结束增加终点图标
//        finish.setOnClickListener(new
//
//        OnClickListener(){
//@Overridepublic
//            void onClick(View v){
//                    if(mLocClient!=null&&mLocClient.isStarted()){
//                    mLocClient.stop();
//                    //停止定位
//                    if(points.size()<=0){
//                    return;
//                    }
//                    }
//                    //运动结束记得标记终点图标
//                    MarkerOptions oFinish=new MarkerOptions();
//                    oFinish.position(points.get(points.size()-1));
//                    oFinish.icon(finishBD);
//                    mBaiduMap.addOverlay(oFinish);
//                    }
//                    }
//                    });
//
////伪代码
//protected void onDestroy(){// 退出时销毁定位
//        mLocClient.unRegisterLocationListener(myListener);
//        mLocClient.stop();
//        // 关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);
//        mMapView.getMap().clear();
//        mMapView.onDestroy();
//        mMapView=null;
//        startBD.recycle();
//        finishBD.recycle();
//        }
//
//        }