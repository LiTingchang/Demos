
package com.park;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
    
    int [] views = {R.id.temp1, R.id.temp2, R.id.temp3,
            R.id.temp4, R.id.temp5, R.id.temp6,};
    
    
//    editText.setInputType(InputType.TYPE_NULL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        for(int i = 0; i < views.length;  ++i) {
            findViewById(views[i]).setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    InputCarNumberActivity.launch(MainActivity.this);
                }
            });
        }
        
    }
}
