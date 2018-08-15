package com.hyt.advsmallscreen.global;

import android.os.Environment;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public class Path {

    final private static String rootPath = Environment.getExternalStorageDirectory() + "/HytSmallAdv";


    final private static String dataPath = rootPath + "/data";
    final public static String advPath = rootPath + "/Adv";
    final private static String defaultAdvPath = advPath + "/default";
    final private static String pullAdvPath = advPath + "/pull";


    final public static String defaultImagePath = defaultAdvPath + "/image";
    final public static String defaultvideoPath = defaultAdvPath + "/video";


    final private static String pullHytPath = pullAdvPath + "/hyt";
    final private static String pullBaiduPath = pullAdvPath + "/baidu";
    final private static String pullSensPath = pullAdvPath + "/sens";
    final private static String pullYishouPath = pullAdvPath + "/yishow";
    
    final private static String cachePath = dataPath + "/cache";
    
    
 
    final public static String hytImagePath = pullHytPath + "/image";
    final public static String hytVideoPath = pullHytPath + "/video"; 
    
    final public static String hytbaiduImagePath = pullBaiduPath + "/image";
    final public static String hytbaiduVideoPath = pullBaiduPath + "/video";

    final public static String hytSensImagePath = pullSensPath + "/image";
    final public static String hytSensVideoPath = pullSensPath + "/video";
    
    final public static String hytYishowImagePath = pullYishouPath + "/image";
    final public static String hytSYishowVideoPath = pullYishouPath + "/video";

    
    final public static String configPath = dataPath + "/config";
    final public static String dbPath = dataPath + "/db";
    final public static String logPath = dataPath + "/log";
    final public static String dataCachePath = cachePath + "/data";
 
    final public static String videoCachePath = cachePath + "/video";
    final public static String imageCachePath = cachePath + "/image";
    
    

}
