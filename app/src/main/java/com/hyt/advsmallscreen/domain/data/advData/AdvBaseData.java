package com.hyt.advsmallscreen.domain.data.advData;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-11-07.
 */

public class AdvBaseData implements Serializable {

    // 本地 路径
    String localPath;
    // 数据来源类型  默认 locast 0，  hyt 1，  百度 2 , st 3 , ys 4
    AdvDataType dataType = AdvDataType.local;
    boolean useMd5 = true;
    String md5;
    long viladStartTimeMillie = System.currentTimeMillis();
    long viladEndTimeMillie = System.currentTimeMillis();
    
    long viladCount = -1;
    long timestamp = 1;
    long beginstamp = 1;
    long endstamp = 1;
    int duration = 1;
    String formattime;
    private String nextUpdateTime;   //下次更新时间
    private Integer advId;//广告id
    private String advName;//广告名称
    //地址
    private String advUrl;//广告链接地址
 
    private Integer advDimension;//广告尺寸
    private String advCategory;//广告类别
    private String advMessage;//广告详细信息
    // 日期
    private String advDayStime;//广告开始播放时间
    private String advDayFtime;//广告结束播放时间
    private String advStime;//广告上传时间
    private String advFtime;//广告更改时间
    private String advRemark;//广告标记
    private Integer advAudit;//广告审核状态
    private String advUser;//广告上传人
    private String advFileName;//广告文件名称
    private String advText;//广告上传路径
    private AdvMediaType advMediaType; // 广告类型  text 1,  video 2, image 3
    private int orderByte;//文字广告滚动速度
    // 播放序号
    int orderNumber = -1;
    // 显示时间
    private int onceShowTime;
    String startCallback;
    String endCallback;
    // 广告
    String jpAdtext;
    //熊掌
    String jpAdlogo;
    long maxPlayCount;
    long thisCount;
 

    boolean useCall;// 回掉控制
    boolean useSync;   // 同步控制
    
    String startMintorCall;   // 起始第三方回调
    String completedMintorCall; // 结束第三方回调


    public String getLocalPath() {
        return localPath;
    }

    public AdvDataType getDataType() {
        return dataType;
    }

    public boolean isUseMd5() {
        return useMd5;
    }

    public String getMd5() {
        return md5;
    }

    public long getViladStartTimeMillie() {
        return viladStartTimeMillie;
    }

    public long getViladEndTimeMillie() {
        return viladEndTimeMillie;
    }

