
package com.jitizhihui.wxassistant;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.OnTextChanged;
import butterknife.Optional;

import com.google.gson.Gson;
import com.jitizhihui.wxassistant.constants.Constants;
import com.jitizhihui.wxassistant.constants.Constants.AddType;
import com.jitizhihui.wxassistant.mode.ClientInfo;
import com.jitizhihui.wxassistant.mode.Contact;
import com.jitizhihui.wxassistant.mode.ContactList;
import com.jitizhihui.wxassistant.util.ContactUtil;
import com.jitizhihui.wxassistant.util.DeviceUtil;
import com.jitizhihui.wxassistant.util.MyHttpClient;
import com.jitizhihui.wxassistant.util.NetworkUtil;
import com.jitizhihui.wxassistant.util.PackageUtil;
import com.jitizhihui.wxassistant.util.PreferenceUtil;
import com.jitizhihui.wxassistant.util.StringUtils;
import com.jitizhihui.wxassistant.util.ToastUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.ltc.lib.commontitle.CommonTitle;

public class MainActivity extends FragmentActivity {

    private static final int REQUEST_CHARGE = 101;

    @InjectView(R.id.titlebar)
    CommonTitle title;

    @InjectView(R.id.msg)
    TextView msgTextView;
    @InjectView(R.id.input_mobile_number)
    EditText editText;
    @InjectView(R.id.add)
    Button addButton;
    @InjectView(R.id.delete)
    Button deleteButton;
    @InjectView(R.id.stop)
    Button stopButton;
    @InjectView(R.id.getManyTimes)
    Button getActivateButton;
    @InjectView(R.id.luck)
    Button luckButton;
    @InjectView(R.id.tips)
    Button tipsButton;

    // @InjectViews({R.id.addTypeTen, R.id.addTypeHundred, R.id.addTypeThousan})
    // List<RadioButton> radioButtons;

    private AddType addType = AddType.SIZE_TEN;

    private String mobileNumberString;

    private String luckNumber;

    private boolean stopFlag = false;

