package com.hyt.advsmallscreen.domain.data.dataHelper;

import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;
import com.hyt.advsmallscreen.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
 

/**
 * Created by ${Tao} on 2017-11-1014.
 * Created by ${Tao} on 2017-11-1014.
 */


public class AdverstingDtataHelper {
    
    static String TAG = "AdverstingDtataHelper";

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
                            advBaseData.setOnceShowTime(2);
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

