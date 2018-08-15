package com.hyt.advsmallscreen.utils;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityManager;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017-09-28.
 */

public class AppInfoUtil {
    private static String TAG = "AppInfoUtil";
    // 获取所有安装包信息
    public static List<PackageInfo> getPackageInfoList(Context context) {
        PackageManager pm = context.getPackageManager();
        return pm.getInstalledPackages(0);
    }

    //获取指定包信息
    public static PackageInfo getAppointPackageInfo(Context context, String packageName) {
        List<PackageInfo> packageInfoList = getPackageInfoList(context);
//        String packageName = context.getPackageName();
        for (PackageInfo info : packageInfoList) {
            if (packageName != null) {
//                LogUtil.e(TAG, " 已安装包名 " + info.packageName);
                if (info.packageName.equals(packageName)) {
                    return info;
                }

            }

        }

        return null;
    }

    // 查看apk 信息

    public static PackageInfo getApkInfo(String apkPath, Context context) {
        PackageManager pm = context.getPackageManager();
        return pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
    }


    /**
     * 检测辅助功能是否开启<br>
     * 方 法 名：isAccessibilitySettingsOn <br>
     * 创 建 人 <br>
     * 创建时间：2016-6-22 下午2:29:24 <br>
     * 修 改 人： <br>
     * 修改日期： <br>
     *
     * @param mContext
     * @return boolean
     */
    public static boolean isAccessibilitySettingsOn(Context mContext, String classPath) {
        int accessibilityEnabled = 0;
        // TestService为对应的服务  
//        final String service = getPackageName() + "/" + TestService.class.getCanonicalName();
        LogUtil.i(TAG, "service:" + classPath);
        // com.z.buildingaccessibilityservices/android.accessibilityservice.AccessibilityService  
        try {
            accessibilityEnabled = Settings.Secure.getInt(mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
            LogUtil.e(TAG, "accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            LogUtil.e(TAG, "Error finding setting, default accessibility to not found: " + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            LogUtil.e(TAG, "***ACCESSIBILITY IS ENABLED*** -----------------");
            String settingValue = Settings.Secure.getString(mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            // com.z.buildingaccessibilityservices/com.z.buildingaccessibilityservices.TestService  
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();

                    LogUtil.e(TAG, "-------------- > accessibilityService :: " + accessibilityService + " " + classPath);
                    if (accessibilityService.equalsIgnoreCase(classPath)) {
                        LogUtil.e(TAG, "We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }
            }
        } else {
            LogUtil.e(TAG, "***ACCESSIBILITY IS DISABLED***");
        }
        return false;
    }

    public static boolean isAccessibleEnabled(Context paramContext) {
        Log.e("info", "start");
        AccessibilityManager localAccessibilityManager;
        try {
            localAccessibilityManager = (AccessibilityManager) paramContext.getSystemService(Context.ACCESSIBILITY_SERVICE);
            if (localAccessibilityManager == null) {
                Log.e("manager", "is null");
                return false;
            }
            if (!localAccessibilityManager.isEnabled()) {
                Log.e("manager", "is not enable");
                return false;
            }
        } catch (Exception localException) {
            localException.printStackTrace();
            return false;
        }
        Log.e("manager", "is enable");
        List localList = localAccessibilityManager.getEnabledAccessibilityServiceList(-1);
        if (localList == null) {
            Log.e("runningServices", "is not null");
            return false;
        }
        Iterator localIterator = localList.iterator();
        boolean i;
        while (true) {
            boolean bool = localIterator.hasNext();
            i = false;
            if (!bool)
                break;
            AccessibilityServiceInfo localAccessibilityServiceInfo = (AccessibilityServiceInfo) localIterator.next();
            if ((localAccessibilityServiceInfo == null) || (localAccessibilityServiceInfo.getId() == null))
                continue;
            Log.e("包名：", localAccessibilityServiceInfo.getId());
            Log.e("包名2", paramContext.getPackageName() + "/com.wwxyz.wd.service.MoneyService");
            if (!localAccessibilityServiceInfo.getId().equals(paramContext.getPackageName() + "/com.wwxyz.wd.service.MoneyService"))
                continue;
            Log.d("ssss", "isAccessibleEnabled: true");
            i = true;
        }
        return i;
    }

    public static void jumpAccessibility(Context mContext) {

        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        mContext.startActivity(intent);
    }

    public static void RestartApp(Context mContext) {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        am.restartPackage(mContext.getPackageName());
    }

    public static HashMap getEnthenetMac(Context context) {
        HashMap<String , String> macMap = new HashMap<>();
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        String macAddress = wifiManager.getConnectionInfo().getMacAddress();

        LogUtil.e(TAG, " NetWorkName   " + wifiManager.getConnectionInfo().getSSID() + " WIFI Mac  " + macAddress);

        macMap.put("wifi",macAddress);
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {

                while (networkInterfaces.hasMoreElements()) {

                    NetworkInterface networkInterface = networkInterfaces.nextElement();

                    if (networkInterface != null) {

                        byte[] hardwareAddress = networkInterface.getHardwareAddress();

                        StringBuffer buffer = new StringBuffer();

                        if (hardwareAddress != null)
                            for (int i = 0; i < hardwareAddress.length; i++) {
                                if (i != 0) {
                                    buffer.append('_');
                                }
                                String str = Integer.toHexString(hardwareAddress[i] & 0xFF);

                                buffer.append(str.length() == 1 ? 0 + str : str);
                            }

                        String strMacAddr = buffer.toString().toUpperCase();

                        macMap.put("ethernet",strMacAddr);

                        LogUtil.e(TAG, " NetWorkName   " + networkInterface.getName() + " strMacAddr " + strMacAddr);

                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return macMap;
    }

    /**
     * 获取本地软件版本号
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            LogUtil.d("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
            LogUtil.d("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }



    public static TopActivityInfo getTopActivityInfo(Context context) {
        ActivityManager manager = ((ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE));
        TopActivityInfo info = new TopActivityInfo();
        if (Build.VERSION.SDK_INT >= 21) {
            List<ActivityManager.RunningAppProcessInfo> pis = manager.getRunningAppProcesses();
            ActivityManager.RunningAppProcessInfo topAppProcess = pis.get(0);
            if (topAppProcess != null && topAppProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                info.packageName = topAppProcess.processName;
                info.topActivityName = "";
            }
        } else {
            //getRunningTasks() is deprecated since API Level 21 (Android 5.0)  
            List localList = manager.getRunningTasks(1);
            ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)localList.get(0);
            info.packageName = localRunningTaskInfo.topActivity.getPackageName();
            info.topActivityName = localRunningTaskInfo.topActivity.getClassName();
        }

        Log.e(TAG, ""+info.toString());
        return info;
    }

    public static class TopActivityInfo {
        public String packageName = "";
        public String topActivityName = "";
        @Override
        public String toString() {
            return "TopActivityInfo [packageName=" + packageName
                    + ", topActivityName=" + topActivityName + "]";
        }


    }

}