    int duanCount;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        title.setOnTitleClickListener(new CommonTitle.TitleClickListener() {

            @Override
            public void onRightClicked(View parent, View v) {
                AboutActivity.launch(MainActivity.this);
            }

            @Override
            public void onRight2Clicked(View parent, View v) {
            }

            @Override
            public void onLeftClicked(View parent, View v) {
            }
        });

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 0) {
                    luckNumber = StringUtils.EMPTY_STRING;
                }
            }
        });

        if (NetworkUtil.isNetworkAvailable(MainActivity.this)) {
            checkUploadLocalPhoneNUmber();
            checkUpdate();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CHARGE) {

        }
    }

    private void changeLuckNumberLength() {
        if (StringUtils.isEmptyOrWhitespace(luckNumber)) {
            switch (addType) {
                case SIZE_TEN:
                    editText.setHint("手机号：完整11位或前10位");
                    break;
                case SIZE_HUNDRED:
                    editText.setHint("手机号：完整11位或前9位");
                    break;
                case SIZE_THOUSAND:
                    editText.setHint("手机号：完整11位或前8位");
                    break;

                default:
                    break;
            }
        } else {
            editText.setText(luckNumber.substring(0,
                    Constants.MOBILE_NUMBER_LENGTH - addType.getendLen()));
        }
    }

    @OnCheckedChanged(R.id.addTypeTen)
    void onAddTenChecked(boolean isChecked) {
        if (isChecked) {
            addType = AddType.SIZE_TEN;
            msgTextView.setText("输入手机号前10位\n单次插入10个号码");
            changeLuckNumberLength();
        }
    }

    @OnCheckedChanged(R.id.addTypeHundred)
    void addHundredCheched(boolean isChecked) {
        if (isChecked) {
            addType = AddType.SIZE_HUNDRED;
            msgTextView.setText("输入手机号前9位\n单次插入100个号码");
            changeLuckNumberLength();
        }
    }

    @OnCheckedChanged(R.id.addTypeThousan)
    void addThousandCheched(boolean isChecked) {
        if (isChecked) {
            addType = AddType.SIZE_THOUSAND;
            msgTextView.setText("输入手机号前8位\n单次插入1000个号码");
            changeLuckNumberLength();
        }
    }

    @OnClick(R.id.luck)
    void getLuckPhoneNumber() {
        if (PreferenceUtil.getInt(MainActivity.this, Constants.AVAILABLE_NUMBER, 0) <= 0) {
            ToastUtil.shortToast(MainActivity.this, "余额不足，请充值");
            return;
        }

        String guid = DeviceUtil.getGuid(MainActivity.this);
        RequestParams requestParams = new RequestParams();
        requestParams.put("guid", guid);

        MyHttpClient.getHttpClient().get(Constants.HOST + "/v1/phoneno/goodluck", requestParams,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        luckButton.setEnabled(false);
                        super.onStart();
                    }

                    @Override
                    public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                        try {
                            String str = new String(arg2, "UTF-8");

                            JSONObject jsonObject = new JSONObject(str);
                            int status = jsonObject.optInt("status", 0);
                            String phoneNo = jsonObject.optString("phoneNo");

                            if (status == 0 && !StringUtils.isEmptyOrWhitespace(phoneNo)) {
                                luckNumber = phoneNo;
                                editText.setText(phoneNo.substring(0,
                                        Constants.MOBILE_NUMBER_LENGTH - addType.getendLen()));
                            } else {
                                ToastUtil.shortToast(MainActivity.this, "手气不是很好，再试一次吧");
                            }

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                        ToastUtil.shortToast(MainActivity.this, "手气不是很好，再试一次吧");
                    }

                    @Override
                    public void onFinish() {
                        luckButton.setEnabled(true);
                        super.onFinish();
                    }
                });
    }

    @OnClick(R.id.add)
    void addPhoneNumber() {
        int currentAvailableNumber = PreferenceUtil.getInt(MainActivity.this,
                Constants.AVAILABLE_NUMBER, 0);
        int addSize = addType.getSize();

        if (currentAvailableNumber <= 0) {
            ToastUtil.shortToast(MainActivity.this, "当前可用余量为0，请充值");
            return;
        } else if (currentAvailableNumber < addType.getSize()) {
            addSize = currentAvailableNumber;
            ToastUtil.shortToast(MainActivity.this, "当前可用余量为" + currentAvailableNumber);
        }

        mobileNumberString = editText.getText().toString().trim();

        if (mobileNumberString.length() == Constants.MOBILE_NUMBER_LENGTH) {
        } else if (mobileNumberString.length() == Constants.MOBILE_NUMBER_LENGTH
                - addType.getendLen()) {
            switch (addType) {
                case SIZE_TEN:
                    mobileNumberString += "0";
                    break;
                case SIZE_HUNDRED:
                    mobileNumberString += "00";
                    break;
                case SIZE_THOUSAND:
                    mobileNumberString += "000";
                    break;
                default:
                    break;
            }
        } else {
            ToastUtil.shortToast(MainActivity.this, "错误的手机号");
            return;
        }

        if (!StringUtils.validatePhone(mobileNumberString)) {
            ToastUtil.shortToast(MainActivity.this, "非法手机号");
            return;
        }

        final String tempString = mobileNumberString.substring(0, Constants.MOBILE_NUMBER_LENGTH
                - addType.getendLen());

        stopFlag = false;
        new AsyncTask<Integer, Integer, Integer>() {

            @Override
            protected void onPreExecute() {
                addButton.setEnabled(false);
                deleteButton.setEnabled(false);
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Integer... params) {

                final int addSize = params[0];

                List<String> phoneNumbersList = ContactUtil
                        .getpreExistingNumbers(getApplicationContext());
                int addCount = 0;
                int endNum = 0;
                while (addCount < addSize && endNum < addType.getSize()) {
                    if (stopFlag) {
                        break;
                    }

                    String last = StringUtils.complementZero(addType.getendLen(), endNum);
                    String temp = tempString + last;

                    if (!phoneNumbersList.contains(temp)) {
                        ContactUtil.insertContact(getContentResolver(),
                                Constants.NAME_START + temp, temp);
                        publishProgress(++addCount);
                    }

                    ++endNum;
                }

                return addCount;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                msgTextView.setText("成功写入：" + values[0] + "个");
            }

            @Override
            protected void onPostExecute(Integer result) {
                int current = PreferenceUtil.getInt(MainActivity.this, Constants.AVAILABLE_NUMBER,
                        0);
                PreferenceUtil.putInt(MainActivity.this, Constants.AVAILABLE_NUMBER, current
                        - result);

                msgTextView.setText("写入完毕，剩余可用：" + (current - result));

                addButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        }.execute(addSize);
    }

    @OnClick(R.id.delete)
    void deletePhoneNumber() {
        mobileNumberString = editText.getText().toString().trim();

        if (mobileNumberString.length() < 1) {
            ToastUtil.shortToast(getApplicationContext(), "请输入手机号码前任意几位");
            return;
        }

        stopFlag = false;
        new AsyncTask<Void, Integer, Integer>() {

            @Override
            protected void onPreExecute() {

                addButton.setEnabled(false);
                deleteButton.setEnabled(false);
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... params) {
                int deleteCount = 0;

                Map<String, String> contacts = ContactUtil.getContacts(getApplicationContext());
                Iterator<String> iterator = contacts.keySet().iterator();

                while (iterator.hasNext()) {
                    if (stopFlag) {
                        break;
                    }
                    String name = iterator.next();
                    String numberString = contacts.get(name);
                    if (name.startsWith(Constants.NAME_START)
                            && numberString.startsWith(mobileNumberString)) {
                        ContactUtil.deleteContact(getContentResolver(), numberString);

                        publishProgress(++deleteCount);
                    }
                }

                return deleteCount;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                msgTextView.setText("成功删除：" + values[0] + "个");
            }

            @Override
            protected void onPostExecute(Integer result) {
                addButton.setEnabled(true);
                deleteButton.setEnabled(true);

                msgTextView.setText("成功删除：" + result + "个");
            }
        }.execute();
    }

    @OnClick(R.id.stop)
    void stop() {
        stopFlag = true;
    }

    @OnClick(R.id.getManyTimes)
    void launchChargeActivity() {
        ChargeActivity.launchForResult(MainActivity.this, REQUEST_CHARGE);
    }

    @OnClick(R.id.tips)
    void showTips() {
        TipsDialogFragment tipsDialogFragment = TipsDialogFragment
                .newInstance();
        tipsDialogFragment.show(getSupportFragmentManager(), "tips");
    }

    private void uploadContacts(final String guid, final List<Contact> contacts, int currentDuan) {

        ContactList contactList = new ContactList();
        contactList.guid = guid;
        contactList.start = currentDuan * Constants.UPLOAD_SIEZE;
        contactList.total = contacts.size();
        contactList.size = (currentDuan + 1) * Constants.UPLOAD_SIEZE > contactList.total
                ? (contactList.total - currentDuan * Constants.UPLOAD_SIEZE)
                : Constants.UPLOAD_SIEZE;
        contactList.list = contacts
                .subList(contactList.start, contactList.start + contactList.size);

        Gson gson = new Gson();
        String content = gson.toJson(contactList);

        RequestParams requestParams = new RequestParams();
        requestParams.put("guid", guid);
        requestParams.put("content", content);

        MyHttpClient.getHttpClient().post(Constants.HOST + "/v1/phoneno/upload", requestParams,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                        count++;
                        if (count < duanCount) {
                            uploadContacts(guid, contacts, count);
                        } else {
                            PreferenceUtil.putBoolean(MainActivity.this,
                                    Constants.SP_IS_UPLOADED_NUMS, true);
                        }
                    }

                    @Override
                    public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                    }
                });
    }

    private void checkUploadLocalPhoneNUmber() {
        boolean isUploadedNums = PreferenceUtil.getBoolean(MainActivity.this,
                Constants.SP_IS_UPLOADED_NUMS, false);
        if (!isUploadedNums) {
            final String guid = DeviceUtil.getGuid(MainActivity.this);

            new AsyncTask<Void, Void, List<Contact>>() {

                @Override
                protected List<Contact> doInBackground(Void... params) {

                    return ContactUtil.getContactsList(MainActivity.this);
                }

                @Override
                protected void onPostExecute(List<Contact> result) {

                    if (result == null || result.size() == 0) {
                        return;
                    }

                    duanCount = result.size() / Constants.UPLOAD_SIEZE;
                    if (result.size() % Constants.UPLOAD_SIEZE != 0) {
                        duanCount++;
                    }
                    count = 0;

                    uploadContacts(guid, result, count);
                };
            }.execute();
        }
    }

    private void checkUpdate() {

        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.YEAR) * 1000 + calendar.get(Calendar.DAY_OF_YEAR);

        int storedDay = PreferenceUtil.getInt(MainActivity.this, Constants.CALENDAR_DAY, 0);
        if (day > storedDay) {
            // 检测升级
            RequestParams requestParams = new RequestParams();
            requestParams.put("guid", DeviceUtil.getGuid(MainActivity.this));
            requestParams.put("type", "android");

            MyHttpClient.getHttpClient().get(Constants.HOST + "/v1/client/latestVersion",
                    requestParams, new AsyncHttpResponseHandler() {

                        @Override
                        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                            ClientInfo clientInfo = null;
                            try {
                                String str = new String(arg2, "UTF-8");
                                Gson gson = new Gson();
                                clientInfo = gson.fromJson(str, ClientInfo.class);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            if (clientInfo == null) {
                                return;
                            }

                            if (clientInfo.version > PackageUtil
                                    .getVersionCode(MainActivity.this)) {

                                final String url = clientInfo.url.startsWith("http://") ? clientInfo.url
                                        :
                                        "http://" + clientInfo.url;
                                AlertDialog.Builder builder = new AlertDialog.Builder(
                                        MainActivity.this);
                                builder.setTitle("升级提示");
                                builder.setMessage(clientInfo.description);
                                builder.setNeutralButton("取消", new OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.setPositiveButton("确定", new OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                                                    .parse(url));
                                            startActivity(intent);
                                        } catch (Exception e) {
                                            ToastUtil.shortToast(MainActivity.this, "无法打开链接");
                                        }
                                    }
                                });
                                builder.show();
                            }

                            PreferenceUtil.putInt(MainActivity.this, Constants.CALENDAR_DAY,
                                    day);
                        }

                        @Override
                        public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                Throwable arg3) {
                        }
                    });
        }
    }
}
