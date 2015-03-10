package com.jitizhihui.wxassistant.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class PackageUtil {
    
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }
    
    public static int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        int versionCode = 0;
        try {
            versionCode = packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        
        return versionCode;
    }
    
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String versionName = "";
        try {
            versionName = packageManager.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        
        return versionName;
    }

}
