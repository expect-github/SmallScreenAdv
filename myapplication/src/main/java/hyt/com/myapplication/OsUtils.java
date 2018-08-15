package hyt.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import java.io.File;


/**
 * Created by ${Tao} on 2017-11-2318.
 */

public class OsUtils
{
    private static String TAG="OsUtils";
    public static void installAPK(Context context, String path) {
       File file = new File(path);
        if (!file.exists()){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);

    }

    /**
     * 卸载指定包名的应用
     * @param packageName
     */
    public  static boolean uninstall(Context context ,String packageName) {
        boolean b = checkApplication(context,packageName);
      Log.e(TAG, "Test:check:"+b);
        if (b) {
            Uri packageURI = Uri.parse("package:".concat(packageName));
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(packageURI);
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * 判断该包名的应用是否安装
     *
     * @param packageName
     * @return
     */
    public  static boolean checkApplication(Context context ,String packageName) {
      Log .e(TAG, "Test,run");
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        try {
            context.  getPackageManager().getApplicationInfo(packageName,
                    PackageManager.MATCH_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Log .e(TAG, "Test:"+e.toString());
        }
        return false;
    }

 

}
