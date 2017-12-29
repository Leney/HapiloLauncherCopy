package com.zongke.hapiloimservice.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * Created by ${xinGen} on 2017/12/2.
 */

public class ActivityUtils {
    public static final String defaultPackageName="com.zongke.hapilolauncher.test";
    public static final String defaultActivityName="TestActivity";
    /**
     * 拼接Activity所属包名路径
     *
     * @param packageName
     * @param activity
     * @return
     */
   public  static String getActivityNames(String packageName, String activity) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(packageName);
        stringBuilder.append(".test.");
        stringBuilder.append(activity);
        return stringBuilder.toString();
    }

    /**
     * 开启某个指定的Activity
     * @param context
     * @param packageName
     * @param activityName
     */
    public static void startOtherActivity(Context context, String packageName, String activityName) {
        Intent intent = new Intent();
        //这里的Activity是必须包含包名路径
        intent.setClassName(packageName, activityName);
        starActivity(context,intent);
    }

    /**
     * 通过包名开启其他的程序
     *
     * @param context
     * @param packageName
     */
    public static void startOtherLauncherApp(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
         starActivity(context,intent);
    }
    public static void starActivity(Context context,Intent intent){
        if (intent != null && intent.resolveActivity(context.getPackageManager()) != null) {
            try {
                if (!(context instanceof Activity)){
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void startCameraApp(Context context){
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        starActivity(context,intent);
    }
}
