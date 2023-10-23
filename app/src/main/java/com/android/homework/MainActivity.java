package com.android.homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.homework.bean.BaseControlBean;
import com.android.homework.bean.Can1Bean;
import com.android.homework.bean.InputDiagnosticsBean;
import com.android.homework.bean.OutputControlBean;
import com.android.homework.bean.RealTimeStatusBean;
import com.android.homework.bean.StateOfWorkBean;
import com.android.homework.bean.WzString;
import com.android.homework.constant.CarConstant;
import com.android.homework.inter.CANInterface;
import com.android.homework.observer.CanOneChangObserver;
import com.android.homework.observer.CanTwoChangObserver;
import com.android.homework.utils.CameraManagerHsj;
import com.android.homework.utils.CameraManagerLc;
import com.android.homework.utils.CameraManagerXlt;
import com.android.homework.utils.CameraUtilsWr;
import com.android.homework.utils.DataUtils;
import com.android.homework.view.NumberedProgressBarView;
import com.android.homework.view.SwProgressBarView;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.model.LatLng;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener, CanTwoChangObserver.CanChangeListener, CanOneChangObserver.CanChangeListener {
    //跳转
    private ImageView img_home, img_fh, img_sz, img_fs;
    private LinearLayout layoutimg_sz,layoutimg_home,layoutimg_fh,layoutimg_fs;

    //灯光
    private ImageView img_zzd, img_dd, img_xd, img_rgd, img_qwd, img_hwd, img_leftzy, img_rightzy, img_yzx;
    //速度转速
    private TextView zs_rpm, ya_kpa, dc_v;
    private TextView sd_km;
    private TextView text_P, text_R, text_N, text_D, text_1, text_2, text_3;
    //故障灯
    private ImageView img_fdj, img_sw, img_lqy, img_hoy, img_huy, img_bzd;
    private ImageView xp1, xp2, xp3, xzch, xfxp, img_diy,xp33;
    private ImageView img_xp4, img_able, img_zcd, img_pd;
    private TextView text_h, text_f;

    private ImageView cj;
    private LinearLayout layout_xp3,layout_xp2,layout_xp4,layout_xp33;
    private LinearLayout layoutxp3_view,layoutxp3cl_view,layoutxp3ms_view,layoutxp3ks_view;
    private ImageView textxp3_view,textxp3cl_view,textxp3ms_view,textxp3ks_view;

    private ImageView img_ywh,img_swh,xblqy,xbdy;


    private TextView textview_xp3,textview_xp2,textview_xp4,textview_xp33;


    private NumberedProgressBarView numbarview;
    private SwProgressBarView swbarview;

    private TextView texte_clxx;

    //灯光can2
    private RealTimeStatusBean realTimeStatusBean;
    //工作状态can2
    private StateOfWorkBean stateOfWorkBean;
    //输出控制can2
    private OutputControlBean outputControlBean;
    //输入诊断can2
    private InputDiagnosticsBean inputDiagnosticsBean;
    //基座can2
    private BaseControlBean baseControlBean;

    private Can1Bean can1Bean;


    //CAN
    private CANInterface canInterface;


    //工作数据
    private TextView text_gtgd, text_jsyw, text_xlgd, text_yyyw, text_bhlzs, text_qgyl, text_zlgt, text_fjzs, text_zljl, text_gzsc;

    private int fault1 = 0;
    private int fault2 = 0;

    //测亩
    private ImageView img_cx, img_ly, img_xh;
    private TextView text_sjs;
    private TextView text_start, text_end, text_mjdx, text_qs;
    private ImageView img_jia, img_jian;
    private MapView mMapView;


    private LocationClient mLocClient;
    private BaiduMap mBaiduMap;

    private BitmapDescriptor bitmap;//标点的图标
    private int mCurrentDirection = 0;
    private double markerLatitude = 0;//标点纬度
    private double markerLongitude = 0;//标点经度
    private MyLocationData locData;
    private Button buttonStart, buttonFinish;
    boolean isFirstLoc = true; // 是否首次定位
    private LatLng latLng;
    private SensorManager mSensorManager;
    private Sensor sensor;


    // 定义LocationManager对象
    private LocationManager locationManager;

    private Location location;
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


    //相机id
    private LinearLayout layout1, layout2;
    private RelativeLayout relayout_lc, relayout_wr, relayout_xlc, relayout_hsj;
    private ImageView img_lc, img_wr, img_xlt, img_hsj;
    private TextView lc_sj, wr_sj, xlt_sj, hsj_sj;

    private int year, month, day;
    private String mWay;


    private TextureView texview_lc, texview_wr, texview_xlt, texview_hsj;

    private SurfaceTexture surfaceTextureLc1, surfaceTextureLc2;
    private SurfaceTexture surfaceTextureWr1, surfaceTextureWr2;
    private SurfaceTexture surfaceTextureXlt1, surfaceTextureXlt2;
    private SurfaceTexture surfaceTextureHsj1, surfaceTextureHsj2;

    private LinearLayout view_showlc, view_showwr, view_showxlt, view_showhsj;
    private TextureView texview_showlc, texview_showwr, texview_showxlt, texview_showhsj;
    private ImageView img_showlc, img_showwr, img_showxlt, img_showhsj;

    private TextView lc_showsj, wr_showsj, xlt_showsj, hsj_showsj;

    private  WzString wzString = new WzString();

    private double longitude;
    private double latitude;
    public LocationClient mLocationClient = null;

    private boolean mFlag = true;
    private boolean isnum;




private  Handler handler = new Handler();
private  Handler handler1 = new Handler();

    private MyHandle myHandle = new MyHandle();


    private class MyHandle extends Handler {

        //弱引用，在垃圾回收时，activity可被回收
        private WeakReference<Context> mWeakReference;

        public MyHandle() {
            mWeakReference = new WeakReference<>(MainActivity.this);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long sysTime = System.currentTimeMillis();
                    long sysTimeData = System.currentTimeMillis();
                    CharSequence sysTimeStr = DateFormat.format("HH:mm:ss", sysTime);
                    CharSequence timeData = DateFormat.format("HH:mm", sysTimeData);

                    text_sjs.setText(timeData);
                    getDate();

                    lc_sj.setText(year + "年" + month + "月" + day + "日" + " 星期" + mWay + " " + sysTimeStr);
                    wr_sj.setText(year + "年" + month + "月" + day + "日" + " 星期" + mWay + " " + sysTimeStr);
                    xlt_sj.setText(year + "年" + month + "月" + day + "日" + " 星期" + mWay + " " + sysTimeStr);
                    hsj_sj.setText(year + "年" + month + "月" + day + "日" + " 星期" + mWay + " " + sysTimeStr);

                    lc_showsj.setText(year + "年" + month + "月" + day + "日" + " 星期" + mWay + " " + sysTimeStr);
                    wr_showsj.setText(year + "年" + month + "月" + day + "日" + " 星期" + mWay + " " + sysTimeStr);
                    xlt_showsj.setText(year + "年" + month + "月" + day + "日" + " 星期" + mWay + " " + sysTimeStr);
                    hsj_showsj.setText(year + "年" + month + "月" + day + "日" + " 星期" + mWay + " " + sysTimeStr);

                    break;

                case 2:
//                    setGone();
                    break;
                case 3:
                    Log.d("12321344","outputControlBean: "+outputControlBean);
                    if(outputControlBean!=null){
                        if( outputControlBean.getNumber()==0){
                            Log.d("12321344","数量:  "+outputControlBean.getNumber());
                            texte_clxx.setText("车辆工作正常");
                        }
                    }

                    break;
                case 4:

                        break;

                default:
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_HomeWork);
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        //添加这下面的一部分
//动态申请权限
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        hideNavigationBar();
        initPermission();
        // 定位初始化
        mLocationClient.setAgreePrivacy(true);
        try {
            mLocClient = new LocationClient(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        initView();
        new DataThread().start();

        initData();
        initXj();
        new TimeThread().start();


        can1Bean = new Can1Bean();
        realTimeStatusBean = new RealTimeStatusBean();
        stateOfWorkBean = new StateOfWorkBean();
        baseControlBean = new BaseControlBean();
        inputDiagnosticsBean = new InputDiagnosticsBean();
        outputControlBean = new OutputControlBean();

        canInterface = new CANInterface();
        canInterface.openCan2Interface(CarConstant.DEVICES_CAN2);
        canInterface.openCan1Interface(CarConstant.DEVICES_CAN1);


        CanOneChangObserver canOneChangObserver = new CanOneChangObserver();
        Log.d("ccm======>","canOneChangObserver 0");
        if (canOneChangObserver != null) {
            Log.d("ccm======>","canOneChangObserver 1");
            canOneChangObserver.setCanChangeListener(this);
        }

        CanTwoChangObserver canTwoChangObserver = new CanTwoChangObserver();
        if (canTwoChangObserver != null) {
            canTwoChangObserver.setCanChangeListener(this);
        }

        initCan1();
        initJxCan2RealTimeStatus();
        initJxCan2StateOfWork();
        initJxCan2InputDiagnostics();
        initJxCan2OutputControl();
        initJxCan2BaseControl();
        new can2sj().start();

          hideNavigationBar();

    }


    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private void initView() {
        //跳转
        img_home = findViewById(R.id.img_home);
        img_fh = findViewById(R.id.img_fh);
        img_sz = findViewById(R.id.img_sz);
        img_fs = findViewById(R.id.img_fs);

        layoutimg_sz = findViewById(R.id.layoutimg_sz);
        layoutimg_home = findViewById(R.id.layoutimg_home);
        layoutimg_fh = findViewById(R.id.layoutimg_fh);
        layoutimg_fs = findViewById(R.id.layoutimg_fs);

        //灯光
        img_zzd = findViewById(R.id.img_zzd);
        img_dd = findViewById(R.id.img_dd);
        img_xd = findViewById(R.id.img_xd);
        img_rgd = findViewById(R.id.img_rgd);
        img_qwd = findViewById(R.id.img_qwd);
        img_hwd = findViewById(R.id.img_hwd);
        img_leftzy = findViewById(R.id.img_leftzy);
        img_rightzy = findViewById(R.id.img_rightzy);
        img_yzx = findViewById(R.id.img_yzx);

        //转速速度
        zs_rpm = findViewById(R.id.zs_rpm);
        ya_kpa = findViewById(R.id.ya_kpa);
        dc_v = findViewById(R.id.dc_v);

        sd_km = findViewById(R.id.sd_km);

        text_P = findViewById(R.id.text_P);
        text_R = findViewById(R.id.text_R);
        text_N = findViewById(R.id.text_N);
        text_D = findViewById(R.id.text_D);
        text_1 = findViewById(R.id.text_1);
        text_2 = findViewById(R.id.text_2);
        text_3 = findViewById(R.id.text_3);


        //故障灯
        img_fdj = findViewById(R.id.img_fdj);
        img_sw = findViewById(R.id.img_sw);
        img_lqy = findViewById(R.id.img_lqy);
        img_hoy = findViewById(R.id.img_hoy);
        img_huy = findViewById(R.id.img_huy);
        img_bzd = findViewById(R.id.img_bzd);

        xp1 = findViewById(R.id.xp1);
        xp2 = findViewById(R.id.xp2);
        xp3 = findViewById(R.id.xp3);
        xp33 = findViewById(R.id.xp33);
        xzch = findViewById(R.id.xzch);
        xfxp = findViewById(R.id.xfxp);
        img_diy = findViewById(R.id.img_diy);

        img_xp4 = findViewById(R.id.img_xp4);
        img_able = findViewById(R.id.img_able);
        img_zcd = findViewById(R.id.img_zcd);
        img_pd = findViewById(R.id.img_pd);

        texte_clxx = findViewById(R.id.texte_clxx);
        text_h = findViewById(R.id.text_h);
        text_f = findViewById(R.id.text_f);

        cj = findViewById(R.id.cj);

        img_ywh = findViewById(R.id.img_ywh);
        img_swh = findViewById(R.id.img_swh);
        xblqy = findViewById(R.id.xblqy);
        xbdy = findViewById(R.id.xbdy);

        layout_xp3 = findViewById(R.id.layout_xp3);
        layout_xp33 = findViewById(R.id.layout_xp33);
        layout_xp2 = findViewById(R.id.layout_xp2);
        layout_xp4 = findViewById(R.id.layout_xp4);
        layoutxp3_view = findViewById(R.id.layoutxp3_view);
        layoutxp3cl_view = findViewById(R.id.layoutxp3cl_view);
        layoutxp3ms_view = findViewById(R.id.layoutxp3ms_view);
        layoutxp3ks_view = findViewById(R.id.layoutxp3ks_view);

        textview_xp3 = findViewById(R.id.textview_xp3);
        textview_xp33 = findViewById(R.id.textview_xp33);
        textview_xp2 = findViewById(R.id.textview_xp2);
        textview_xp4 = findViewById(R.id.textview_xp4);

        textxp3_view = findViewById(R.id.textxp3_view);
        textxp3cl_view = findViewById(R.id.textxp3cl_view);
        textxp3ms_view = findViewById(R.id.textxp3ms_view);
        textxp3ks_view = findViewById(R.id.textxp3ks_view);

        numbarview = findViewById(R.id.numbarview);
        swbarview = findViewById(R.id.swbarview);

        //工作数据
        text_gtgd = findViewById(R.id.text_gtgd);
        text_jsyw = findViewById(R.id.text_jsyw);
        text_xlgd = findViewById(R.id.text_xlgd);
        text_yyyw = findViewById(R.id.text_yyyw);
        text_bhlzs = findViewById(R.id.text_bhlzs);
        text_qgyl = findViewById(R.id.text_qgyl);
        text_zlgt = findViewById(R.id.text_zlgt);
        text_fjzs = findViewById(R.id.text_fjzs);
        text_zljl = findViewById(R.id.text_zljl);
        text_gzsc = findViewById(R.id.text_gzsc);

        //测亩
        text_sjs = findViewById(R.id.text_sjs);
        img_cx = findViewById(R.id.img_cx);
        img_ly = findViewById(R.id.img_ly);
        img_xh = findViewById(R.id.img_xh);
        text_start = findViewById(R.id.text_start);
        text_end = findViewById(R.id.text_end);
        text_mjdx = findViewById(R.id.text_mjdx);
        text_qs = findViewById(R.id.text_qs);
        img_jia = findViewById(R.id.img_jia);
        img_jian = findViewById(R.id.img_jian);

//        mMapView = findViewById(R.id.map);
//        mMapView.showScaleControl(false);  // 设置比例尺是否可见（true 可见/false不可见）
//        mMapView.showZoomControls(false);  // 设置缩放控件是否可见（true 可见/false不可见）
//        mMapView.removeViewAt(1);// 删除百度地图Logo
//        mBaiduMap = mMapView.getMap();

//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//普通地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//卫星地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);//空白地图
//        int nightMode = AppCompatDelegate.getDefaultNightMode();

//        mBaiduMap.setMapBackgroundColor(Color.argb(255, 255, 0, 0));


        //相机RelativeLayout
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        relayout_lc = findViewById(R.id.relayout_lc);
        relayout_wr = findViewById(R.id.relayout_wr);
        relayout_xlc = findViewById(R.id.relayout_xlc);
        relayout_hsj = findViewById(R.id.relayout_hsj);
        texview_lc = findViewById(R.id.texview_lc);
        texview_wr = findViewById(R.id.texview_wr);
        texview_xlt = findViewById(R.id.texview_xlt);
        texview_hsj = findViewById(R.id.texview_hsj);


        //相机图片
        img_lc = findViewById(R.id.img_lc);
        img_wr = findViewById(R.id.img_wr);
        img_xlt = findViewById(R.id.img_xlt);
        img_hsj = findViewById(R.id.img_hsj);
        //相机时间
        lc_sj = findViewById(R.id.lc_sj);
        wr_sj = findViewById(R.id.wr_sj);
        xlt_sj = findViewById(R.id.xlt_sj);
        hsj_sj = findViewById(R.id.hsj_sj);

        view_showlc = findViewById(R.id.view_showlc);
        view_showwr = findViewById(R.id.view_showwr);
        view_showxlt = findViewById(R.id.view_showxlt);
        view_showhsj = findViewById(R.id.view_showhsj);


        texview_showlc = findViewById(R.id.texview_showlc);
        texview_showwr = findViewById(R.id.texview_showwr);
        texview_showxlt = findViewById(R.id.texview_showxlt);
        texview_showhsj = findViewById(R.id.texview_showhsj);

        img_showlc = findViewById(R.id.img_showlc);
        img_showwr = findViewById(R.id.img_showwr);
        img_showxlt = findViewById(R.id.img_showxlt);
        img_showhsj = findViewById(R.id.img_showhsj);

        lc_showsj = findViewById(R.id.lc_showsj);
        wr_showsj = findViewById(R.id.wr_showsj);
        xlt_showsj = findViewById(R.id.xlt_showsj);
        hsj_showsj = findViewById(R.id.hsj_showsj);



    }


    private void initXj() {
        texview_lc.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                surfaceTextureLc1 = surface;
                CameraManagerLc.getInstance().openCamera(MainActivity.this, 0);
                CameraManagerLc.getInstance().startPreview(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                CameraManagerLc.getInstance().stopCamera();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {
                CameraManagerLc.getInstance().openCamera(MainActivity.this, 0);
                CameraManagerLc.getInstance().startPreview(surface);
            }
        });

        texview_showlc.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                surfaceTextureLc2 = surface;
                CameraManagerLc.getInstance().openCamera(MainActivity.this, 0);
                CameraManagerLc.getInstance().startPreview(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                CameraManagerLc.getInstance().stopCamera();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {
                CameraManagerLc.getInstance().openCamera(MainActivity.this, 0);
                CameraManagerLc.getInstance().startPreview(surface);
            }
        });

        texview_lc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraManagerLc.getInstance().stopCamera();
                texview_lc.setVisibility(View.GONE);
                view_showlc.setVisibility(View.VISIBLE);

                if (surfaceTextureLc2 != null) {
                    CameraManagerLc.getInstance().openCamera(MainActivity.this, 0);
                    CameraManagerLc.getInstance().startPreview(surfaceTextureLc2);
                }

            }
        });
        view_showlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraManagerLc.getInstance().stopCamera();
                view_showlc.setVisibility(View.GONE);
                texview_lc.setVisibility(View.VISIBLE);
                CameraManagerLc.getInstance().openCamera(MainActivity.this, 0);
                CameraManagerLc.getInstance().startPreview(surfaceTextureLc1);
            }
        });


        texview_wr.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                surfaceTextureWr1 = surface;
                CameraUtilsWr.getInstance().openCamera(MainActivity.this, 1);
                CameraUtilsWr.getInstance().startPreview(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                CameraUtilsWr.getInstance().stopCamera();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {
                CameraUtilsWr.getInstance().openCamera(MainActivity.this, 1);
                CameraUtilsWr.getInstance().startPreview(surface);
            }
        });


        texview_showwr.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                surfaceTextureWr2 = surface;
                CameraUtilsWr.getInstance().openCamera(MainActivity.this, 1);
                CameraUtilsWr.getInstance().startPreview(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                CameraUtilsWr.getInstance().stopCamera();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {
                CameraUtilsWr.getInstance().openCamera(MainActivity.this, 1);
                CameraUtilsWr.getInstance().startPreview(surface);
            }
        });


        texview_wr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraUtilsWr.getInstance().stopCamera();
                texview_wr.setVisibility(View.GONE);
                view_showwr.setVisibility(View.VISIBLE);
                if (surfaceTextureWr2 != null) {
                    CameraUtilsWr.getInstance().openCamera(MainActivity.this, 1);
                    CameraUtilsWr.getInstance().startPreview(surfaceTextureWr2);
                }
            }
        });

        view_showwr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraUtilsWr.getInstance().stopCamera();
                view_showwr.setVisibility(View.GONE);
                texview_wr.setVisibility(View.VISIBLE);
                CameraUtilsWr.getInstance().openCamera(MainActivity.this, 1);
                CameraUtilsWr.getInstance().startPreview(surfaceTextureWr1);
            }
        });

        texview_xlt.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                surfaceTextureXlt1 = surface;
                CameraManagerXlt.getInstance().openCamera(MainActivity.this, 2);
                CameraManagerXlt.getInstance().startPreview(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                CameraManagerXlt.getInstance().stopCamera();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {
                CameraManagerXlt.getInstance().openCamera(MainActivity.this, 2);
                CameraManagerXlt.getInstance().startPreview(surface);
            }
        });

        texview_showxlt.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                surfaceTextureXlt2 = surface;
                CameraManagerXlt.getInstance().openCamera(MainActivity.this, 2);
                CameraManagerXlt.getInstance().startPreview(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                CameraManagerXlt.getInstance().stopCamera();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {
                CameraManagerXlt.getInstance().openCamera(MainActivity.this, 2);
                CameraManagerXlt.getInstance().startPreview(surface);
            }
        });

        texview_xlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraManagerXlt.getInstance().stopCamera();
                texview_xlt.setVisibility(View.GONE);
                view_showxlt.setVisibility(View.VISIBLE);
                if (surfaceTextureXlt2 != null) {
                    CameraManagerXlt.getInstance().openCamera(MainActivity.this, 2);
                    CameraManagerXlt.getInstance().startPreview(surfaceTextureXlt2);
                }
            }
        });
        view_showxlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraManagerXlt.getInstance().stopCamera();
                view_showxlt.setVisibility(View.GONE);
                texview_xlt.setVisibility(View.VISIBLE);
                CameraManagerXlt.getInstance().openCamera(MainActivity.this, 2);
                CameraManagerXlt.getInstance().startPreview(surfaceTextureXlt1);
            }
        });

        texview_hsj.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                surfaceTextureHsj1 = surface;
                CameraManagerHsj.getInstance().openCamera(MainActivity.this, 3);
                CameraManagerHsj.getInstance().startPreview(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                CameraManagerHsj.getInstance().stopCamera();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {
                CameraManagerHsj.getInstance().openCamera(MainActivity.this, 3);
                CameraManagerHsj.getInstance().startPreview(surface);
            }
        });

        texview_showhsj.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                surfaceTextureHsj2 = surface;
                CameraManagerHsj.getInstance().openCamera(MainActivity.this, 3);
                CameraManagerHsj.getInstance().startPreview(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
                CameraManagerHsj.getInstance().stopCamera();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {
                CameraManagerHsj.getInstance().openCamera(MainActivity.this, 3);
                CameraManagerHsj.getInstance().startPreview(surface);
            }
        });

        texview_hsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraManagerHsj.getInstance().stopCamera();
                texview_hsj.setVisibility(View.GONE);
                view_showhsj.setVisibility(View.VISIBLE);

                if (surfaceTextureHsj2 != null) {
                    CameraManagerHsj.getInstance().openCamera(MainActivity.this, 3);
                    CameraManagerHsj.getInstance().startPreview(surfaceTextureHsj2);
                }
            }
        });
        view_showhsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraManagerHsj.getInstance().stopCamera();
                view_showhsj.setVisibility(View.GONE);
                texview_hsj.setVisibility(View.VISIBLE);
                CameraManagerHsj.getInstance().openCamera(MainActivity.this, 3);
                CameraManagerHsj.getInstance().startPreview(surfaceTextureHsj1);
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


    private void initData() {
        // 开启定位图层
//        mBaiduMap.setMyLocationEnabled(true);
//
        myListener = new MyLocationListener();
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置高精度定位
////
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
//        option.setCoorType("gcj02");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(1000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocClient.setLocOption(option);
        mLocClient.start();//开始定位

        layoutimg_sz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_sz.setPressed(!img_sz.isPressed());
            }
        });

        layoutimg_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img_home.setPressed(!img_home.isPressed());

                CameraManagerLc.getInstance().stopCamera();
                CameraUtilsWr.getInstance().stopCamera();
                CameraManagerXlt.getInstance().stopCamera();
                CameraManagerHsj.getInstance().stopCamera();

                //前提：知道要跳转应用的包名、类名
                Intent intent = new Intent(Intent.ACTION_MAIN);
