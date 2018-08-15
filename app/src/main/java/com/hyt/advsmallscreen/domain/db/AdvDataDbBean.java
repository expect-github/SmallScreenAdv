package com.hyt.advsmallscreen.domain.db;

/**
 * Created by Tao on 2018/8/10 0010.
 */

public class AdvDataDbBean {


    private Integer advId;//广告id
    String advCategory;  // 广告类别描述
    String updataTime; // 更新时间
    String md5; //md5
    String name; // name
    String advurl; //url
    String startPlayCall;    // 起始播放回调
    String startMintorCall;   // 起始第三方回调
    String completedEndCall;  // 结束回调
    String completedMintorCall; // 结束第三方回调
    long startTime;  // 开始控制时间
    long endTime;      // 结束控制时间
    String endTimeFormart;      // 结束时间展示
    int onesTime;      // 单次播放时间
    int playstatue;    // 播放状态
    int advDataType;   // 供应商类型
    int advMediaType;            // 媒体类型
    String localPath;   // 本地地址
    boolean useCall;// 回掉控制
    boolean useSync;   // 同步控制
    boolean useMd5 = true;      // MD5控制
    long playcount = 0;          // 播放次数
    long maxplaycount = 0;        // 最大播放次数
    private String advFileName;     //广告文件名称
    private String advMessage;     //广告描述

    int orIndex =0;   // 播放序号

    // 广告
    String jpAdtext;
    //熊掌
    String jpAdlogo;
    
    String standby1;
    String standby2;
    String standby3;
    String standby4;
    String standby5;
    String standby6;
    String standby7;
    String standby8;


    public Integer getAdvId() {
        return advId;
    }

    public void setAdvId(Integer advId) {
        this.advId = advId;
    }

    public String getAdvMessage() {
        return advMessage;
    }

    public void setAdvMessage(String advMessage) {
        this.advMessage = advMessage;
    }

    public String getAdvCategory() {
        return advCategory;
    }

    public String getUpdataTime() {
        return updataTime;
    }

    public String getMd5() {
        return md5;
    }

    public String getName() {
        return name;
    }

    public String getAdvurl() {
        return advurl;
    }

    public String getStartPlayCall() {
        return startPlayCall;
    }

    public String getStartMintorCall() {
        return startMintorCall;
    }

    public String getCompletedEndCall() {
        return completedEndCall;
    }

