package com.jitizhihui.wxassistant.util;

import com.loopj.android.http.AsyncHttpClient;

public class MyHttpClient {
    
    private static AsyncHttpClient asyncHttpClient;
    
    public static AsyncHttpClient getHttpClient() {
        if(asyncHttpClient == null) {
            synchronized (MyHttpClient.class) {
                if (asyncHttpClient == null) {
                    asyncHttpClient = new AsyncHttpClient();
                    asyncHttpClient.setTimeout(30000);
                }
            }
        }
        
        return asyncHttpClient;
    }
    
}
