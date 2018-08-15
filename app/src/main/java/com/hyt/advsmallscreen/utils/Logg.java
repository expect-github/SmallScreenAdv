package com.hyt.advsmallscreen.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tao on 2018/8/14 0014.
 */

public class Logg {


    // 总log开关
    public static boolean isLog = true;
    public static String TAG = "Logg";
    static String logPath = Environment.getExternalStorageDirectory() + "/Log/log.txt";

    // 简洁log
    public static void e(String msg) {
        if (!isLog)
            return;
        eAll(TAG, msg);
    }

    //带tag
    public static void e(String tag, String msg) {
        if (!isLog)
            return;
        eAll(tag, msg);
    }


    // 带日志保存
    public static void e(String tag, String msg, boolean save) {
        if (!isLog)
            return;
        eAll(tag, msg);
        if (save)
            saveLog(tag, msg, logPath);
    }
        // 带日志保存
    public static void e(String tag, String msg, String logPath ,boolean save) {
        if (!isLog)
            return;
        eAll(tag, msg);
        if (save)
            saveLog(tag, msg, logPath);
    }

    // 简洁log
    public static void d(String msg) {
        if (!isLog)
            return;
        dAll(TAG, msg);
    }

    //带tag
    public static void d(String tag, String msg) {
        if (!isLog)
            return;
        dAll(tag, msg);
    }


    // 带日志保存
    public static void d(String tag, String msg, boolean save) {
        if (!isLog)
            return;
        dAll(tag, msg);
        if (save)
            saveLog(tag, msg, logPath);
    }
        // 带日志保存
    public static void d(String tag, String msg, String logPath ,boolean save) {
        if (!isLog)
            return;
        dAll(tag, msg);
        if (save)
            saveLog(tag, msg, logPath);
    }


    private static void eAll(String tag, String msg) {
        int max_str_length=2001-tag.length();
        //大于4000时
        while (msg.length()>max_str_length){
            Log.e(tag, msg.substring(0,max_str_length) );
            msg=msg.substring(max_str_length);
        }
        //剩余部分
        Log.e(tag, msg);
    }
    
    private static void dAll(String tag, String msg) {
        int max_str_length=2001-tag.length();
        //大于4000时
        while (msg.length()>max_str_length){
            Log.e(tag, msg.substring(0,max_str_length) );
            msg=msg.substring(max_str_length);
        }
        //剩余部分
        Log.d(tag, msg);
    }
    
    

    public static void saveLog(String tag, String msg, String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            StringBuilder sb = new StringBuilder();
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));

            sb.append(time);
            sb.append("\n");
            sb.append(tag);
            sb.append("\n");
            sb.append(msg);
            sb.append("\n ");

            FileOutputStream outputStream = new FileOutputStream(file, true);
            outputStream.write(sb.toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
