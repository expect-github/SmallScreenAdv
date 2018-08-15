package com.hyt.advsmallscreen.element.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hyt.advsmallscreen.element.server.DamonService;
import com.hyt.advsmallscreen.ui.GuidActivity;
import com.hyt.advsmallscreen.utils.LogUtil;

/**
 * Created by Tao on 2018/2/27 0027.
 */

public class InatallStartReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        intent=new Intent(context, DamonService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(intent);
        LogUtil.e("","安装完成立即开启！");
    }
}
