package com.hyt.advsmallscreen.domain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hyt.advsmallscreen.domain.data.StorageHelper;
import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;
import com.hyt.advsmallscreen.global.Action;
import com.hyt.advsmallscreen.global.IntentKey;
import com.hyt.advsmallscreen.global.Path;
import com.hyt.advsmallscreen.utils.FileUtils;
import com.hyt.advsmallscreen.utils.LogUtil;
import com.hyt.advsmallscreen.utils.MD5Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Tao on 2018/8/10 0010.
 */

public class DownloadHelper {

    DownloadCall downloadCall;

    int totalCount = 0;
    int alleadyCpint = 0;
    ArrayList<AdvBaseData> advBaseDatas;
    Context context;
    private String TAG = getClass().getSimpleName();

    public DownloadHelper(Context context, ArrayList<AdvBaseData> advBaseDatas, DownloadCall downloadCall) {
        this.downloadCall = downloadCall;
        this.advBaseDatas = advBaseDatas;
        this.context = context;
    }

    public void downLoadAdv() {
        if (advBaseDatas == null)
            return;
      
        
        totalCount = advBaseDatas.size();
        int size = 0;
        for (int i = 0; i < (size = advBaseDatas.size()); i++) {

            AdvBaseData data = advBaseDatas.get(i);
            if (data == null) {
                advBaseDatas.remove(data);
                checkComplete(++alleadyCpint);
                continue;
            }
            try {
                download(data);
            } catch (Exception e) {
                e.printStackTrace();
                advBaseDatas.remove(data);
                checkComplete(++alleadyCpint);
            }

            if (size > advBaseDatas.size()) {
                i -= size - advBaseDatas.size();
            }

        }
    }

    private void download(AdvBaseData data) throws Exception {

        AdvBaseData.AdvDataType dataType = data.getDataType();

        switch (dataType) {
            case local:
                break;
            case hyt:
                downloadHyt(data);
                break;
            case baidu:
                downloadBaidu(data);
                break;
            case sens:
                downloadSens(data);
                break;
            case yishou:
                downloadYishow(data);
                break;

        }


    }

    private void downloadYishow(AdvBaseData data) {
        switch (data.getAdvMediaType()) {
            case video:

                break;

            case image:

                break;
        }

    }

    private void downloadBaidu(AdvBaseData data) {

        switch (data.getAdvMediaType()) {
            case video:
                requestDownload(data, Path.hytbaiduVideoPath);
                break;
            case image:
                requestDownload(data, Path.hytbaiduImagePath);
                break;
        }
    }

    private void downloadSens(AdvBaseData data) {
        switch (data.getAdvMediaType()) {
            case video:
                requestDownload(data, Path.hytSensVideoPath);
                break;
            case image:
                requestDownload(data, Path.hytSensImagePath);
                break;
        }

    }

    private void downloadHyt(final AdvBaseData data) {

        switch (data.getAdvMediaType()) {
            case video:
                requestDownload(data, Path.hytVideoPath);
                break;
            case image:
                requestDownload(data, Path.hytImagePath);
                break;
        }
    }

    private void requestDownload(final AdvBaseData data, final String path) {
        final String filePath = path + "/" + data.getAdvFileName();
        File file = new File(filePath);
        if (file.exists()) {
            if (data.isUseMd5()) {
                if (Md5Helper.checkMd5(data, filePath)) {
                    if (downloadCall != null)
                        downloadCall.success(data);
                    data.setLocalPath(filePath);
                    advBaseDatas.set(advBaseDatas.indexOf(data), data);
                    checkComplete(++alleadyCpint);
                    return;
                }
            } else {
                if (downloadCall != null)
                    downloadCall.success(data);
                data.setLocalPath(filePath);
                advBaseDatas.set(advBaseDatas.indexOf(data), data);
                checkComplete(++alleadyCpint);
                return;
            }

      
        }

        HttpRequest.requestGet(data.getAdvUrl(), new HttpRequest.RequestCall() {
                    @Override
                    public void Success(InputStream stream) {
                        super.Success(stream);

                        LogUtil.e(TAG, " " + data.getAdvFileName());
                        boolean md5 = false;
                        boolean save = FileUtils.saveFileFromStream(stream, filePath);
                        if (save)
                            md5 = Md5Helper.checkMd5(data, filePath);
                        LogUtil.e(TAG, " md5 : " + md5);

                        if (md5 || data.isUseMd5()) {
                            data.setLocalPath(filePath);
                            advBaseDatas.set(advBaseDatas.indexOf(data), data);
                            if (downloadCall != null)
                                downloadCall.success(data);
                        } else {
                            try {
                                if (md5)
                                data.setMd5(MD5Util.md5FromFile(filePath,false)); 
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (downloadCall != null)
                                downloadCall.failue(data);
                            advBaseDatas.remove(data);
                        }
                        checkComplete(++alleadyCpint);
                    }

                    @Override
                    public void Failure(String msg) {
                        super.Failure(msg);
                        if (downloadCall != null)
                            downloadCall.failue(data);

                        checkComplete(++alleadyCpint);
                        advBaseDatas.remove(data);
                    }
                }
        );
    }

    private void checkComplete(int count) {
//        LogUtil.e(TAG, " count " + count + " totalCount : " + totalCount);
        if (count >= totalCount) {
            try {
                sendComplete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendComplete() {
        Intent intent = new Intent();
        intent.setAction(Action.ACTION_ALERADY_PREPARE_HYT＿DATA);
        Bundle bundle = new Bundle();
        ArrayList<AdvBaseData> advBaseCopy = new ArrayList<>();
        advBaseCopy.addAll(advBaseDatas);
        bundle.putSerializable(IntentKey.data, advBaseDatas);
        intent.putExtras(bundle);
        context.sendBroadcast(intent);

        LogUtil.e(TAG, "下载完成！");
    }

    public interface DownloadCall {
        void success(AdvBaseData data);

        void failue(AdvBaseData data);
    }
}
