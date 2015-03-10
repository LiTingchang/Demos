package com.jitizhihui.wxassistant;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class TipsDialogFragment extends DialogFragment {

    private ArrayList<TipsItem> tipsItems;


    public static TipsDialogFragment newInstance() {
        TipsDialogFragment rulesDialogFragment = new TipsDialogFragment();

        return rulesDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setStyle(STYLE_NO_TITLE, 0);
        
        
        tipsItems = new ArrayList<TipsItem>();
        
        String [] titles = getResources().getStringArray(R.array.tips_title_array);
        String [] contents = getResources().getStringArray(R.array.tips_content_array);;

        if (contents != null) {
            for (int i = 0; i < titles.length; i++) {
                TipsItem item = new TipsItem(titles[i], contents[i]);
                tipsItems.add(item);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(true);
        }
        
        LinearLayout layout = (LinearLayout) inflater.inflate(
                R.layout.fragmentdialog_tips, container, false);
        
        ListView listView = (ListView) layout.findViewById(R.id.fragment_rules_list);
        listView.setAdapter(new RulesAdapter(getActivity(), tipsItems));

        return layout;
    }
    
    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    
    private int [] liResId = {R.drawable.shape_oval_red, R.drawable.shape_oval_blue};
 
    static private class TipsItem {
        public String title;
        public String content;
        
        public TipsItem(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    static class ViewHolder {
        @InjectView(R.id.rules_item_title)
        TextView title;
        @InjectView(R.id.rules_item_content)
        TextView content;
        @InjectView(R.id.rules_item_li)
        ImageView li;
        
        
        public ViewHolder(View v) {
            ButterKnife.inject(this, v);
        }
    }

    class RulesAdapter extends ArrayAdapter<TipsItem> {

        LayoutInflater layoutInflater;
        ArrayList<TipsItem> items;
        HashMap<Integer, Boolean> isSelected;

        public RulesAdapter(Context context, ArrayList<TipsItem> items) {
            super(context, 0, items);
            this.items = items;
            layoutInflater = LayoutInflater.from(context);

        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public TipsItem getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView,
                ViewGroup parent) {
            final String title = getItem(position).title;
            final String content = getItem(position).content;

            ViewHolder holder = null;
            if (convertView == null) {

                convertView = layoutInflater.inflate(
                        R.layout.listview_item_tips,
                        parent, false);

                holder = new ViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.title.setText(title);
            holder.content.setText(content);
            holder.li.setImageResource(liResId[position % liResId.length]);

            return convertView;
        }
    }
    
    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (Exception e) {
        }
    }
}