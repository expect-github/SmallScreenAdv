package com.hyt.advsmallscreen.element.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hyt.advsmallscreen.domain.HttpRequest;
import com.hyt.advsmallscreen.global.Action;
import com.hyt.advsmallscreen.global.IntentKey;
import com.hyt.advsmallscreen.global.Shared;
import com.hyt.advsmallscreen.global.Url;
import com.hyt.advsmallscreen.utils.LogUtil;
import com.hyt.advsmallscreen.utils.NetworkInfoUtil;
import com.hyt.advsmallscreen.utils.SharedUtlis;
import com.hyt.advsmallscreen.utils.ToastTools;

import java.util.HashMap;

/**
 * Created by Tao on 2018/3/14 0014.
 */

public class NetStatueReceiver extends BroadcastReceiver {
    String TAG = "NetStatueReceiver" ;
    
    public NetStatueReceiver() {}
    @Override
    public void onReceive(Context context, Intent intent) {
        
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        
        if (networkInfo!=null&& networkInfo.isAvailable()){
            // 网络可用
            LogUtil.e(TAG , " 网络可用 "  );
            ToastTools.showShort(context ," Network Change " +networkInfo.getTypeName());
            SharedUtlis.putString(context , Shared.config , Shared.netWorkType , networkInfo.getTypeName());
            sendCheckNet(context);
            
            sendNetUichange(context , true);    
            
            pushMac(context);
        }else {
            sendNetUichange(context , false);
        }
    }

    private void pushMac(Context context) {
        HashMap <String , String> map = new HashMap<>();
        HashMap<String, String> enthenetMac = NetworkInfoUtil.getTwoMac(context);
        
        map.put("machineWirelessMac", enthenetMac.get("ethernet") == null ? "" : enthenetMac.get("wifi"));
        map.put("machineWiredMac", enthenetMac.get("ethernet") == null ? "" : enthenetMac.get("ethernet"));
        map.put("machineCode", new SharedUtlis(context, Shared.config).getString(Shared.deviceCode, ""));
        
        HttpRequest.httpPost(map, Url.pullMac, new HttpRequest.RequestCall() {
            @Override
            public void Success(String msg) {
                super.Success(msg);
                LogUtil.e(TAG , "提交mac地址 " + msg);
            }
        });
        
    }

    private void sendNetUichange(Context context , boolean net) {
        Intent netintent = new Intent();
        netintent.setAction(Action.ACTION_NETSTATUE_CHANGE);
        netintent.putExtra(IntentKey.intent_wifi_satue , net);
        context.sendBroadcast(netintent);
    }

    private void sendCheckNet(Context context) {
        Intent netintent = new Intent();
        netintent.setAction(Action.ACTION_CHECK_NET_TAG);
        context.sendBroadcast(netintent);
    }


}