//ComponentName的第一处是package的包名，第二个是锁需要进行跳转的类名
                ComponentName componentName = new ComponentName("com.example.launcher", "com.example.launcher.MainActivity");
                intent.setComponent(componentName);
                startActivity(intent);


                Log.d("dd", "onClick:home ");
            }
        });
        layoutimg_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_fh.setPressed(!img_fh.isPressed());

                CameraManagerLc.getInstance().stopCamera();
                CameraUtilsWr.getInstance().stopCamera();
                CameraManagerXlt.getInstance().stopCamera();
                CameraManagerHsj.getInstance().stopCamera();

                //前提：知道要跳转应用的包名、类名
                Intent intent = new Intent(Intent.ACTION_MAIN);
//ComponentName的第一处是package的包名，第二个是锁需要进行跳转的类名
                ComponentName componentName = new ComponentName("com.example.launcher", "com.example.launcher.MainActivity");
                intent.setComponent(componentName);
                startActivity(intent);

                Log.d("dd", "onClick:fanhui ");
            }

        });

        layoutimg_fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_fs.setPressed(!img_fs.isPressed());
                Log.d("lkj", "跳转000000000000000000000");

                CameraManagerLc.getInstance().stopCamera();
                CameraUtilsWr.getInstance().stopCamera();
                CameraManagerXlt.getInstance().stopCamera();
                CameraManagerHsj.getInstance().stopCamera();

                Intent intent = new Intent(Intent.ACTION_MAIN);
                Log.d("lkj", "跳转1111111111111111");
