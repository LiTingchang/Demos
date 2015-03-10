package com.jitizhihui.wxassistant;

import butterknife.ButterKnife;
import android.app.Application;

public class ContactsApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }

}