    public long getViladCount() {
        return viladCount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getBeginstamp() {
        return beginstamp;
    }

    public long getEndstamp() {
        return endstamp;
    }

    public int getDuration() {
        return duration;
    }

    public String getFormattime() {
        return formattime;
    }

    public String getNextUpdateTime() {
        return nextUpdateTime;
    }

    public Integer getAdvId() {
        return advId;
    }

    public String getAdvName() {
        return advName;
    }

    public String getAdvUrl() {
        return advUrl;
    }

 
    public Integer getAdvDimension() {
        return advDimension;
    }

    public String getAdvCategory() {
        return advCategory;
    }

    public String getAdvMessage() {
        return advMessage;
    }

    public String getAdvDayStime() {
        return advDayStime;
    }

    public String getAdvDayFtime() {
        return advDayFtime;
    }

    public String getAdvStime() {
        return advStime;
    }

    public String getAdvFtime() {
        return advFtime;
    }

    public String getAdvRemark() {
        return advRemark;
    }

    public Integer getAdvAudit() {
        return advAudit;
    }

    public String getAdvUser() {
        return advUser;
    }

    public String getAdvFileName() {
        return advFileName;
    }

    public String getAdvText() {
        return advText;
    }

    public AdvMediaType getAdvMediaType() {
        return advMediaType;
    }

    public int getOrderByte() {
        return orderByte;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getOnceShowTime() {
        return onceShowTime;
    }

    public String getStartCallback() {
        return startCallback;
    }

    public String getEndCallback() {
        return endCallback;
    }

    public String getJpAdtext() {
        return jpAdtext;
    }

    public String getJpAdlogo() {
        return jpAdlogo;
    }

    public long getMaxPlayCount() {
        return maxPlayCount;
    }

    public long getThisCount() {
        return thisCount;
    }

 
    public boolean isUseCall() {
        return useCall;
    }

    public boolean isUseSync() {
        return useSync;
    }

    public String getStartMintorCall() {
        return startMintorCall;
    }

    public String getCompletedMintorCall() {
        return completedMintorCall;
    }
    
    

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public void setDataType(AdvDataType dataType) {
        this.dataType = dataType;
    }

    public void setUseMd5(boolean useMd5) {
        this.useMd5 = useMd5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setViladStartTimeMillie(long viladStartTimeMillie) {
        this.viladStartTimeMillie = viladStartTimeMillie;
    }

    public void setViladEndTimeMillie(long viladEndTimeMillie) {
        this.viladEndTimeMillie = viladEndTimeMillie;
    }

    public void setViladCount(long viladCount) {
        this.viladCount = viladCount;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setBeginstamp(long beginstamp) {
        this.beginstamp = beginstamp;
    }

    public void setEndstamp(long endstamp) {
        this.endstamp = endstamp;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setFormattime(String formattime) {
        this.formattime = formattime;
    }

    public void setNextUpdateTime(String nextUpdateTime) {
        this.nextUpdateTime = nextUpdateTime;
    }

    public void setAdvId(Integer advId) {
        this.advId = advId;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    public void setAdvUrl(String advUrl) {
        this.advUrl = advUrl;
    }

 
    public void setAdvDimension(Integer advDimension) {
        this.advDimension = advDimension;
    }

    public void setAdvCategory(String advCategory) {
        this.advCategory = advCategory;
    }

    public void setAdvMessage(String advMessage) {
        this.advMessage = advMessage;
    }

    public void setAdvDayStime(String advDayStime) {
        this.advDayStime = advDayStime;
    }

    public void setAdvDayFtime(String advDayFtime) {
        this.advDayFtime = advDayFtime;
    }

    public void setAdvStime(String advStime) {
        this.advStime = advStime;
    }

    public void setAdvFtime(String advFtime) {
        this.advFtime = advFtime;
    }

    public void setAdvRemark(String advRemark) {
        this.advRemark = advRemark;
    }

    public void setAdvAudit(Integer advAudit) {
        this.advAudit = advAudit;
    }

    public void setAdvUser(String advUser) {
        this.advUser = advUser;
    }

    public void setAdvFileName(String advFileName) {
        this.advFileName = advFileName;
    }

    public void setAdvText(String advText) {
        this.advText = advText;
    }

    public void setAdvMediaType(AdvMediaType advMediaType) {
        this.advMediaType = advMediaType;
    }

    public void setOrderByte(int orderByte) {
        this.orderByte = orderByte;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOnceShowTime(int onceShowTime) {
        this.onceShowTime = onceShowTime;
    }

    public void setStartCallback(String startCallback) {
        this.startCallback = startCallback;
    }

    public void setEndCallback(String endCallback) {
        this.endCallback = endCallback;
    }

    public void setJpAdtext(String jpAdtext) {
        this.jpAdtext = jpAdtext;
    }

    public void setJpAdlogo(String jpAdlogo) {
        this.jpAdlogo = jpAdlogo;
    }

    public void setMaxPlayCount(long maxPlayCount) {
        this.maxPlayCount = maxPlayCount;
    }

    public void setThisCount(long thisCount) {
        this.thisCount = thisCount;
    }

 
    public void setUseCall(boolean useCall) {
        this.useCall = useCall;
    }

    public void setUseSync(boolean useSync) {
        this.useSync = useSync;
    }

    public void setStartMintorCall(String startMintorCall) {
        this.startMintorCall = startMintorCall;
    }

    public void setCompletedMintorCall(String completedMintorCall) {
        this.completedMintorCall = completedMintorCall;
    }

    @Override
    public String toString() {
        return "AdvBaseData{" +
                "localPath='" + localPath + '\'' +
                ", dataType=" + dataType +
                ", useMd5=" + useMd5 +
                ", md5='" + md5 + '\'' +
                ", viladStartTimeMillie=" + viladStartTimeMillie +
                ", viladEndTimeMillie=" + viladEndTimeMillie +
                ", viladCount=" + viladCount +
                ", timestamp=" + timestamp +
                ", beginstamp=" + beginstamp +
                ", endstamp=" + endstamp +
                ", duration=" + duration +
                ", formattime='" + formattime + '\'' +
                ", nextUpdateTime='" + nextUpdateTime + '\'' +
                ", advId=" + advId +
                ", advName='" + advName + '\'' +
                ", advUrl='" + advUrl + '\'' +
                ", advDimension=" + advDimension +
                ", advCategory='" + advCategory + '\'' +
                ", advMessage='" + advMessage + '\'' +
                ", advDayStime='" + advDayStime + '\'' +
                ", advDayFtime='" + advDayFtime + '\'' +
                ", advStime='" + advStime + '\'' +
                ", advFtime='" + advFtime + '\'' +
                ", advRemark='" + advRemark + '\'' +
                ", advAudit=" + advAudit +
                ", advUser='" + advUser + '\'' +
                ", advFileName='" + advFileName + '\'' +
                ", advText='" + advText + '\'' +
                ", advMediaType=" + advMediaType +
                ", orderByte=" + orderByte +
                ", orderNumber=" + orderNumber +
                ", onceShowTime=" + onceShowTime +
                ", startCallback='" + startCallback + '\'' +
                ", endCallback='" + endCallback + '\'' +
                ", jpAdtext='" + jpAdtext + '\'' +
                ", jpAdlogo='" + jpAdlogo + '\'' +
                ", maxPlayCount=" + maxPlayCount +
                ", thisCount=" + thisCount +
                ", useCall=" + useCall +
                ", useSync=" + useSync +
                ", startMintorCall='" + startMintorCall + '\'' +
                ", completedMintorCall='" + completedMintorCall + '\'' +
                '}';
    }

    public enum AdvDataType {

        local(0), hyt(1), baidu(2), sens(3), yishou(4);

        private int type;

        AdvDataType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public static AdvDataType getType(int type) {
            if (type == local.getType())
                return local;
            else if (type == hyt.getType())
                return hyt;
            else if (type == baidu.getType())
                return baidu;
            else if (type == sens.getType())
                return sens;
            else if (type == yishou.getType())
                return yishou;
            return null;
        }
    }

    public enum AdvMediaType {
        video(2), image(3);
        private int type;

        AdvMediaType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public static AdvMediaType getType(int type) {
            if (type == video.getType())
                return video;
            else if (type == image.getType())
                return image;
            return null;
        }

    }

}
