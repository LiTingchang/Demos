package com.jitizhihui.wxassistant;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;

import com.jitizhihui.wxassistant.constants.Constants;
import com.jitizhihui.wxassistant.util.MyHttpClient;
import com.jitizhihui.wxassistant.util.NetworkUtil;
import com.jitizhihui.wxassistant.util.PreferenceUtil;
import com.jitizhihui.wxassistant.util.StringUtils;
import com.jitizhihui.wxassistant.util.ToastUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.ltc.lib.commontitle.CommonTitle;

public class ChargeActivity extends Activity{
    
    @InjectView(R.id.titlebar)
    CommonTitle title;
    @InjectView(R.id.availableNo)
    TextView availableTextView;
    @InjectView(R.id.inputPhoneNumber)
    EditText chargePhoneNUmberEditText;
    @InjectView(R.id.inputChargeCode)
    EditText chargeCodeEditText;
    @InjectView(R.id.chargeBtn)
    Button chargeButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);
        ButterKnife.inject(this);
        
        title = (CommonTitle) findViewById(R.id.titlebar);
        title.setOnTitleClickListener(new CommonTitle.TitleClickListener() {
            
            @Override
            public void onRightClicked(View parent, View v) {
            }
            
            @Override
            public void onRight2Clicked(View parent, View v) {
            }
            
            @Override
            public void onLeftClicked(View parent, View v) {
                ChargeActivity.this.finish();
            }
        });
        
        availableTextView.setText("当前可用额度" + PreferenceUtil.getInt(ChargeActivity.this, Constants.AVAILABLE_NUMBER, 0) + "个");
        
        chargeButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if(!NetworkUtil.isNetworkAvailable(ChargeActivity.this)) {
                    ToastUtil.shortToast(ChargeActivity.this, "网络不可用，请联网后重试");
                    return;
                }
                
                String phoneNumber = chargePhoneNUmberEditText.getText().toString().trim();
                if(!StringUtils.validatePhone(phoneNumber)) {
                    ToastUtil.shortToast(ChargeActivity.this, "手机号码格式不正确");
                    return;
                }
                
                String chargeCode = chargeCodeEditText.getText().toString().trim();
                if(StringUtils.isEmptyOrWhitespace(chargeCode)) {
                    ToastUtil.shortToast(ChargeActivity.this, "请输入充值码");
                    return;
                }
                
                
                RequestParams requestParams = new RequestParams();
                
                requestParams.put("phoneNo", phoneNumber);
                requestParams.put("code", chargeCode);
                
                MyHttpClient.getHttpClient().get(Constants.HOST + "/v1/activate/activate", requestParams, new AsyncHttpResponseHandler() {
                    
                    @Override
                    public void onStart() {
                        chargeButton.setEnabled(false);
                        super.onStart();
                    }
                    
                    @Override
                    public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                        try {
                            String str = new String(arg2, "UTF-8");
                            
                            JSONObject jsonObject = new JSONObject(str);
                            int status = jsonObject.optInt("status", 0);
                            int times = jsonObject.optInt("manyTimes", -1);
                            
                            if (times != -1) {
                                int current = PreferenceUtil.getInt(ChargeActivity.this, Constants.AVAILABLE_NUMBER, 0);
                                int total = current + times;
                                PreferenceUtil.putInt(ChargeActivity.this, Constants.AVAILABLE_NUMBER, total);
                                
                                availableTextView.setText("当前可用个数： " + total);
                                
                                setResult(RESULT_OK);
                            } else {
                                ToastUtil.shortToast(ChargeActivity.this, "该激活码不可用");
                            }
                            
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    
                    @Override
                    public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                    }
                    
                    @Override
                    public void onFinish() {
                        chargeButton.setEnabled(true);
                        super.onFinish();
                    }
                });
            }
        });
    }
    
    public static void launchForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, ChargeActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

}
