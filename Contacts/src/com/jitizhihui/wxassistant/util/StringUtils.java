package com.jitizhihui.wxassistant.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {
    
    public static final String EMPTY_STRING = "";
    public static final String DOUBLE_SPACE = "  ";
    
    public static final String DOT = ",";

    public static final String WHITE_SPACES = " \r\n\t\u3000\u00A0\u2007\u202F";

    public static final String LINE_BREAKS = "\r\n";
    
    private static final String PHONE_PATTERN = "^((170)|(13[0-9])|(15[^4,\\D])|(18[0,2,3,5-9]))\\d{8}$";
    
    public static boolean validatePhone(final String phone) {
//        Pattern pattern = Pattern.compile(PHONE_PATTERN);
//        Matcher matcher = pattern.matcher(phone);
//        return matcher.matches();
        return phone.length() == 11;
    }
    
    public static String complementZero(int len, int num) {
        String temp = String.valueOf(num);
        if(len <= temp.length()) {
            return temp;
        }
        
        return String.format("%0" + len + "d", num);
    }
    
    /**
     * Compares two strings, guarding against nulls If both Strings are null we
     * return true
     */
    public static boolean equals(String s1, String s2) {
        if (s1 == s2) {
            return true; // Either both the same String, or both null
        }
        if (s1 != null) {
            if (s2 != null) {
                return s1.equals(s2);
            }
        }
        return false;
    }

    public static boolean equalsIgnoreCase(String s1, String s2) {
        if (s1 == s2) {
            return true; // Either both the same String, or both null
        }
        if (s1 != null) {
            if (s2 != null) {
                return s1.equalsIgnoreCase(s2);
            }
        }
        return false;
    }
    
    public static String strip(String str) {
        return megastrip(str, true, true, WHITE_SPACES);
    }
    
    /**
     * This is a both way strip
     * 
     * @param str
     *            the string to strip
     * @param left
     *            strip from left
     * @param right
     *            strip from right
     * @param what
     *            character(s) to strip
     * @return the stripped string
     */
    public static String megastrip(String str, boolean left, boolean right,
            String what) {
        if (str == null) {
            return null;
        }

        int limitLeft = 0;
        int limitRight = str.length() - 1;

        while (left && limitLeft <= limitRight
                && what.indexOf(str.charAt(limitLeft)) >= 0) {
            limitLeft++;
        }
        while (right && limitRight >= limitLeft
                && what.indexOf(str.charAt(limitRight)) >= 0) {
            limitRight--;
        }

        return str.substring(limitLeft, limitRight + 1);
    }
    
    /**
     * Helper function for null, empty, and whitespace string testing.
     * 
     * @return true if s == null or s.equals("") or s contains only whitespace
     *         characters.
     */
    public static boolean isEmptyOrWhitespace(String s) {
        s = makeSafe(s);
        for (int i = 0, n = s.length(); i < n; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Helper function for making null strings safe for comparisons, etc.
     * 
     * @return (s == null) ? "" : s;
     */
    public static String makeSafe(String s) {
        return (s == null) ? EMPTY_STRING : s;
    }
}
