package com.hyt.advsmallscreen.domain.data;

/**
 * Created by Tao on 2018/4/12 0012.
 */

public class pushSensData {
    String type ="2";
    String deviceCode = "";
    String networkType = "0";
    String androidId = "a114df306d2411e8b68bfa163eefa58b";
    String mac = "123";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
