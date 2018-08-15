package com.hyt.advsmallscreen.video;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.SurfaceHolder;

import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;
import com.hyt.advsmallscreen.domain.data.advJson.AdvSensInfo;
import com.hyt.advsmallscreen.global.Http;

import java.util.HashMap;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public class VideoPalyer implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnErrorListener {

    public MediaPlayer mediaPlayer;
    SurfaceHolder surfaceHolder;
    VideoStatueCall videoPlayCall;
    MediaStatue mediaStatue = MediaStatue.idle;
    VideoManager.VideoInfo info = new VideoManager.VideoInfo();
    int errorCode = 0;
    int videoStrem = AudioManager.STREAM_MUSIC;

    HashMap<VideoManager.VideoInfo, AdvBaseData> cacheMap = new HashMap<>();


    public VideoPalyer(SurfaceHolder surfaceHolder, VideoStatueCall videoPlayCall) {
        this.surfaceHolder = surfaceHolder;
        this.videoPlayCall = videoPlayCall;
        init();
    }

    public void init() {

        if (mediaPlayer != null && mediaStatue.value() < MediaStatue.end.value()) {
            mediaPlayer.release();
        }
        mediaPlayer = new MediaPlayer();
        mediaStatue = MediaStatue.idle;
        videoPlayCall.onInit(errorCode);


    }

    public void release() {
        if (mediaStatue.value() < MediaStatue.end.value()) {
            mediaPlayer.release();
            mediaStatue = MediaStatue.end;
            videoPlayCall.onRelease(info, errorCode);
            mediaPlayer = null;
        }
    }

    public void reset() {
        if (mediaStatue.value() > MediaStatue.initializ.value() && mediaStatue.value() < MediaStatue.end.value()) {
            mediaPlayer.reset();
            mediaStatue = MediaStatue.idle;
            videoPlayCall.onReset(info, errorCode);
        }
    }

    public void stop() {
        if ((mediaStatue.value < MediaStatue.prepered.value() || mediaStatue.value() > MediaStatue.started.value())
                && mediaStatue.value() != MediaStatue.started.value())
            return;

        mediaPlayer.stop();
        mediaStatue = MediaStatue.stopped;
        videoPlayCall.onStop(info, errorCode);
    }

    public void start() {
        if (mediaStatue.value() != MediaStatue.prepered.value() && mediaStatue.value() != MediaStatue.pause.value())
            return;

        mediaPlayer.start();
        mediaStatue = MediaStatue.started;

        if (cacheMap.containsKey(info)) {
            if (cacheMap.get(info).getDataType() == AdvBaseData.AdvDataType.sens)
                cacheMap.get(info).setFormattime(Http.getformatTimeStr(cacheMap.get(info).getStartCallback()));

            cacheMap.get(info).setBeginstamp(System.currentTimeMillis());
            cacheMap.get(info).setTimestamp(System.currentTimeMillis());
        }

        videoPlayCall.onStart(info, cacheMap, errorCode);


    }

    public void pause() {
        if (mediaStatue.value != MediaStatue.started.value)
            return;

        mediaPlayer.pause();
        mediaStatue = MediaStatue.pause;
        videoPlayCall.onPause(info, errorCode);
    }


    public void playVideo(VideoManager.VideoInfo info, HashMap<VideoManager.VideoInfo, AdvBaseData> cacheMap) throws Exception {
        this.cacheMap = cacheMap;
        if (info == null)
            throw new NullPointerException("videoinfo id Null");

        this.info = info;
        String path = info.videoPath;
        init();
        if (TextUtils.isEmpty(path)) {
            errorCode = 1;
            videoPlayCall.onError(info, errorCode);
            return;
        }
        try {
            reset();
            mediaPlayer.setDataSource(path);
            mediaStatue = MediaStatue.initializ;

            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.prepareAsync();
            mediaStatue = MediaStatue.prepareing;
            videoPlayCall.onPrepare(info, errorCode);
        } catch (Exception e) {
            e.printStackTrace();
            errorCode = 5;
            videoPlayCall.onError(info, errorCode);
            if (mediaPlayer != null)
                mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mediaStatue = MediaStatue.completed;
        if (cacheMap.containsKey(info)) {
            if (cacheMap.get(info).getDataType() == AdvBaseData.AdvDataType.sens)
                cacheMap.get(info).setFormattime(Http.getformatTimeStr(cacheMap.get(info).getEndCallback()));

            cacheMap.get(info).setTimestamp(System.currentTimeMillis());
            cacheMap.get(info).setEndstamp(System.currentTimeMillis());
            cacheMap.get(info).setDuration((int) ((System.currentTimeMillis() - cacheMap.get(info).getBeginstamp()) / 1000));
        }

        videoPlayCall.onComplete(info, cacheMap, errorCode);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaStatue = MediaStatue.prepered;
        videoPlayCall.onPrepared(info, errorCode);
        mediaPlayer.setAudioStreamType(videoStrem);
        mediaPlayer.setDisplay(surfaceHolder);
        start();
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        surfaceHolder.setFixedSize(width, height);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mediaStatue = MediaStatue.error;
        errorCode = 2;
        videoPlayCall.onError(info, errorCode);
        reset();
        return false;
    }

    public void reStart() {
        if (mediaStatue.value() == MediaStatue.pause.value())
            start();
    }

    enum MediaStatue {
        idle(1), initializ(2), prepareing(3), prepered(4), started(5), pause(6), stopped(7), completed(8), error(9), end(10);
        int value;

        MediaStatue(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
