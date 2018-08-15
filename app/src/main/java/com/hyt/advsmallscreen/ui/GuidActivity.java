package com.hyt.advsmallscreen.ui;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
 
import com.hyt.advsmallscreen.R;
import com.hyt.advsmallscreen.domain.accessibility.AccessibilityService;
import com.hyt.advsmallscreen.global.Action;
import com.hyt.advsmallscreen.global.Filed;
import com.hyt.advsmallscreen.global.Gloable;
import com.hyt.advsmallscreen.global.Path;
import com.hyt.advsmallscreen.global.Shared;
import com.hyt.advsmallscreen.ui.base.BaseActivity;
import com.hyt.advsmallscreen.utils.AppInfoUtil;
import com.hyt.advsmallscreen.utils.DialogUtil;
import com.hyt.advsmallscreen.utils.FileUtil;
import com.hyt.advsmallscreen.utils.FileUtils;
import com.hyt.advsmallscreen.utils.IntentUtil;
import com.hyt.advsmallscreen.utils.LogUtil;
import com.hyt.advsmallscreen.utils.OsUtils;
import com.hyt.advsmallscreen.utils.SharedUtlis;
import com.hyt.advsmallscreen.utils.ToastTools;
import com.hyt.advsmallscreen.utils.ViewUtil;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

 


