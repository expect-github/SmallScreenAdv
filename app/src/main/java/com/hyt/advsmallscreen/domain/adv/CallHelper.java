package com.hyt.advsmallscreen.domain.adv;

import com.hyt.advsmallscreen.app.AdvApplication;
import com.hyt.advsmallscreen.domain.HttpRequest;
import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;
import com.hyt.advsmallscreen.domain.data.advJson.AdvSensInfo;
import com.hyt.advsmallscreen.domain.db.AdvDbHelper;
import com.hyt.advsmallscreen.utils.LogUtil;

/**
 * Created by Tao on 2018/8/14 0014.
 */

public class CallHelper {
    private   AdvDbHelper advDbHelper;
    private String TAG = getClass().getSimpleName();

    public CallHelper() {
        advDbHelper = AdvApplication.advDbHelper;
    }
    public void callStartUrl(AdvBaseData baseData) {

        if (baseData == null)
            return;

        switch (baseData.getDataType()) {
            case hyt:
                break;
            case baidu:
                break;
            case local:
                break;
            case sens:
                callSensStart(baseData);
                break;
            case yishou:
                break;
        }


    }


    public void callEndUrl(AdvBaseData baseData) {
        if (baseData == null)
            return;
        switch (baseData.getDataType()) {
            case hyt:
                break;
            case baidu:
                break;
            case local:
                break;
            case sens:
                callSensEnd(baseData);
                break;
            case yishou:
                break;
        }

    }

    private void callSensStart(AdvBaseData baseData) {
        HttpRequest.requestGet(baseData.getStartMintorCall(),null);

        HttpRequest.requestGet(prepareSensBeginUrl(baseData), new HttpRequest.RequestCall() {
            @Override
            public void Failure(String msg) {
                super.Failure(msg);
                LogUtil.e(TAG,"callSensStart Failure:" + msg);
            }
            
            @Override
            public void Success(String msg) {
                super.Success(msg);
                LogUtil.e(TAG,"callSensStart Success:" + msg);
            }
        });
    }


    private void callSensEnd(final AdvBaseData baseData) {
        
        HttpRequest.requestGet(baseData.getCompletedMintorCall(),null);
        
        HttpRequest.requestGet(prepareSensEndUrl(baseData), new HttpRequest.RequestCall() {
            @Override
            public void Failure(String msg) {
                super.Failure(msg);
                LogUtil.e(TAG,"Failure Success:" + msg);
                
                advDbHelper.updataCallEnd(baseData, true);
            }

            @Override
            public void Success(String msg) {
                super.Success(msg);
                LogUtil.e(TAG,"callSensEnd Success:" + msg);
                advDbHelper.updataCallEnd(baseData,false);

            }
        });

    }

    private String prepareSensBeginUrl(AdvBaseData baseData) {
        if (baseData == null || !(baseData instanceof AdvSensInfo))
            return null;

        String startCallback = ((AdvSensInfo) baseData).getStartCallback();
        if (startCallback == null || startCallback.isEmpty())
            return null;
//        LogUtil.e(TAG ," startCallback " + startCallback);
        long timestamp = baseData.getTimestamp();
        long beginstamp = baseData.getBeginstamp();
        int duration = baseData.getDuration();
        long endstamp = baseData.getEndstamp();
        String formattime = baseData.getFormattime();
        // 当前时间
        if (startCallback.contains("__timestamp__"))
            startCallback = startCallback.replace("__timestamp__", String.valueOf(timestamp));
        // 开始时间
        if (startCallback.contains("__beginstamp__"))
            startCallback = startCallback.replace("__beginstamp__", String.valueOf(beginstamp));
        // 结束时间
        if (startCallback.contains("__endstamp__"))
            startCallback = startCallback.replace("__endstamp__", String.valueOf(endstamp));
        // 播放时间
        if (startCallback.contains("__duration__"))
            startCallback = startCallback.replace("__duration__", String.valueOf(duration));
        // 格式化时间
        if (startCallback.contains("__formattime__"))
            startCallback = startCallback.replace("__formattime__", String.valueOf(formattime));

        return startCallback;
    }

    private String prepareSensEndUrl(AdvBaseData baseData) {
        if (baseData == null || !(baseData instanceof AdvSensInfo))
            return null;

        String endCall = ((AdvSensInfo) baseData).getEndCallback();
        if (endCall == null || endCall.isEmpty())
            return null;

        long timestamp = baseData.getTimestamp();
        long beginstamp = baseData.getBeginstamp();
        int duration = baseData.getDuration();
        long endstamp = baseData.getEndstamp();
        String formattime = baseData.getFormattime();
        // 当前时间
        if (endCall.contains("__timestamp__"))
            endCall = endCall.replace("__timestamp__", String.valueOf(timestamp));
        // 开始时间
        if (endCall.contains("__beginstamp__"))
            endCall = endCall.replace("__beginstamp__", String.valueOf(beginstamp));
        // 结束时间
        if (endCall.contains("__endstamp__"))
            endCall = endCall.replace("__endstamp__", String.valueOf(endstamp));
        // 播放时间
        if (endCall.contains("__duration__"))
            endCall = endCall.replace("__duration__", String.valueOf(duration));
        // 格式化时间
        if (endCall.contains("__formattime__"))
            endCall = endCall.replace("__formattime__", String.valueOf(formattime));

        return endCall;
    }

}
