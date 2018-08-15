package com.hyt.advsmallscreen.global;

 

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tao on 2018/4/12 0012.
 */

public class Http {
    public  static OkHttpClient client ;
    public static  String  getformatTimeStr (String str){
        int formatIndex = str.indexOf("date_format=");
        SimpleDateFormat simpleDateFormat;
        String formatTime;
        if(formatIndex > 0){
            int nextIndex = str.indexOf("&",formatIndex);
            if(nextIndex <0){
                nextIndex = str.length();
            }
            String dateFormatStr = str.substring(formatIndex+"date_format=".length(),nextIndex);

            if(dateFormatStr!=null &&!dateFormatStr.isEmpty()){
                try {
                    simpleDateFormat = new SimpleDateFormat(URLDecoder.decode(dateFormatStr,"UTF-8"));
                    formatTime = simpleDateFormat.format(new Date());
                    return  formatTime ;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static void post(String url, String json , Callback callback) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
       new OkHttpClient().newCall(request).enqueue(callback);
    }
}
