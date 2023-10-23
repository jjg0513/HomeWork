package com.android.homework.utils;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @auther 19062
 * @date 2023/8/29
 * @time 20:08.
 */


public class CameraManagerXlt {
    private static CameraManagerXlt mInstance;
    private Camera mCamera;
    private boolean isPreview;
    private static int cameraID = 1;
    private SurfaceTexture mTexture;
    private WeakReference<Activity> mActivity;

    public static CameraManagerXlt getInstance() {
        if (mInstance == null) {
            mInstance = new CameraManagerXlt();
        }
        return mInstance;
    }


    public void openCamera(Activity activity,int cameraId) {
        mActivity = new WeakReference<>(activity);
        if (mCamera == null) {
            try {
                mCamera = Camera.open(cameraId);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void startPreview(SurfaceTexture mTexture) {
        this.mTexture = mTexture;
        if (mCamera != null && !isPreview) {
            try {
                Log.d("xj","卸粮筒startPreview: ");
                mCamera.setPreviewTexture(mTexture);
                mCamera.startPreview();
                isPreview = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopPreview() {
        if (mCamera != null && isPreview) {
            Log.d("xj","卸粮筒stopPreview: ");
            mCamera.stopPreview();
            isPreview = false;
        }
    }

    public void stopCamera() {
        Log.d("xj","卸粮筒stopCamera: "+mCamera);
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    public CameraManagerXlt.CameraInfo getCameraInfo() {
        if(mCamera != null) {
            Camera.Size size = mCamera.getParameters().getPreviewSize();
            CameraInfo cameraInfo = new CameraInfo();
            cameraInfo.previewWidth = size.width;
            cameraInfo.previewHeight = size.height;
            Camera.CameraInfo cameraInfo1 = new Camera.CameraInfo();
            Camera.getCameraInfo(cameraID, cameraInfo1);
            cameraInfo.orientation = cameraInfo1.orientation;
            cameraInfo.front = cameraInfo1.facing;
            Camera.Size pictureSize = mCamera.getParameters().getPictureSize();
            cameraInfo.pictureWidth = pictureSize.width;
            cameraInfo.pictureHeight = pictureSize.height;
            return cameraInfo;
        }
        return null;
    }
    private void setParameters() {
        Camera.Parameters parameters = mCamera.getParameters();
        if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }
        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
        parameters.setPreviewSize(sizes.get(0).width, sizes.get(0).height);
        List<Camera.Size> pictureSizes = parameters.getSupportedPictureSizes();
        parameters.setPictureSize(pictureSizes.get(0).width, pictureSizes.get(0).height);
        setCameraDisplayOrientation(mActivity.get());
        mCamera.setParameters(parameters);
    }


    /**
     * 得到摄像头默认旋转角度后，旋转回来  注意是逆时针旋转
     *
     * @param activity
     */
    public void setCameraDisplayOrientation(Activity activity) {
        CameraInfo info = getCameraInfo();
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int result;
        if (info.front == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        mCamera.setDisplayOrientation(result);
    }

    public class CameraInfo {

        public int previewWidth;

        public int previewHeight;

        public int orientation;

        public int front;

        public int pictureWidth;

        public int pictureHeight;
    }



}