    public String getCompletedMintorCall() {
        return completedMintorCall;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getEndTimeFormart() {
        return endTimeFormart;
    }

    public int getOnesTime() {
        return onesTime;
    }

    public int getPlaystatue() {
        return playstatue;
    }

    public int getAdvDataType() {
        return advDataType;
    }

    public int getAdvMediaType() {
        return advMediaType;
    }

    public String getLocalPath() {
        return localPath;
    }

    public boolean isUseCall() {
        return useCall;
    }

    public boolean isUseSync() {
        return useSync;
    }

    public boolean isUseMd5() {
        return useMd5;
    }

    public long getPlaycount() {
        return playcount;
    }

    public long getMaxplaycount() {
        return maxplaycount;
    }

    public String getAdvFileName() {
        return advFileName;
    }

    public int getOrIndex() {
        return orIndex;
    }

    public String getJpAdtext() {
        return jpAdtext;
    }

    public String getJpAdlogo() {
        return jpAdlogo;
    }

    public String getStandby1() {
        return standby1;
    }

    public String getStandby2() {
        return standby2;
    }

    public String getStandby3() {
        return standby3;
    }

    public String getStandby4() {
        return standby4;
    }

    public String getStandby5() {
        return standby5;
    }

    public String getStandby6() {
        return standby6;
    }

    public String getStandby7() {
        return standby7;
    }

    public String getStandby8() {
        return standby8;
    }
    
    

    public void setAdvCategory(String advCategory) {
        this.advCategory = advCategory;
    }

    public void setUpdataTime(String updataTime) {
        this.updataTime = updataTime;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdvurl(String advurl) {
        this.advurl = advurl;
    }

    public void setStartPlayCall(String startPlayCall) {
        this.startPlayCall = startPlayCall;
    }

    public void setStartMintorCall(String startMintorCall) {
        this.startMintorCall = startMintorCall;
    }

    public void setCompletedEndCall(String completedEndCall) {
        this.completedEndCall = completedEndCall;
    }

    public void setCompletedMintorCall(String completedMintorCall) {
        this.completedMintorCall = completedMintorCall;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setEndTimeFormart(String endTimeFormart) {
        this.endTimeFormart = endTimeFormart;
    }

    public void setOnesTime(int onesTime) {
        this.onesTime = onesTime;
    }

    public void setPlaystatue(int playstatue) {
        this.playstatue = playstatue;
    }

    public void setAdvDataType(int advDataType) {
        this.advDataType = advDataType;
    }

    public void setAdvMediaType(int advMediaType) {
        this.advMediaType = advMediaType;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public void setUseCall(boolean useCall) {
        this.useCall = useCall;
    }

    public void setUseSync(boolean useSync) {
        this.useSync = useSync;
    }

    public void setUseMd5(boolean useMd5) {
        this.useMd5 = useMd5;
    }

    public void setPlaycount(long playcount) {
        this.playcount = playcount;
    }

    public void setMaxplaycount(long maxplaycount) {
        this.maxplaycount = maxplaycount;
    }

    public void setAdvFileName(String advFileName) {
        this.advFileName = advFileName;
    }

    public void setOrIndex(int orIndex) {
        this.orIndex = orIndex;
    }

    public void setJpAdtext(String jpAdtext) {
        this.jpAdtext = jpAdtext;
    }

    public void setJpAdlogo(String jpAdlogo) {
        this.jpAdlogo = jpAdlogo;
    }

    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    public void setStandby2(String standby2) {
        this.standby2 = standby2;
    }

    public void setStandby3(String standby3) {
        this.standby3 = standby3;
    }

    public void setStandby4(String standby4) {
        this.standby4 = standby4;
    }

    public void setStandby5(String standby5) {
        this.standby5 = standby5;
    }

    public void setStandby6(String standby6) {
        this.standby6 = standby6;
    }

    public void setStandby7(String standby7) {
        this.standby7 = standby7;
    }

    public void setStandby8(String standby8) {
        this.standby8 = standby8;
    }

    @Override
    public String toString() {
        return "AdvDataDbBean{" +
                "advId=" + advId +
                ", advCategory='" + advCategory + '\'' +
                ", updataTime='" + updataTime + '\'' +
                ", md5='" + md5 + '\'' +
                ", name='" + name + '\'' +
                ", advurl='" + advurl + '\'' +
                ", startPlayCall='" + startPlayCall + '\'' +
                ", startMintorCall='" + startMintorCall + '\'' +
                ", completedEndCall='" + completedEndCall + '\'' +
                ", completedMintorCall='" + completedMintorCall + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", endTimeFormart='" + endTimeFormart + '\'' +
                ", onesTime=" + onesTime +
                ", playstatue=" + playstatue +
                ", advDataType=" + advDataType +
                ", advMediaType=" + advMediaType +
                ", localPath='" + localPath + '\'' +
                ", useCall=" + useCall +
                ", useSync=" + useSync +
                ", useMd5=" + useMd5 +
                ", playcount=" + playcount +
                ", maxplaycount=" + maxplaycount +
                ", advFileName='" + advFileName + '\'' +
                ", advMessage='" + advMessage + '\'' +
                ", orIndex=" + orIndex +
                ", jpAdtext='" + jpAdtext + '\'' +
                ", jpAdlogo='" + jpAdlogo + '\'' +
                ", standby1='" + standby1 + '\'' +
                ", standby2='" + standby2 + '\'' +
                ", standby3='" + standby3 + '\'' +
                ", standby4='" + standby4 + '\'' +
                ", standby5='" + standby5 + '\'' +
                ", standby6='" + standby6 + '\'' +
                ", standby7='" + standby7 + '\'' +
                ", standby8='" + standby8 + '\'' +
                '}';
    }
}
