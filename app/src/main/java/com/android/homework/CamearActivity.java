package com.android.homework;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.Manifest;

import android.content.pm.PackageManager;

import android.graphics.SurfaceTexture;

import android.os.Build;
import android.os.Bundle;

import android.util.Log;

import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.homework.utils.CameraManagerHsj;
import com.android.homework.utils.CameraManagerLc;
import com.android.homework.utils.CameraManagerXlt;
import com.android.homework.utils.CameraUtilsWr;


public class CamearActivity extends AppCompatActivity {


    private TextureView  textview1,textview2;
    private SurfaceTexture  surfaceTexture1,surfaceTexture2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_camear);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.CAMERA}, 1);
            }
        }


//        hideNavigationBar();
        initView();


    }

    private void initView() {
        textview1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);

        textview1.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                Log.d("qq", "onSurfaceTextureAvailable: 小屏粮仓");
                surfaceTexture1 = surface;
                CameraManagerLc.getInstance().openCamera(CamearActivity.this,0);
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

            }
        });

        textview2.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                Log.d("qq", "onSurfaceTextureAvailable: 小屏粮仓");
                surfaceTexture2 = surface;
                CameraManagerLc.getInstance().openCamera(CamearActivity.this,0);
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

            }
        });


        textview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qq", "onSurfaceTextureAvailable: 小屏粮仓跳转");
                CameraManagerLc.getInstance().stopCamera();
                textview1.setVisibility(View.GONE);
                textview2.setVisibility(View.VISIBLE);
                if (surfaceTexture2 != null) {
                    CameraManagerLc.getInstance().openCamera(CamearActivity.this,0);
                    CameraManagerLc.getInstance().startPreview(surfaceTexture2);
                }
            }
        });

        textview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qq", "onSurfaceTextureAvailable: 大屏粮仓跳转");
                CameraManagerLc.getInstance().stopCamera();
                textview2.setVisibility(View.GONE);
                textview1.setVisibility(View.VISIBLE);
                CameraManagerLc.getInstance().openCamera(CamearActivity.this,0);
                CameraManagerLc.getInstance().startPreview(surfaceTexture1);

            }
        });

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        CameraManagerLc.getInstance().stopCamera();
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


        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN; // hide status bar

        if (android.os.Build.VERSION.SDK_INT >= 19) {
            uiFlags |= 0x00001000;    //SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide navigation bars - compatibility: building API level is lower thatn 19, use magic number directly for higher API target level
        } else {
            uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }

        getWindow().getDecorView().setSystemUiVisibility(uiFlags);
    }


}