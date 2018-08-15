package com.hyt.advsmallscreen.domain.data.senstom;

import java.util.ArrayList;

import static android.R.attr.key;
import static android.R.attr.type;
import static android.R.attr.width;

/**
 * Created by Tao on 2018/6/16 0016.
 */

public class AdvYisJson {

    int code;
    String message;
    ArrayList<Payload> payload;

    public ArrayList<Payload> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<Payload> payload) {
        this.payload = payload;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    class Payload {

        String expire_time;
        long file_size;
        int height;
        String key;
        int show_time;
        String sign;
        int slot_id;
        String track_url;
        String type;
        String url;
        int width;

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        public long getFile_size() {
            return file_size;
        }

        public void setFile_size(long file_size) {
            this.file_size = file_size;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getShow_time() {
            return show_time;
        }

        public void setShow_time(int show_time) {
            this.show_time = show_time;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getSlot_id() {
            return slot_id;
        }

        public void setSlot_id(int slot_id) {
            this.slot_id = slot_id;
        }

        public String getTrack_url() {
            return track_url;
        }

        public void setTrack_url(String track_url) {
            this.track_url = track_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        @Override
        public String toString() {
            return "Payload{" +
                    "expire_time='" + expire_time + '\'' +
                    ", file_size=" + file_size +
                    ", height=" + height +
                    ", key='" + key + '\'' +
                    ", show_time=" + show_time +
                    ", sign='" + sign + '\'' +
                    ", slot_id=" + slot_id +
                    ", track_url='" + track_url + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", width=" + width +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AdvYisJson{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", payload=" + payload +
                '}';
    }
}
