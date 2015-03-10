package com.park.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.park.R;
import com.park.bean.InputChar;

public class InputPanel extends LinearLayout{
    
    private Context mContext;
    private FullSizeGridView myGridView;
    private InputPanelAdapter myAdapter;
    private OnItemClickListener mItemClickListener;
    private List<InputChar> mInputChars;

    public InputPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        
        LayoutInflater.from(context).inflate(
                R.layout.view_input_panel, this, true);

        myGridView = (FullSizeGridView) findViewById(R.id.input_panel_gridview);
    }

    public InputPanel(Context context) {
        this(context, null);
    }
    
    public void init(List<InputChar> inputChars, int layoutResId) {
        init(inputChars, layoutResId, 6);
    }
    
    
    public void init(List<InputChar> inputChars, int layoutResId, int numberColums) {
        
        myGridView.setNumColumns(numberColums);
        
        mInputChars = inputChars;
        
        myAdapter = new InputPanelAdapter(mContext, inputChars, layoutResId);
        
        myGridView.setAdapter(myAdapter);
        
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mItemClickListener != null) {
                    mItemClickListener.onItemClick(position, mInputChars.get(position));
                }
            }
        });
    }
    
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
    
    
    
    private static class ViewHolder {
        TextView inputCharTextView;;
    }
    
    private class InputPanelAdapter extends BaseAdapter {
        
        private LayoutInflater mLayoutInflater;
        private List<InputChar> mItems;
        private int mLayoutId;
        
        public InputPanelAdapter(Context context, List<InputChar> inputChars, int layoutId) {
            mLayoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            
            mItems = inputChars;    
            
            mLayoutId = layoutId;
        }

        @Override
        public int getCount() {
            return mItems == null ? 0 : mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems == null ? null : mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;

            final InputChar item = mItems.get(position);
            
            if(convertView == null) {
                convertView = mLayoutInflater.inflate(mLayoutId, parent, false);
                holder = new ViewHolder();

                holder.inputCharTextView = (TextView) convertView
                        .findViewById(R.id.input_char);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            holder.inputCharTextView.setText(item.content);
            
            return convertView;
        }
    }
    
    
    public interface OnItemClickListener {
        public void onItemClick(int position, InputChar inputChar);
    }

}
