package com.hyt.advsmallscreen.element.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hyt.advsmallscreen.app.AdvApplication;
import com.hyt.advsmallscreen.utils.LogUtil;

/**
 * Created by ${Tao} on 2017-11-1416.
 */

public class SyncPushReceiver extends BroadcastReceiver {

    private String TAG = getClass().getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.e(TAG ," onReceive ");
        if (context != null) {
          pushCount(context);
        }
    }

    private void pushCount(Context context) {
        
//        AdvApplication.advDbHelper.query();
        
    }
}