//ComponentName的第一处是package的包名，第二个是锁需要进行跳转的类名
                ComponentName componentName = new ComponentName("com.ivicar.avm", "com.ivicar.modules.main.view.MainActivity");
                intent.setComponent(componentName);
                startActivity(intent);

//                finish();

            }
        });
        zs_rpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, XltActivity.class));
            }
        });

        img_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        img_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        text_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isnum){
                    isnum=false;
                    text_start.setText("结束");
                    text_start.setBackgroundResource(R.mipmap.xkg);
                }else {
                    isnum=true;
                    text_start.setText("开始");
                    text_start.setBackgroundResource(R.mipmap.xkgc);
                }

//                if (mLocClient != null && !mLocClient.isStarted()) {
//
//                    isFirstLoc = true;
//                    mLocClient.start();
//                    mBaiduMap.clear();
//                }
            }
        });

        text_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mLocClient != null && mLocClient.isStarted()) {
//                    mLocClient.stop();
//
//                    if (isFirstLoc) {
//                        points.clear();
//                        last = new LatLng(0, 0);
//                        return;
//                    }
//
//                    MarkerOptions oFinish = new MarkerOptions();// 地图标记覆盖物参数配置类
//                    oFinish.position(points.get(points.size() - 1));
//                    oFinish.icon(finishBD);// 设置覆盖物图片
//                    mBaiduMap.addOverlay(oFinish); // 在地图上添加此图层
//
//                    //复位
//                    points.clear();
//                    last = new LatLng(0, 0);
//                    isFirstLoc = true;
//                }

            }
        });

    }

    @Override
    public void onCan1Changed(String part1, String part2) {
        String str1 = "0x";
        int x = 8;
        String substring1 = part1.substring(0, 2);
        String substring2 = part1.substring(3, 5);
        String substring3 = part1.substring(6, 8);
        String substring4 = String.valueOf(Integer.parseInt(part1.substring(9, 10)) - x) + part1.substring(10, 11);

        String can1Id = str1 + substring4 + substring3 + substring2 + substring1;
        String str2 = can1Id + " " + part2;
        String[] split1 = str2.split(" ");
        Log.d("c1", "split1: " + split1[0] + " , " + split1[1] + " , " + split1[2] + " , " + split1[3] + " , " + split1[4] + " , " + split1[5] + " , " + split1[6] + " , " + split1[7] + " , " + split1[8]);

        String can1Sp1 = split1[1];
        String can1Sp2 = split1[2];
        String can1Sp3 = split1[3];
        String can1Sp4 = split1[4];
        String can1Sp5 = split1[5];
        String can1Sp6 = split1[6];
        String can1Sp7 = split1[7];
        String can1Sp8 = split1[8];


        Log.d("c1", "split1: " + split1[0]);

        if (split1[0].equals(CarConstant.SPEED_OF_VEHICLE_ID)) {
            can1Bean.setSpeedOfVehicle(DataUtils.HexTo10Int(can1Sp7 + can1Sp8));
        } else if (split1[0].equals(CarConstant.ROTATIONAL_SPEED_ID)) {
            can1Bean.setRotationalSpeed((int) (DataUtils.HexTo10Int(can1Sp5 + can1Sp4) * 0.125));
        } else if (split1[0].equals(CarConstant.REACTANT_ALLOWANCE_ID)) {
            Log.d("c1", "dsadyiuyasuifyiuyf,can1Sp1: " + can1Sp1);
            can1Bean.setReactantAllowance((int) (DataUtils.HexTo10Int(can1Sp1) * 0.4 ));
            Log.e("nsssssssssssssss", "onCan1Changed: "+ (int) (DataUtils.HexTo10Int(can1Sp1) * 0.4 ));

        }else if(split1[0].equals(CarConstant.SINGLE_FAULT_CODE_ID)){
            can1Bean.setDpfWarningLight(DataUtils.HexTo10Int(can1Sp1));

            fault1 = DataUtils.HexTo10Int(can1Sp3);
            Log.d("ccm------>11111111", "i1: " + fault1);
        } else if (split1[0].equals(CarConstant.ALARM_LIGHT_ID)) {
            //0 1 2 3 4 5 6 7
            //7 6 5 4 3 2 1 0

            can1Bean.setDpfProhibition(DataUtils.HexTo10Int(can1Sp3));

            String s1 = DataUtils.HexTo2String(can1Sp1);
            String s2 = DataUtils.HexTo2String(can1Sp2);
            while (s1.length() < 8) {
                s1 = "0" + s1;
            }
            Log.d("s11111111", "wwwwwwww: "+s1.substring(5,8));

            can1Bean.setDriverWarningLight(Integer.parseInt(s1.substring(5,8)));
            while (s2.length() < 8) {
                s2 = "0" + s2;
            }
            Log.d("s11111111", "333333333: "+s2.substring(4,6));
            can1Bean.setRegenerationreminder(Integer.parseInt(s2.substring(4,6)));

            can1Bean.setExcessiveExhaustTemperature(DataUtils.HexTo10Int(can1Sp7));
        } else if (split1[0].equals(CarConstant.WATER_TEMPERATURE_ID)) {
            can1Bean.setWaterTemperature(DataUtils.HexTo10Int(can1Sp1) - 40);
        } else if (split1[0].equals(CarConstant.OIL_PRESSURE_ID)) {
            can1Bean.setOilPressure(DataUtils.HexTo10Int(can1Sp4) * 4);

        } else if (split1[0].equals(CarConstant.WATER_IN_OIL_ID)) {
            can1Bean.setWaterInOil(DataUtils.HexTo10Int(can1Sp1));

        } else if (split1[0].equals(CarConstant.ENGINE_WARM_UP_LIGHT_ID)) {
            String s5 = DataUtils.HexTo2String(can1Sp5);
            // 解析第一个字节
            while (s5.length() < 8) {
                s5 = "0" + s5;
            }
            can1Bean.setEngineWarmUpLight(Integer.parseInt(s5.substring(1, 2)));
        } else if (split1[0].equals(CarConstant.ENGINE_INPUT_VOLTAGE_ID)) {
            can1Bean.setEngineInputVoltage((float) (DataUtils.HexTo10Int(can1Sp6 + can1Sp5) * 0.05));
        }  else if (split1[0].equals(CarConstant.INFORMATION_CIRCULAR_ID)) {
            fault2 = DataUtils.HexTo10Int(can1Sp2);
        }
        if (fault1 == 0 && fault2 == 0) {
            can1Bean.setFaultCode(0);
        } else {
            can1Bean.setFaultCode(1);
        }


    }



    @Override
    public void onCan2Changed(String part1, String part2) {
        String str1 = "0x";
        String substring1 = part1.substring(0, 2);
        String substring2 = part1.substring(3, 5);
        String can2Id = str1 + substring2 + substring1;
        String str2 = can2Id + " " + part2;
        String[] split2 = str2.split(" ");
        Log.d("c2", "split2: " + split2[0] + " , " + split2[1] + " , " + split2[2] + " , " + split2[3] + " , " + split2[4] + " , " + split2[5] + " , " + split2[6] + " , " + split2[7] + " , " + split2[8]);

        String can2Sp1 = split2[1];
        String can2Sp2 = split2[2];
        String can2Sp3 = split2[3];
        String can2Sp4 = split2[4];
        String can2Sp5 = split2[5];
        String can2Sp6 = split2[6];
        String can2Sp7 = split2[7];
        String can2Sp8 = split2[8];

        if (split2[0].equals(CarConstant.REALTIME_STATUS_ID)) {

            String s1 = DataUtils.HexTo2String(can2Sp1);
            Log.d("s11", "33333333: "+s1.length());
            String s2 = DataUtils.HexTo2String(can2Sp2);
            int s3 = DataUtils.HexTo10Int(can2Sp3);
            int s4 = DataUtils.HexTo10Int(can2Sp4);
            int s5 = DataUtils.HexTo10Int(can2Sp5);
            int s6 = DataUtils.HexTo10Int(can2Sp6);
            String s7 = DataUtils.HexTo2String(can2Sp7);
            String s8 = DataUtils.HexTo2String(can2Sp8);

            Log.d("c2", "s1: " + s1 + " ，s2: " + s2 + " ，s3: " + s3 + " ，s4: " + s4 + " ，s5: " + s5 + " ，s6: " + s6 + " ，s7: " + s7 + " ，s8: " + s8);
            // 解析第一个字节
            while (s1.length() < 8) {
                s1 = "0" + s1;
            }
            realTimeStatusBean.setTurnLeftLightSignal(Integer.parseInt(s1.substring(7, 8)));
            realTimeStatusBean.setRightTurnLightSignal(Integer.parseInt(s1.substring(6, 7)));
            realTimeStatusBean.setHighBeamSignal(Integer.parseInt(s1.substring(5, 6)));
            realTimeStatusBean.setNearLightSignal(Integer.parseInt(s1.substring(4, 5)));
            realTimeStatusBean.setSignalOfProfileLight(Integer.parseInt(s1.substring(3, 4)));
            realTimeStatusBean.setRearFogLamp(Integer.parseInt(s1.substring(2, 3)));
            realTimeStatusBean.setMainDrivingSeatBelt(Integer.parseInt(s1.substring(1, 2)));
            realTimeStatusBean.setPassengerSeatBelt(Integer.parseInt(s1.substring(0, 1)));

            // 解析第二个字节
            while (s2.length() < 8) {
                s2 = "0" + s2;
            }
            realTimeStatusBean.setPositionLight(Integer.parseInt(s2.substring(7, 8)));
            realTimeStatusBean.setParkingSignal(Integer.parseInt(s2.substring(6, 7)));
            realTimeStatusBean.setPowerGenerationSignal(Integer.parseInt(s2.substring(5, 6)));
            realTimeStatusBean.setReverseSignal(Integer.parseInt(s2.substring(4, 5)));

            Log.d("dcss", "onCan2Changed: "+Integer.parseInt(s2.substring(4, 5)));

//            realTimeStatusBean.setFuelLevel(DataUtils.HexTo10Int(s2.substring(1, 4)));00

            realTimeStatusBean.setFuelLevel(DataUtils.mum2(s2.substring(0,3)));


            // 解析第三个字节
            realTimeStatusBean.setHydraulicOilTemperature(s3);

            // 解析第四个字节
            realTimeStatusBean.setGasTankPressure(s4);

            // 解析第五个字节
            realTimeStatusBean.setCuttingTableAngleSensor(s5);

            // 解析第六个字节
            realTimeStatusBean.setUnloadingCylinderAngleSensor(s6);

            // 解析第七个字节
            while (s7.length() < 4) {
                s7 = "0" + s7;
            }
            realTimeStatusBean.setModeOfWork(Integer.parseInt(s7.substring(3, 4)));
            realTimeStatusBean.setAutopilotStatus(Integer.parseInt(s7.substring(1, 3)));

            Log.d("mRealTimeStatusList", "realTimeStatusBean: " + realTimeStatusBean.toString());

        } else if (split2[0].equals(CarConstant.WORKING_CONDITION_ID)) {
            Log.d("mStateOfWorkList", "11111111111111111111111: ");
            // 解析第一，二个字节
            stateOfWorkBean.setRotationSpeedOfTheWheel(DataUtils.HexTo10Int(can2Sp1 + can2Sp2));
            // 解析第三，四个字节
            stateOfWorkBean.setAxialFlowDrumSpeed(DataUtils.HexTo10Int(can2Sp3 + can2Sp4));
            // 解析第五，六个字节
            stateOfWorkBean.setGrainAugerRotationSpeed(DataUtils.HexTo10Int(can2Sp5 + can2Sp6));
            // 解析第七，八个字节
            stateOfWorkBean.setSpeedOfFan(DataUtils.HexTo10Int(can2Sp7 + can2Sp8));
            Log.d("mStateOfWorkList", "stateOfWorkBean: " + stateOfWorkBean.toString());


        } else if (split2[0].equals(CarConstant.INPUT_DIAGNOSIS_ID)) {

            String s1 = DataUtils.HexTo2String(can2Sp1);
            String s2 = DataUtils.HexTo2String(can2Sp2);
            String s3 = DataUtils.HexTo2String(can2Sp3);
            String s4 = DataUtils.HexTo2String(can2Sp4);
            String s5 = DataUtils.HexTo2String(can2Sp5);
            String s6 = DataUtils.HexTo2String(can2Sp6);
            String s7 = DataUtils.HexTo2String(can2Sp7);
            String s8 = DataUtils.HexTo2String(can2Sp8);

            // 解析第一个字节
            while (s1.length() < 8) {
                s1 = "0" + s1;
            }
            outputControlBean.setPowerOut1(Integer.parseInt(s1.substring(6, 8)));
            outputControlBean.setPowerOut2(Integer.parseInt(s1.substring(4, 6)));
            outputControlBean.setPowerOut3(Integer.parseInt(s1.substring(2, 4)));
            outputControlBean.setPowerOut4(Integer.parseInt(s1.substring(0, 2)));

            // 解析第二个字节
            while (s2.length() < 8) {
                s2 = "0" + s2;
            }
            outputControlBean.setPowerOut5(Integer.parseInt(s2.substring(6, 8)));
            outputControlBean.setPowerOut6(Integer.parseInt(s2.substring(4, 6)));
            outputControlBean.setPowerOut7(Integer.parseInt(s2.substring(2, 4)));
            outputControlBean.setPowerOut8(Integer.parseInt(s2.substring(0, 2)));

            // 解析第三个字节
            while (s3.length() < 8) {
                s3 = "0" + s3;
            }
            outputControlBean.setPowerOut9(Integer.parseInt(s3.substring(6, 8)));
            outputControlBean.setPowerOut10(Integer.parseInt(s3.substring(4, 6)));
            outputControlBean.setPowerOut11(Integer.parseInt(s3.substring(2, 4)));
            outputControlBean.setPowerOut12(Integer.parseInt(s3.substring(0, 2)));

            // 解析第四个字节
            while (s4.length() < 8) {
                s4 = "0" + s4;
            }
            outputControlBean.setPowerOut13(Integer.parseInt(s4.substring(6, 8)));
            outputControlBean.setPowerOut14(Integer.parseInt(s4.substring(4, 6)));
            outputControlBean.setPowerOut15(Integer.parseInt(s4.substring(2, 4)));
            outputControlBean.setPowerOut16(Integer.parseInt(s4.substring(0, 2)));

            // 解析第五个字节
            while (s5.length() < 8) {
                s5 = "0" + s5;
            }
            outputControlBean.setPowerOut17(Integer.parseInt(s5.substring(6, 8)));
            outputControlBean.setPowerOut18(Integer.parseInt(s5.substring(4, 6)));
            outputControlBean.setPowerOut19(Integer.parseInt(s5.substring(2, 4)));
            outputControlBean.setPowerOut20(Integer.parseInt(s5.substring(0, 2)));

            // 解析第六个字节
            while (s6.length() < 8) {
                s6 = "0" + s6;
            }
            outputControlBean.setPowerOut21(Integer.parseInt(s6.substring(6, 8)));
            outputControlBean.setPowerOut22(Integer.parseInt(s6.substring(4, 6)));
            outputControlBean.setPowerOut23(Integer.parseInt(s6.substring(2, 4)));
            outputControlBean.setPowerOut24(Integer.parseInt(s6.substring(0, 2)));


            // 解析第七个字节
            while (s7.length() < 8) {
                s7 = "0" + s7;
            }
            outputControlBean.setProportionalValveOutput1(Integer.parseInt(s7.substring(6, 8)));
            outputControlBean.setProportionalValveOutput2(Integer.parseInt(s7.substring(4, 6)));
            outputControlBean.setProportionalValveOutput3(Integer.parseInt(s7.substring(2, 4)));
            outputControlBean.setProportionalValveOutput4(Integer.parseInt(s7.substring(0, 2)));


            // 解析第八个字节
            while (s8.length() < 8) {
                s8 = "0" + s8;
            }
            outputControlBean.setNegativeOut1(Integer.parseInt(s8.substring(7, 8)));
            outputControlBean.setNegativeOut2(Integer.parseInt(s8.substring(6, 7)));
            outputControlBean.setNegativeOut3(Integer.parseInt(s8.substring(5, 6)));
            outputControlBean.setNegativeOut4(Integer.parseInt(s8.substring(4, 5)));
            outputControlBean.setPushRod2HOutput(Integer.parseInt(s8.substring(3, 4)));
            outputControlBean.setPushRod2LOutput(Integer.parseInt(s8.substring(2, 3)));
            outputControlBean.setPushRod4HOutput(Integer.parseInt(s8.substring(1, 2)));
            outputControlBean.setPushRod4LOutput(Integer.parseInt(s8.substring(0, 1)));

            Log.d("mOutputControlList", "outputControlBean: " + outputControlBean.toString());
            Log.d("mOutputControlList", "mOutputControlList: " + outputControlBean.toString());
        } else if (split2[0].equals(CarConstant.INPUT_DIAGNOSIS_ID)) {

            String s1 = DataUtils.HexTo2String(can2Sp1);
            String s2 = DataUtils.HexTo2String(can2Sp2);
            String s3 = DataUtils.HexTo2String(can2Sp3);
            String s4 = DataUtils.HexTo2String(can2Sp4);
            String s5 = DataUtils.HexTo2String(can2Sp5);
            String s6 = DataUtils.HexTo2String(can2Sp6);
            String s7 = DataUtils.HexTo2String(can2Sp7);
            String s8 = DataUtils.HexTo2String(can2Sp8);

            // 解析第一个字节
            while (s1.length() < 8) {
                s1 = "0" + s1;
            }

            inputDiagnosticsBean.setNegativeControlSignalInput1(Integer.parseInt(s1.substring(6, 8)));
            inputDiagnosticsBean.setNegativeControlSignalInput2(Integer.parseInt(s1.substring(4, 6)));
            inputDiagnosticsBean.setNegativeControlSignalInput3(Integer.parseInt(s1.substring(2, 4)));
            inputDiagnosticsBean.setNegativeControlSignalInput4(Integer.parseInt(s1.substring(0, 2)));

            // 解析第二个字节
            while (s2.length() < 8) {
                s2 = "0" + s2;
            }
            inputDiagnosticsBean.setNegativeControlSignalInput5(Integer.parseInt(s2.substring(6, 8)));
            inputDiagnosticsBean.setNegativeControlSignalInput6(Integer.parseInt(s2.substring(4, 6)));
            inputDiagnosticsBean.setNegativeControlSignalInput7(Integer.parseInt(s2.substring(2, 4)));
            inputDiagnosticsBean.setNegativeControlSignalInput8(Integer.parseInt(s2.substring(0, 2)));

            // 解析第三个字节
            while (s3.length() < 8) {
                s3 = "0" + s3;
            }
            inputDiagnosticsBean.setNegativeControlSignalInput9(Integer.parseInt(s3.substring(6, 8)));
            inputDiagnosticsBean.setNegativeControlSignalInput10(Integer.parseInt(s3.substring(4, 6)));
            inputDiagnosticsBean.setNegativeControlSignalInput11(Integer.parseInt(s3.substring(2, 4)));
            inputDiagnosticsBean.setNegativeControlSignalInput12(Integer.parseInt(s3.substring(0, 2)));

            // 解析第四个字节
            while (s4.length() < 8) {
                s4 = "0" + s4;
            }
            inputDiagnosticsBean.setNegativeControlSignalInput13(Integer.parseInt(s4.substring(6, 8)));
            inputDiagnosticsBean.setNegativeControlSignalInput14(Integer.parseInt(s4.substring(4, 6)));
            inputDiagnosticsBean.setNegativeControlSignalInput15(Integer.parseInt(s4.substring(2, 4)));
            inputDiagnosticsBean.setNegativeControlSignalInput16(Integer.parseInt(s4.substring(0, 2)));

            // 解析第五个字节
            while (s5.length() < 8) {
                s5 = "0" + s5;
            }
            inputDiagnosticsBean.setNegativeControlSignalInput17(Integer.parseInt(s5.substring(6, 8)));
            inputDiagnosticsBean.setNegativeControlSignalInput18(Integer.parseInt(s5.substring(4, 6)));
            inputDiagnosticsBean.setNegativeControlSignalInput19(Integer.parseInt(s5.substring(2, 4)));
            inputDiagnosticsBean.setNegativeControlSignalInput20(Integer.parseInt(s5.substring(0, 2)));

            // 解析第六个字节
            while (s6.length() < 8) {
                s6 = "0" + s6;
            }
            inputDiagnosticsBean.setNegativeControlSignalInput21(Integer.parseInt(s6.substring(6, 8)));
            inputDiagnosticsBean.setNegativeControlSignalInput22(Integer.parseInt(s6.substring(4, 6)));
            inputDiagnosticsBean.setNegativeControlSignalInput23(Integer.parseInt(s6.substring(2, 4)));
            inputDiagnosticsBean.setNegativeControlSignalInput24(Integer.parseInt(s6.substring(0, 2)));

            // 解析第七个字节
            while (s7.length() < 8) {
                s7 = "0" + s7;
            }
            inputDiagnosticsBean.setNegativeControlSignalInput25(Integer.parseInt(s7.substring(6, 8)));
            inputDiagnosticsBean.setNegativeControlSignalInput26(Integer.parseInt(s7.substring(4, 6)));
            inputDiagnosticsBean.setNegativeControlSignalInput27(Integer.parseInt(s7.substring(2, 4)));
            inputDiagnosticsBean.setNegativeControlSignalInput28(Integer.parseInt(s7.substring(0, 2)));

            // 解析第八个字节
            while (s8.length() < 8) {
                s8 = "0" + s8;
            }
            inputDiagnosticsBean.setNegativeControlSignalInput29(Integer.parseInt(s8.substring(6, 8)));
            inputDiagnosticsBean.setNegativeControlSignalInput30(Integer.parseInt(s8.substring(4, 6)));
            inputDiagnosticsBean.setNegativeControlSignalInput31(Integer.parseInt(s8.substring(2, 4)));
            inputDiagnosticsBean.setNegativeControlSignalInput32(Integer.parseInt(s8.substring(0, 2)));
            Log.d("mInputDiagnosticsList", "inputDiagnosticsBean: " + inputDiagnosticsBean.toString());

        } else if (split2[0].equals(CarConstant.BASE_CONTROL_ID)) {

            String s1 = DataUtils.HexTo2String(can2Sp1);
            int s2 = DataUtils.HexTo10Int(can2Sp2);
            int s3 = DataUtils.HexTo10Int(can2Sp3);
            int s4 = DataUtils.HexTo10Int(can2Sp4);
            int s5 = DataUtils.HexTo10Int(can2Sp5);

            Log.d("zxcv", "onCan2Changed444444444444: "+s4);
            Log.d("zxcv", "onCan2Changed5555555555: "+s5);

            // 解析第一个字节
            while (s1.length() < 8) {
                s1 = "0" + s1;
            }
            baseControlBean.setBaseWalkingPushRodPositionSignal(Integer.parseInt(s1.substring(6, 8)));
            baseControlBean.setWalkingSensorFaultInformation(Integer.parseInt(s1.substring(4, 6)));
            baseControlBean.setPositionSignalOfBaseShiftPushRod(Integer.parseInt(s1.substring(2, 4)));
            baseControlBean.setShiftSensorFaultInformation(Integer.parseInt(s1.substring(0, 2)));
            // 解析第二个字节
            baseControlBean.setDegreeOfPushBeforeWalking(s2);
            // 解析第三个字节
            baseControlBean.setDegreeOfPushAfterWalking(s3);
            // 解析第四个字节
            baseControlBean.setDegreeOfPushBeforeShifting(s4);
            // 解析第五个字节
            baseControlBean.setDegreeOfPushAfterShifting(s5);

            Log.d("mBaseControlList", "baseControlBean: " + baseControlBean.toString());
        }
    }

    private void initCan1() {

        can1Bean.getSpeedOfVehicle().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dqg", "车速22222: " + integer);
                //车速
                sd_km.setText(integer + "");
            }
        });

        can1Bean.getRotationalSpeed().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //转速
                Log.d("dqg", "转速111111: " + (int)integer/100);
                zs_rpm.setText((int) integer / 100 + "");
            }
        });

        //发动机故障警告灯  0-关闭 1-打开
        can1Bean.getFaultCode().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("fdjs", "onChanged: "+integer);
                //发动机故障警告灯
                if (integer == 0) {
                    img_fdj.setVisibility(View.GONE);

                } else if (integer == 1) {
                    img_fdj.setVisibility(View.VISIBLE);

                }
            }
        });

        // 冷却液温度过高警告灯 < 120
        can1Bean.getWaterTemperature().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("sww", "onChanged: " + integer);
                if (integer > 120) {
                    img_sw.setVisibility(View.VISIBLE);
//                    img_swh.setImageResource(R.mipmap.xswh);
                } else if (integer < 120) {
                    img_sw.setVisibility(View.GONE);
//                    img_swh.setImageResource(R.mipmap.xsw);
                }
