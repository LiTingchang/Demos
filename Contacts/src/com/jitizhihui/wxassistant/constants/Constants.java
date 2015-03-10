package com.jitizhihui.wxassistant.constants;


public class Constants {
    
    public enum AddType {
        
        SIZE_TEN(1, 10),
        SIZE_HUNDRED(2, 100),
        SIZE_THOUSAND(3, 1000);
        
        private int endLen;
        private int size;
        
        private AddType(int endLen,int size) {
            this.endLen = endLen;
            this.size = size;
        }
        
        public int getendLen() {
            return endLen;
        }
        
        public int getSize() {
            return size;
        }
    }
    
    public static final String HOST = "http://www.jitizhihui.com/userflood";
    
    public static final int MOBILE_NUMBER_LENGTH = 11;
    public static final int UPLOAD_SIEZE = 100;
    
    public static final String NAME_START = "营销助手_";
    
    public static final String AVAILABLE_NUMBER = "sp_available_number";
    
    public static final String CLIENT = "ANDROID_";
    public static final String SP_IMEI = "sp_imei";
    public static final String SP_IS_UPLOADED_NUMS = "sp_is_upload_nums";
    public static final String CALENDAR_DAY = "sp_calendar_day";
}
