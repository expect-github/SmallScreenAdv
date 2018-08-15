package com.hyt.advsmallscreen.video;

import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;

import java.util.HashMap;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public interface VideoStatueCall {
   public void onError(VideoManager.VideoInfo videoInfo, int errorCode);
   public void onInit(int errorCode);
   public void onPrepare(VideoManager.VideoInfo videoInfo, int errorCode);
 
   public void onPause(VideoManager.VideoInfo videoInfo, int errorCode);
   public void onStop(VideoManager.VideoInfo videoInfo, int errorCode);
   public void onComplete(VideoManager.VideoInfo videoInfo, HashMap<VideoManager.VideoInfo, AdvBaseData> cacheMap, int errorCode);

   void onRelease(VideoManager.VideoInfo videoInfo, int errorCode);

   void onReset(VideoManager.VideoInfo videoInfo, int errorCode);

   void onStart(VideoManager.VideoInfo videoInfo, HashMap<VideoManager.VideoInfo, AdvBaseData> cacheMap,int errorCode);

   void onPrepared(VideoManager.VideoInfo info ,int errorCode);
}
