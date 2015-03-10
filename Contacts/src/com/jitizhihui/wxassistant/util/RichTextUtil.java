package com.jitizhihui.wxassistant.util;

import java.util.List;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.util.Pair;

public class RichTextUtil
{
    
    // 简单的改变文字大小
    public static SpannableStringBuilder getSpannableString(List<Pair<String, Float>> list) {
        
        if (list == null || list.size() == 0)
        {
            return null;
        }
        
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        
        int position = 0;
        for ( Pair<String, Float> pair : list )
        {
            int len = pair.first.length();
            ssb.append(pair.first);
            
            ssb.setSpan(new RelativeSizeSpan(pair.second), position, position + len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            position += len;
        }
        
        return ssb;
    }
}
