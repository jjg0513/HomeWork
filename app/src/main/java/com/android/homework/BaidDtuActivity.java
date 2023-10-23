package com.android.homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polygon;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.utils.AreaUtil;
import com.baidu.mapapi.utils.DistanceUtil;

import java.util.ArrayList;
import java.util.List;

public class BaidDtuActivity extends AppCompatActivity implements SensorEventListener {

    private MapView mMapView;

    private LocationClient mLocClient;
    private BaiduMap mBaiduMap;

    private int mCurrentDirection = 0;
    private double markerLatitude = 0;//标点纬度
    private double markerLongitude = 0;//标点经度
    private MyLocationData locData;
    private Button buttonStart, buttonFinish;
    boolean isFirstLoc = true; // 是否首次定位
    private LatLng latLng;
    private SensorManager mSensorManager;
    private Sensor sensor;
    private LinearLayout layout;
    private TextView info;

    // 定义LocationManager对象
    private LocationManager locationManager;

    private double mLongitude;
    private double mLatitude;
    LocationListener listener;



    //起点图标
    BitmapDescriptor startBD = BitmapDescriptorFactory.fromResource(R.drawable.qqq);
    //终点图标
    BitmapDescriptor finishBD = BitmapDescriptorFactory.fromResource(R.drawable.zzz);

    List<LatLng> points = new ArrayList<LatLng>();//位置点集合

    Polyline mPolyline;//运动轨迹图层
    LatLng last = new LatLng(0, 0);//上一个定位点
    MapStatus.Builder builder;
    double lastX;
    private MyLocationListener myListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        try {
            mLocClient = new LocationClient(getApplicationContext());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        myListener = new MyLocationListener();
        mLocClient.registerLocationListener(myListener);

        setContentView(R.layout.activity_baid_dtu);

        initView();//视图初始化
        initLocation();// 定位初始化

        //添加这下面的一部分
//动态申请权限
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(BaidDtuActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(BaidDtuActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(BaidDtuActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(BaidDtuActivity.this, permissions, 1);
        }

        // 获取系统LocationManager服务
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 从GPS获取最近的定位信息
//        location = locationManager
//                .getLastKnownLocation(LocationManager.GPS_PROVIDER);


        // 设置每2秒获取一次GPS的定位信息
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                2000, 8, new LocationListener() {
//
//                    @Override
//                    public void onLocationChanged(Location location) {
//
//                        //获取位置变化结果
//                        float accuracy = location.getAccuracy();//精确度，以密为单位
//                        double altitude = location.getAltitude();//获取海拔高度
//                        mLongitude = location.getLongitude();//经度
//                        mLatitude = location.getLatitude();//纬度
//                        float speed = location.getSpeed();//速度
//
//
//                    }
//
//                    @Override
//                    public void onProviderDisabled(String provider) {
//
//                    }
//
//                    @Override
//                    public void onProviderEnabled(String provider) {
//                        // 当GPS LocationProvider可用时，更新位置
//
//
//                    }
//
//                    @Override
//                    public void onStatusChanged(String provider, int status,
//                                                Bundle extras) {
//                    }
//
//                });


//        设置定位信息
//        坐标位置改变，回调此监听方法
        listener = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub

            }

            //位置改变的时候调用，这个方法用于返回一些位置信息
            @Override
            public void onLocationChanged(Location location) {
                //获取位置变化结果
                float accuracy = location.getAccuracy();//精确度，以密为单位
                double altitude = location.getAltitude();//获取海拔高度
                mLongitude = location.getLongitude();//经度
                mLatitude = location.getLatitude();//纬度

                Log.e("dddd", "listener经度: "+mLongitude);
                Log.e("dddd", "listener纬度: "+mLatitude);


                float speed = location.getSpeed();//速度


            }
        };

        locationManager.requestLocationUpdates("gps", 0, 0, listener);//Register for location updates


    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
        // 为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);// 获取传感器管理服务
        if (mSensorManager != null) {
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }
        if (sensor != null) {
            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        // 取消注册传感器监听
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        // 在activity执行onDestroy时必须调用mMapView.onDestroy()
        mMapView.onDestroy();

    }


    private void initView() {
        // 地图初始化
        mMapView = (MapView) findViewById(R.id.bmapView);
        buttonStart = findViewById(R.id.buttonStart);
        buttonFinish = findViewById(R.id.buttonFinish);
        layout = findViewById(R.id.layout);
        info = findViewById(R.id.info);

//        mMapView.showScaleControl(false);  // 设置比例尺是否可见（true 可见/false不可见）
        mMapView.showZoomControls(false);  // 设置缩放控件是否可见（true 可见/false不可见）
        mMapView.removeViewAt(1);// 删除百度地图Logo


        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//普通地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//卫星地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);//空白地图


    }

    public void initLocation() {

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置高精度定位

        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
//        option.setCoorType("gcj02");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(5000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
//        option.setPriority(LocationClientOption.NETWORK_FIRST);
        option.setPriority(LocationClientOption.GPS_FIRST);

        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocClient.setLocOption(option);
        mLocClient.start();//开始定位

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ccm------>", "buttonStartL: " + (mLocClient != null) + " , " + (!mLocClient.isStarted()));

                if (mLocClient != null && !mLocClient.isStarted()) {

                    isFirstLoc = false;
                    mLocClient.start();
                    layout.setVisibility(View.VISIBLE);
                    info.setText("GPS信号搜索中，请稍后...");
                    mBaiduMap.clear();

                }
            }
        });

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLocClient != null && mLocClient.isStarted()) {
                    mLocClient.stop();

                    layout.setVisibility(View.GONE);
                    if (isFirstLoc) {
                        points.clear();
                        last = new LatLng(0, 0);
                        return;
                    }

                    MarkerOptions oFinish = new MarkerOptions();// 地图标记覆盖物参数配置类
                    oFinish.position(points.get(points.size() - 1));
                    oFinish.icon(finishBD);// 设置覆盖物图片
                    mBaiduMap.addOverlay(oFinish); // 在地图上添加此图层

                    //复位
                    points.clear();
                    last = new LatLng(0, 0);
                    isFirstLoc = true;

                }

            }
        });

        // 创建多边形覆盖物对象
