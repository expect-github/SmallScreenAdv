package com.hyt.advsmallscreen.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;


/**
 * Created by Tao on 2018/3/26 0026.
 */

public class IntentUtil {


    public static void sendActionBroadCast(Context context, String action) {

        Intent intent = new Intent();
        intent.setAction(action);
        context.sendBroadcast(intent);

    }



    public static void installAPK(Context context, String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        context.startActivity(intent);

    }



}
