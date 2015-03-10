package com.park;

import java.util.ArrayList;
import java.util.List;

import com.park.bean.InputChar;
import com.park.utils.ToastUtil;
import com.park.view.InputPanel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class InputCarNumberActivity extends FragmentActivity{
    
    
    InputPanel inputPanel;
    
    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        
        
        setContentView(R.layout.activity_input_car_number);
        
        
        List<InputChar> inputChars = new ArrayList<InputChar>();
        for(int i = 0; i < 36; ++i) {
            InputChar inputChar = new InputChar();
            inputChar.id = i;
            inputChar.content = i + "";
            inputChars.add(inputChar);
        }
        
        inputPanel = (InputPanel) findViewById(R.id.input_panel);
        inputPanel.init(inputChars, R.layout.view_input_line);
        
        inputPanel.setOnItemClickListener(new InputPanel.OnItemClickListener() {
            
            @Override
            public void onItemClick(int position, InputChar inputChar) {
                // TODO Auto-generated method stub
                ToastUtil.shortToast(getApplicationContext(), inputChar.content);
            }
        });
    }
    
    
    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, InputCarNumberActivity.class);
        activity.startActivity(intent);
    }

}
