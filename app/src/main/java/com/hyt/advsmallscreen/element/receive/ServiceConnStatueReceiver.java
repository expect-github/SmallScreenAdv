package com.hyt.advsmallscreen.element.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.hyt.advsmallscreen.element.server.DamonService;
import com.hyt.advsmallscreen.global.Action;
import com.hyt.advsmallscreen.global.IntentKey;
import com.hyt.advsmallscreen.global.Shared;
import com.hyt.advsmallscreen.global.Url;
import com.hyt.advsmallscreen.utils.AppInfoUtil;
import com.hyt.advsmallscreen.utils.LogUtil;
import com.hyt.advsmallscreen.utils.SharedUtlis;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ${Tao} on 2017-11-1416.
 */

public class ServiceConnStatueReceiver extends BroadcastReceiver {
    
    String TAG = getClass().getSimpleName();
    public static int last_statue = 1;
    
    public void onReceive(final Context context, Intent intent) {
        final SharedUtlis sharedUtlis = new SharedUtlis(context, Shared.config);
        OkHttpClient okHttpClient = new OkHttpClient();

        MultipartBody.Builder encoding = new MultipartBody.Builder();
        encoding.addFormDataPart("machineCode", sharedUtlis.getString(Shared.deviceCode, ""));
        encoding.addFormDataPart("machineSoftName", AppInfoUtil.getLocalVersionName(context.getApplicationContext()));
        encoding.addFormDataPart("machineSoftCode", String.valueOf(AppInfoUtil.getLocalVersion(context.getApplicationContext())));


        okHttpClient.newCall(new Request.Builder().post(encoding.build()).url(Url.advStateConnUrl).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                badStatue(context);
                LogUtil.e(TAG, "onFailure " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    
                    String resStr = response.body().string();
                    LogUtil.e(TAG,  " 请求状态 ： "+ resStr);
                    JSONObject jsonObject = new JSONObject(resStr);
                    int statue = jsonObject.getInt("body");
                 
                    if (statue == 0) {
                        if (last_statue < 3)
                            last_statue++;
                        else
                            last_statue = 3;
 
                        updtatUIStatue(context, last_statue);
                    } else {
                        badStatue(context);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    badStatue(context);
                }
            }
        });

//        pullCommende(context);
    }

    private void pullCommende(final Context context) {

        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBody.Builder encoding = new MultipartBody.Builder();
        encoding.addFormDataPart("status", "1");
        encoding.addFormDataPart("json", "");

        okHttpClient.newCall(new Request.Builder().post(encoding.build()).url(Url.pullCommend).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e(TAG, "" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String string = response.body().string();
                    LogUtil.e(TAG, "res:" + string);
//                    CommanderHelper.prepareComender(context, string);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private void badStatue(Context context) {
        if (last_statue > 2)
            last_statue--;
        else
            last_statue = 1;
        updtatUIStatue(context, last_statue);
    }

    public void updtatUIStatue(Context context, int statue) {
        
        if (statue ==1){
            try {
                DamonService.syncServerStatue(context , 5*60*1000,5*60*1000);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else {
            try {
                DamonService.syncServerStatue(context , 20*60*1000,20*60*1000);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        
    
        Intent intent = new Intent();
        intent.putExtra(IntentKey.intent_signal_statue, statue);
        intent.setAction(Action.ACTION_SIGNAL_CHANGE);
        context.sendBroadcast(intent);

    }
}
