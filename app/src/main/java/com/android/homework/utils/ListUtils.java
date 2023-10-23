package com.android.homework.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;


import com.android.homework.bean.AppInfos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @auther 19062
 * @date 2023/7/18
 * @time 9:52.
 */


public class ListUtils {


    private static final SparseArray<List<AppInfos>> mSparseList = new SparseArray<>();

    public static void putArray(int key, List<AppInfos> appMetaData) {
        mSparseList.put(key, appMetaData);
    }

    public static List<AppInfos> getArray(int key) {
        return mSparseList.get(key);
    }

    public static SparseArray<List<AppInfos>> getSparseList() {
        return mSparseList;
    }



    public static List<AppInfos> queryAppInfos(Context context) {
        List<AppInfos> mList = new ArrayList<>();
        // 获得PackageManager对象
        PackageManager pm = context.getPackageManager();

        Intent mIntent = new Intent();
        mIntent.setAction(Intent.ACTION_MAIN);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        // 通过查询，获得所有ResolveInfo对象
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mIntent, 0);
        // 调用系统排序 ， 根据name排序
        // 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
        Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(pm));
        if (mList != null) {
            mList.clear();
            for (ResolveInfo reInfo : resolveInfos) {
                String name = (String) reInfo.loadLabel(pm);
                Drawable drawable = reInfo.loadIcon(pm);
                String resolvePackageName = reInfo.activityInfo.packageName;
                String namemz = reInfo.activityInfo.name;
                int flags = reInfo.activityInfo.applicationInfo.flags;
                Intent intentLauncher = new Intent();
                intentLauncher.setComponent(new ComponentName(resolvePackageName, namemz));
//                 创建一个AppInfo对象，并赋值
                AppInfos appInfos = new AppInfos(name, resolvePackageName, drawable);
                appInfos.setAppIcon(drawable);
                appInfos.setAppName(name);
                appInfos.setPackageName(resolvePackageName);
                appInfos.setFlg(flags);
                mList.add(appInfos);
                Log.e("zs", "类名:" + name + "   " + "包名: " + reInfo.activityInfo.packageName + "   程序的name: " + reInfo.activityInfo.name);

            }

        }
        return mList;
    }



    public static List<AppInfos> dataAppInfos(Context context) {
        List<AppInfos> mList = new ArrayList<>();
        // 获得PackageManager对象
        PackageManager pm = context.getPackageManager();

        Intent mIntent = new Intent();
        mIntent.setAction(Intent.ACTION_MAIN);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        // 通过查询，获得所有ResolveInfo对象
        List<ResolveInfo> packageInfoList = pm.queryIntentActivities(mIntent, 0);
        // 调用系统排序 ， 根据name排序
        // 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
        Collections.sort(packageInfoList, new ResolveInfo.DisplayNameComparator(pm));
        if (mList != null) {
            mList.clear();
            for (ResolveInfo reInfo : packageInfoList) {
                String name = (String) reInfo.loadLabel(pm);
                Drawable drawable = reInfo.loadIcon(pm);
                String resolvePackageName = reInfo.activityInfo.packageName;
                String namemz = reInfo.activityInfo.name;
                Intent intentLauncher = new Intent();
                intentLauncher.setComponent(new ComponentName(resolvePackageName, namemz));
//                 创建一个AppInfo对象，并赋值
                AppInfos appInfos = new AppInfos(name, resolvePackageName, drawable);
                appInfos.setAppIcon(drawable);
                appInfos.setAppName(name);
                appInfos.setPackageName(resolvePackageName);

                mList.add(appInfos);
                Log.e("zs", "类名:" + name + "   " + "包名: " + reInfo.activityInfo.packageName + "   程序的name: " + reInfo.activityInfo.name);

            }

        }
        return mList;
    }

}




