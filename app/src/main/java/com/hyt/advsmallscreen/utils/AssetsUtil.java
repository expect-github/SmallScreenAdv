package com.hyt.advsmallscreen.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;

/**
 * Created by Tao on 2018/8/9 0009.
 */

public class AssetsUtil {
    
    public  static  void   copyAssetsFiles (Context context ,String assetsForder , String newForder){
        AssetManager assets = context.getAssets();
        try {
            String[] list = assets.list(assetsForder);
            for (String name:list)
            {
                    FileUtils.copyAssiets(assets.open(assetsForder+"/"+name),newForder+"/"+name);                
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    } 
}
