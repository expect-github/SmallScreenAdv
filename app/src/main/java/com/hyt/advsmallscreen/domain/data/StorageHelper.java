package com.hyt.advsmallscreen.domain.data;

import com.hyt.advsmallscreen.app.AdvApplication;
import com.hyt.advsmallscreen.domain.Md5Helper;
import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;
import com.hyt.advsmallscreen.domain.db.AdvDataDbBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tao on 2018/8/14 0014.
 */

public class StorageHelper {

    public static void deleteLoseEfficacy(ArrayList<AdvBaseData> advBaseDataS) throws Exception {
        if (advBaseDataS == null)
            return;

        File file = new File(advBaseDataS.get(0).getLocalPath());
        if (file.getParentFile().exists()) {
            File[] list = file.getParentFile().listFiles();
            for (File f : list) {
                boolean have = false;
                for (AdvBaseData data : advBaseDataS) {
                    if (f.getAbsolutePath().equals(data.getLocalPath())) {
                        if (data.isUseMd5()) {
                            if (Md5Helper.matchMd5(data.getMd5(), f)) {
                                have = true;
                                break;
                            }
                        } else {
                            have = true;
                            break;
                        }
                    }
                }
                try {
                    if (!have)
                        f.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void deleteLose(AdvBaseData advBaseData) {
        if (advBaseData == null)
            return;

        File file = new File(advBaseData.getLocalPath());
        if (file.getParentFile().exists()) {
            File[] list = file.getParentFile().listFiles();
            for (File f : list) {
                boolean have = false;

                if (f.getAbsolutePath().equals(advBaseData.getLocalPath())) {
                    if (advBaseData.isUseMd5()) {
                        if (Md5Helper.matchMd5(advBaseData.getMd5(), f)) {
                            have = true;
                        }
                    } else {
                        have = true;
                    }
                }
                try {
                    if (!have)
                        f.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void deleteLoseEfficacyAsDb() {
        List<AdvDataDbBean> advDataDbBeans = AdvApplication.advDbHelper.fingallCache();
        try {
            deleteLoseEfficacy(advDataDbBeans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteLoseEfficacy(List<AdvDataDbBean> advDataDbBeans)  throws  Exception{
        if (advDataDbBeans == null)
            return;

        File file = new File(advDataDbBeans.get(0).getLocalPath());
        if (file.getParentFile().exists()) {
            File[] list = file.getParentFile().listFiles();
            for (File f : list) {
                boolean have = false;
                for (AdvDataDbBean data : advDataDbBeans) {
                    if (f.getAbsolutePath().equals(data.getLocalPath())) {
                        if (data.isUseMd5()) {
                            if (Md5Helper.matchMd5(data.getMd5(), f)) {
                                have = true;
                                break;
                            }
                        } else {
                            have = true;
                            break;
                        }
                    }
                }
                try {
                    if (!have)
                        f.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