public class GuidActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private ViewPager advertsingPager;
    private ProgressDialog progressDialog;
    private EditText etIP;
    private EditText etPort;
    private EditText etAccount;
    private EditText etPassword;
    private EditText etTableName;
    private RadioGroup rgSelect;
    private Button btConfim;
 
    private CheckBox rbPic;
    private EditText etRegisterCode;
    private boolean isHaveconfig;
    private EditText etFaceAccount;
    private EditText etFacePassword;
    private CheckBox cb_usID_reader;

    private ViewGroup ll_use_;
    private boolean mIsFirst = true;
    private EditText etDeviceID;
    private ViewGroup ll_config_file;
    private String TAG = "GuidActivity";
    private boolean mIsFirstInstall;
    private EditText et__gongan_de_name;
    private EditText et_sql_name;
    private Button btMore;
    private boolean isJump2Main = false;
    private boolean isFromMain = false;
    private Button breakButton;
    private String pass;
    private RadioGroup rgFace;
    private LinearLayout llFace;
    private RadioButton rb_hx;
    private RadioButton rb_smy;
    private RadioButton rb_senstum;
    private LinearLayout llID;
    private RadioGroup rgId;
    private RadioButton cb_usb;
    private RadioButton cb_serial;
    private RadioButton cb_bluetooth;
    private ViewGroup mLl_acount;
    private ViewGroup mLl_pass;

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void ui() {
        initview();
    }

    @Override
    public void data() {
        initdata();
    }

    private void initdata() {

        cb_usb.setOnClickListener(this);
        cb_serial.setOnClickListener(this);
        cb_bluetooth.setOnClickListener(this);
        rb_hx.setOnClickListener(this);
        rb_smy.setOnClickListener(this);
        rb_senstum.setOnClickListener(this);
        rbPic.setOnCheckedChangeListener(this);
        cb_usID_reader.setOnCheckedChangeListener(this);
        
        isJump2Main = sharedUtlis.getBoolean(Shared.isJump2Main, false);
        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        if (from != null && from.equals("main")) {
            isFromMain = true;
        }
        boolean isAutoEnter = sharedUtlis.getBoolean(Shared.autoEnter, false);
        mIsFirst = sharedUtlis.getBoolean(Filed.firstKey, true);
        mIsFirstInstall = sharedUtlis.getBoolean(Filed.firstInstalKey, true);

        rbPic.setChecked(isAutoEnter);
        if (mIsFirst)
            readConfigFile();
        isHaveconfig = sharedUtlis.getBoolean(Gloable.isHaveconfig, false);
        pass = sharedUtlis.getString(Shared.moreSettingPass, "");
        if (pass == null || pass.isEmpty())
            sharedUtlis.putString(Shared.moreSettingPass, "admin");
        
        if (isHaveconfig) {
            etDeviceID.setText(sharedUtlis.getString(Shared.deviceCode, ""));
        }

        
        etIP.setText(sharedUtlis.getString(Shared.ip, etIP.getText().toString()));
        etPort.setText(sharedUtlis.getString(Shared.port, etPort.getText().toString()));
        etAccount.setText(sharedUtlis.getString(Shared.account, etAccount.getText().toString()));
        etPassword.setText(sharedUtlis.getString(Shared.password, etPassword.getText().toString()));
        etRegisterCode.setText(sharedUtlis.getString(Shared.IKEY, etRegisterCode.getText().toString()));
        etFaceAccount.setText(sharedUtlis.getString(Shared.etFaceAccount, etFaceAccount.getText().toString()));
        etFacePassword.setText(sharedUtlis.getString(Shared.etFacePassword, etFacePassword.getText().toString()));
        cb_usID_reader.setChecked(sharedUtlis.getBoolean(Shared.useIDReader, false));
        et__gongan_de_name.setText(sharedUtlis.getString(Shared.GongAnCode_device, et__gongan_de_name.getText().toString()));
        et_sql_name.setText(sharedUtlis.getString(Shared.gongan_SQL_name, et_sql_name.getText().toString()));


        if (this.sharedUtlis.getBoolean(Shared.useIDReader, false)) {
            this.llFace.setVisibility(View.VISIBLE);
            llID.setVisibility(View.VISIBLE);
        }

        cb_usID_reader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        breakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                IntentUtil.sendActionBroadCast(GuidActivity.this, Action.ACTION_IS_PASS_ENTER_TAG);
                sharedUtlis.putBoolean(Shared.allowExcite , true);

            }
        });
        checkFaceKind();
        checkIdKind();
        requestPermission();
    }

    private void checkIdKind() {
        switch (this.sharedUtlis.getInt(Shared.readIDKind, 0)) {
            case 0:
                cb_usb.setChecked(true);
                break;
            case 1:
                cb_serial.setChecked(true);
                break;
            case 2:
                cb_bluetooth.setChecked(true);
                break;
        }
    }

    private void checkFaceKind() {
        switch (this.sharedUtlis.getInt(Shared.useFaceKind, 0)) {
            case 0:
                rb_hx.setChecked(true);
                break;
            case 1:
                rb_smy.setChecked(true);
                break;
            case 2:
                rb_senstum.setChecked(true);
                break;
        }
    }

    private void clearSql() {

        boolean aBoolean = sharedUtlis.getBoolean(Shared.clearSql, false);

        if (aBoolean)
            return;
        File file = new File(Path.dbPath);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files)
                    f.delete();
            } else {
                file.delete();
            }
        }
        file.mkdirs();

        sharedUtlis.getBoolean(Shared.clearSql, true);
    }

    public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt) {
        if (paramArrayOfInt.length <= 0) {
 
            initFirst();
            clearSql();
            Jump2main(new Intent(getApplicationContext(), MainActivity.class));
            return;
        } else if ((paramInt == 100) && (paramArrayOfInt[0] == 0)) {
  
            initFirst();
            if ((!this.mIsFirst) && (!this.isFromMain)) {
                clearSql();
                Jump2main(new Intent(getApplicationContext(), MainActivity.class));
            }
        } else {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            localBuilder.setTitle("提示");
            localBuilder.setMessage("请确认应用运行的必须权限！");
            localBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    requestPermission();
                }
            });
            final AlertDialog localAlertDialog = localBuilder.create();
            localAlertDialog.show();
            localAlertDialog.setCancelable(false);
            runOnUiThread(new Runnable() {
                public void run() {
                    localAlertDialog.dismiss();
                    GuidActivity.this.requestPermission();
                }
            });

        }
    }

    private void initFirst() {
        if (mIsFirst) {
            ToastTools.showLong(this, "第一次启动请记得输入机器编号");
            ll_config_file.setVisibility(View.VISIBLE);
            File file = new File(Path.advPath);
            deleteFolder(file);
        }
        
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23)
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 100);
        else if (!mIsFirst && !isFromMain) {
            Jump2main(new Intent(getApplicationContext(), MainActivity.class));
        } else {
            initFirst();
        }
    }

    private void Jump2main(Intent intent) {
        if (cb_bluetooth.isChecked() && TextUtils.isEmpty(sharedUtlis.getString(Shared.bondName, ""))) {
            ToastTools.showLong(this, "请配对蓝牙读卡器！");
            return;
        }
        isJump2Main = true;
        sharedUtlis.putBoolean(Shared.isJump2Main, isJump2Main);
        startActivity(intent);
        finish();
    }

    private void deleteFolder(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    if (f.exists()) {
                        if (f.isDirectory()) {
                            deleteFolder(f);
                        } else {
                            f.delete();
                            LogUtil.e(TAG, "删除文件：" + f.getName());
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void installExtraAPK() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtil.e(TAG, "installExtraAPK ");
                AssetManager assets = getApplicationContext().getAssets();
                try {
                    String[] apks = assets.list("apk");
                    LogUtil.e(TAG, "installExtraAPK  apks " + String.valueOf(apks == null));

                    if (apks != null) {

                        for (String str : apks) {

                            String newPath = Path.configPath + "/" + str;
                            FileUtils.copyAssiets(assets.open("apk/" + str), newPath);

                            LogUtil.e(TAG, "installExtraAPK path  " + newPath);

                            PackageInfo apkInfo = AppInfoUtil.getApkInfo(newPath, GuidActivity.this);

                            boolean isInstall = false;

                            if (apkInfo != null) {
                                PackageInfo appointPackageInfo = AppInfoUtil.getAppointPackageInfo(GuidActivity.this, apkInfo.packageName);
                                if (appointPackageInfo != null) {
                                    if (apkInfo.versionCode > appointPackageInfo.versionCode) {
                                        isInstall = true;
                                    }
                                } else {
                                    isInstall = true;
                                }
                            } else {
                                isInstall = true;
                            }
                            if (isInstall)
                                OsUtils.installAPK(getApplicationContext(), newPath);

                            boolean settingsOn = AppInfoUtil.isAccessibilitySettingsOn(GuidActivity.this, getPackageName() + "/" + AccessibilityService.class.getCanonicalName());
                            if (!settingsOn)
                                LogUtil.e(TAG, " 广告机未设置 " + getPackageName() + "/" + AccessibilityService.class.getCanonicalName());

                            boolean settingsOn1 = AppInfoUtil.isAccessibilitySettingsOn(GuidActivity.this, "com.huanyang.autoupdata" + "/" + "com.huanyang.autoupdata.AccessibilityService");

                            if (!settingsOn1)
                                LogUtil.e(TAG, " 工具未设置 " + "com.huanyang.autoupdata" + "/" + "com.huanyang.autoupdata.Accessibility.AccessibilityService");

                            if (!settingsOn) {
                                AppInfoUtil.jumpAccessibility(GuidActivity.this);
                                IntentUtil.sendActionBroadCast(GuidActivity.this, Action.ACTION_IS_SETTING_BREAK_TAG);
                                sharedUtlis.putBoolean(Shared.allowExcite , true);
                            }
                            
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    sharedUtlis.putBoolean(Filed.firstInstalKey, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initview() {
        ViewUtil viewUtil = new ViewUtil(this);
        etIP = (EditText) viewUtil.viewFromId(R.id.et_ip);
        etPort = (EditText) viewUtil.viewFromId(R.id.et_port);
        etAccount = (EditText) viewUtil.viewFromId(R.id.et_account);
        etPassword = (EditText) viewUtil.viewFromId(R.id.et_password);
        etFaceAccount = (EditText) viewUtil.viewFromId(R.id.et_face_account);
        etFacePassword = (EditText) viewUtil.viewFromId(R.id.et_FasePassword);
        etDeviceID = (EditText) viewUtil.viewFromId(R.id.et_device_id);
        rgSelect = (RadioGroup) viewUtil.viewFromId(R.id.rg_kind_select);
        etRegisterCode = (EditText) viewUtil.viewFromId(R.id.et_register_number);
        et__gongan_de_name = (EditText) viewUtil.viewFromId(R.id.et__gongan_de_name);
        et_sql_name = (EditText) viewUtil.viewFromId(R.id.et_sql_name);
        btConfim = (Button) viewUtil.viewFromId(R.id.bt_confirm);
        btMore = (Button) viewUtil.viewFromId(R.id.bt_config_more);
        breakButton = (Button) viewUtil.viewFromId(R.id.bt_break);
        rbPic = (CheckBox) viewUtil.viewFromId(R.id.cb_pic);
        cb_usID_reader = (CheckBox) viewUtil.viewFromId(R.id.cb_usID_reader);
        mLl_acount = (ViewGroup) viewUtil.viewFromId(R.id.ll_face_acount);
        mLl_pass = (ViewGroup) viewUtil.viewFromId(R.id.ll_face_pass);
        ll_use_ = (ViewGroup) viewUtil.viewFromId(R.id.ll_use_);
        ll_config_file = (ViewGroup) viewUtil.viewFromId(R.id.ll_config_file);
      

        rgFace = ((RadioGroup) viewUtil.viewFromId(R.id.rg_face_select));
        llFace = ((LinearLayout) viewUtil.viewFromId(R.id.ll_face_slect));

        rb_hx = ((RadioButton) viewUtil.viewFromId(R.id.rb_hx));
        rb_smy = ((RadioButton) viewUtil.viewFromId(R.id.rb_smy));
        rb_senstum = ((RadioButton) viewUtil.viewFromId(R.id.rb_senstum));
      
 

        rgId = ((RadioGroup) viewUtil.viewFromId(R.id.rg_id_select));
        llID = ((LinearLayout) viewUtil.viewFromId(R.id.ll_id_read));

        cb_usb = ((RadioButton) viewUtil.viewFromId(R.id.cb_usb));
        cb_serial = ((RadioButton) viewUtil.viewFromId(R.id.cb_serial));
        cb_bluetooth = ((RadioButton) viewUtil.viewFromId(R.id.cb_bluetooth));

 

        btMore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ViewGroup viewGroup = (ViewGroup) View.inflate(GuidActivity.this, R.layout.layout_enter_passeord, null);
                final EditText etEnter = (EditText) viewGroup.findViewById(R.id.et_enter_password);
                Button btEnter = (Button) viewGroup.findViewById(R.id.bt_enter_password);

                AlertDialog.Builder builder = new AlertDialog.Builder(GuidActivity.this, AlertDialog.THEME_HOLO_LIGHT);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(true);
                alertDialog.setView(viewGroup);
                if (mIsFirst)
                    alertDialog.show();
                else
                    startActivity(new Intent(GuidActivity.this, MoreSettingActivity.class));

                btEnter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = etEnter.getText().toString();
                        if (text != null && !text.isEmpty()) {
                            String string = sharedUtlis.getString(Shared.moreSettingPass, "");
                            if (text.equals(string)) {
                                startActivity(new Intent(GuidActivity.this, MoreSettingActivity.class));
                                alertDialog.dismiss();
                            } else {
                                ToastTools.showShort(GuidActivity.this, "密码错误，请重新输入！");
                            }
                        } else {
                            ToastTools.showShort(GuidActivity.this, "请输入密码");
                        }
                    }
                });
            }
        });

        rgSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            }
        });

        btConfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取数据并存储到prefrance
                String string = etDeviceID.getText().toString();
                if (!AppInfoUtil.isAccessibilitySettingsOn(getApplicationContext(), getPackageName()+"/"+AccessibilityService.class.getCanonicalName()))
                {
                    ToastTools.showLong(getApplicationContext() , "请打开辅助功能！");
                    AppInfoUtil.jumpAccessibility(GuidActivity.this);
                    sharedUtlis.putBoolean(Shared.allowExcite, true);
                }
                else if (string != null && !string.equals("")) {
                    sharedUtlis.putString(Shared.deviceCode, string);

                    FileUtils.saveStringToFile(Path.configPath + "/" + Filed.configFile, "{\n" +
                            " \n" +
                            "\n" +
                            "\"deviceidAnnotation\":\"机器编号\", \"deviceid\":\"" + (string) + "\",\n" +
                            " \n" +
                            "\n" +
                            "\"phoneNumberAnnotation\":\"设备厂商缩写\", \"firm\":\"HYT\"\n" +
                            "\n" +
                            "}\n");
                    pushData();
                    Jump2main(new Intent(GuidActivity.this, MainActivity.class));
                    
                } else if (!isHaveconfig && mIsFirst) {
                    DialogUtil.showProcessDialog(GuidActivity.this, "请输入设备ID或者检查配置文件重试！", progressDialog, new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {   }  });
                } else {
                    pushData();
                    Jump2main(new Intent(GuidActivity.this, MainActivity.class));

                }
            }
        });
    }

    private void pushData() {
        String ip = etIP.getText().toString().trim();
        String port = etPort.getText().toString().trim();
        String account = etAccount.getText().toString().trim();
        String passeord = etPassword.getText().toString().trim();
        String faceAccount = etFaceAccount.getText().toString().trim();
        String facePassword = etFacePassword.getText().toString().trim();
        String gongan_deviceid = et__gongan_de_name.getText().toString().trim();
        String registerGode = etRegisterCode.getText().toString().trim();
        String sqlName = et_sql_name.getText().toString().trim();

        sharedUtlis.putString(Shared.ip, ip);
        sharedUtlis.putString(Shared.port, port);
        sharedUtlis.putString(Shared.account, account);
        sharedUtlis.putString(Shared.password, passeord);

        sharedUtlis.putString(Shared.IKEY, registerGode);
        sharedUtlis.putString(Shared.GongAnCode_device, gongan_deviceid);
        sharedUtlis.putString(Shared.gongan_SQL_name, sqlName);

        if (cb_usID_reader.isChecked()) {
            sharedUtlis.putString(Shared.etFaceAccount, faceAccount);
            sharedUtlis.putString(Shared.etFacePassword, facePassword);
        }
        sharedUtlis.putBoolean(Shared.useIDReader, cb_usID_reader.isChecked());
        sharedUtlis.putBoolean(Filed.firstKey, false);
        sharedUtlis.putBoolean(Shared.allowExcite , true);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DialogUtil.hideProgress(progressDialog);
        if (!isJump2Main)
            android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void destory() {
        
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_pic:
                sharedUtlis.putBoolean(Shared.autoEnter, isChecked);
                break;
            case R.id.cb_usID_reader:
                if (cb_usID_reader.isChecked()) {
                    ll_use_.setVisibility(View.VISIBLE);
                    llFace.setVisibility(View.VISIBLE);
                    llID.setVisibility(View.VISIBLE);
                } else {
                    llID.setVisibility(View.GONE);
                    ll_use_.setVisibility(View.GONE);
                    llFace.setVisibility(View.GONE);
                }
                break;
        }
    }

    private void readConfigFile() {
        File configFile = new File(Path.configPath + "/" + Filed.configFile);
        if (!configFile.exists()) {
            // 配置文件为空 提醒进行文件配置 不进入主页
            if (mIsFirst) {
                sharedUtlis.putBoolean(Gloable.isHaveconfig, false);
            }

        } else {
            // 读取配置文件
            try {
                if (mIsFirst || sharedUtlis.getString(Shared.deviceCode, "").isEmpty()) {
                    FileInputStream inputStream = new FileInputStream(configFile);
                    BufferedReader reader = new BufferedReader(  new InputStreamReader(inputStream));
                    StringBuilder builder = new StringBuilder();
                    String readLine = null;
                    while ((readLine = reader.readLine()) != null) {
                        builder.append(readLine);
                    }
                    String configData = builder.toString();
                    JSONObject object = new JSONObject(configData);
                    String deviceid = (String) object.get("deviceid");
                    String firm = (String) object.get("firm");

                    LogUtil.e(TAG, " deviceid：" + deviceid);
                    sharedUtlis.putString(Shared.deviceCode, deviceid);
                    sharedUtlis.putString(Filed.firm_key, firm);
                    inputStream.close();
                }

                sharedUtlis.putBoolean(Gloable.isHaveconfig, true);

            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e(TAG, " 异常：" + e.toString());
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && !mIsFirst) {
            ToastTools.showLong(this, "请点击按钮退出！");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rb_hx:
                this.sharedUtlis.putInt(Shared.useFaceKind, 0);
                break;
            case R.id.rb_smy:
                this.sharedUtlis.putInt(Shared.useFaceKind, 1);
                break;
            case R.id.rb_senstum:
                this.sharedUtlis.putInt(Shared.useFaceKind, 2);
                break;

            case R.id.cb_bluetooth:
                this.sharedUtlis.putInt(Shared.readIDKind, 2);

                break;
            case R.id.cb_serial:
                this.sharedUtlis.putInt(Shared.readIDKind, 1);
                break;
            case R.id.cb_usb:
                this.sharedUtlis.putInt(Shared.readIDKind, 0);
                break;
        }

    }

  
}