//-40----210
                if (integer < 0) {
                    swbarview.setNumber(1);
                    text_h.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_swh.setImageResource(R.mipmap.xsw);
                } else if (integer < 30) {
                    swbarview.setNumber(2);
                    text_h.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_swh.setImageResource(R.mipmap.xsw);
                } else if (integer < 60) {
                    swbarview.setNumber(3);
                    text_h.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_swh.setImageResource(R.mipmap.xsw);
                } else if (integer < 90) {
                    swbarview.setNumber(4);
                    text_h.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_swh.setImageResource(R.mipmap.xsw);
                } else if (integer < 120) {
                    swbarview.setNumber(5);
                    text_h.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_swh.setImageResource(R.mipmap.xsw);
                } else if (integer < 150) {
                    swbarview.setNumber(6);
                    text_h.setTextColor(Color.parseColor("#FFFB0303"));
                    img_swh.setImageResource(R.mipmap.xswh);

                } else if (integer < 180) {
                    swbarview.setNumber(7);
                    text_h.setTextColor(Color.parseColor("#FFFB0303"));
                    img_swh.setImageResource(R.mipmap.xswh);

                } else if (integer < 210) {
                    swbarview.setNumber(8);
                    text_h.setTextColor(Color.parseColor("#FFFB0303"));
                    img_swh.setImageResource(R.mipmap.xswh);
                }
            }
        });

        //机油压力低报警 < 0.25mpa
        can1Bean.getOilPressure().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("TAG", "onChanged: " + integer + "");
                //JYkpa
                ya_kpa.setText(integer + " kpa");
                // jygz
                if (integer < 250) {
                    img_lqy.setVisibility(View.VISIBLE);
                    xblqy.setImageResource(R.mipmap.xblqyh);
                } else if (integer > 250) {
                    img_lqy.setVisibility(View.GONE);
                    xblqy.setImageResource(R.mipmap.xblqy);
                }
            }
        });
        //柴油发动机油水分离警告灯 0-关闭 3-打开
        can1Bean.getWaterInOil().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("ysfl", "油水分离: " + integer);
                //柴油发动机油水分离警告灯
                if (integer == 3) {
                    img_hoy.setVisibility(View.VISIBLE);
                } else if (integer == 0) {
                    img_hoy.setVisibility(View.GONE);
                }
            }
        });

        //柴油发动机预热警告灯
        can1Bean.getEngineWarmUpLight().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //柴油发动机预热警告灯
