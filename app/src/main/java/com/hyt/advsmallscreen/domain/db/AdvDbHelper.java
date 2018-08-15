package com.hyt.advsmallscreen.domain.db;

import android.content.Context;

import com.hyt.advsmallscreen.domain.adv.AdvDataPullHelper;
import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;
import com.hyt.advsmallscreen.global.Path;
import com.hyt.advsmallscreen.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it_tao.ormlib.DB;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public class AdvDbHelper {
    private final DB playerDb;
    Context context;
    String dbName = "adv.db";
    String advCacheTable = "cache";
    String advPlayerTable = "play";

    private final DB cacheDb;
    private String TAG = getClass().getSimpleName();

    public AdvDbHelper(Context context) {
        this.context = context;
        cacheDb = new DB(context, Path.dbPath, dbName, advCacheTable);
        playerDb = new DB(context, Path.dbPath, dbName, advPlayerTable);
    }

    public void cacheDatas(ArrayList<AdvBaseData> advBaseDataS) {
        ArrayList<AdvDataDbBean> dataDbBeans = baseDataS2DbBeanS(advBaseDataS);
        saveAllCache(dataDbBeans);
    }

    private void saveAllCache(ArrayList<AdvDataDbBean> dataDbBeans) {

        for (AdvDataDbBean dataDbBean : dataDbBeans) {
            if (dataDbBean == null)
                continue;
            List<? extends AdvDataDbBean> adv = cacheDb.findAllByWhere(dataDbBean.getClass(), dataDbBean.getMd5() == null && dataDbBean.isUseMd5() ? " md5='" + dataDbBean.getMd5() + "'" : "advUrl='" + dataDbBean.getAdvurl() + "'");
            if (adv.size() > 0) {
                cacheDb.update(dataDbBean, dataDbBean.getMd5() == null && dataDbBean.isUseMd5() ? " md5='" + dataDbBean.getMd5() + "'" : "advUrl='" + dataDbBean.getAdvurl() + "'");
            } else {
                cacheDb.save(dataDbBean);
            }
        }
    }

    private ArrayList<AdvDataDbBean> baseDataS2DbBeanS(ArrayList<AdvBaseData> advBaseDataS) {
        if (advBaseDataS == null)
            return null;

        LogUtil.e(TAG, "" + advBaseDataS.toString());
        ArrayList<AdvDataDbBean> dataDbBeans = new ArrayList<>();
        for (AdvBaseData data : advBaseDataS) {
            AdvDataDbBean dbBean = advdata2advDbData(data);
            if (dbBean != null)
                dataDbBeans.add(dbBean);
        }

        return dataDbBeans;
    }

    private AdvDataDbBean advdata2advDbData(AdvBaseData data) {
        if (data == null)
            return null;
        AdvDataDbBean dataDbBean = new AdvDataDbBean();
        dataDbBean.setAdvId(data.getAdvId());
        dataDbBean.setName(dataDbBean.getName());
        dataDbBean.setAdvFileName(dataDbBean.getAdvFileName());
        dataDbBean.setAdvDataType(data.getDataType().getType());
        dataDbBean.setAdvMediaType(data.getAdvMediaType().getType());
        dataDbBean.setAdvurl(data.getAdvUrl());
        dataDbBean.setLocalPath(data.getLocalPath());
        dataDbBean.setUseMd5(data.isUseMd5());
        dataDbBean.setMd5(data.getMd5());
        dataDbBean.setOnesTime(data.getOnceShowTime());
        dataDbBean.setAdvCategory(data.getAdvCategory());
        dataDbBean.setUpdataTime(data.getNextUpdateTime());
        dataDbBean.setMd5(data.getMd5());
        dataDbBean.setName(data.getAdvName());
        dataDbBean.setAdvurl(data.getAdvUrl());
        dataDbBean.setStartPlayCall(data.getStartCallback());
        dataDbBean.setStartMintorCall(data.getStartMintorCall());
        dataDbBean.setCompletedEndCall(data.getEndCallback());
        dataDbBean.setCompletedMintorCall(data.getCompletedMintorCall());
        dataDbBean.setStartTime(data.getViladStartTimeMillie());
        dataDbBean.setEndTime(data.getViladEndTimeMillie());
        dataDbBean.setEndTimeFormart(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(data.getViladEndTimeMillie()));
        dataDbBean.setOnesTime(data.getOnceShowTime());
        dataDbBean.setPlaystatue(0);
        dataDbBean.setAdvDataType(data.getDataType().getType());
        dataDbBean.setAdvMediaType(data.getAdvMediaType().getType());
        dataDbBean.setLocalPath(data.getLocalPath());
        dataDbBean.setUseCall(data.isUseCall());
        dataDbBean.setUseSync(data.isUseSync());
        dataDbBean.setUseMd5(data.isUseMd5());
        dataDbBean.setPlaycount(data.getViladCount());
        dataDbBean.setMaxplaycount(data.getMaxPlayCount());
        dataDbBean.setAdvFileName(data.getAdvFileName());
        dataDbBean.setOrIndex(data.getOrderNumber());
        dataDbBean.setJpAdtext(data.getJpAdtext());
        dataDbBean.setJpAdlogo(data.getJpAdlogo());

        dataDbBean.setAdvMessage(data.getAdvMessage());

        dataDbBean.setStandby1("");
        dataDbBean.setStandby2("");
        dataDbBean.setStandby3("");
        dataDbBean.setStandby4("");
        dataDbBean.setStandby5("");
        dataDbBean.setStandby6("");
        dataDbBean.setStandby7("");
        dataDbBean.setStandby8("");

        return dataDbBean;
    }

    public ArrayList<AdvBaseData> findadv(AdvDataPullHelper.PullAcvData pullAcvData) {
        List<AdvDataDbBean> dataS = cacheDb.findAllByWhere(AdvDataDbBean.class, "advDataType ='" + pullAcvData.getDataType().getType() + "'and advMediaType='" + pullAcvData.getMediaType().getType() + "'");
        ArrayList<AdvBaseData> baseDataS = new ArrayList<>();
        for (AdvDataDbBean dataDbBean : dataS) {
            if (dataDbBean == null)
                continue;
            AdvBaseData data = dbData2AdvData(dataDbBean);
            baseDataS.add(data);
        }
        return baseDataS;
    }

    private AdvBaseData dbData2AdvData(AdvDataDbBean advDataDbBean) {

        if (advDataDbBean == null)
            return null;

        AdvBaseData data = new AdvBaseData();
        data.setLocalPath(advDataDbBean.getLocalPath());
        data.setDataType(AdvBaseData.AdvDataType.getType(advDataDbBean.getAdvDataType()));
        data.setUseMd5(advDataDbBean.isUseMd5());
        data.setMd5(advDataDbBean.getMd5());
        data.setViladStartTimeMillie(advDataDbBean.getStartTime());
        data.setViladEndTimeMillie(advDataDbBean.getEndTime());
        data.setViladCount(advDataDbBean.getPlaycount());
        data.setAdvId(advDataDbBean.getAdvId());
        data.setAdvName(advDataDbBean.getAdvFileName());
        data.setAdvUrl(advDataDbBean.getAdvurl());
        data.setAdvCategory(advDataDbBean.getAdvCategory());
        data.setAdvMessage(advDataDbBean.getAdvMessage());
        data.setAdvFileName(advDataDbBean.getAdvFileName());
        data.setAdvMediaType(AdvBaseData.AdvMediaType.getType(advDataDbBean.getAdvMediaType()));
        data.setOrderNumber(advDataDbBean.getOrIndex());
        data.setOnceShowTime(advDataDbBean.getOnesTime());
        data.setStartCallback(advDataDbBean.getStartPlayCall());
        data.setEndCallback(advDataDbBean.getCompletedEndCall());
        data.setJpAdtext(advDataDbBean.getJpAdtext());
        data.setJpAdlogo(advDataDbBean.getJpAdlogo());
        data.setUseCall(advDataDbBean.isUseCall());
        data.setUseSync(advDataDbBean.isUseSync());
        data.setStartMintorCall(advDataDbBean.getStartMintorCall());
        data.setCompletedMintorCall(advDataDbBean.getCompletedMintorCall());

        return data;
    }

    public void updataPlayDb(AdvBaseData baseData) {
        if (baseData == null)
            return;
        AdvPlayDbBean playDbBean = baseData2PlayDb(baseData);
        List<AdvPlayDbBean> allByWhere = playerDb.findAllByWhere(AdvPlayDbBean.class, baseData.getMd5() == null && baseData.isUseMd5() ? " md5='" + playDbBean.getMd5() + "'" : "advUrl='" + playDbBean.getAdvurl() + "'");

        if (allByWhere.size() > 0) {
            playerDb.update(playDbBean, baseData.getMd5() == null && baseData.getMd5() == null && baseData.isUseMd5() ? " md5='" + playDbBean.getMd5() + "'" : "advUrl='" + playDbBean.getAdvurl() + "'");
        } else {
            playerDb.save(playDbBean);
        }
    }

    private AdvPlayDbBean baseData2PlayDb(AdvBaseData baseData) {
        if (baseData == null)
            return null;

        List<AdvPlayDbBean> playDbBeans = playerDb.findAllByWhere(AdvPlayDbBean.class, baseData.getMd5() == null && baseData.isUseMd5() ? " md5='" + baseData.getMd5() + "'" : "advUrl='" + baseData.getAdvUrl() + "'");
        AdvPlayDbBean playDbBean = null;
        if (playDbBeans.size() > 0) {
            playDbBean = playDbBeans.get(0);
        }

        AdvPlayDbBean playBean = new AdvPlayDbBean();
        playBean.setFilePath(baseData.getLocalPath());
        playBean.setMd5(playDbBean == null ? baseData.getMd5() : playDbBean.getMd5());
        playBean.setName(baseData.getAdvName());
        playBean.setAdvurl(playDbBean == null ? baseData.getAdvUrl() : playDbBean.getAdvurl());
        playBean.setUseCall(baseData.isUseCall());
        playBean.setStartPlayCall(baseData.getStartCallback());
        playBean.setStartMintorCall(baseData.getStartMintorCall());
        playBean.setCompletedEndCall(baseData.getEndCallback());
        playBean.setCompletedMintorCall(baseData.getCompletedMintorCall());


        playBean.setAdvDataType(baseData.getDataType().getType());
        playBean.setAdvMediaType(baseData.getAdvMediaType().getType());
        playBean.setStartPlayTime(baseData.getViladStartTimeMillie());
        playBean.setEndPlayTime(baseData.getViladEndTimeMillie());

        playBean.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date(System.currentTimeMillis())));

        playBean.setUseSync(baseData.isUseSync());
        playBean.setDataPushStatue(1);
        playBean.setTotalCount(baseData.getViladCount());
        playBean.setThisTimeTotalCount(baseData.getThisCount());
        
        playBean.setEndCallFailure(playDbBean == null ? 0 : playDbBean.getEndCallFailure());
        playBean.setStartCallFailure(playDbBean == null ? 0 : playDbBean.getStartCallFailure());

        playBean.setStartCallSuccess(playDbBean == null ? 0 : playDbBean.getStartCallFailure());
        playBean.setEndCallSuccess(playDbBean == null ? 0 : playDbBean.getStartCallFailure());

        playBean.setThisTimeCount(playDbBean == null ? 1 : playDbBean.getThisTimeCount()+1);
        playBean.setAlreadyCount(playDbBean == null ? 1 : playDbBean.getAlreadyCount()+1);

        return playBean;
    }

    public void updataCallEnd(AdvBaseData baseData, boolean callSuccess) {

        if (baseData == null)
            return;

        List<AdvPlayDbBean> beans = playerDb.findAllByWhere(AdvPlayDbBean.class, baseData.getMd5() == null && baseData.isUseMd5() ? " md5='" + baseData.getMd5() + "'" : "advUrl='" + baseData.getAdvUrl() + "'");
        AdvPlayDbBean dbBean = null;
        if (beans.size() > 0) {
            dbBean = beans.get(0);
        }

        AdvPlayDbBean playBean = new AdvPlayDbBean();

        playBean.setFilePath(dbBean == null ? baseData.getLocalPath() : dbBean.getFilePath());
        playBean.setMd5(dbBean == null ? baseData.getMd5() : dbBean.getMd5());
        playBean.setName(dbBean == null ? baseData.getAdvName() : dbBean.getName());
        playBean.setAdvurl(dbBean == null ? baseData.getAdvUrl() : baseData.getAdvUrl());
        playBean.setUseCall(dbBean == null ? baseData.isUseCall() : baseData.isUseCall());
        playBean.setStartPlayCall(baseData.getStartCallback());
        playBean.setStartMintorCall(baseData.getStartMintorCall());
        playBean.setCompletedEndCall(baseData.getEndCallback());
        playBean.setCompletedMintorCall(baseData.getCompletedMintorCall());


        playBean.setAdvDataType(dbBean == null ? baseData.getDataType().getType() : baseData.getDataType().getType());
        playBean.setAdvMediaType(dbBean == null ? baseData.getAdvMediaType().getType() : baseData.getAdvMediaType().getType());
        playBean.setStartPlayTime(baseData.getViladStartTimeMillie());
        playBean.setEndPlayTime(baseData.getViladEndTimeMillie());

        playBean.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date(System.currentTimeMillis())));

        playBean.setUseSync(baseData.isUseSync());
        playBean.setDataPushStatue(1);

        playBean.setTotalCount(baseData.getViladCount());

        playBean.setThisTimeTotalCount(dbBean == null ? baseData.getThisCount() : dbBean.getThisTimeTotalCount());


        playBean.setThisTimeCount(dbBean == null ? 1 : dbBean.getThisTimeCount());
        playBean.setAlreadyCount(dbBean == null ? 1 : dbBean.getAlreadyCount());
        playBean.setStartCallFailure(dbBean == null ? 0 : dbBean.getStartCallFailure());
        playBean.setStartCallSuccess(dbBean == null ? 0 : dbBean.getStartCallSuccess());

        if (callSuccess) {
            playBean.setEndCallFailure(dbBean == null ? 0 : dbBean.getEndCallFailure());
            playBean.setEndCallSuccess(dbBean == null ? 1 : dbBean.getStartCallSuccess() + 1);
        } else {
            playBean.setEndCallFailure(dbBean == null ? 1 : dbBean.getEndCallFailure() + 1);
            playBean.setEndCallSuccess(dbBean == null ? 0 : dbBean.getStartCallSuccess());
        }
        
        if (dbBean == null) {
            playerDb.save(playBean);
        } else {
            playerDb.update(playBean, baseData.getMd5() == null && baseData.isUseMd5() ? " md5='" + baseData.getMd5() + "'" : "advUrl='" + baseData.getAdvUrl() + "'");
        }
    }

    public void updataCalStart(AdvBaseData baseData, boolean callSuccess) {

        if (baseData == null)
            return;

        List<AdvPlayDbBean> beans = playerDb.findAllByWhere(AdvPlayDbBean.class, baseData.getMd5() == null && baseData.isUseMd5() ? " md5='" + baseData.getMd5() + "'" : "advUrl='" + baseData.getAdvUrl() + "'");
        AdvPlayDbBean dbBean = null;
        if (beans.size() > 0) {
            dbBean = beans.get(0);
        }

        AdvPlayDbBean playBean = new AdvPlayDbBean();

        playBean.setFilePath(dbBean == null ? baseData.getLocalPath() : dbBean.getFilePath());
        playBean.setMd5(dbBean == null ? baseData.getMd5() : dbBean.getMd5());
        playBean.setName(dbBean == null ? baseData.getAdvName() : dbBean.getName());
        playBean.setAdvurl(dbBean == null ? baseData.getAdvUrl() : baseData.getAdvUrl());
        playBean.setUseCall(dbBean == null ? baseData.isUseCall() : baseData.isUseCall());
        playBean.setStartPlayCall(baseData.getStartCallback());
        playBean.setStartMintorCall(baseData.getStartMintorCall());
        playBean.setCompletedEndCall(baseData.getEndCallback());
        playBean.setCompletedMintorCall(baseData.getCompletedMintorCall());


        playBean.setAdvDataType(dbBean == null ? baseData.getDataType().getType() : baseData.getDataType().getType());
        playBean.setAdvMediaType(dbBean == null ? baseData.getAdvMediaType().getType() : baseData.getAdvMediaType().getType());
        playBean.setStartPlayTime(baseData.getViladStartTimeMillie());
        playBean.setEndPlayTime(baseData.getViladEndTimeMillie());

        playBean.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date(System.currentTimeMillis())));

        playBean.setUseSync(baseData.isUseSync());
        playBean.setDataPushStatue(1);

        playBean.setTotalCount(baseData.getViladCount());

        playBean.setThisTimeTotalCount(dbBean == null ? baseData.getThisCount() : dbBean.getThisTimeTotalCount());

        playBean.setEndCallFailure(dbBean == null ? 0 : dbBean.getEndCallFailure());
        playBean.setEndCallSuccess(dbBean == null ? 1 : dbBean.getStartCallSuccess());

        playBean.setThisTimeCount(dbBean == null ? 1 : dbBean.getThisTimeCount());
        playBean.setAlreadyCount(dbBean == null ? 1 : dbBean.getAlreadyCount());
        playBean.setStartCallFailure(dbBean == null ? 0 : dbBean.getStartCallFailure());
        playBean.setStartCallSuccess(dbBean == null ? 0 : dbBean.getStartCallSuccess());

        if (callSuccess) {
            playBean.setStartCallFailure(dbBean == null ? 0 : dbBean.getStartCallFailure());
            playBean.setStartCallSuccess(dbBean == null ? 0 : dbBean.getStartCallSuccess() + 1);
        } else {
            playBean.setStartCallFailure(dbBean == null ? 0 : dbBean.getStartCallFailure() + 1);
            playBean.setStartCallSuccess(dbBean == null ? 0 : dbBean.getStartCallSuccess());
        }

        if (dbBean == null) {
            playerDb.save(playBean);
        } else {
            playerDb.update(playBean, baseData.getMd5() == null && baseData.isUseMd5() ? " md5='" + baseData.getMd5() + "'" : "advUrl='" + baseData.getAdvUrl() + "'");

        }


    }

    public List<AdvDataDbBean> fingallCache() {
     return cacheDb.findAll(AdvDataDbBean.class);

    }
}
