package com.zongke.hapilolauncher.utils
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ResolveInfo
import android.util.Log
import java.lang.Exception
import java.util.*


/**
 * Created by ${xingen} on 2017/7/19.
 *
 *  // 要求 permission SET_PREFERRED_APPLICATIONS.
 *
 */

class LauncherUtils {

    /**
     * 设置系统默认的Launcher
     */
    fun setDefaultLauncher(context: Context, mainActivityName: String) {
        clearDefaultLauncherApps(context)
        var allLauncherList = getAllLauncherApps(context)
        Log.i(tag," 设备上安装的Launcher个数 : ${allLauncherList.size}  ")
        var intentFilter = IntentFilter(Intent.ACTION_MAIN)
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        intentFilter.addCategory(Intent.CATEGORY_HOME)
        var launcher = ComponentName(context.packageName, mainActivityName)
        var componentNameSet = kotlin.arrayOfNulls<ComponentName>(allLauncherList.size)
        var defaultMatchLauncher = 0
        for (i in allLauncherList.indices) {
            var resolveInfo = allLauncherList[i]
            componentNameSet[i] = ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name)
            var stringBuffer=createMatchName(launcher)
            Log.i(tag," 索引是$i  将要设置成默认的Launcher:${stringBuffer.toString()}    信息名：${resolveInfo.activityInfo.name} ")
            if (stringBuffer.toString().equals(resolveInfo.activityInfo.name)) {
                defaultMatchLauncher = resolveInfo.match
                Log.i(tag," 匹配到的match : ${resolveInfo.match}  ")
            }
        }
        try {
            context.packageManager.addPreferredActivity(intentFilter, defaultMatchLauncher, componentNameSet, launcher)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 创建匹配的名字
     */
    fun  createMatchName(launcher:ComponentName ):StringBuffer{
        var stringBuffer=StringBuffer()
        stringBuffer.append(launcher.packageName)
        stringBuffer.append(".")
        stringBuffer.append(launcher.className)
        return  stringBuffer
    }

    /**
     * 清除当前默认的Launcher
     */
    fun clearDefaultLauncherApps(context: Context) {
        var packageManager = context.packageManager
        var intentList = ArrayList<IntentFilter>()
        var componetNameList = ArrayList<ComponentName>()
        //查询到首先的Activity
        packageManager.getPreferredActivities(intentList, componetNameList, null)
        try {
            for (i in intentList.indices) {
                var intentFilter = intentList[i]
                //筛选除首先的Launcher中主界面
                if (intentFilter.hasAction(Intent.ACTION_MAIN) && intentFilter.hasCategory(Intent.CATEGORY_HOME)) {
                    Log.i(tag," 清空的Launcher包名: ${componetNameList[i].packageName}")
                    packageManager.clearPackagePreferredActivities(componetNameList[i].packageName)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    /**
     * 获取到Android系统硬件或者手机上安装的全部的Launcher
     */
    fun getAllLauncherApps(context: Context): List<ResolveInfo> {
        var packageManager = context.packageManager
        var intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        return packageManager.queryIntentActivities(intent, 0)
    }
    companion object {
        @JvmStatic//Java代码可以调用
        var instance= LauncherUtils()
        val tag=LauncherUtils::class.java.simpleName
    }
}