//              if(can1DataBean.getEngineWarmUpLight()==){
//                  img_huy.setVisibility(View.VISIBLE);
//              }else {
//                  img_huy.setVisibility(View.GONE);
//              }
            }
        });

        // 驾驶员警告 0-关闭 4-打开
        can1Bean.getDpfWarningLight().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("xp1", "xp1: "+integer);
                // 驾驶员警告灯driverWarningLight
                if (integer == 4) {
                    xp1.setVisibility(View.VISIBLE);

                } else if (integer == 0) {
                    xp1.setVisibility(View.GONE);

                }
            }
        });

        // 2.DPF禁止 0-关闭 4-打开
        can1Bean.getDpfProhibition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("xp2", "xp2: "+integer);
                //                        // DPF禁止
                if (integer == 4) {
                    xp2.setVisibility(View.VISIBLE);
                    layout_xp2.setVisibility(View.VISIBLE);
                    Log.d("xp2", "xp2:1111 "+integer);
                    textview_xp2.setText("再生处于禁止状态，发动机需要进行再生服务\n"+"请打开允许再生开关。");

                } else if (integer == 0) {
                    xp2.setVisibility(View.GONE);
                    layout_xp2.setVisibility(View.GONE);

                }
            }
        });
        //                        //DPF警告灯
        can1Bean.getDriverWarningLight().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                 //DPF警告灯
                if (integer == 0) {
                    Log.d("xp3111111", "xp30000000000000: "+integer);

                    xp33.setVisibility(View.GONE);
                    layout_xp33.setVisibility(View.GONE);

                    layoutxp3cl_view.setVisibility(View.GONE);
                    layoutxp3ms_view.setVisibility(View.GONE);
                    layoutxp3ks_view.setVisibility(View.GONE);
                    layoutxp3_view.setVisibility(View.GONE);
                } else if (integer == 1) {
                    xp33.setVisibility(View.VISIBLE);
                    layout_xp33.setVisibility(View.VISIBLE);
                    textview_xp33.setText("DPF中碳载量偏高,\n" +
                            "建议驻车再生");
                    layoutxp3cl_view.setVisibility(View.VISIBLE);
                }else if(integer == 10){

                    if (xp33.getVisibility() == View.VISIBLE){
                        xp33.setVisibility(View.GONE);
                    }else {
                        xp33.setVisibility(View.VISIBLE);
                    }

                    layout_xp33.setVisibility(View.VISIBLE);
                    textview_xp33.setText("DPF中碳载量过高,\n"+
                            "立即驻车或服务再生");
                    layoutxp3ms_view.setVisibility(View.VISIBLE);

                }else if(integer == 100){
                    xp33.setVisibility(View.VISIBLE);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("ccm123------>","handler 1231");
                            xp33.setVisibility(View.VISIBLE);
                        }
                    },300);

                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("ccm123------>","handler 1232");
                            xp33.setVisibility(View.GONE);
                        }
                    },300);

                    layout_xp33.setVisibility(View.VISIBLE);
                    textview_xp33.setText("DPF严重堵塞,\n" +
                            "立即服务再生");
                    layoutxp3ks_view.setVisibility(View.VISIBLE);
                }
            }
        });
        can1Bean.getRegenerationreminder().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("xp32", "xp3222222222222: "+integer);
                if(integer == 0){
                    xp3.setVisibility(View.GONE);
                    layout_xp3.setVisibility(View.GONE);
                    layoutxp3_view.setVisibility(View.GONE);

                }else if(integer == 1){
                    xp3.setVisibility(View.VISIBLE);

                    layout_xp3.setVisibility(View.VISIBLE);
                    textview_xp3.setText("DPF正处于主动再生过程状态");
                    layoutxp3_view.setVisibility(View.VISIBLE);

                }
            }
        });

        // 排气温度过高报警灯 0-关闭 4-打开
        can1Bean.getExcessiveExhaustTemperature().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("xp4", "xp4: "+integer);
                //                        //排气温度过高报警灯
                if (integer == 4) {
                    img_xp4.setVisibility(View.VISIBLE);
                    textview_xp4.setText("");
                    layout_xp4.setVisibility(View.VISIBLE);
                    textview_xp4.setText("排气温度超过设定阈值\n"+"请停止继续主动再生，立即到服务站清理。");

                } else if (integer == 0) {
                    img_xp4.setVisibility(View.GONE);
                    layout_xp4.setVisibility(View.GONE);
                }
            }
        });
        //尿素
        can1Bean.getReactantAllowance().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                Log.d("nsdfgh", "onChanged: "+integer);
                //尿素
                text_jsyw.setText(integer + " %");
                //                        //尿素
                if (integer < 10) {
                    img_able.setVisibility(View.VISIBLE);
                } else if (integer > 15) {
                    img_able.setVisibility(View.GONE);
                }
            }
        });

        //充电系统故障警告灯 <18V
        can1Bean.getEngineInputVoltage().observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {

                //充电系统故障警告灯
                dc_v.setText(aFloat + " v");
                //                        //充电系统故障警告灯
                if (aFloat < 18.0f) {
                    img_diy.setVisibility(View.VISIBLE);
                    xbdy.setImageResource(R.mipmap.xbdyh);
                } else if (aFloat > 18.0f) {
                    img_diy.setVisibility(View.GONE);
                    xbdy.setImageResource(R.mipmap.xbdy);
                }
            }
        });


    }

    private void setFlickerAnimation(ImageView im) {
        final Animation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(1000); // duration - half a second
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        im.setAnimation(animation);
    }



    private void initJxCan2RealTimeStatus() {
        realTimeStatusBean.getTurnLeftLightSignal().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "左转灯信号: " + integer);
                // 1.左转灯信号
                if (integer == 0) {
                    img_zzd.setImageResource(R.mipmap.xblleftd);
                } else {
                    img_zzd.setImageResource(R.mipmap.xlzzd);
                }
            }
        });

        realTimeStatusBean.getRightTurnLightSignal().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "右转灯信号: " + integer);
                // 2.右转灯信号
                if (integer == 0) {
                    img_yzx.setImageResource(R.mipmap.xblrightd);
                } else {
                    img_yzx.setImageResource(R.mipmap.xlrightd);
                }
            }
        });

        realTimeStatusBean.getHighBeamSignal().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "远光灯信号: " + integer);
                // 3.远光灯信号
                if (integer == 0) {
                    img_dd.setImageResource(R.mipmap.xbldd);
                } else {
                    img_dd.setImageResource(R.mipmap.xldd);
                }
            }
        });

        realTimeStatusBean.getNearLightSignal().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "近光灯信号: " + integer);
                // 4.近光灯信号
                if (integer == 0) {
                    img_xd.setImageResource(R.mipmap.xblxd);
                } else {
                    img_xd.setImageResource(R.mipmap.xlxd);
                }
            }
        });

        realTimeStatusBean.getSignalOfProfileLight().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "示廓灯信号: " + integer);
                // 5.示廓灯信号
                if (integer == 0) {
                    img_rgd.setImageResource(R.mipmap.xblrgd);
                } else {
                    img_rgd.setImageResource(R.mipmap.xlrgd);
                }
            }
        });

        realTimeStatusBean.getRearFogLamp().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "后雾灯: " + integer);
                // 6.后雾灯
                if (integer == 0) {
                    img_qwd.setImageResource(R.mipmap.xblqwd);
//                    img_leftzy.setImageResource(R.mipmap.xblhwd);
                } else {
                    img_qwd.setImageResource(R.mipmap.xlqwd);
//                    img_leftzy.setImageResource(R.mipmap.xlhwd);
                }
            }
        });
        realTimeStatusBean.getMainDrivingSeatBelt().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "主驾驶安全带: " + integer);
                // 7.主驾驶安全带
                if (integer == 0) {
                    img_leftzy.setImageResource(R.mipmap.xblzzy);
                } else {
                    img_leftzy.setImageResource(R.mipmap.xlzzy);
                }
            }
        });
        realTimeStatusBean.getPassengerSeatBelt().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "副驾驶安全带: " + integer);
                // 8.副驾驶安全带
                if (integer == 0) {
                    img_rightzy.setImageResource(R.mipmap.xblyzy);
                } else {
                    img_rightzy.setImageResource(R.mipmap.xlyzy);
                }
            }
        });
        realTimeStatusBean.getFuelLevel().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "刹车油液位过低: " + integer);
                // 燃油液位过低
                if (integer < 1) {
                    img_huy.setVisibility(View.VISIBLE);
                } else if (integer > 2) {
                    img_huy.setVisibility(View.GONE);
                }
            }
        });

        realTimeStatusBean.getParkingSignal().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "驻车信号: " + integer);
                // 驻车信号（手刹信号）
                if (integer == 1) {
                    img_pd.setVisibility(View.VISIBLE);
                } else {
                    img_pd.setVisibility(View.GONE);
                }
            }
        });

        realTimeStatusBean.getAutopilotStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "自动驾驶状态: " + integer);
                // 自动驾驶状态 00-处于准备状态（灰） 01-启用状态（蓝色） 10-激活状态（橙黄色） 11-功能故障（红色）
                if (integer == 00) {
                    xfxp.setImageResource(R.mipmap.xhzbs);
                }else if(integer == 01){
                    xfxp.setImageResource(R.mipmap.xhqys);
                }else if(integer == 10){
                    xfxp.setImageResource(R.mipmap.xhjhs);
                }else if(integer == 11){
                    xfxp.setImageResource(R.mipmap.xhgzs);
                }
            }
        });

        realTimeStatusBean.getHydraulicOilTemperature().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "液压油温: " + integer);
                // 液压油温/℃
                text_yyyw.setText(integer + " ℃");
            }
        });
        realTimeStatusBean.getGasTankPressure().observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float integer) {
                Log.d("dpg", "气罐压力: " + integer);
                // 气罐压力/Mpa
                text_qgyl.setText(integer/100 + "Mpa");
                if(integer/100<0.6){
                    img_zcd.setVisibility(View.VISIBLE);
                }else if(integer/100>0.6){
                    img_zcd.setVisibility(View.GONE);
                }

            }
        });

        realTimeStatusBean.getCuttingTableAngleSensor().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "割台角度传感器: " + integer);
                // 割台角度传感器
                text_gtgd.setText(integer + " cm");
            }
        });

        realTimeStatusBean.getUnloadingCylinderAngleSensor().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("dpg", "卸粮筒角度传感器: " + integer);
                // 卸粮筒角度传感器
                text_xlgd.setText(integer + " °");
            }
        });
        realTimeStatusBean.getFuelLevel().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("yww", "燃油液位: " + integer);
                // 燃油液位/格
                if (integer == 7) {
                    numbarview.setNumber(7);
                    text_f.setTextColor(Color.parseColor("#ffffffff"));
                    img_ywh.setImageResource(R.mipmap.xbyy);
                } else if (integer == 6) {
                    numbarview.setNumber(6);
                    text_f.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_ywh.setImageResource(R.mipmap.xbyy);
                } else if (integer == 5) {
                    numbarview.setNumber(5);
                    text_f.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_ywh.setImageResource(R.mipmap.xbyy);
                } else if (integer == 4) {
                    numbarview.setNumber(4);
                    text_f.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_ywh.setImageResource(R.mipmap.xbyy);
                } else if (integer == 3) {
                    numbarview.setNumber(3);
                    text_f.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_ywh.setImageResource(R.mipmap.xbyy);
                } else if (integer == 2) {
                    numbarview.setNumber(2);
                    text_f.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_ywh.setImageResource(R.mipmap.xbyy);
                } else if (integer == 1) {
                    numbarview.setNumber(1);
                    text_f.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_ywh.setImageResource(R.mipmap.xbyy);
                }
                else if (integer == 0) {
                    numbarview.setNumber(0);
                    text_f.setTextColor(Color.parseColor("#FF3E3E3E"));
                    img_ywh.setImageResource(R.mipmap.xbyyh);
                }
            }
        });

    }

    private void initJxCan2StateOfWork() {
        stateOfWorkBean.getRotationSpeedOfTheWheel().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("拨禾轮转速", "拨禾轮转速: "+integer);
                //拨禾轮转速/rpm
                text_bhlzs.setText(integer + " r/min");
            }
        });
        stateOfWorkBean.getAxialFlowDrumSpeed().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("轴流滚筒转速", "轴流滚筒转速: "+integer);
                // 轴流滚筒转速/rpm
                text_zlgt.setText(integer + " r/min");
            }
        });
        stateOfWorkBean.getGrainAugerRotationSpeed().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("籽粒搅龙转速", "籽粒搅龙转速: "+integer);
                //籽粒搅龙转速/rpm
                text_zljl.setText(integer + " r/min");
            }
        });
        stateOfWorkBean.getSpeedOfFan().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("风机转速", "风机转速: "+integer);
                //风机转速/rpm
                text_fjzs.setText(integer + " r/min");
            }
        });

    }


    private void initJxCan2OutputControl() {
        outputControlBean.getPowerOut1().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("mainactiOutputControlBeanity","integer: "+(int)integer);
                if (integer == 1) {
                    texte_clxx.setText("割台升");
                }
            }
        });//xblleftd
        outputControlBean.getPowerOut2().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("割台降");
                }
            }
        });
        outputControlBean.getPowerOut3().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("拨禾轮升");
                }
            }
        });
        outputControlBean.getPowerOut4().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("拨禾轮降");
                }
            }
        });
        outputControlBean.getPowerOut5().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("卸粮筒升");
                }
            }
        });
        outputControlBean.getPowerOut6().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("卸粮筒降");
                }
            }
        });
        outputControlBean.getPowerOut7().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("卸粮筒左旋");
                }
            }
        });
        outputControlBean.getPowerOut8().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("卸粮筒右旋");
                }
            }
        });
        outputControlBean.getPowerOut9().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("脱谷离合器结合");
                }
            }
        });
        outputControlBean.getPowerOut10().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("脱谷离合器分离");
                }
            }
        });
        outputControlBean.getPowerOut11().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("割台输送离合器结合");
                }
            }
        });
        outputControlBean.getPowerOut12().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("割台输送离合器分离");
                }
            }
        });
        outputControlBean.getPowerOut13().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("7#阀开");
                }
            }
        });
        outputControlBean.getPowerOut14().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("7#阀关");
                }
            }
        });
        outputControlBean.getPowerOut15().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("8#阀开");
                }
            }
        });
        outputControlBean.getPowerOut16().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("8#阀关");
                }
            }
        });
        outputControlBean.getPowerOut17().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("9#阀开");
                }
            }
        });
        outputControlBean.getPowerOut18().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("9#阀关");
                }
            }
        });
        outputControlBean.getPowerOut19().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("10#阀开");
                }
            }
        });
        outputControlBean.getPowerOut20().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    texte_clxx.setText("10#阀关");
                }
            }
        });
        outputControlBean.getPowerOut21().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("卸粮开关关");
                } else if (integer == 1) {
                    texte_clxx.setText("卸粮开关开");
                }
            }
        });
        outputControlBean.getPowerOut22().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("取消割台反转");
                } else if (integer == 1) {
                    texte_clxx.setText("割台反转排除堵塞");
                }
            }
        });
