package com.hyt.advsmallscreen.domain.data.senstom;

import java.util.ArrayList;

import static android.R.attr.data;

/**
 * Created by Tao on 2018/6/11 0011.
 */

public class AdsJson {

    String reason;
    String status;
    Data data;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "AdsJson{" +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
 

public  class Ads {

    String ad_id;
    String advertisement_id;
    String advertisement_name;
    String advertisement_type;
    ArrayList<String> begin_probles;
    int duration;
    ArrayList<String> end_probles;
    long exp_time;
    ArrayList<Materials> materials;
    long exposure_time;
    String position;
    String top;
    int type;
    int index;


    @Override
    public String toString() {
        return "Ads{" +
                "ad_id='" + ad_id + '\'' +
                ", advertisement_id='" + advertisement_id + '\'' +
                ", advertisement_name='" + advertisement_name + '\'' +
                ", advertisement_type='" + advertisement_type + '\'' +
                ", begin_probles=" + begin_probles +
                ", duration=" + duration +
                ", end_probles=" + end_probles +
                ", exp_time=" + exp_time +
                ", materials=" + materials +
                ", exposure_time='" + exposure_time + '\'' +
                ", position='" + position + '\'' +
                ", top='" + top + '\'' +
                ", type=" + type +
                ", index=" + index +
                '}';
    }

    public long getExposure_time() {
        return exposure_time;
    }

    public void setExposure_time(long exposure_time) {
        this.exposure_time = exposure_time;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getAdvertisement_id() {
        return advertisement_id;
    }

    public void setAdvertisement_id(String advertisement_id) {
        this.advertisement_id = advertisement_id;
    }

    public String getAdvertisement_name() {
        return advertisement_name;
    }

    public void setAdvertisement_name(String advertisement_name) {
        this.advertisement_name = advertisement_name;
    }

    public String getAdvertisement_type() {
        return advertisement_type;
    }

    public void setAdvertisement_type(String advertisement_type) {
        this.advertisement_type = advertisement_type;
    }

    public ArrayList<String> getBegin_probles() {
        return begin_probles;
    }

    public void setBegin_probles(ArrayList<String> begin_probles) {
        this.begin_probles = begin_probles;
    }

    public long getExp_time() {
        return exp_time;
    }

    public void setExp_time(long exp_time) {
        this.exp_time = exp_time;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<String> getEnd_probles() {
        return end_probles;
    }

    public void setEnd_probles(ArrayList<String> end_probles) {
        this.end_probles = end_probles;
    }

    public ArrayList<Materials> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<Materials> materials) {
        this.materials = materials;
    }

}

    public  class Materials {
    int material_height;
    int material_width;
    String url;

    public int getMaterial_height() {
        return material_height;
    }

    public void setMaterial_height(int material_height) {
        this.material_height = material_height;
    }

    public int getMaterial_width() {
        return material_width;
    }

    public void setMaterial_width(int material_width) {
        this.material_width = material_width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return "Materials{" +
                "material_height=" + material_height +
                ", material_width=" + material_width +
                ", url='" + url + '\'' +
                '}';
    }
}

    public  class Data {
    String adslot_id;
    String request_id;
    ArrayList<Ads> ads;

    public ArrayList<Ads> getAds() {
        return ads;
    }

    public void setAds(ArrayList<Ads> ads) {
        this.ads = ads;
    }

    public String getAdslot_id() {
        return adslot_id;
    }

    public void setAdslot_id(String adslot_id) {
        this.adslot_id = adslot_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }


    @Override
    public String toString() {
        return "Data{" +
                "adslot_id='" + adslot_id + '\'' +
                ", request_id='" + request_id + '\'' +
                ", ads=" + ads +
                '}';
    }
}
}
