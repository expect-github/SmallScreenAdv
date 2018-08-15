package com.hyt.advsmallscreen.domain.data;



/**
 * Created by Tao on 2018/4/12 0012.
 */

public class pushBaiduData {
    // 请求id
    String requestId;
    // 广告位id  平台生成
    String imageadslotId = "5569170";
    String videoadslotId = "5569170";
    // 设备唯一识别码（长度不限）
    String udId = "51244d5d88246936ccdf69a65e84b929";
    // 设备名称
    String vendor = "\\xb2L\\xb0\\xa2\\xbba";
    // 设备型号
    String model = "123456";
    //ip
    String ipv4 = "192.168.1.123";
    // 网络类型代码
    String connectionType = "MOBILE_2G";
    //运营商代码
    String operatorType = "ISP_CHINA_MOBILE";

    public static class OperatorType {
        String MOBILE_2G = "MOBILE_2G";
        String ISP_UNKNOWN = "ISP_UNKNOWN";
        String ISP_CHINA_MOBILE = "ISP_CHINA_MOBILE";
        String ISP_CHINA_TELECOM = "ISP_CHINA_TELECOM";
        String ISP_FOREIGN = "ISP_FOREIGN";

//        ISP_UNKNOWN         = 0;  //未知运营商
//        ISP_CHINA_MOBILE    = 1;  //中国移动
//        ISP_CHINA_UNICOM    = 2;  //中国联通
//        ISP_CHINA_TELECOM   = 3;  //中国电信
//        ISP_FOREIGN
    }

    public static class ConnectionType {
        String ISP_CHINA_MOBILE = "ISP_CHINA_MOBILE";
        String WIFI = "WIFI";
        String MOBILE_2G = "MOBILE_2G";
        String MOBILE_3G = "MOBILE_3G";
        String MOBILE_4G = "MOBILE_4G";
        String ETHERNET = "ETHERNET";
        String NEW_TYPE = "NEW_TYPE";

//
//        系统部-陈恭林 2018/4/12 星期四  17:13:24
//        UNKNOWN_NETWORK = 0;
//        WIFI = 1;
//        MOBILE_2G = 2;
//        MOBILE_3G = 3;
//        MOBILE_4G = 4;
//        ETHERNET = 101; // 以太网接入
//        NEW_TYPE = 999;  // 未知新类型

    }


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getImageadslotId() {
        return imageadslotId;
    }

    public void setImageadslotId(String imageadslotId) {
        this.imageadslotId = imageadslotId;
    }

    public String getVideoadslotId() {
        return videoadslotId;
    }

    public void setVideoadslotId(String videoadslotId) {
        this.videoadslotId = videoadslotId;
    }

    public String getUdId() {
        return udId;
    }

    public void setUdId(String udId) {
        this.udId = udId;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }
}