//        outputControlBean.getPowerOut23().observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                if (integer == 10) {
//                    texte_clxx.setText("割台回位");
//                } else if (integer == 1) {
////                    texte_clxx.setText("保存当前割台位置为常用位置");
//                }
//            }
//        });
//        outputControlBean.getPowerOut24().observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                if (integer == 10) {
//                    texte_clxx.setText("仪表焦点确定");
//                } else if (integer == 1) {
//                    texte_clxx.setText("仪表焦点取消");
//                }
//            }
//        });
        outputControlBean.getProportionalValveOutput1().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
//                    texte_clxx.setText("仪表焦点上移");
                    texte_clxx.setText("NEGATIVE_OUT1");
                }
            }
        });
        outputControlBean.getProportionalValveOutput2().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
//                    texte_clxx.setText("仪表焦点下移");
                    texte_clxx.setText("NEGATIVE_OUT2");
                }
            }
        });
        outputControlBean.getProportionalValveOutput3().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
//                    texte_clxx.setText("仪表焦点左移");
                    texte_clxx.setText("NEGATIVE_OUT3");
                }
            }
        });
        outputControlBean.getProportionalValveOutput4().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
//                    texte_clxx.setText("仪表焦点右移");
                    texte_clxx.setText("NEGATIVE_OUT4");
                }
            }
        });

    }


    private void initJxCan2InputDiagnostics() {
        inputDiagnosticsBean.getNegativeControlSignalInput1().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("割台升");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput2().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("割台降");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput3().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("拨禾轮升");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput4().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("拨禾轮降");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput5().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("卸粮筒升");
                }

            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput6().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("卸粮筒降");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput7().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("卸粮筒左旋");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput8().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("卸粮筒右旋");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput9().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("脱谷离合器结合");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput10().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("脱谷离合器分离");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput11().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("割台输送离合器结合");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput12().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("割台输送离合器分离");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput13().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("7#阀开");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput14().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("7#阀关");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput15().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("8#阀关");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput16().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("8#阀关");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput17().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("9#阀开");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput18().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("9#阀关");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput19().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("10#阀开");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput20().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("10#阀关");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput21().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("卸粮开关关");
                } else if (integer == 1) {
                    texte_clxx.setText("卸粮开关开");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput22().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("取消割台反转");
                } else if (integer == 1) {
                    texte_clxx.setText("割台反转排除堵塞");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput23().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("割台回位");
                } else if (integer == 1) {
                    texte_clxx.setText("保存当前割台位置为常用位置");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput24().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("仪表焦点确定");
                } else if (integer == 1) {
                    texte_clxx.setText("仪表焦点取消");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput25().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("仪表焦点上移");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput26().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("仪表焦点下移");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput27().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("仪表焦点左移");
                }
            }
        });
        inputDiagnosticsBean.getNegativeControlSignalInput28().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 10) {
                    texte_clxx.setText("仪表焦点右移");
                }
            }
        });


    }

    private void initJxCan2BaseControl() {


        // 1~2.基座行走推杆位置信号 00-前进 01-倒车 10-停止 11-未定义
        baseControlBean.getBaseWalkingPushRodPositionSignal().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //前进   倒车   停止

                if (integer == 00) {
                    text_D.setTextColor(Color.parseColor("#FF17D9CD"));
                    text_P.setTextColor(Color.parseColor("#ffffffff"));
                    text_R.setTextColor(Color.parseColor("#ffffffff"));
                    text_N.setTextColor(Color.parseColor("#ffffffff"));
                } else if (integer == 01) {
                    text_R.setTextColor(Color.parseColor("#FF17D9CD"));
                    text_P.setTextColor(Color.parseColor("#ffffffff"));
                    text_N.setTextColor(Color.parseColor("#ffffffff"));
                    text_D.setTextColor(Color.parseColor("#ffffffff"));
                } else if (integer == 10) {
                    text_N.setTextColor(Color.parseColor("#FF17D9CD"));
                    text_R.setTextColor(Color.parseColor("#ffffffff"));
                    text_P.setTextColor(Color.parseColor("#ffffffff"));
                    text_D.setTextColor(Color.parseColor("#ffffffff"));
                    text_1.setTextColor(Color.parseColor("#ffffffff"));
                    text_2.setTextColor(Color.parseColor("#ffffffff"));
                    text_3.setTextColor(Color.parseColor("#ffffffff"));
                }
            }
        });

        // 5~6.基座换挡推杆位置信号 00-加档 01-减档 10-空挡 11-未定义
