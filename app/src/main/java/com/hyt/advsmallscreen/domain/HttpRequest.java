package com.hyt.advsmallscreen.domain;

import android.support.annotation.NonNull;

import com.hyt.advsmallscreen.global.Url;
import com.hyt.advsmallscreen.utils.LogUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public class HttpRequest {
    static OkHttpClient okHttpClient = new OkHttpClient();

    public static void synchronizationPass(final RequestCall requestCall) {
        try {
            okHttpClient.newCall(new Request.Builder().get().url(Url.adminPass).build()).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    if (requestCall != null)
                        requestCall.Failure(e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        if (requestCall != null)
                            requestCall.Success(call, response);
                    } else {

                        if (requestCall != null)
                            requestCall.Failure(response.code() + "");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (requestCall != null)
                requestCall.Failure(e.toString());
        }
    }

    public static void httpPost(HashMap<String, String> parmeter, String url, final RequestCall requestCall) {
        okHttpClient.newCall(createPostRequest(parmeter, url)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                requestCall.Failure(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    requestCall.Success(call, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private static Request createPostRequest(HashMap<String, String> parmeter, String url) {
        return new Request.Builder().post(creatRequestBody(parmeter)).url(url).build();
    }

    private static Request createGetRequest(String url) {
        return new Request.Builder().get().url(url).build();
    }

    @NonNull
    private static RequestBody creatRequestBody(HashMap<String, String> pameraMap) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        Set<String> strings = pameraMap.keySet();
        for (String key : strings) {
            String value = pameraMap.get(key);
            builder.addFormDataPart(key, value);
        }
        return builder.build();
    }

    public static void requestGet(String url, final RequestCall requestCall) {
        okHttpClient.newCall(createGetRequest(url)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                if (requestCall!=null)
                requestCall.Failure(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (requestCall!=null)
                        requestCall.Success(call, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public abstract static class RequestCall {
        String TAG = "RequestCall";
        public void Failure(String msg) {
            LogUtil.e(TAG, "" + msg);
        }
        public void Success(Call call, Response response) {
            ResponseBody body = response.body();
            try {
                byte[] bytes = body.bytes();
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                Success(new String(bytes));
                Success(bis);
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
                Failure(e.toString());
            }
        }

 

        public void Success(String msg) {
//            LogUtil.e(TAG, ""+ msg);
        }

        public void Success(InputStream stream) {
        }

    }
}
