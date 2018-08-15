package com.hyt.advsmallscreen.element.server;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyt.advsmallscreen.domain.DownloadHelper;
import com.hyt.advsmallscreen.domain.HttpRequest;
import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;
import com.hyt.advsmallscreen.domain.data.advJson.AdvSensInfo;
import com.hyt.advsmallscreen.domain.data.dataHelper.SenseTomParse;
import com.hyt.advsmallscreen.domain.data.pushSensData;
import com.hyt.advsmallscreen.domain.data.senstom.AdsJson;
import com.hyt.advsmallscreen.global.IntentKey;
import com.hyt.advsmallscreen.global.Path;
import com.hyt.advsmallscreen.global.Shared;
import com.hyt.advsmallscreen.global.Url;
import com.hyt.advsmallscreen.utils.AssetsUtil;
import com.hyt.advsmallscreen.utils.FileUtils;
import com.hyt.advsmallscreen.utils.LogUtil;
import com.hyt.advsmallscreen.utils.MD5Util;
import com.hyt.advsmallscreen.utils.NetworkInfoUtil;
import com.hyt.advsmallscreen.utils.SharedUtlis;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ${Tao} on 2017-11-1008.
 */

public class PullAdvService extends Service {
    private String TAG = getClass().getSimpleName();
    private SharedUtlis mSharedUtlis;
    private long gapTime = 1*60*1000;
    long hytTime = 0;
    private pushSensData sensData;
    boolean useGap =true ;

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedUtlis = new SharedUtlis(getApplicationContext(), Shared.config);
        sensData = new pushSensData();
        sensData.setType("2");
        sensData.setDeviceCode(mSharedUtlis.getString(Shared.deviceCode ,""));
        sensData.setAndroidId(MD5Util.creatRequest());
        sensData.setMac(NetworkInfoUtil.getNetworkMac(getApplicationContext()));
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        request(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void request(Intent intent) {
        if (intent == null)
            return;
        Bundle extras = intent.getExtras();
        if (extras == null)
            return;

        int adv = extras.getInt(IntentKey.requestAdvType, 0);
        int data = extras.getInt(IntentKey.requestDataType, 0);

        requestLocal(AdvBaseData.AdvMediaType.getType(adv));
        requestHyt(AdvBaseData.AdvMediaType.getType(adv));
        requestSens(AdvBaseData.AdvMediaType.getType(adv));
        requestBaidu(AdvBaseData.AdvMediaType.getType(adv));
        requestYishou(AdvBaseData.AdvMediaType.getType(adv));
      
    }

    private void requestYishou(AdvBaseData.AdvMediaType type) {
//        requestHyt(type);
    }

    private void requestSens(AdvBaseData.AdvMediaType type) {

        HashMap<String, String> parmeter = new HashMap<>();
        parmeter.put("type", "" + sensData.getType());
        parmeter.put("deviceCode",sensData.getDeviceCode());
        parmeter.put("networkType",sensData.getNetworkType());
        parmeter.put("androidId", sensData.getAndroidId());
        parmeter.put("mac", sensData.getMac());

        HttpRequest.httpPost(parmeter, Url.sensPullPath, new HttpRequest.RequestCall() {
            @Override
            public void Success(String msg) {
                super.Success(msg);
                LogUtil.e(TAG , "senseTon data:" + msg);

                try {
                    Gson gson = new GsonBuilder().create();
                    AdsJson adsJson = gson.fromJson(msg, AdsJson.class);
                    if (adsJson.getStatus().toUpperCase().equals("OK")) {
                        // 解析数据
                        ArrayList<AdvBaseData> advSensInfos = SenseTomParse.data2LocalData(adsJson);
                        LogUtil.e(TAG, " 解析正常 ：" + adsJson.toString());
                        new DownloadHelper(getApplicationContext(),advSensInfos , new DownloadHelper.DownloadCall() {
                            @Override
                            public void success(AdvBaseData data) {
                                LogUtil.e(TAG, "下载成功 ：" + data.getAdvFileName());
                            }
                            
                            @Override
                            public void failue(AdvBaseData data) {
                                LogUtil.e(TAG, "下载失败 ：" + data.toString());
                            }
                        }).downLoadAdv();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.e(TAG, " 解析异常 ：" + e.toString());
                }
            }
        });
        
       
    }

    private void requestBaidu(AdvBaseData.AdvMediaType type) {
//        requestHyt(type);
    }

    private void requestLocal(AdvBaseData.AdvMediaType type) {
//        requestHyt(type);
    }

    private void requestHyt(AdvBaseData.AdvMediaType type) {
        
        LogUtil.e(TAG ,"请求环阳通广告1 ——————————————————————————————————————————————");
        if (System.currentTimeMillis() -hytTime < gapTime &&useGap)
            return;
        LogUtil.e(TAG ,"请求环阳通广告2 ——————————————————————————————————————————————");

        HashMap<String, String> parmeter = new HashMap<>();
        parmeter.put("machineCode", mSharedUtlis.getString(Shared.deviceCode, ""));
        HttpRequest.httpPost(parmeter, Url.advHytUrl, new HttpRequest.RequestCall() {
            @Override
            public void Success(String msg) {
                super.Success(msg);
                AssetsUtil.copyAssetsFiles(getApplicationContext(), "testdata", Path.dataCachePath);
                msg = FileUtils.readFileStr(Path.dataCachePath + "/hytAdv.txt");

                ArrayList<AdvBaseData> advBaseDatas = parseAdverstionJson(msg);
                
                if (advBaseDatas !=null) {
                    hytTime = System.currentTimeMillis();
                    useGap = true ;
                }
                new DownloadHelper(getApplicationContext(), advBaseDatas, new DownloadHelper.DownloadCall() {
                    @Override
                    public void success(AdvBaseData data) {
                        LogUtil.e(TAG, "下载成功 ：" + data.getAdvFileName());
                        
                    }

                    @Override
                    public void failue(AdvBaseData data) {
                        LogUtil.e(TAG, "下载失败 ：" + data.toString());
                        useGap =false ;
                    }
                }).downLoadAdv();
            }

            @Override
            public void Failure(String msg) {
                super.Failure(msg);
                useGap = false ;
                // test
//                
//                AssetsUtil.copyAssetsFiles(getApplicationContext(), "testdata", Path.dataCachePath);
//                msg = FileUtils.readFileStr(Path.dataCachePath + "/hytAdv.txt");
////                LogUtil.e(TAG, " msg: " + msg);
//                new DownloadHelper(getApplicationContext(), parseAdverstionJson(msg), new DownloadHelper.DownloadCall() {
//                    @Override
//                    public void success(AdvBaseData data) {
//                        LogUtil.e(TAG, "下载成功 ：" + data.getAdvFileName());
//                    }
//
//                    @Override
//                    public void failue(AdvBaseData data) {
//                        LogUtil.e(TAG, "下载失败 ：" + data.getAdvFileName());
//                    }
//                }).downLoadAdv();
            }
        });
    }

 

    public static ArrayList<AdvBaseData> parseAdverstionJson(String jsonStr) {
        ArrayList<AdvBaseData> infoList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            int status = jsonObject.getInt("status");
            String message = jsonObject.getString("message");
            if (status == 200) {
                JSONArray bodyArray = jsonObject.getJSONArray("body");
                if (bodyArray != null) {
                    for (int i = 0; i < bodyArray.length(); i++) {
                        JSONObject advObject = (JSONObject) bodyArray.get(i);
                        if (advObject != null) {
                            AdvBaseData advBaseData = new AdvBaseData();
                            int advId = advObject.getInt("advId");
                            String advName = advObject.getString("advName");
                            String advUrl = advObject.getString("advUrl");
                            int advDimension = advObject.getInt("advDimension");
                            String advCategory = advObject.getString("advCategory");
                            String advMessage = advObject.getString("advMessage");
//                            String advDayStime = advObject.getString("advDayStime");
//                            String advDayFtime = advObject.getString("advDayFtime");
//                            String advStime = advObject.getString("advStime");
//                            String advFtime = advObject.getString("advFtime");
                            String advRemark = advObject.getString("advRemark");
                            int advAudit = advObject.getInt("advAudit");
                            String advUser = advObject.getString("advUser");
                            String advFileName = advObject.getString("advFileName");
                            int onceShowTime = advObject.getInt("onceShowTime");
                            int orderNumber = advObject.getInt("orderNumber");
                            int advType = advObject.getInt("advType");
//                            String advText = advObject.getString("advText");
                            int orderByte = advObject.getInt("orderByte");
                            long startPlayTime = advObject.getLong("startPlayTime");
                            long stopPlayTime = advObject.getLong("stopPlayTime");
                            long runCount = advObject.getLong("runCount");

                            advBaseData.setDataType(AdvBaseData.AdvDataType.hyt);
                            advBaseData.setAdvId(advId);
                            advBaseData.setAdvName(advName);
                            advBaseData.setMd5(advFileName.split("\\.")[0]);
                            advBaseData.setAdvUrl(advUrl);
                            advBaseData.setAdvDimension(advDimension);
                            advBaseData.setAdvCategory(advCategory);
                            advBaseData.setAdvMessage(advMessage);
//                            advBaseData.setAdvDayStime(advDayStime);
//                            advBaseData.setAdvDayFtime(advDayFtime);
//                            advBaseData.setAdvStime(advStime);
//                            advBaseData.setAdvFtime(advFtime);
                            advBaseData.setAdvRemark(advRemark);
                            advBaseData.setAdvAudit(advAudit);
                            advBaseData.setAdvUser(advUser);
                            advBaseData.setAdvFileName(advFileName);
                            advBaseData.setOnceShowTime(onceShowTime);
                            
                            // test
                            advBaseData.setOnceShowTime(5);
                            
                            advBaseData.setOrderNumber(orderNumber);
                            advBaseData.setAdvMediaType(AdvBaseData.AdvMediaType.getType(advType));
//                            advBaseData.setAdvText(advText);
                            advBaseData.setOrderByte(orderByte);
                            advBaseData.setViladStartTimeMillie(startPlayTime);
                            advBaseData.setViladEndTimeMillie(stopPlayTime);
                            advBaseData.setViladCount(runCount);
                            advBaseData.setMaxPlayCount(Long.MAX_VALUE);
                            
                            advBaseData.setUseCall(true);
                            advBaseData.setUseMd5(true);
                            advBaseData.setUseSync(true);

                            if (stopPlayTime < System.currentTimeMillis()) {
                                continue;
                            }
                            infoList.add(advBaseData);
                        }
                    }
                    LogUtil.e("TAG", " 解析数据 \n " + infoList.toString());
                }
            } else {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }



}
