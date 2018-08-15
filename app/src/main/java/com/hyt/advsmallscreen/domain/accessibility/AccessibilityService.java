package com.hyt.advsmallscreen.domain.accessibility;

import android.content.Intent;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.hyt.advsmallscreen.utils.LogUtil;

import java.util.List;

/**
 * Created by ${Tao} on 2017-11-2113.
 */

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {

    private String TAG = "AccessibilityService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        try {
            if (event != null)
                if (event.getPackageName().equals("com.android.packageinstaller") ||
                        event.getPackageName().equals("com.estrongs.android.pop")) {


                    LogUtil.e(TAG, "ContentEvent" + event.toString());

                    
                    List<AccessibilityNodeInfo> unintall_nodes = event.getSource().findAccessibilityNodeInfosByText("安装");
                    if (unintall_nodes != null && !unintall_nodes.isEmpty()) {
                        AccessibilityNodeInfo node;
                        for (int i = 0; i < unintall_nodes.size(); i++) {
                            node = unintall_nodes.get(i);
                            if (node.getClassName().equals("android.widget.Button") && node.isEnabled()) {
                                LogUtil.e(TAG, "点击安装");
                                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);

                            }
                        }
                    }

                    List<AccessibilityNodeInfo> next_nodes = event.getSource().findAccessibilityNodeInfosByText("下一步");
                    if (next_nodes != null && !next_nodes.isEmpty()) {
                        AccessibilityNodeInfo node;
                        for (int i = 0; i < next_nodes.size(); i++) {
                            node = next_nodes.get(i);
                            if (node.getClassName().equals("android.widget.Button") && node.isEnabled()) {
                                LogUtil.e(TAG, "下一步");

                                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }

                    List<AccessibilityNodeInfo> ok_nodes = event.getSource().findAccessibilityNodeInfosByText("打开");
                    if (ok_nodes != null && !ok_nodes.isEmpty()) {
                        AccessibilityNodeInfo node;
                        for (int i = 0; i < ok_nodes.size(); i++) {
                            node = ok_nodes.get(i);
                            if (node.getClassName().equals("android.widget.Button") && node.isEnabled()) {
                                LogUtil.e(TAG, "打开");

                                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }
                    List<AccessibilityNodeInfo> Error = event.getSource().findAccessibilityNodeInfosByText("确定");
                    if (Error != null && !Error.isEmpty()) {
                        AccessibilityNodeInfo node;
                        for (int i = 0; i < Error.size(); i++) {
                            node = Error.get(i);
                            if (node.getClassName().equals("android.widget.Button") && node.isEnabled()) {
                                LogUtil.e(TAG, "确定");

                                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }


                    List<AccessibilityNodeInfo> complete = event.getSource().findAccessibilityNodeInfosByText("完成");
                    if (complete != null && !complete.isEmpty()) {
                        AccessibilityNodeInfo node;
                        for (int i = 0; i < complete.size(); i++) {
                            node = complete.get(i);
                            if (node.getClassName().equals("android.widget.Button") && node.isEnabled()) {
                                LogUtil.e(TAG, "完成");
                                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }
                } else if (event.getPackageName().equals("com.android.settings")) {


                    LogUtil.e(TAG, "onAccessibilityEvent  android 设置" + event.toString());

                    AccessibilityNodeInfo source = event.getSource();
                 
                        source = getRootInActiveWindow();
                    
                    List<AccessibilityNodeInfo> complete;  
//                    
//                    if (complete != null && !complete.isEmpty()) {
//                        AccessibilityNodeInfo node;
//                        for (int i = 0; i < complete.size(); i++) {
//                            node = complete.get(i);
//                            if ( node.isEnabled()) {
//                                preformClick(node);
//
//                            }
//                        }
//                    }

                    List< AccessibilityNodeInfo >closeInfo = source.findAccessibilityNodeInfosByText("关闭");
                    
                    if (closeInfo!=null&&closeInfo.size()>0) {
                        complete = source.findAccessibilityNodeInfosByText("升级辅助");

                        if (complete != null && !complete.isEmpty()) {
                            
                            
                         boolean isInside =   checkCloseInsideComplete(closeInfo,complete);

                            if (isInside) {
                                AccessibilityNodeInfo node;
                                for (int i = 0; i < complete.size(); i++) {
                                    node = complete.get(i);
                                    String s = node.getText().toString();
                                    LogUtil.e(TAG, s);
                                    if (node != null  ) {
//                                        preformClick(node);
                                    }
                                }
                            }
                        }

                        complete = source.findAccessibilityNodeInfosByText("升级辅助工具");

                        if (complete != null && !complete.isEmpty()) {
 

                            boolean isInside =   checkCloseInsideComplete(closeInfo,complete);

                            if (isInside) {
                                AccessibilityNodeInfo node;
                                for (int i = 0; i < complete.size(); i++) {
                                    node = complete.get(i);
                                    String s = node.getText().toString();
                                    LogUtil.e(TAG, s);
                                    if (node != null && s.contains("关闭")) {
//                                        preformClick(node);
                                    }
                                }
                            }
                        }
                    }
                    closeInfo = source.findAccessibilityNodeInfosByViewId("android:id/action_bar");
                    
                    if (closeInfo != null && !closeInfo.isEmpty()) {

                        AccessibilityNodeInfo node;
                        
                        for (int i = 0; i < closeInfo.size(); i++) {
                            
                            node = closeInfo.get(i);
                            
                            LogUtil.e(TAG,"action_bar " +node.toString()+" \n"+node.getChildCount());
                            
                            for (int j =0 ; j<node.getChildCount() ; j ++) {
                                AccessibilityNodeInfo child = node.getChild(j);
                                LogUtil.e(TAG,"child " +child.toString()+" \n"+child.getClassName());

                                if (node!=null&&(child.getClassName().toString().contains("android.widget.Switch")||child.getText().toString().contains("关闭"))) {
                                  LogUtil.e(TAG," 准备选择 " );
                                    if(!node.performAction(AccessibilityNodeInfo.ACTION_SELECT)){
                                        LogUtil.e(TAG," 准备点击 " );

                                        preformClick(node);
                                    }
                                }
                            }
                        }
                    }
                    complete = source.findAccessibilityNodeInfosByText("确定");
                    if (complete != null && !complete.isEmpty()) {
                        AccessibilityNodeInfo node;
                        for (int i = 0; i < complete.size(); i++) {
                            node = complete.get(i);
                            if (node!=null&&node.getClassName().equals("android.widget.Button") ) {
                                preformClick(node);
                                preformBack(this);
                            }
                        }
                    }

                    complete = source.findAccessibilityNodeInfosByText("激活");
                    if (complete != null && !complete.isEmpty()) {
                        AccessibilityNodeInfo node;
                        for (int i = 0; i < complete.size(); i++) {
                            node = complete.get(i);
                            if (node!=null&&node.getClassName().equals("android.widget.Button") ) {
                                preformClick(node);
                                preformBack(this);
                            }
                        }
                    }

                }else if (event.getPackageName().equals("com.android.systemui")) {
                    
              

                    List<AccessibilityNodeInfo> infosByViewId = event.getSource().findAccessibilityNodeInfosByViewId("android:id/button1");

                    if (infosByViewId!=null){

                        for (AccessibilityNodeInfo info : infosByViewId){

                            if (info!=null&&info.getClassName().equals("android.widget.Button")){
                                preformClick(info);
                            }
                        }
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkCloseInsideComplete(List<AccessibilityNodeInfo> closeInfo, List<AccessibilityNodeInfo> complete) {
        
        if (closeInfo!=null&&complete!=null){
            for (AccessibilityNodeInfo close : closeInfo){
                if (close==null)
                    continue;
                Rect rect = new Rect();
                close.getBoundsInScreen(rect);
                for (AccessibilityNodeInfo info :complete ){
                    if (info==null)
                        continue;
                    Rect infoRect = new Rect();
                    
                    info.getBoundsInScreen(infoRect);
                    int i = rect.top-infoRect.top  ;
                    LogUtil.e(TAG ," 高度差 " +i);
                    if (i>0&&i<=30) {
                        return true;
                    }
                }
            }
        }
        return false ;
    }

    private void preformClick(AccessibilityNodeInfo node) {
        if (node==null)
            return;
        if (node.isClickable()||node.isCheckable() ){
            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            LogUtil.e(TAG, "点击 完成 " +node.toString());
            return;
        }else {
            
            preformClick(node.getParent());
        }
    }
    
  private void  preformBack (android.accessibilityservice.AccessibilityService sevice){
      sevice.performGlobalAction(android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_BACK);
  }

    @Override
    public void onInterrupt() {
        LogUtil.e(TAG, "onInterrupt");
    }

    @Override
    public boolean onUnbind(Intent intent) {

        LogUtil.e(TAG, "onUnbind");

        return super.onUnbind(intent);
    }

    @Override
    protected void onServiceConnected() {

        LogUtil.e(TAG, "onServiceConnected");
        // 

        super.onServiceConnected();
    }
    
}
