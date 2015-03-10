package com.jitizhihui.wxassistant.util;

import java.util.UUID;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.jitizhihui.wxassistant.constants.Constants;

public class DeviceUtil {
    
    public static String getIMEI(Context context) {
        return ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }
    
    
    public static String getGuid(Context context) {
        String uuid = PreferenceUtil.getString(context, Constants.SP_IMEI, "");
        if(StringUtils.isEmptyOrWhitespace(uuid)) {
            uuid = DeviceUtil.getIMEI(context);
            if(StringUtils.isEmptyOrWhitespace(uuid)) {
                uuid = UUID.randomUUID().toString();
            }
            
            uuid = Constants.CLIENT + uuid;
            PreferenceUtil.putString(context, Constants.SP_IMEI, uuid);
        }
        
        return uuid;
    }
}
