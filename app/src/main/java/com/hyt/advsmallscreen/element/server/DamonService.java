package com.hyt.advsmallscreen.element.server;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.hyt.advsmallscreen.domain.data.StorageHelper;
import com.hyt.advsmallscreen.global.Action;
import com.hyt.advsmallscreen.global.Filed;
import com.hyt.advsmallscreen.global.Flag;
import com.hyt.advsmallscreen.global.Gloable;
import com.hyt.advsmallscreen.global.Path;
import com.hyt.advsmallscreen.ui.GuidActivity;

import java.io.File;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public class DamonService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        StorageHelper.deleteLoseEfficacyAsDb();
    }
    private void init() {
        // 检查否配置好应用
        File file = new File(Path.configPath + "/" + Filed.configFile);
        if (file.exists()){
            try {
                Intent intent1 = new Intent(getApplicationContext(), GuidActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        try {
            syncServerStatue(getApplicationContext() , 100,20*60*1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public static  void syncServerStatue(Context context , long current,long repetTime )  throws Exception{
        AlarmManager alarmM = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent();
        intent.setAction(Action.ACTION_CHECK_NET_TAG);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, Flag.flag_Server_statue, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmM.setRepeating(AlarmManager.RTC_WAKEUP ,  current , repetTime,broadcast);
    }
}