//        baseControlBean.getPositionSignalOfBaseShiftPushRod().observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                if (integer == 00) {
//                    text_D.setTextColor(Color.parseColor("#FF17D9CD"));
//                    text_P.setTextColor(Color.parseColor("#ffffffff"));
//                    text_R.setTextColor(Color.parseColor("#ffffffff"));
//                    text_N.setTextColor(Color.parseColor("#ffffffff"));
////                    if(){
////                        text_1.setTextColor(Color.parseColor("#FF17D9CD"));
////                        text_2.setTextColor(Color.parseColor("#ffffffff"));
////                        text_3.setTextColor(Color.parseColor("#ffffffff"));
////                    }else if(){
////                        text_1.setTextColor(Color.parseColor("#ffffffff"));
////                        text_2.setTextColor(Color.parseColor("#FF17D9CD"));
////                        text_3.setTextColor(Color.parseColor("#ffffffff"));
////                    }else if(){
////                        text_1.setTextColor(Color.parseColor("#ffffffff"));
////                        text_2.setTextColor(Color.parseColor("#ffffffff"));
////                        text_3.setTextColor(Color.parseColor("#FF17D9CD"));
////                    }
//                } else if (integer == 01) {
//                    text_D.setTextColor(Color.parseColor("#FF17D9CD"));
//                    text_R.setTextColor(Color.parseColor("#ffffffff"));
//                    text_N.setTextColor(Color.parseColor("#ffffffff"));
//                    text_D.setTextColor(Color.parseColor("#ffffffff"));
////                    if(){
////                        text_1.setTextColor(Color.parseColor("#ffffffff"));
////                        text_2.setTextColor(Color.parseColor("#ffffffff"));
////                        text_3.setTextColor(Color.parseColor("#FF17D9CD"));
////                    }else if(){
////                        text_1.setTextColor(Color.parseColor("#ffffffff"));
////                        text_2.setTextColor(Color.parseColor("#FF17D9CD"));
////                        text_3.setTextColor(Color.parseColor("#ffffffff"));
////                    }else if(){
////                        text_1.setTextColor(Color.parseColor("#FF17D9CD"));
////                        text_2.setTextColor(Color.parseColor("#ffffffff"));
////                        text_3.setTextColor(Color.parseColor("#ffffffff"));
////                    }
//
//                }
////                else if (integer == 10) {
////                    text_N.setTextColor(Color.parseColor("#FF17D9CD"));
////                    text_P.setTextColor(Color.parseColor("#ffffffff"));
////                    text_R.setTextColor(Color.parseColor("#ffffffff"));
////                    text_D.setTextColor(Color.parseColor("#ffffffff"));
////                    text_1.setTextColor(Color.parseColor("#ffffffff"));
////                    text_2.setTextColor(Color.parseColor("#ffffffff"));
////                    text_3.setTextColor(Color.parseColor("#ffffffff"));
////                }
//            }
//        });

        // 1~8.换挡前推开度
        baseControlBean.getDegreeOfPushBeforeShifting().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                Log.d("tyui", "getDegreeOfPushBefore11111111111: "+integer);
                if(integer > 0){
                    setTextColor(integer);
                }

            }
        });
        // 1~8.换挡后推开度
          baseControlBean.getDegreeOfPushAfterShifting().observe(this, new Observer<Integer>() {
              @Override
              public void onChanged(Integer integer) {

                  Log.d("tyui", "222222222222222222222222: "+integer);
                  if(integer > 0){
                      setTextColor(integer);
                  }

              }
          });

    }

    /**
     * 定位SDK监听函数
     */
//    public class MyLocationListener implements BDLocationListener {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
////            Toast.makeText(MainActivity.this, location.getAddrStr(), Toast.LENGTH_SHORT).show();
//            Log.d("TAG", "getLatitude: " + location.getLatitude() + "getLongitude:  " + location.getLongitude());
//            double resultLatitude = 0;
//            double resultLongitude = 0;
//            // MapView 销毁后不在处理新接收的位置
//            if (location == null || mMapView == null) {
//                return;
//            }
//
//            if (markerLatitude == 0 && markerLongitude == 0) {//自动定位
//                resultLatitude = location.getLatitude();
//                resultLongitude = location.getLongitude();
//
//                Log.e("dddd", "经度: " + resultLongitude);
//                Log.e("dddd", "纬度: " + resultLatitude);
//                MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())// 设置定位数据的精度信息，单位：米
//                        .direction(location.getDirection()) // 此处设置开发者获取到的方向信息，顺时针0-360
//                        .latitude(resultLatitude).longitude(resultLongitude).build();
//
//                mBaiduMap.setMyLocationData(locData);// 设置定位数据, 只有先允许定位图层后设置数据才会生效
//
//                latLng = new LatLng(resultLatitude, resultLongitude);
//                MapStatus.Builder builder = new MapStatus.Builder();
//                builder.target(latLng).zoom(20.0f);
//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//
//            }
//
//
////            if (isFirstLoc) {//首次定位
////
//////                latLng = getMostAccuracyLocation(location);
////
////                    if (latLng == null) {
////                        return;
////                    }
////                    isFirstLoc = false;
////
//////                latLng = new LatLng(location.getLatitude(), location.getLongitude());
//////                MapStatus.Builder builder = new MapStatus.Builder();
//////                builder.target(latLng).zoom(20.0f);
//////                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
////
////                points.add(latLng);
////                    last = latLng;
//////                    //显示当前定位点，缩放地图
////                MapStatus.Builder builder = new MapStatus.Builder();
////                builder.target(latLng).zoom(20.0f);
//////                    locateAndZoom(location, latLng);
////                    //标记起点图层位置
////                    MarkerOptions oStart = new MarkerOptions();// 地图标记覆盖物参数配置类
////                    oStart.position(points.get(0));// 覆盖物位置点，第一个点为起点
////                    oStart.icon(startBD);// 设置覆盖物图片
////                    mBaiduMap.addOverlay(oStart); // 在地图上添加此图层
////
////                    layout.setVisibility(View.GONE);
////                    return;
////
////            }
//
//
//            if (isFirstLoc) {//首次定位
//                //第一个点很重要，决定了轨迹的效果，gps刚开始返回的一些点精度不高，尽量选一个精度相对较高的起始点
////                LatLng ll = null;
////                if (mLatitude == location.getLatitude() && mLongitude == location.getLongitude()) {
//                latLng = getMostAccuracyLocation(location);
//                if (latLng == null) {
//                    return;
//                }
//                isFirstLoc = false;
//                points.add(latLng);//加入集合
//                last = latLng;
//
//                //显示当前定位点，缩放地图
//                locateAndZoom(location, latLng);
//
//                //标记起点图层位置
//                MarkerOptions oStart = new MarkerOptions();// 地图标记覆盖物参数配置类
//                oStart.position(points.get(0));// 覆盖物位置点，第一个点为起点
//                oStart.icon(startBD);// 设置覆盖物图片
//                mBaiduMap.addOverlay(oStart); // 在地图上添加此图层
//
//                return;//画轨迹最少得2个点，首地定位到这里就可以返回了
////                }
//
//            }
//            //从第二个点开始
//            LatLng lls = new LatLng(location.getLatitude(), location.getLongitude());
//            //sdk回调gps位置的频率是1秒1个，位置点太近动态画在图上不是很明显，可以设置点之间距离大于为5米才添加到集合中
//            if (DistanceUtil.getDistance(last, lls) < 5) {
//                return;
//            }
//
//            points.add(lls);//如果要运动完成后画整个轨迹，位置点都在这个集合中
//
//            last = lls;
//
//            //显示当前定位点，缩放地图
//            locateAndZoom(location, lls);
//
//            //清除上一次轨迹，避免重叠绘画
//            mMapView.getMap().clear();
//
//            //起始点图层也会被清除，重新绘画
//            MarkerOptions oStarts = new MarkerOptions();
//            oStarts.position(points.get(0));
//            oStarts.icon(startBD);
//            mBaiduMap.addOverlay(oStarts);
//
//            //将points集合中的点绘制轨迹线条图层，显示在地图上
//            OverlayOptions ooPolyline = new PolylineOptions().width(13).color(Color.parseColor("#09A394")).points(points);
//            mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
//
//        }
//
//    }

