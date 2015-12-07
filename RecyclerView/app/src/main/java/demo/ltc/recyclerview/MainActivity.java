package demo.ltc.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Integer> items;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        for (int i = 0; i < 20; ++i) {
            items.add(i);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        myAdapter = new MyAdapter(this, items);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class VH1 extends RecyclerView.ViewHolder{
        TextView textView;
        public VH1(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.title);
        }
    }

    class VH2 extends RecyclerView.ViewHolder{
        TextView textView;
        public VH2(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.title);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_1 = 0;
        private static final int TYPE_2 = 1;

        private Context context;
        List<Integer> data;

        LayoutInflater layoutInflater;

        public MyAdapter(Context contex, List<Integer> data)  {
            this.context = contex;
            this.data = data;
            layoutInflater = LayoutInflater.from(contex);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
//            return super.getItemViewType(position);
            return data.get(position) / 2 == 0 ? TYPE_1 : TYPE_2;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if(TYPE_1 == i) {
                return new VH1(layoutInflater.inflate(R.layout.item_1, viewGroup, false));
            } else if (TYPE_2 == i){
                return new VH1(layoutInflater.inflate(R.layout.item_2, viewGroup, false));
            }
            return null;
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        }

        void bindVH(VH2 vh2, String s) {

        }
    }
}
