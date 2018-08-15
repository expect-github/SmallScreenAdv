package com.hyt.advsmallscreen.app;

import android.app.Application;
import android.content.Context;

import com.hyt.advsmallscreen.domain.db.AdvDbHelper;
import com.hyt.advsmallscreen.global.Path;
import com.hyt.advsmallscreen.utils.LogUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Tao on 2018/8/8 0008.
 */

public class AdvApplication extends Application {
    public static AdvDbHelper advDbHelper;
    private String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        init();
    }

    private void init() {
        initPath();
        initDb();
    }

    private void initDb() {
        advDbHelper = new AdvDbHelper(this);
    }

    private void initPath() {
        
        createFolder(Path.configPath);
        createFolder(Path.dbPath);
        createFolder(Path.logPath);
        createFolder(Path.advPath);
        
        createFolder(Path.defaultvideoPath);
        createFolder(Path.defaultImagePath);
        createFolder(Path.videoCachePath);
  
    }

    private void createFolder(String path) {
        File file = new File(path);
        if (!file.exists())   
            file.mkdirs();
    }
}
