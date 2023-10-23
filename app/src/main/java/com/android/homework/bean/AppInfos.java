package com.android.homework.bean;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * @auther 19062
 * @date 2023/7/14
 * @time 17:58.
 */


public class AppInfos {
    private String appName;
    private String packageName;
    private Drawable appIcon;
    private Intent intent;

    private int flg;

    public int getFlg() {
        return flg;
    }

    public void setFlg(int flg) {
        this.flg = flg;
    }

    public AppInfos(CharSequence appName, String packageName, Drawable appIcon) {
        appName = appName == null ? "" : appName.toString();
        packageName = packageName == null ? "" : packageName;
        appIcon = appIcon;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    @Override
    public String toString() {
        return "AppInfos{" +
                "appName='" + appName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", appIcon=" + appIcon +
                ", intent=" + intent +
                '}';
    }
}