//        OverlayOptions polygonOptions = new PolygonOptions()
//                .points(points)
//                .fillColor(Color.GREEN) // 设置填充颜色，如Color.GREEN
//                .stroke(new Stroke(5, Color.BLACK)); // 设置边框颜色和宽度，如5px的红色边框

// 添加多边形覆盖物到地图上
//        Polygon polygon = (Polygon)  mBaiduMap.addOverlay(polygonOptions);

// 调整地图视图，使覆盖物完全显示在屏幕上
//        MapStatusUpdate update = MapStatusUpdateFactory.newLatLngBounds(getBounds(points));
//        mBaiduMap.setMapStatus(update);

//        double area = calculatePolygonArea(points);
//        Log.e("asdf", "面积: "+area +"米" );


    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];

        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;

            if (isFirstLoc) {
                lastX = x;
                return;
            }

            locData = new MyLocationData.Builder().accuracy(0)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(mCurrentDirection).latitude(0.00).longitude(0.00).build();
//                    .direction(mCurrentDirection).longitude(mLongitude).latitude(mLatitude).build();
                    .direction(mCurrentDirection).latitude(mLatitude).longitude(mLongitude).build();

            Log.e("dddd", "onSensorChanged经度: "+mLatitude);
            Log.e("dddd", "onSensorChanged纬度: "+mLongitude);
            mBaiduMap.setMyLocationData(locData);
            MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, null);
            mBaiduMap.setMyLocationConfigeration(configuration);

        }
        lastX = x;

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.d("TAG", "getLatitude: "+location.getLatitude() +"getLongitude:  "+location.getLongitude());
            Toast.makeText(BaidDtuActivity.this, location.getAddrStr(), Toast.LENGTH_SHORT).show();
            double resultLatitude = 0;
            double resultLongitude = 0;
            // MapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }

            if (markerLatitude == 0 && markerLongitude == 0) {//自动定位
                resultLatitude = location.getLatitude();
                resultLongitude = location.getLongitude();

                Log.e("dddd", "经度: "+resultLongitude);
                Log.e("dddd", "纬度: "+resultLatitude);
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())// 设置定位数据的精度信息，单位：米
                        .direction(location.getDirection()) // 此处设置开发者获取到的方向信息，顺时针0-360
                        .latitude(resultLatitude)
                        .longitude(resultLongitude)
                        .build();

                mBaiduMap.setMyLocationData(locData);// 设置定位数据, 只有先允许定位图层后设置数据才会生效

                latLng = new LatLng(resultLatitude, resultLongitude);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(latLng).zoom(20.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            }



