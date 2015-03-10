package com.park;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.park.bean.PlateNumber;

public class ClearingActivity extends FragmentActivity{
    
    
    private ListView listView;
    private PlateNUmberAdapter adapter;
    
    private View callCameraBtn;
    
    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        
        setContentView(R.layout.activity_clearing);
        
        callCameraBtn = findViewById(R.id.call_camera);
        callCameraBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
            }
        });
        
        // 
        listView = (ListView) findViewById(R.id.clearing_list);
        
        List<PlateNumber> plateNumbers = new ArrayList<PlateNumber>();
        for(int i = 0; i < 4; ++i) {
            PlateNumber plateNumber = new PlateNumber();
            plateNumber.plateNO = "京A88888";
            plateNumber.startTime = System.currentTimeMillis();
            
            plateNumbers.add(plateNumber);
        }
        
        adapter = new PlateNUmberAdapter(ClearingActivity.this, plateNumbers);
        
        listView.setAdapter(adapter);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ClearingActivity.class);
        activity.startActivity(intent);
    }
    
    private static class ViewHolder {
        TextView plateNumberTextView;
        TextView startTimeTextView;
        TextView clearingTextView;
    }
    
    private class PlateNUmberAdapter extends BaseAdapter {
        private Context context;
        private List<PlateNumber> items;;
        private LayoutInflater inflater;
        
        DateFormat dataFormat = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        public PlateNUmberAdapter(Context context, List<PlateNumber> plateNumbers) {
            this.context = context;
            this.items = plateNumbers;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public Object getItem(int position) {
            return items == null ? null : items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return items == null ? 0 : position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.view_clearing_list_item, parent, false);
                
                viewHolder = new ViewHolder();
                viewHolder.plateNumberTextView = (TextView) convertView.findViewById(R.id.clearing_plate_number);
                viewHolder.startTimeTextView = (TextView) convertView.findViewById(R.id.clearing_start_time);
                viewHolder.clearingTextView = (TextView) convertView.findViewById(R.id.clearing_btn);
                
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            
            final PlateNumber plateNumber = (PlateNumber)getItem(position);
            
            viewHolder.plateNumberTextView.setText(plateNumber.plateNO);
            if(position % 2 == 0) {
                viewHolder.plateNumberTextView.setBackgroundColor(context.getResources().getColor(R.color.blue_light));
            } else {
                viewHolder.plateNumberTextView.setBackgroundColor(context.getResources().getColor(R.color.silver_dark));
            }
            
            
            viewHolder.startTimeTextView.setText(dataFormat.format(new Date(plateNumber.startTime)));
            
            viewHolder.clearingTextView.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    
                }
            });
            return convertView;
        }
        
    }

}
