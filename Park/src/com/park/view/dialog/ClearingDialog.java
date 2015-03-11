package com.park.view.dialog;

import com.park.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ClearingDialog extends DialogFragment{
    
    private static final String EXTRA_PLATTE_NUMBER = "platte_number";
    private static final String EXTRA_MONEY= "money";
    private static final String EXTRA_START_TIME= "start_time";
    private static final String EXTRA_END_TIME= "end_time";
    private static final String EXTRA_LENGTH_OF_TIME= "length_if_time";
    
    private String mPlateNumber;
    private double mMoney = 0l;
    private String mStartTime;
    private String mEndTime;
    private String mLengthOfTime;
    
    private OnBtnClickListener mClickListener;
    
    // TODO API未知，类型不缺定，时间参数根据自己需求改吧
    public static ClearingDialog newInstance(String plateNumber,
            double money, String startTime, String endTime, String lengthOfTime) {
        
        ClearingDialog dialog = new ClearingDialog();
        
        Bundle args = new Bundle();
        args.putString(EXTRA_PLATTE_NUMBER, plateNumber);
        args.putDouble(EXTRA_MONEY, money);
        args.putString(EXTRA_START_TIME, startTime);
        args.putString(EXTRA_END_TIME, endTime);
        args.putString(EXTRA_LENGTH_OF_TIME, lengthOfTime);
        
        dialog.setArguments(args);
        
        return dialog;
        
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mPlateNumber = getArguments().getString(EXTRA_PLATTE_NUMBER);
        mMoney = getArguments().getDouble(EXTRA_MONEY);
        mStartTime = getArguments().getString(EXTRA_START_TIME);
        mEndTime = getArguments().getString(EXTRA_END_TIME);
        mLengthOfTime = getArguments().getString(EXTRA_LENGTH_OF_TIME);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        
        LinearLayout layout = (LinearLayout) inflater.inflate(
                R.layout.dialog_clearing, container, false);
        
        TextView plateNumber = (TextView) layout.findViewById(R.id.clearing_plate_number);
        plateNumber.setText(mPlateNumber);
        
        
        TextView moneyTextView = (TextView) layout.findViewById(R.id.clearing_money);
        moneyTextView.setText(getResources().getString(R.string.clearing_money, mMoney));
        
        TextView timeTextView = (TextView) layout.findViewById(R.id.clearing_time);
        timeTextView.setText(Html.fromHtml(getResources().getString(R.string.clearing_time, mStartTime, mEndTime)));
        
        TextView timeLengthTextView = (TextView) layout.findViewById(R.id.clearing_time_length);
        timeLengthTextView.setText(Html.fromHtml(getResources().getString(R.string.clearing_time_length,mLengthOfTime)));
        
        layout.findViewById(R.id.dialog_flee_btn).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if(mClickListener != null) {
                    mClickListener.onFleeClicked();
                    ClearingDialog.this.dismissAllowingStateLoss();
                }
            }
        });
        
        layout.findViewById(R.id.dialog_cancle_btn).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if(mClickListener != null) {
                    mClickListener.onCancleClicked();
                    ClearingDialog.this.dismissAllowingStateLoss();
                }
            }
        });
        
        layout.findViewById(R.id.clearing_by_cash_btn).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if(mClickListener != null) {
                    mClickListener.onClearingByCash();
                    ClearingDialog.this.dismissAllowingStateLoss();
                }
            }
        });
        
        layout.findViewById(R.id.clearing_on_line_btn).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if(mClickListener != null) {
                    mClickListener.onClearingOnline();
                    ClearingDialog.this.dismissAllowingStateLoss();
                }
            }
        });
        
        return layout;
    }
    
    public void setOnBtnClickListener(OnBtnClickListener clickListener) {
        mClickListener = clickListener;
    }
    
    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (Exception e) {
        }
    }
    
    @Override
    public int show(FragmentTransaction transaction, String tag) {
        try {
            return super.show(transaction, tag);
        } catch (Exception e) {
        }
        return 0;
    }
    
    public interface OnBtnClickListener {
        // 取消按钮
        public void onCancleClicked();
        // 逃单按钮
        public void onFleeClicked();
        // 现金结算
        public void onClearingByCash();
        // 在线结算
        public void onClearingOnline();
    }
}
