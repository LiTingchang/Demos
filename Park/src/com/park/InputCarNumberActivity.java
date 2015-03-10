package com.park;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.park.bean.InputChar;
import com.park.view.InputPanel;

public class InputCarNumberActivity extends FragmentActivity{
    
    private InputPanel inputPanel1;
    private InputPanel inputPanel2;
    private InputPanel inputPanel3;
    private InputPanel inputPanel4;
    
    private View inputContainerPanel23;
    private View inputContainerPanel4;
    
    private RadioButton radioBtn1;
    private RadioButton radioBtn2;
    private RadioButton radioBtn3;
    
    private EditText inputPlateNumber;
    private View generateOrder;
    
    private View callSysKeyboard;
    
    private StringBuffer plateNumer;
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        
        plateNumer = new StringBuffer();
        
        setContentView(R.layout.activity_input_car_number);
        
        inputPanel1 = (InputPanel) findViewById(R.id.input_panel1);
        inputPanel2 = (InputPanel) findViewById(R.id.input_panel2);
        inputPanel3 = (InputPanel) findViewById(R.id.input_panel3);
        inputPanel4 = (InputPanel) findViewById(R.id.input_panel4);
        inputContainerPanel23 = findViewById(R.id.input_panel23_container);
        inputContainerPanel4 = findViewById(R.id.input_panel4_container);
        
        radioBtn1 = (RadioButton) findViewById(R.id.input_tab_1);
        radioBtn2 = (RadioButton) findViewById(R.id.input_tab_2);
        radioBtn3 = (RadioButton) findViewById(R.id.input_tab_3);
        
        generateOrder = findViewById(R.id.generate_order);
        
        callSysKeyboard = findViewById(R.id.call_system_keyboard);
        
        //  省份
        String [] str = {"京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", 
                "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋", 
                "蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"};  
        List<InputChar> inputChars = new ArrayList<InputChar>();
        for(int i = 0; i < str.length; ++i) {
            InputChar inputChar = new InputChar();
            inputChar.id = i;
            inputChar.content = str[i];
            inputChars.add(inputChar);
        }
        inputPanel1.init(inputChars, R.layout.view_input_line);
        inputPanel1.setOnItemClickListener(new InputPanel.OnItemClickListener() {
            
            @Override
            public void onItemClick(int position, InputChar inputChar) {
                appendPlateNumber(inputChar);
            }
        });
        
        
        // 字母 
        List<InputChar> inputChars1 = new ArrayList<InputChar>();
        char ch = 'A';
        for(int i = 0; i <= 25; ++i) {
            InputChar inputChar = new InputChar();
            inputChar.id = i;
            inputChar.content = String.valueOf((char)(ch + i));
            inputChars1.add(inputChar);
        }
        
        inputPanel2.init(inputChars1, R.layout.view_input_char , 5);
        inputPanel2.setOnItemClickListener(new InputPanel.OnItemClickListener() {
            
            @Override
            public void onItemClick(int position, InputChar inputChar) {
                appendPlateNumber(inputChar);
            }
        });
        
        
        // 数字
        List<InputChar> inputChars2 = new ArrayList<InputChar>();
        for(int i = 0; i <= 9; ++i) {
            InputChar inputChar = new InputChar();
            inputChar.id = i;
            inputChar.content = (i+1)%10 + "";
            inputChars2.add(inputChar);
        }
        inputPanel3.init(inputChars2, R.layout.view_input_number, 5);
        inputPanel3.setOnItemClickListener(new InputPanel.OnItemClickListener() {
            
            @Override
            public void onItemClick(int position, InputChar inputChar) {
                appendPlateNumber(inputChar);
            }
        });
        
        
        // 特殊牌照
        String [] str2 = {"军", "空", "海", "北", "沈", "兰", "济", "南", "广", 
                "成", "WJ", "边", "电", "金", "警", "森", "通", "消"};  
        List<InputChar> inputChars4 = new ArrayList<InputChar>();
        for(int i = 0; i < str2.length; ++i) {
            InputChar inputChar = new InputChar();
            inputChar.id = i;
            inputChar.content = str2 [i];
            inputChars4.add(inputChar);
        }
        
        inputPanel4.init(inputChars4, R.layout.view_input_line);
        inputPanel4.setOnItemClickListener(new InputPanel.OnItemClickListener() {
            
            @Override
            public void onItemClick(int position, InputChar inputChar) {
                appendPlateNumber(inputChar);
            }
        });
        
        
        radioBtn1.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    inputPanel1.setVisibility(View.VISIBLE);
                    inputContainerPanel23.setVisibility(View.GONE);
                    inputContainerPanel4.setVisibility(View.GONE);
                }
            }
        });
        
        radioBtn2.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    inputPanel1.setVisibility(View.GONE);
                    inputContainerPanel23.setVisibility(View.VISIBLE);
                    inputContainerPanel4.setVisibility(View.GONE);
                }
            }
        });

        radioBtn3.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    inputPanel1.setVisibility(View.GONE);
                    inputContainerPanel23.setVisibility(View.GONE);
                    inputContainerPanel4.setVisibility(View.VISIBLE);
                }
            }
        });
        
        radioBtn1.setChecked(true);
        
        
        
        inputPlateNumber = (EditText) findViewById(R.id.input_plate_number);
        // 设置不接收系统软件盘输入
        inputPlateNumber.setInputType(InputType.TYPE_NULL);
        
//        inputPlateNUmber.setInputType(InputType.TYPE_CLASS_TEXT);

//        inputPlateNumber.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
        
        generateOrder.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
            }
        });
        
        callSysKeyboard.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                inputPlateNumber.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        });
    }
    
    private void appendPlateNumber(InputChar inputChar) {
        plateNumer.append(inputChar.content);
        
        inputPlateNumber.setText(plateNumer.toString());
    }
    
    private void removeEndPlateNumber() {
        int length = plateNumer.length();
        plateNumer.delete(length - 1, length);
        
        inputPlateNumber.setText(plateNumer.toString());
    }
    
    
    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, InputCarNumberActivity.class);
        activity.startActivity(intent);
    }
}
