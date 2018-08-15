package com.hyt.advsmallscreen.video;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hyt.advsmallscreen.domain.adv.AdvControl;
import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;
import com.hyt.advsmallscreen.global.Http;
import com.hyt.advsmallscreen.global.Path;
import com.hyt.advsmallscreen.utils.AssetsUtil;
import com.hyt.advsmallscreen.utils.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public class VideoManager<T extends VideoManager.VideoInfo> implements SurfaceHolder.Callback {

    private final VideoPalyer videoPalyer;
    SurfaceHolder surfaceHolder;
    SurfaceView surfaceView;
    String TAG = getClass().getSimpleName();
    ArrayList<VideoInfo> videoInfos = new ArrayList<>();
    Context context;
    String defaultAssets;
    boolean autoNext = true;
    int index = 0;
    Handler handler = new Handler();
    private AudioManager audioManager;
    AdvControl advControl;
    int audioPercent = 15;
    int showTime = 15;
    HashMap<VideoInfo, AdvBaseData> cacheMap = new HashMap<>();
    private PlayStatue.MyRunable myRunable;
    private boolean isPrepareData = false;

    public VideoManager(Context context, SurfaceView surfaceView, String defaultAssets) {
        this.context = context;
        this.defaultAssets = defaultAssets;
        this.surfaceHolder = surfaceView.getHolder();
        this.surfaceView = surfaceView;
        surfaceHolder.addCallback(this);
        videoPalyer = new VideoPalyer(surfaceHolder, new PlayStatue());
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

    }

    public VideoManager(Context context, SurfaceView surfaceView) {
        this.context = context;
        this.surfaceHolder = surfaceView.getHolder();
        this.surfaceView = surfaceView;
        surfaceHolder.addCallback(this);
        videoPalyer = new VideoPalyer(surfaceHolder, new PlayStatue());
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        setVideoValume(audioPercent);

    }

    public VideoManager(Context context, SurfaceView surfaceView, AdvControl advControl) {
        this.context = context;
        this.advControl = advControl;
        this.surfaceHolder = surfaceView.getHolder();
        this.surfaceView = surfaceView;
        surfaceHolder.addCallback(this);
        videoPalyer = new VideoPalyer(surfaceHolder, new PlayStatue());
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        setVideoValume(audioPercent);
        advControl.setVideoManager(this);
    }

    private void setVideoValume(int audioPercent) {
        if (audioPercent > 100)
            audioPercent = 100;
        this.audioPercent = audioPercent;
        int streamMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int voluem = (int) (streamMaxVolume * (this.audioPercent / 100f));
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, voluem, 0);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        LogUtil.e(TAG, " surfaceCreated ");
        isSurfaceDestory = false;
        start();
    }

    private void start() {
        if (videoInfos.size() > 0) {
            try {
                videoPalyer.playVideo(videoInfos.get(index), cacheMap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            prepareLocal(new PrepareCall() {
                @Override
                public void onPrepareCall(File[] list) {
                    for (int i = 0; i < list.length; i++) {
                        File f = list[i];
                        VideoInfo videoInfo = new VideoInfo();
                        videoInfo.setVideoPath(f.getAbsolutePath());
                        videoInfo.setVideoUrl(f.getAbsolutePath());
                        videoInfo.setIndex(i);
                        videoInfo.setTime(10 * 1000);
                        videoInfos.add(videoInfo);
                    
                    }
                    try {
                        videoPalyer.playVideo(videoInfos.get(index), cacheMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    boolean isLocal = false;
    boolean isSurfaceDestory = true;

    private void prepareLocal(final PrepareCall prepareCall) {
        if (isLocal)
            return;
        isLocal = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(Path.videoCachePath);
                File[] list = file.listFiles();
                try {
                    String[] videos = context.getAssets().list("video");
                    boolean conten = true;
                    for (String aN : videos) {
                        boolean have = false;
                        for (File fN : list) {
                            if (fN.getName().equals(aN)) {
                                have = true;
                            } else {
                                fN.delete();
                            }
                        }
                        if (!have) {
                            conten = false;
                            break;
                        }
                    }
                    if (!conten)
                        AssetsUtil.copyAssetsFiles(context, "video", Path.videoCachePath);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                AssetsUtil.copyAssetsFiles(context, "video", Path.defaultvideoPath);
                prepareCall.onPrepareCall(new File(Path.defaultvideoPath).listFiles());
            }
        }).start();


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        LogUtil.e(TAG, " surfaceChanged ");
        holder.setFixedSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        LogUtil.e(TAG, " surfaceDestroyed ");
        isSurfaceDestory = true;
        videoPalyer.release();
        handler.removeCallbacksAndMessages(null);
    }

    public static class VideoInfo {

        String videoPath;
        String videoUrl;
        int index = 0;
        long time = 20 * 1000;

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getVideoPath() {
            return videoPath;
        }

        public void setVideoPath(String videoPath) {
            this.videoPath = videoPath;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        @Override
        public String toString() {
            return "VideoInfo{" +
                    "videoPath='" + videoPath + '\'' +
                    ", videoUrl='" + videoUrl + '\'' +
                    ", index=" + index +
                    '}';
        }
    }

    interface PrepareCall {
        void onPrepareCall(File[] list);
    }

    class PlayStatue implements VideoStatueCall {


        @Override
        public void onError(final VideoInfo videoInfo, final int errorCode) {

            LogUtil.e(TAG, " onError " + videoInfo.toString());
            handler.removeCallbacks(myRunable);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    switch (errorCode) {
                        case 1:
                            // 路径异常
                            try {
                                end(videoInfo, cacheMap);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            // oerror
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            // prepare

                            videoPalyer.init();
                            break;
                    }
                }
            });

        }

        @Override
        public void onInit(int errorCode) {
            LogUtil.e(TAG, " onInit ");
        }

        @Override
        public void onPrepare(VideoInfo videoInfo, int errorCode) {
            LogUtil.e(TAG, " onPrepare ");
        }

        @Override
        public void onPause(VideoInfo videoInfo, int errorCode) {
            LogUtil.e(TAG, " onPause ");
        }

        @Override
        public void onStop(VideoInfo videoInfo, int errorCode) {
            LogUtil.e(TAG, " onStop ");
        }

        @Override
        public void onComplete(VideoInfo videoInfo, HashMap<VideoInfo, AdvBaseData> cacheMap, int errorCode) {
            LogUtil.e(TAG, " onComplete ");
            try {
                end(videoInfo, cacheMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.removeCallbacks(myRunable);
            nextPlayer();
        }

        @Override
        public void onRelease(VideoInfo videoInfo, int errorCode) {
            LogUtil.e(TAG, " onRelease ");
        }

        @Override
        public void onReset(VideoInfo videoInfo, int errorCode) {
            LogUtil.e(TAG, " onReset ");
            if (errorCode == 5) {
                // 异常导致重置
                nextPlayer();
            }
        }


        @Override
        public void onStart(VideoInfo videoInfo, HashMap<VideoInfo, AdvBaseData> cacheMap, int errorCode) {
            LogUtil.e(TAG, " onStart " + videoInfo.toString());
            try {
                start(videoInfo, cacheMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (myRunable != null)
                handler.removeCallbacks(myRunable);
            myRunable = new MyRunable(videoInfo, cacheMap);
            handler.postDelayed(myRunable, videoInfo.getTime());
        }

        @Override
        public void onPrepared(VideoInfo info, int errorCode) {
            LogUtil.e(TAG, " onPrepared ");
        }

        class MyRunable implements Runnable {
            VideoInfo videoInfo;
            HashMap<VideoInfo, AdvBaseData> cacheMap;

            public MyRunable(VideoInfo videoInfo, HashMap<VideoInfo, AdvBaseData> cacheMap) {
                this.videoInfo = videoInfo;
                this.cacheMap = cacheMap;
            }

            @Override
            public void run() {
                try {
                    if (cacheMap.containsKey(videoInfo)) {
                        if (cacheMap.get(videoInfo).getDataType() == AdvBaseData.AdvDataType.sens)
                            cacheMap.get(videoInfo).setFormattime(Http.getformatTimeStr(cacheMap.get(videoInfo).getEndCallback()));

                        cacheMap.get(videoInfo).setTimestamp(System.currentTimeMillis());
                        cacheMap.get(videoInfo).setEndstamp(System.currentTimeMillis());
                        cacheMap.get(videoInfo).setDuration((int) ((System.currentTimeMillis() - cacheMap.get(videoInfo).getBeginstamp()) / 1000));
                    }
                    if (!end(videoInfo, cacheMap)) {
                        nextPlayer();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    nextPlayer();
                }
            }
        }
    }

    private boolean end(VideoInfo videoInfo, HashMap<VideoInfo, AdvBaseData> cacheMap) throws Exception {

        LogUtil.e(TAG , " end "+ "+++++++++++++++++++");
        
        if (cacheMap.containsKey(videoInfo)) {
            return advControl.endCall(cacheMap.get(videoInfo));
        } else {
            return advControl.endCall(null);
        }
    }

    private void start(VideoInfo videoInfo, HashMap<VideoInfo, AdvBaseData> cacheMap) throws Exception {
        if (cacheMap.containsKey(videoInfo)) {
            advControl.StartCall(cacheMap.get(videoInfo));
        } else {
            advControl.StartCall(null);
        }
    }

    private void nextPlayer() {
        if (autoNext && !isSurfaceDestory && !isPrepareData) {
            try {
                videoPalyer.playVideo(nextVideo(), cacheMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updataDataChange(ArrayList<AdvBaseData> baseDataS) {

        if (baseDataS == null || baseDataS.size() < 1)
            return;
        switch (baseDataS.get(0).getDataType()) {
            case local:
                break;
            case hyt:
                hytadv2VideoInfo(baseDataS);
                break;
            case baidu:
                break;
            case sens:
                break;
            case yishou:
                break;
        }
    }

    private void hytadv2VideoInfo(ArrayList<AdvBaseData> baseDataS) {
        if (baseDataS == null || baseDataS.size() < 1)
            return;
        isPrepareData = true;
        handler.removeCallbacks(myRunable);
        videoPalyer.reset();
        videoInfos.clear();
        for (int i = 0; i < baseDataS.size(); i++) {
            AdvBaseData data = baseDataS.get(0);
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setVideoUrl(data.getAdvUrl());
            videoInfo.setVideoPath(data.getLocalPath());
            videoInfo.setTime(data.getOnceShowTime() * 1000);
            videoInfo.setIndex(data.getOrderNumber());
            videoInfos.add(videoInfo);
            cacheMap.put(videoInfo, data);
        }
        isPrepareData = false;
        // updata data 
        index = 0;
        orderVideo();
        nextPlayer();

    }

    private void orderVideo() {
        for (int i = 0; i < videoInfos.size() - 1; i++) {
            VideoInfo videoInfo = videoInfos.get(0);
            for (int j = 1; j < videoInfos.size() - 1 - i; j++) {
                VideoInfo videoInfoJ = videoInfos.get(j);
                if (videoInfo.index > videoInfoJ.index) {
                    videoInfos.set(i, videoInfoJ);
                    videoInfos.set(j, videoInfo);
                }
            }
        }
    }

    private VideoInfo nextVideo() {
        if (videoInfos.size() <= 0)
            return null;
        if (index + 1 > videoInfos.size() - 1) index = 0;
        else
            ++index;

        return videoInfos.get(index);
    }

    public void player() {
        start();
    }

    public void pause() {
        videoPalyer.pause();
    }

    public void stop() {
        videoPalyer.stop();
    }

    public void reset() {
        videoPalyer.reset();
    }

    public void restart() {
        videoPalyer.reStart();
    }

    public void init() {
        videoPalyer.init();
    }

    public void release() {
        videoPalyer.release();
    }
}