//    private void locateAndZoom(final BDLocation location, LatLng ll) {
//        markerLatitude = location.getLatitude();
//        markerLongitude = location.getLongitude();
//        locData = new MyLocationData.Builder().accuracy(0)
//                // 此处设置开发者获取到的方向信息，顺时针0-360
//                .direction(mCurrentDirection).latitude(markerLatitude).longitude(markerLongitude).build();
//        Log.e("dddd", "locateAndZoom经度: " + markerLatitude);
//        Log.e("dddd", "locateAndZoom纬度: " + markerLongitude);
//        mBaiduMap.setMyLocationData(locData);
//        MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
//        mBaiduMap.setMyLocationConfigeration(configuration);
//
//
//        builder = new MapStatus.Builder();
//        builder.target(ll).zoom(20.0f);
//        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//    }

    public void setTextColor(int integer){
        Log.d("========================>","integer: "+integer);
        if(integer==0){
            text_1.setTextColor(Color.parseColor("#ffffffff"));
            text_2.setTextColor(Color.parseColor("#ffffffff"));
            text_3.setTextColor(Color.parseColor("#ffffffff"));
        } else if(integer>1&&integer<30){
            text_1.setTextColor(Color.parseColor("#FF17D9CD"));
            text_2.setTextColor(Color.parseColor("#ffffffff"));
            text_3.setTextColor(Color.parseColor("#ffffffff"));
        }else if(integer>30&&integer<60){
            text_2.setTextColor(Color.parseColor("#FF17D9CD"));
            text_1.setTextColor(Color.parseColor("#ffffffff"));
            text_3.setTextColor(Color.parseColor("#ffffffff"));
        }else if(integer>60&&integer<=100){
            text_3.setTextColor(Color.parseColor("#FF17D9CD"));
            text_1.setTextColor(Color.parseColor("#ffffffff"));
            text_2.setTextColor(Color.parseColor("#ffffffff"));
        }
    }


    /**
     * 首次定位很重要，选一个精度相对较高的起始点
     * 注意：如果一直显示gps信号弱，说明过滤的标准过高了，
     * 你可以将location.getRadius()>25中的过滤半径调大，比如>40，
     * 并且将连续5个点之间的距离DistanceUtil.getDistance(last, ll ) > 5也调大一点，比如>10，
     * 这里不是固定死的，你可以根据你的需求调整，如果你的轨迹刚开始效果不是很好，你可以将半径调小，两点之间距离也调小，
     * gps的精度半径一般是10-50米
     */
//    private LatLng getMostAccuracyLocation(BDLocation location) {
//
//        if (location.getRadius() > 40) {//gps位置精度大于40米的点直接弃用
//            return null;
//        }
//
//        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
//        Log.e("dddd", "getMostAccuracyLocation经度: " + location.getLatitude());
//        Log.e("dddd", "getMostAccuracyLocation纬度: " + location.getLongitude());
//
//        if (DistanceUtil.getDistance(last, ll) > 10) {
//            last = ll;
//            points.clear();//有任意连续两点位置大于10，重新取点
//            return null;
//        }
//        points.add(ll);
//        last = ll;
//        //有5个连续的点之间的距离小于10，认为gps已稳定，以最新的点为起始点
//        if (points.size() >= 5) {
//            points.clear();
//            return ll;
//        }
//        return null;
//    }
    public void setGone() {
//        img_zzd, img_dd, img_xd, img_rgd, img_qwd, img_hwd, img_leftzy, img_rightzy, img_yzx;
        //灯光
//        img_zzd.setImageResource(R.drawable.zsxcv);
        img_dd.setImageResource(R.mipmap.xbldd);
        img_xd.setImageResource(R.mipmap.xblxd);
        img_rgd.setImageResource(R.mipmap.xblrgd);
        img_qwd.setImageResource(R.mipmap.xblqwd);
        img_hwd.setImageResource(R.mipmap.xblhwd);
        img_leftzy.setImageResource(R.mipmap.xblzzy);
        img_rightzy.setImageResource(R.mipmap.xblyzy);
        img_yzx.setImageResource(R.mipmap.xblrightd);
//故障灯
        img_fdj.setVisibility(View.GONE);
        img_sw.setVisibility(View.GONE);
        img_lqy.setVisibility(View.GONE);
        img_hoy.setVisibility(View.GONE);
        img_huy.setVisibility(View.GONE);
        img_bzd.setVisibility(View.GONE);
        xp1.setVisibility(View.GONE);
        xp2.setVisibility(View.GONE);
        xp3.setVisibility(View.GONE);
        xzch.setVisibility(View.GONE);
        xfxp.setVisibility(View.GONE);
        img_diy.setVisibility(View.GONE);
        img_xp4.setVisibility(View.GONE);
        img_able.setVisibility(View.GONE);
        img_zcd.setVisibility(View.GONE);
        img_pd.setVisibility(View.GONE);
//    private ImageView img_fdj, img_sw, img_lqy, img_hoy, img_huy, img_bzd;
//    private ImageView xp1, xp2, xp3, xzch, xfxp, img_diy;
//    private ImageView img_xp4, img_able, img_zcd, img_pd;

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//        double x = sensorEvent.values[SensorManager.DATA_X];
//
//        if (Math.abs(x - lastX) > 1.0) {
//            mCurrentDirection = (int) x;
//
//            if (isFirstLoc) {
//                lastX = x;
//                return;
//            }
//
//            locData = new MyLocationData.Builder().accuracy(0)
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
////                    .direction(mCurrentDirection).latitude(0.00).longitude(0.00).build();
////                    .direction(mCurrentDirection).longitude(mLongitude).latitude(mLatitude).build();
//                    .direction(mCurrentDirection).latitude(mLatitude).longitude(mLongitude).build();
//
//            Log.e("dddd", "onSensorChanged经度: " + mLatitude);
//            Log.e("dddd", "onSensorChanged纬度: " + mLongitude);
//            mBaiduMap.setMyLocationData(locData);
//            MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, null);
//            mBaiduMap.setMyLocationConfigeration(configuration);
//
//        }
//        lastX = x;
//
//
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    myHandle.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }

    }

    public class DataThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(3000);
                    Message msg = new Message();
                    msg.what = 2;
                    myHandle.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }

    }

    public class can2sj extends Thread {
        @Override
        public void run() {
            Log.d("11111", "dfdddddd");
            do {
                try {
                    Message msg = new Message();
                    msg.what = 3;
                    myHandle.sendMessage(msg);

                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }

    }

    public class can2zstms extends Thread {

        @Override
        public void run() {
            do {
                try {
                    Message msg = new Message();
                    msg.what = 4;
                    myHandle.sendMessage(msg);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    private void getDate() {

        //获取系统的 日期
        Calendar calendar = Calendar.getInstance();
//年
        year = calendar.get(Calendar.YEAR);
//月
        month = calendar.get(Calendar.MONTH) + 1;
//日
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);

        mWay = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    /**
     * 隐藏底部底部导航栏
     */
    public void hideNavigationBar() {

        Window window;
        window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        window.setAttributes(params);

        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN; // hide status bar

        if (android.os.Build.VERSION.SDK_INT >= 19) {
            uiFlags |= 0x00001000;    //SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide navigation bars - compatibility: building API level is lower thatn 19, use magic number directly for higher API target level
        } else {
            uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }

        getWindow().getDecorView().setSystemUiVisibility(uiFlags);
    }


    public void startApp(String packageName) {
        Log.d("lm", "startApp: " + packageName);
        try {
            PackageInfo packageInfo = MainActivity.this.getPackageManager().getPackageInfo(packageName, 0);
            Intent mResolveIntent = new Intent(Intent.ACTION_MAIN, null);
            mResolveIntent.setPackage(packageInfo.packageName);
            PackageManager packageManager = MainActivity.this.getPackageManager();
            List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(mResolveIntent, 0);
            ResolveInfo next = resolveInfos.iterator().next();
            if (next != null) {
                packageName = next.activityInfo.packageName;
                String className = next.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("lm", "className :" + className);
                intent.setComponent(new ComponentName(packageName, className));
                startActivity(intent);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明


            longitude = location.getLongitude(); //获取经度
            latitude = location.getLatitude(); //获取纬度

            Log.d("dqy", "获取详细地址信息" + "经度：" + longitude + "\n" + "纬度：" + latitude);

        }
    }


    /**
     * 获取定位的权限
     */
    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // 检查权限状态
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    /**
                     * 用户彻底拒绝授予权限，一般会提示用户进入设置权限界面
                     * 第一次授权失败之后，退出App再次进入时，再此处重新调出允许权限提示框
                     */
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    Log.d("info:", "-----get--Permissions--success--1-");
                } else {
                    /**
                     * 用户未彻底拒绝授予权限
                     * 第一次安装时，调出的允许权限提示框，之后再也不提示
                     */
                    Log.d("info:", "-----get--Permissions--success--2-");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

//        在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
//        mMapView.onResume();
        // 为系统的方向传感器注册监听器
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
//        mMapView.onPause();

    }

    @Override
    protected void onStart() {
        super.onStart();
//        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);// 获取传感器管理服务
//        if (mSensorManager != null) {
//            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
//        }
//        if (sensor != null) {
//            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
//        }

        CameraManagerLc.getInstance().openCamera(MainActivity.this, 0);
        CameraUtilsWr.getInstance().openCamera(MainActivity.this, 1);
        CameraManagerXlt.getInstance().openCamera(MainActivity.this, 2);
        CameraManagerHsj.getInstance().openCamera(MainActivity.this, 3);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 取消注册传感器监听
//        mSensorManager.unregisterListener(this);

        CameraManagerLc.getInstance().stopCamera();
        CameraUtilsWr.getInstance().stopCamera();
        CameraManagerXlt.getInstance().stopCamera();
        CameraManagerHsj.getInstance().stopCamera();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("dd", "onDestroy: gzxh");
        // 退出时销毁定位
//        mLocClient.stop();
        // 关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);
        // 在activity执行onDestroy时必须调用mMapView.onDestroy()
//        mMapView.onDestroy();
        CameraManagerLc.getInstance().stopCamera();
        CameraUtilsWr.getInstance().stopCamera();
        CameraManagerXlt.getInstance().stopCamera();
        CameraManagerHsj.getInstance().stopCamera();
        if (myHandle != null) {
            myHandle.removeCallbacksAndMessages(null);
            myHandle.mWeakReference.clear();
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (handler1 != null) {
            handler1.removeCallbacksAndMessages(null);
        }
        if (canInterface != null) {
            canInterface.closeCan1Interface();
            canInterface.closeCan2Interface();
        }
    }
}