package com.jitizhihui.wxassistant.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkUtil {
	
	/**
     * Returns whether the network is available
     */
	public static boolean isNetworkAvailable(Context context){
	    
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isConnected()
                || info.getState() != NetworkInfo.State.CONNECTED) {
            return false;
        } 
        
        return true;
    }
}
