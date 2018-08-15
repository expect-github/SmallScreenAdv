package com.hyt.advsmallscreen.element.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.hyt.advsmallscreen.global.Filed;
import com.hyt.advsmallscreen.global.Shared;
import com.hyt.advsmallscreen.ui.GuidActivity;
import com.hyt.advsmallscreen.utils.AppInfoUtil;
import com.hyt.advsmallscreen.utils.SharedUtlis;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public class ExternalStartService extends Service {

    private SharedUtlis sharedUtlis;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedUtlis = new SharedUtlis(getApplicationContext(), Shared.config);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        check();
        return super.onStartCommand(intent, flags, startId);
    }

    private void check() {
            
        if (sharedUtlis . getBoolean(Shared.allowExcite, true))
            return;
        
        AppInfoUtil.TopActivityInfo topActivityInfo = AppInfoUtil.getTopActivityInfo(getApplicationContext());
        if (topActivityInfo == null || !topActivityInfo.packageName.equals(getApplicationContext().getPackageName()))
        {
            Intent intent = new Intent(getApplicationContext(), GuidActivity.class);
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(intent);

        }

    }
}
