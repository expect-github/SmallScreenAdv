package com.hyt.advsmallscreen.domain.adv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.hyt.advsmallscreen.app.AdvApplication;
import com.hyt.advsmallscreen.domain.HttpRequest;
import com.hyt.advsmallscreen.domain.Md5Helper;
import com.hyt.advsmallscreen.domain.data.StorageHelper;
import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;
import com.hyt.advsmallscreen.domain.data.advJson.AdvSensInfo;
import com.hyt.advsmallscreen.domain.db.AdvDbHelper;
import com.hyt.advsmallscreen.element.server.PullAdvService;
import com.hyt.advsmallscreen.global.Action;
import com.hyt.advsmallscreen.global.IntentKey;
import com.hyt.advsmallscreen.utils.LogUtil;
import com.hyt.advsmallscreen.utils.OrderUtil;
import com.hyt.advsmallscreen.video.VideoManager;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by Tao on 2018/8/9 0009.
 */

public class AdvControl {

    Context context;
    private String TAG = getClass().getSimpleName();
    private VideoManager videoManager;
    private final AdvDataReceiver advDataReceiver;
    private final AdvDataPullHelper advDataPullHelper;
    private final AdvDbHelper advDbHelper;
    private final CallHelper callHelper;
 

    public AdvControl(Context context) {
        this.context = context;
        advDataReceiver = new AdvDataReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.ACTION_ALERADY_PREPARE_HYT＿DATA);
        context.registerReceiver(advDataReceiver, intentFilter);
        advDataPullHelper = new AdvDataPullHelper();
        advDbHelper = AdvApplication.advDbHelper;
        callHelper = new CallHelper();
    }

    public boolean requestAdv(AdvBaseData baseData) {
        AdvDataPullHelper.PullAcvData pullAcvData = advDataPullHelper.nextPullAdv(baseData);
        requestHyt(pullAcvData);
        return sechNextAdv(pullAcvData, 0);
    }

    private boolean sechNextAdv(AdvDataPullHelper.PullAcvData pullAcvData, int i) {
        LogUtil.e(TAG, "sechNextAdv " + pullAcvData.toString());
        ArrayList<AdvBaseData> advBaseDataS = conditionControl(AdvApplication.advDbHelper.findadv(pullAcvData));
        if (advBaseDataS.size() > 0) {
            // 更新UI播放本次数据广告
            updataAdv(advBaseDataS);
        } else {
            if (pullAcvData.getDataType() == AdvBaseData.AdvDataType.hyt) {
                pullAcvData.setDataType(AdvBaseData.AdvDataType.local);
                advBaseDataS = AdvApplication.advDbHelper.findadv(pullAcvData);
                updataAdv(advBaseDataS);
                if (advBaseDataS.size() > 0)
                    return true;
                else
                    return false;
            }
            AdvDataPullHelper.PullAcvData acvData = advDataPullHelper.nextPullAdv(pullAcvData);
            return sechNextAdv(acvData, i++);
        }
        return false;
    }

    private void updataAdv(ArrayList<AdvBaseData> advBaseDataS) {
        LogUtil.e(TAG, "updata" + advBaseDataS.toString());
        videoManager.updataDataChange(advBaseDataS);
    }
    
  
    /**
     * 播放数据的条件控制   time  md5
     *
     * @param advBaseDataS
     * @return
     */

    int lastOrder = -1;

    private ArrayList<AdvBaseData> conditionControl(ArrayList<AdvBaseData> advBaseDataS) {

        // md5 检查
        // 时间检查
        for (int i = 0; i < advBaseDataS.size(); i++) {
            AdvBaseData data = advBaseDataS.get(0);
            boolean useMd5 = data.isUseMd5();
            String localPath = data.getLocalPath();
            long viladStartTimeMillie = data.getViladStartTimeMillie();
            long viladEndTimeMillie = data.getViladEndTimeMillie();

            if (viladStartTimeMillie > System.currentTimeMillis() || (viladEndTimeMillie - data.getOnceShowTime() * 1000) < System.currentTimeMillis()) {
                advBaseDataS.remove(i);
                i--;
                continue;
            }
            if (useMd5 && !Md5Helper.matchMd5(data.getMd5(), new File(localPath))) {
                advBaseDataS.remove(i);
                i--;
                continue;
            }
        }

        OrderUtil.OrderList(advBaseDataS);


        if (advBaseDataS.size() > 0) {
            for (int i = 0; i < advBaseDataS.size(); i++) {
                AdvBaseData data = advBaseDataS.get(i);
                if (data.getOrderNumber() > lastOrder) {
                    lastOrder = data.getOrderNumber();
                    advBaseDataS.clear();
                    advBaseDataS.add(data);
                    return advBaseDataS;
                }
            }

            AdvBaseData data = advBaseDataS.get(0);
            lastOrder = data.getOrderNumber();
            advBaseDataS.clear();
            advBaseDataS.add(data);
        }

        return advBaseDataS;
    }

    private void requestHyt(AdvDataPullHelper.PullAcvData pullAcvData) {
        if (pullAcvData == null)
            return;
        LogUtil.e(TAG, "request adv ");
        Intent intent = new Intent(context, PullAdvService.class);
        Bundle bundle = new Bundle();
        bundle.putInt(IntentKey.requestDataType, pullAcvData.dataType.getType());
        bundle.putInt(IntentKey.requestAdvType, pullAcvData.mediaType.getType());
        intent.putExtras(bundle);
        context.startService(intent);
    }

    public void StartCall(AdvBaseData baseData) {
        if (baseData != null)
            LogUtil.e(TAG, baseData.toString());
        
        callHelper.callStartUrl(baseData);
    }


    public boolean endCall(AdvBaseData baseData) {
        if (baseData != null)
            LogUtil.e(TAG, baseData.toString());

                      updataPlayDb(baseData);
        callHelper.callEndUrl(baseData);
        return requestAdv(baseData);
    }

    private void updataPlayDb(AdvBaseData baseData) {
        AdvApplication.advDbHelper.updataPlayDb(baseData);
    }


    public <T extends VideoManager.VideoInfo> void setVideoManager(VideoManager<T> videoManager) {
        this.videoManager = videoManager;
    }

    public void destory() {
        context.unregisterReceiver(advDataReceiver);
    }

    class AdvDataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Action.ACTION_ALERADY_PREPARE_HYT＿DATA:
                    try {
                        ArrayList<AdvBaseData> advBaseDataS = (ArrayList<AdvBaseData>) intent.getExtras().getSerializable(IntentKey.data);
                        advDbHelper.cacheDatas(advBaseDataS);
                     
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
