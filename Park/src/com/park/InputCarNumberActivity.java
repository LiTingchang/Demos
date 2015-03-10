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
    InputPanel inputPanel2;
    InputPanel inputPanel3;
    InputPanel inputPanel4;
    
    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        
        
        setContentView(R.layout.activity_input_car_number);
        
        
        List<InputChar> inputChars = new ArrayList<InputChar>();
        char ch = 'A';
        for(int i = 0; i <= 25; ++i) {
            InputChar inputChar = new InputChar();
            inputChar.id = i;
            inputChar.content = String.valueOf((char)(ch + i));
            inputChars.add(inputChar);
        }
        
        inputPanel = (InputPanel) findViewById(R.id.input_panel);
        inputPanel.init(inputChars, R.layout.view_input_char , 5);
        
        inputPanel.setOnItemClickListener(new InputPanel.OnItemClickListener() {
            
            @Override
            public void onItemClick(int position, InputChar inputChar) {
                // TODO Auto-generated method stub
                ToastUtil.shortToast(getApplicationContext(), inputChar.content);
            }
        });
        
        
        
        List<InputChar> inputChars2 = new ArrayList<InputChar>();
        for(int i = 0; i <= 9; ++i) {
            InputChar inputChar = new InputChar();
            inputChar.id = i;
            inputChar.content = (i+1)%10 + "";
            inputChars2.add(inputChar);
        }
        
        inputPanel2 = (InputPanel) findViewById(R.id.input_panel2);
        inputPanel2.init(inputChars2, R.layout.view_input_number, 5);
        
        inputPanel2.setOnItemClickListener(new InputPanel.OnItemClickListener() {
            
            @Override
            public void onItemClick(int position, InputChar inputChar) {
                // TODO Auto-generated method stub
                ToastUtil.shortToast(getApplicationContext(), inputChar.content);
            }
        });
        
        String [] str = {"京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", 
                "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋", 
                "蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"};  
        List<InputChar> inputChars3 = new ArrayList<InputChar>();
        for(int i = 0; i < str.length; ++i) {
            InputChar inputChar = new InputChar();
            inputChar.id = i;
            inputChar.content = str[i];
            inputChars3.add(inputChar);
        }
        
        inputPanel3 = (InputPanel) findViewById(R.id.input_panel3);
        inputPanel3.init(inputChars3, R.layout.view_input_line);
        
        inputPanel3.setOnItemClickListener(new InputPanel.OnItemClickListener() {
            
            @Override
            public void onItemClick(int position, InputChar inputChar) {
                // TODO Auto-generated method stub
                ToastUtil.shortToast(getApplicationContext(), inputChar.content);
            }
        });
        
        String [] str2 = {"京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", 
                "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋", 
                "蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"};  
        List<InputChar> inputChars4 = new ArrayList<InputChar>();
        for(int i = 0; i < str2.length; ++i) {
            InputChar inputChar = new InputChar();
            inputChar.id = i;
            inputChar.content = str[i];
            inputChars3.add(inputChar);
        }
        
        inputPanel4 = (InputPanel) findViewById(R.id.input_panel4);
        inputPanel4.init(inputChars4, R.layout.view_input_line);
        
        inputPanel4.setOnItemClickListener(new InputPanel.OnItemClickListener() {
            
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
