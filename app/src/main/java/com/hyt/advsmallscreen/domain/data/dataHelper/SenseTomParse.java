package com.hyt.advsmallscreen.domain.data.dataHelper;

 
import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;
import com.hyt.advsmallscreen.domain.data.senstom.AdsJson;


import java.util.ArrayList;

 
/**
 * Created by Tao on 2018/6/11 0011.
 */

public class SenseTomParse {

    public static ArrayList<AdvBaseData> data2LocalData(AdsJson ads) {

        if (ads == null || !ads.getStatus().toUpperCase().equals("OK") || ads.getData() == null || ads.getData().getAds().size() <= 0)
            return null;

        ArrayList<AdvBaseData> advSensInfos = new ArrayList<>();

        for (int i = 0; i < ads.getData().getAds().size(); i++) {
            AdsJson.Ads ad = ads.getData().getAds().get(i);
            AdsJson.Materials materials = ad.getMaterials().get(0);
            AdvBaseData AdvBaseData = new AdvBaseData();
            AdvBaseData.setDataType( com.hyt.advsmallscreen.domain.data.advData.AdvBaseData.AdvDataType.sens);
            AdvBaseData.setOnceShowTime((int) (ad.getExposure_time()/1000));
            AdvBaseData.setAdvUrl(materials.getUrl());
            try {
                int advType = trabsType(ad);
                AdvBaseData.setAdvMediaType( com.hyt.advsmallscreen.domain.data.advData.AdvBaseData.AdvMediaType.getType(trabsType(ad)));
                if (advType == 2 ){
                    AdvBaseData.setOnceShowTime(20);
                }else if (advType == 3){
                    AdvBaseData.setOnceShowTime(15);
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            String[] split = materials.getUrl().split("/");
            AdvBaseData.setAdvFileName(split[split.length - 1]);
            AdvBaseData.setViladStartTimeMillie(System.currentTimeMillis());
            AdvBaseData.setViladCount(-1);
            AdvBaseData.setEndCallback(ad.getEnd_probles().get(0));
            AdvBaseData.setStartCallback(ad.getBegin_probles().get(0));
            AdvBaseData.setAdvCategory("sense");
            AdvBaseData.setAdvName("sense");
            AdvBaseData.setViladEndTimeMillie( ad.getExp_time());
            //test
//            AdvBaseData.setViladEndTimeMillie( System.currentTimeMillis() +60*60*1000);
            AdvBaseData.setUseMd5(false);
            AdvBaseData.setMaxPlayCount(1);
            AdvBaseData.setOrderNumber(i);
            advSensInfos.add(AdvBaseData);
            
        }
        
        return advSensInfos;
    }

    private static int trabsType(AdsJson.Ads ad) throws Exception {
 
        AdsJson.Materials materials = ad.getMaterials().get(0);
        String aCaseUrl = materials.getUrl().toUpperCase();
        if (aCaseUrl.endsWith("MP4") || aCaseUrl.endsWith("3GP") || aCaseUrl.endsWith("RMVB") || aCaseUrl.endsWith("AVI"))
            return 2;
        if (aCaseUrl.endsWith("JPG") || aCaseUrl.endsWith("JPEG") || aCaseUrl.endsWith("PNG"))
            return 3;

        throw new Exception(" format Error");

    }
}
