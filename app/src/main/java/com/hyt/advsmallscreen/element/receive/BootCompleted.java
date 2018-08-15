package com.hyt.advsmallscreen.element.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hyt.advsmallscreen.element.server.DamonService;
import com.hyt.advsmallscreen.ui.GuidActivity;

/**
 * Created by Administrator on 2017-09-29.
 */

public class BootCompleted extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Intent intent1 = new Intent(context , DamonService.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(intent1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
