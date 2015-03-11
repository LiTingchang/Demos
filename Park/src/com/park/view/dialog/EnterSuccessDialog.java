package com.park.view.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.park.R;

public class EnterSuccessDialog extends DialogFragment {
    
    private static final String EXTRA_PLATTE_NUMBER = "platte_number";
    
    private String mPlateNumber = "";
    
    public static EnterSuccessDialog newInstance(String plateNumber) {
        
        EnterSuccessDialog enterSuccessDialog = new EnterSuccessDialog();
        
        Bundle args = new Bundle();
        args.putString(EXTRA_PLATTE_NUMBER, plateNumber);
        
        enterSuccessDialog.setArguments(args);
        
        return enterSuccessDialog;
    }
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mPlateNumber = getArguments().getString(EXTRA_PLATTE_NUMBER);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        
        LinearLayout layout = (LinearLayout) inflater.inflate(
                R.layout.dialog_enter_success, container, false);
        
        TextView plateNumber = (TextView) layout.findViewById(R.id.enter_success_plate_number);
        
        plateNumber.setText(mPlateNumber);
        
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