//            if (isFirstLoc) {//首次定位
//
////                latLng = getMostAccuracyLocation(location);
//
//                    if (latLng == null) {
//                        return;
//                    }
//                    isFirstLoc = false;
//
////                latLng = new LatLng(location.getLatitude(), location.getLongitude());
////                MapStatus.Builder builder = new MapStatus.Builder();
////                builder.target(latLng).zoom(20.0f);
////                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//
//                points.add(latLng);
//                    last = latLng;
////                    //显示当前定位点，缩放地图
//                MapStatus.Builder builder = new MapStatus.Builder();
//                builder.target(latLng).zoom(20.0f);
////                    locateAndZoom(location, latLng);
//                    //标记起点图层位置
//                    MarkerOptions oStart = new MarkerOptions();// 地图标记覆盖物参数配置类
//                    oStart.position(points.get(0));// 覆盖物位置点，第一个点为起点
//                    oStart.icon(startBD);// 设置覆盖物图片
//                    mBaiduMap.addOverlay(oStart); // 在地图上添加此图层
//
//                    layout.setVisibility(View.GONE);
//                    return;
//
//            }


            if (isFirstLoc) {//首次定位
                //第一个点很重要，决定了轨迹的效果，gps刚开始返回的一些点精度不高，尽量选一个精度相对较高的起始点
//                LatLng ll = null;
//                if (mLatitude == location.getLatitude() && mLongitude == location.getLongitude()) {
                latLng = getMostAccuracyLocation(location);
                if (latLng == null) {
                    return;
                }
                isFirstLoc = false;
                points.add(latLng);//加入集合
                last = latLng;

                //显示当前定位点，缩放地图
                locateAndZoom(location, latLng);

                //标记起点图层位置
                MarkerOptions oStart = new MarkerOptions();// 地图标记覆盖物参数配置类
                oStart.position(points.get(0));// 覆盖物位置点，第一个点为起点
                oStart.icon(startBD);// 设置覆盖物图片
                mBaiduMap.addOverlay(oStart); // 在地图上添加此图层

                layout.setVisibility(View.GONE);


                return;//画轨迹最少得2个点，首地定位到这里就可以返回了
//                }

            }
            //从第二个点开始
            LatLng lls = new LatLng(location.getLatitude(), location.getLongitude());
            //sdk回调gps位置的频率是1秒1个，位置点太近动态画在图上不是很明显，可以设置点之间距离大于为5米才添加到集合中
            if (DistanceUtil.getDistance(last, lls) < 5) {
                return;
            }

            points.add(lls);//如果要运动完成后画整个轨迹，位置点都在这个集合中

            last = lls;

            //显示当前定位点，缩放地图
            locateAndZoom(location, lls);

            //清除上一次轨迹，避免重叠绘画
            mMapView.getMap().clear();

            //起始点图层也会被清除，重新绘画
            MarkerOptions oStarts = new MarkerOptions();
            oStarts.position(points.get(0));
            oStarts.icon(startBD);
            mBaiduMap.addOverlay(oStarts);

            //将points集合中的点绘制轨迹线条图层，显示在地图上
            OverlayOptions ooPolyline = new PolylineOptions().width(13).color(Color.parseColor("#09A394")).points(points);
            mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);

            Log.e("asd", "onReceiveLocation: "+points.size() );

        }

    }

    private void locateAndZoom(final BDLocation location, LatLng ll) {
        markerLatitude = location.getLatitude();
        markerLongitude = location.getLongitude();
        locData = new MyLocationData.Builder().accuracy(0)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(mCurrentDirection).latitude(markerLatitude)
                .longitude(markerLongitude).build();
        Log.e("dddd", "locateAndZoom经度: "+markerLatitude);
        Log.e("dddd", "locateAndZoom纬度: "+markerLongitude);
        mBaiduMap.setMyLocationData(locData);
        MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
        mBaiduMap.setMyLocationConfigeration(configuration);


        builder = new MapStatus.Builder();
        builder.target(ll).zoom(20.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }


    /**
     * 首次定位很重要，选一个精度相对较高的起始点
     * 注意：如果一直显示gps信号弱，说明过滤的标准过高了，
     * 你可以将location.getRadius()>25中的过滤半径调大，比如>40，
     * 并且将连续5个点之间的距离DistanceUtil.getDistance(last, ll ) > 5也调大一点，比如>10，
     * 这里不是固定死的，你可以根据你的需求调整，如果你的轨迹刚开始效果不是很好，你可以将半径调小，两点之间距离也调小，
     * gps的精度半径一般是10-50米
     */
    private LatLng getMostAccuracyLocation(BDLocation location) {


        if (location.getRadius() > 40) {//gps位置精度大于40米的点直接弃用
            return null;
        }

        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
        Log.e("dddd", "getMostAccuracyLocation经度: "+location.getLatitude());
        Log.e("dddd", "getMostAccuracyLocation纬度: "+location.getLongitude());

        if (DistanceUtil.getDistance(last, ll) > 10) {
            last = ll;
            points.clear();//有任意连续两点位置大于10，重新取点
            return null;
        }
        points.add(ll);
        last = ll;
        //有5个连续的点之间的距离小于10，认为gps已稳定，以最新的点为起始点
        if (points.size() >= 5) {
            points.clear();
            return ll;
        }
        return null;
    }




}