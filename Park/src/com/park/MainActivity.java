
package com.park;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Switch;

public class MainActivity extends Activity implements OnClickListener {

    int[] views = {
            R.id.temp1, R.id.temp2, R.id.temp3,
            R.id.temp4, R.id.temp5, R.id.temp6,
    };

    // editText.setInputType(InputType.TYPE_NULL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < views.length; ++i) {
            findViewById(views[i]).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.temp1:
                InputCarNumberActivity.launch(MainActivity.this);
                break;
            case R.id.temp2:

                break;
            case R.id.temp3:

                break;
            case R.id.temp4:

                break;

            default:
                break;
        }
    }
}
