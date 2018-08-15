package com.hyt.advsmallscreen.domain;

import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;
import com.hyt.advsmallscreen.utils.LogUtil;
import com.hyt.advsmallscreen.utils.MD5Util;

import java.io.File;
import java.io.IOException;

/**
 * Created by Tao on 2018/8/10 0010.
 */

public class Md5Helper {
    public static boolean checkMd5(AdvBaseData data, String filePath) {
        if (!data.isUseMd5()) {
            try {
                data.setMd5(MD5Util.md5FromFile(filePath, false));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        if (data.getMd5() == null)
            return false;

        try {
            String md5FromFile = MD5Util.md5FromFile(filePath, false);
//            LogUtil.e("md5" , "serverMd5 " + data.getMd5() + "   local md5 " + md5FromFile);
            if (data.getMd5().equals(md5FromFile)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean matchMd5(String md5, File file) {
        try {
            String s = MD5Util.md5FromFile(file.getPath(), true);
            if (md5.toUpperCase().equals(s))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
