package com.hyt.advsmallscreen.domain.data;


/**
 * Created by Tao on 2018/4/12 0012.
 */

public class pushYisData {

    String device_uuid = "A08850";
    String slot_id = "128";
    int quantity = 1;
    String type = "VDO";
    String ip = "192.168.56.1";

    public String getDevice_uuid() {
        return device_uuid;
    }
    public void setDevice_uuid(String device_uuid) {
        this.device_uuid = device_uuid;
    }
    public String getSlot_id() {
        return slot_id;
    }
    public void setSlot_id(String slot_id) {
        this.slot_id = slot_id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String toString() {
        return "pushYisData{" +
                "device_uuid='" + device_uuid + '\'' +
                ", slot_id=" + slot_id +
                ", quantity=" + quantity +
                ", type='" + type + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
