
package com.jitizhihui.wxassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.jitizhihui.wxassistant.constants.Constants;
import com.jitizhihui.wxassistant.util.PreferenceUtil;
import com.jitizhihui.wxassistant.util.ToastUtil;
import com.ltc.lib.commontitle.CommonTitle;

public class AboutActivity extends Activity {

    @InjectView(R.id.titlebar)
    CommonTitle title;

//    @InjectView(R.id.about_icon)
//    ImageView aboutIcon;

//    private int clickCount = 0;
//    private long firstClickTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.inject(this);

        title.setOnTitleClickListener(new CommonTitle.TitleClickListener() {

            @Override
            public void onRightClicked(View parent, View v) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onRight2Clicked(View parent, View v) {
            }

            @Override
            public void onLeftClicked(View parent, View v) {
                AboutActivity.this.finish();
            }
        });

    }

//    @OnClick(R.id.about_icon)
//    void onClickIcon() {
//        long l = System.currentTimeMillis();
//        if (l - firstClickTime <= 500L) {
//            clickCount++;
//        } else {
//            clickCount = 1;
//        }
//
//        firstClickTime = l;
//        if (this.clickCount >= 5) {
//            this.clickCount = 0;
//            if (PreferenceUtil.getInt(this, Constants.AVAILABLE_NUMBER, 0) <= 0) {
//                PreferenceUtil.putInt(this, Constants.AVAILABLE_NUMBER, 10);
//                ToastUtil.shortToast(this, "获赠10个额度");
//            }
//        }
//    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }
}
