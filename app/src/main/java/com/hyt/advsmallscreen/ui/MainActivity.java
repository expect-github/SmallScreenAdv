package com.hyt.advsmallscreen.ui;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyt.advsmallscreen.R;
import com.hyt.advsmallscreen.domain.HttpRequest;
import com.hyt.advsmallscreen.domain.adv.AdvControl;
import com.hyt.advsmallscreen.element.receive.NetStatueReceiver;
import com.hyt.advsmallscreen.element.receive.ServiceConnStatueReceiver;
import com.hyt.advsmallscreen.global.Action;
import com.hyt.advsmallscreen.global.IntentKey;
import com.hyt.advsmallscreen.global.Shared;
import com.hyt.advsmallscreen.ui.base.BaseActivity;
import com.hyt.advsmallscreen.ui.custom.SignalView;
import com.hyt.advsmallscreen.ui.fragment.VideoFragment;
import com.hyt.advsmallscreen.utils.AppInfoUtil;
import com.hyt.advsmallscreen.utils.FragmentTools;
import com.hyt.advsmallscreen.utils.IntentUtil;
import com.hyt.advsmallscreen.utils.LogUtil;
import com.hyt.advsmallscreen.utils.NetworkInfoUtil;
import com.hyt.advsmallscreen.utils.SharedUtlis;
import com.hyt.advsmallscreen.utils.ToastTools;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    private String TAG = getClass().getSimpleName();
    Handler handler = new Handler();
    private SharedUtlis sharedUtlis;
    private TextView tvDeviceInfo;
    private SignalView signalView;
    private UiReceiver uiReceiver;
    private ImageView iv_net_statue;
    private NetStatueReceiver netStatueReceiver;
    private ServiceConnStatueReceiver connStatueReceiver;
    
    public  static  final  String video_fragment_tag = "VideoFragment";
    private VideoFragment videoFragment;
    private AdvControl advControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
       
        super.onCreate(savedInstanceState);

      NetworkInfoUtil.getTwoMac(getApplicationContext());
    }
    
    
    long startTime;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - startTime < 2 * 1000 && startTime != 0) {
                HttpRequest.synchronizationPass(new HttpRequest.RequestCall() {
                    @Override
                    public void Success(Call call, Response response) {
                        super.Success(call, response);
                        try {
                            String str = response.body().string();
                            JSONObject object = new JSONObject(str);
                            int statue = object.getInt("status");
                            if (statue == 200) {
                                String body = object.getString("body");
                                if (body != null && !body.isEmpty()) {
                                    sharedUtlis.putString(Shared.moreSettingPass, body);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }   }  });

                ViewGroup viewGroup = (ViewGroup) View.inflate(MainActivity.this, R.layout.layout_enter_passeord, null);
                final EditText etEnter = (EditText) viewGroup.findViewById(R.id.et_enter_password);
                Button btEnter = (Button) viewGroup.findViewById(R.id.bt_enter_password);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, AlertDialog.THEME_HOLO_LIGHT);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(true);
                alertDialog.setView(viewGroup);
                alertDialog.show();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (alertDialog != null && alertDialog.isShowing())
                            alertDialog.dismiss();
                    }
                }, 20 * 1000);
                
              
                
                btEnter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = etEnter.getText().toString();
                        if (text != null && !text.isEmpty()) {
                            final String passwordString = sharedUtlis.getString(Shared.moreSettingPass, "");
                            if (text.equals(passwordString)) {

                                sharedUtlis.putBoolean(Shared.isMainStart, false);
                                Intent intent = new Intent(MainActivity.this, GuidActivity.class);
                                intent.putExtra("from", "main");
                                startActivity(intent);
                                finish();
                                alertDialog.dismiss();
                                IntentUtil.sendActionBroadCast(MainActivity.this, Action.ACTION_IS_PASS_ENTER_TAG);
                                sharedUtlis.putBoolean(Shared.allowExcite , true);
                            } else {
                                ToastTools.showShort(MainActivity.this, "密码错误，请重新输入！");
                                etEnter.setText("");
                            }
                        } else {
                            ToastTools.showShort(MainActivity.this, "请输入密码");
                        }
                    }
                });

                etEnter.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        final String passwordString = sharedUtlis.getString(Shared.moreSettingPass, "");
                        if (etEnter.getText().toString().equals(passwordString)) {
                            sharedUtlis.putBoolean(Shared.isMainStart, false);
                            Intent intent = new Intent(MainActivity.this, GuidActivity.class);
                            intent.putExtra("from", "main");
                            startActivity(intent);
                            finish();
                            alertDialog.dismiss();
                            IntentUtil.sendActionBroadCast(MainActivity.this, Action.ACTION_IS_PASS_ENTER_TAG);
                            sharedUtlis.putBoolean(Shared.allowExcite , true);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                return super.onKeyDown(keyCode, event);
            } else {
                ToastTools.showShort(this, "再次点击进入设置！");
            }
            startTime = System.currentTimeMillis();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void ui() {
        
        tvDeviceInfo = findViewById(R.id.tv_device_text);
        signalView = findViewById(R.id.sv_main_signal);
        iv_net_statue = findViewById(R.id.iv_net_statue);

        advControl = new AdvControl(getApplicationContext());
        
        videoFragment = new VideoFragment();
        videoFragment.setAdvControl(advControl);
        FragmentTools.replaceFragment(this , videoFragment, R.id.fl_adv_main ,video_fragment_tag );
        
    }

    @Override
    public void data() {
        sharedUtlis = new SharedUtlis(getApplicationContext(), Shared.config);
        uiReceiver = new UiReceiver();
        netStatueReceiver = new NetStatueReceiver();
        connStatueReceiver = new ServiceConnStatueReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.ACTION_SIGNAL_CHANGE);
        intentFilter.addAction(Action.ACTION_NETSTATUE_CHANGE);
        registerReceiver(uiReceiver, intentFilter);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(netStatueReceiver, intentFilter);
        intentFilter = new IntentFilter();
        
        intentFilter.addAction(Action.ACTION_CHECK_NET_TAG);
        registerReceiver(connStatueReceiver, intentFilter);
        
        tvDeviceInfo.setText("ID:"+ sharedUtlis.getString(Shared.deviceCode,"")+"  Ver:" + AppInfoUtil.getLocalVersionName(getApplication()));
   
    }

    @Override
    protected void destory() {
        unregisterReceiver(uiReceiver);
        unregisterReceiver(netStatueReceiver);
        unregisterReceiver(connStatueReceiver);
        advControl.destory();
    }


    public void updataUiSignal(int statue) {
        LogUtil.e(TAG, " updata Net statue  " + statue);
        switch (statue) {
            // 好
            case 3:
                signalView.setSignalStatue(SignalView.SignalStatue.great);
                break;
            // 中等
            case 2:
                signalView.setSignalStatue(SignalView.SignalStatue.normal);
                break;
            // 坏
            case 1:
                signalView.setSignalStatue(SignalView.SignalStatue.none);
                break;
        }
    }

    public void updataWifi(boolean b) {
        if (b) {
            iv_net_statue.setImageResource(R.mipmap.good_net);
        } else {
            iv_net_statue.setImageResource(R.mipmap.no_net);
            updataUiSignal(1);
        }
    }
    
    public  void player(View view){
        videoFragment.player();
    } 
    
    public  void pause(View view){
        videoFragment.pause();
    }
   public  void stop(View view){
        videoFragment.stop();
    }
   public  void reset(View view){
       
        videoFragment.reset();
    }  
    public  void restart(View view){
        videoFragment.restart();
    }  
    public  void init(View view){
        videoFragment.init();
    }   public  void release(View view){
        videoFragment.release();
    }

    public AdvControl getAdvControl() {
        return advControl;
    }

    class UiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Action.ACTION_SIGNAL_CHANGE:
                    updataUiSignal(intent.getIntExtra(IntentKey.intent_signal_statue, 1));
                    break;
                    
                case Action.ACTION_NETSTATUE_CHANGE:
                    updataWifi(intent.getBooleanExtra(IntentKey.intent_wifi_satue, false));
                    break;


            }
        }
    }
}
