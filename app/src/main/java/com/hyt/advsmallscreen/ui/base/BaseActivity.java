package com.hyt.advsmallscreen.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.hyt.advsmallscreen.global.Shared;
import com.hyt.advsmallscreen.utils.SharedUtlis;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public SharedUtlis sharedUtlis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getActionBar().hide();
        } catch (Exception e) {

        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        sharedUtlis = new SharedUtlis(getApplicationContext(), Shared.config);
        ui();
        data();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destory();
    }

    protected abstract void destory();

    public abstract void ui();

    public abstract void data();

}
