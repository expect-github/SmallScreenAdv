package com.hyt.advsmallscreen.utils;

import android.graphics.Path;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ${Tao} on 2017-11-1709.
 */

public class FileUtil {


    public static ArrayList<String> listFiles(String path) throws Exception {


        File file = new File(path);
        if (file.exists()) {
            ArrayList<String> list = new ArrayList<>();

            if (file.isDirectory() && file.exists()) {

                File[] files = file.listFiles();
                for (File f : files) {
                    if (f.exists()) {
                      listFiles(f.getAbsolutePath());
                    }
                }

            } else {

                if (file.exists())
                    list.add(file.getAbsolutePath());
            }
            return list;
        }
        return null;
    }


    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
//                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    public static void coppyFolderFile2NewFolder(String oldFolder, String newFolder) {
        File file = new File(oldFolder);
        if(file.isDirectory()&&file.exists()) {
            File[] files = file.listFiles();
            if (files!=null&&files.length>0)
                for (File f : files) {
                    File df = new File(newFolder + "/" + f.getName());

                    if (!df.exists()) {
                        try {
                            file.getParentFile().mkdirs();
                            file.createNewFile();
                            FileUtil.copyFile(f.getAbsolutePath(),df.getAbsolutePath());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        try {

                            String dfMd5 = MD5Util.md5FromFile(df.getAbsolutePath(), false);
                            String fMd5 = MD5Util.md5FromFile(f.getAbsolutePath(), false);

                            if (!fMd5.equals(dfMd5)||dfMd5.isEmpty()){
                                FileUtil.copyFile(f.getAbsolutePath(),df.getAbsolutePath());
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            FileUtil.copyFile(f.getAbsolutePath(),df.getAbsolutePath());
                        }


                    }
                }

        }
    }
}
