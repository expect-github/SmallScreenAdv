package com.hyt.advsmallscreen.global;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public class Url {

    // 百度广告
    public final static String baiduAdvHead = "http://120.78.206.233:7029";

    // 商汤
    public final static String sensAdvHead = "http://120.78.206.233:7029";
    // 易售
    public final static String yisAdvHead = "http://120.78.206.233:7029";

    // hyt
    final public static String hytAdvHead = "http://120.78.206.233:8081";
    //test
//    final public static String hytAdvHead = "192.168.1.11:8080";
    // 状态
    public static String advHytUrl = hytAdvHead + "/advert/download";
    // 提交mac
    public static String pullMac = hytAdvHead + "/advert/update/pullMac";

    public static String advUrl = "http://192.168.1.63:8080/advert/download";
    public static String advDataGonganUrl_local = "http://192.168.1.143:8080/advert/policeAdv ";
    public static String advDataGonganUrl = hytAdvHead + "/advert/policeAdv";
    public static String advAPKUpdataUrl = hytAdvHead + "/advert/downsoft/machineCode";
    public static String advPushAuthenDtataUrl = hytAdvHead + "/advert/insert/autofile";
    public static String advPullAuthenDtataUrl = hytAdvHead + "/advert/select/autofile";

    // 联网状态接口
    public static String advStateConnUrl = hytAdvHead + "/advert/select/state";
    // 提交mac
    public static String mac = hytAdvHead + "/advert/update/mac";
    //同步pass
    public static String adminPass = hytAdvHead + "/advert/select/pass";
//    public static String pushVersion = hytAdvHead+"/advert/select/pass";
    // syncPushData
    public final static String sycback = baiduAdvHead + "/api/back/";
    public final static String baiduPullPath = baiduAdvHead + "/api/adv";
    // commend
    public final static String pullCommend = baiduAdvHead + "/api/json";
    public final static String sensPullPath = sensAdvHead + "/st/ads";
    public final static String sensPullback_Test = sensAdvHead + "/st/back";
    // test
    public final static String YisPullUrl = "http://192.168.1.149:7028/yishou/get/st";

}
