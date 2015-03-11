package com.park.view.dialog;

import android.annotation.SuppressLint;
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

import com.park.R;

@SuppressLint("NewApi")
public class ClearingSuccessDialog extends DialogFragment{
    
    
    
    private static final String EXTRA_PLATTE_NUMBER = "platte_number";
    private static final String EXTRA_MONEY= "money";
    
    private String mPlateNumber = "";
    private double mMoney = 0l;
    
    
    public static ClearingSuccessDialog newInstance(String plateNumber, double money) {
        
        ClearingSuccessDialog clearingSuccessDialog = new ClearingSuccessDialog();
        
        Bundle args = new Bundle();
        args.putString(EXTRA_PLATTE_NUMBER, plateNumber);
        args.putDouble(EXTRA_MONEY, money);
        
        clearingSuccessDialog.setArguments(args);
        
        return clearingSuccessDialog;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlateNumber = getArguments().getString(EXTRA_PLATTE_NUMBER);
        mMoney = getArguments().getDouble(EXTRA_MONEY);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        
        LinearLayout layout = (LinearLayout) inflater.inflate(
                R.layout.dialog_clearing_success, container, false);
        
        TextView plateNumber = (TextView) layout.findViewById(R.id.clearing_plate_number);
        plateNumber.setText(mPlateNumber);
        
        
        TextView money = (TextView) layout.findViewById(R.id.clearing_money);
        
        // TODO formate
        
        money.setText(Html.fromHtml(getResources().getString(R.string.clearing_success_money, mMoney)));
        
        layout.findViewById(R.id.clearing_success_ok_btn).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ClearingSuccessDialog.this.dismissAllowingStateLoss();
            }
        });
        
        return layout;
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
    
    
    
}
