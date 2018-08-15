package com.hyt.advsmallscreen.domain.db;

/**
 * Created by Tao on 2018/8/10 0010.
 */

public class AdvPlayDbBean {

    // 文件路径
    String filePath;
    // md5
    String md5;
    // 名称
    String name;
    // 链接
    String advurl;
    // 是否使用回掉
    boolean useCall;
    // 起始播放回调
    String startPlayCall;
    // 起始第三方回调
    String startMintorCall;
    // 结束回调
    String completedEndCall;
    // 结束第三方回调
    String completedMintorCall;
    
    // 播放控制总次数
    long totalCount;
    // 已播放总次数
    long alreadyCount;
    // 同步前播放次数
    long thisTimeCount;

    // 同步到服务器次数 
    long syncServiceCount;

    // 起始回调失败次数
    long startCallFailure;
    //
    long startCallSuccess;

    // 结束回调失败
    long endCallFailure;
    //
    long endCallSuccess;
    
    // 数据来源类型
    int advDataType;  // 数据来源类型  默认 locast 0， hyt 1，  baidu 2 , st 3 , ys 4
    // 媒体类型
    int advMediaType;

    // 起始播放时间 
    long startPlayTime;
    // 结束播放时间 
    long endPlayTime;

    // 单次请求播放次数控制
    long thisTimeTotalCount;

    // 数据库操作时间
    String time;

    // 数据同步状态 1 未上传 2 正在上传 3 上传失败 4 上传成功
    int dataPushStatue =0;

    // 使用同步
    boolean useSync =false;


    public long getStartCallSuccess() {
        return startCallSuccess;
    }

    public void setStartCallSuccess(long startCallSuccess) {
        this.startCallSuccess = startCallSuccess;
    }

    public long getEndCallSuccess() {
        return endCallSuccess;
    }

    public void setEndCallSuccess(long endCallSuccess) {
        this.endCallSuccess = endCallSuccess;
    }

    public String getFilePath() {
        return filePath;
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

    public boolean isUseCall() {
        return useCall;
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

    public long getTotalCount() {
        return totalCount;
    }

    public long getAlreadyCount() {
        return alreadyCount;
    }

    public long getThisTimeCount() {
        return thisTimeCount;
    }

    public long getSyncServiceCount() {
        return syncServiceCount;
    }

    public long getStartCallFailure() {
        return startCallFailure;
    }

    public long getEndCallFailure() {
        return endCallFailure;
    }

    public int getAdvDataType() {
        return advDataType;
    }

    public int getAdvMediaType() {
        return advMediaType;
    }

    public long getStartPlayTime() {
        return startPlayTime;
    }

    public long getEndPlayTime() {
        return endPlayTime;
    }

    public long getThisTimeTotalCount() {
        return thisTimeTotalCount;
    }

    public String getTime() {
        return time;
    }

    public int getDataPushStatue() {
        return dataPushStatue;
    }

    public boolean isUseSync() {
        return useSync;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public void setUseCall(boolean useCall) {
        this.useCall = useCall;
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

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setAlreadyCount(long alreadyCount) {
        this.alreadyCount = alreadyCount;
    }

    public void setThisTimeCount(long thisTimeCount) {
        this.thisTimeCount = thisTimeCount;
    }

    public void setSyncServiceCount(long syncServiceCount) {
        this.syncServiceCount = syncServiceCount;
    }

    public void setStartCallFailure(long startCallFailure) {
        this.startCallFailure = startCallFailure;
    }

    public void setEndCallFailure(long endCallFailure) {
        this.endCallFailure = endCallFailure;
    }

    public void setAdvDataType(int advDataType) {
        this.advDataType = advDataType;
    }

    public void setAdvMediaType(int advMediaType) {
        this.advMediaType = advMediaType;
    }

    public void setStartPlayTime(long startPlayTime) {
        this.startPlayTime = startPlayTime;
    }

    public void setEndPlayTime(long endPlayTime) {
        this.endPlayTime = endPlayTime;
    }

    public void setThisTimeTotalCount(long thisTimeTotalCount) {
        this.thisTimeTotalCount = thisTimeTotalCount;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDataPushStatue(int dataPushStatue) {
        this.dataPushStatue = dataPushStatue;
    }

    public void setUseSync(boolean useSync) {
        this.useSync = useSync;
    }

    @Override
    public String toString() {
        return "AdvPlayDbBean{" +
                "filePath='" + filePath + '\'' +
                ", md5='" + md5 + '\'' +
                ", name='" + name + '\'' +
                ", advurl='" + advurl + '\'' +
                ", useCall=" + useCall +
                ", startPlayCall='" + startPlayCall + '\'' +
                ", startMintorCall='" + startMintorCall + '\'' +
                ", completedEndCall='" + completedEndCall + '\'' +
                ", completedMintorCall='" + completedMintorCall + '\'' +
                ", totalCount=" + totalCount +
                ", alreadyCount=" + alreadyCount +
                ", thisTimeCount=" + thisTimeCount +
                ", syncServiceCount=" + syncServiceCount +
                ", startCallFailure=" + startCallFailure +
                ", endCallFailure=" + endCallFailure +
                ", advDataType=" + advDataType +
                ", advMediaType=" + advMediaType +
                ", startPlayTime='" + startPlayTime + '\'' +
                ", endPlayTime='" + endPlayTime + '\'' +
                ", thisTimeTotalCount=" + thisTimeTotalCount +
                ", time='" + time + '\'' +
                ", dataPushStatue=" + dataPushStatue +
                ", useSync=" + useSync +
                '}';
    }
}
