package com.zongke.hapilolauncher.applist

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable
import android.util.Log
import com.zongke.hapilolauncher.R
import java.io.File

/**
 * Created by xinGen on 2017/7/15.
 * blog ：http://blog.csdn.net/hexingen
 * 一个实体类，用于存储运用程序的信息。
 */
class AppModel(private val context: Context, val applicationInfo: ApplicationInfo) {
    /**
     * 程序的标签
     */
    var appLabel: String? = null
        private set
    /**
     * 程序的icon
     */
    private var icon: Drawable? = null
    /**
     * 是否安装
     */
    private var mounted: Boolean = false
    /**
     * 程序的所在的路径
     */
    private val apkFile: File = File(this.applicationInfo.sourceDir)

    val applicationPackageName: String get() = applicationInfo.packageName
    private     var  tag=AppModel::class.java.simpleName
    fun getIcon(): Drawable? {
        if (icon == null) {
            if (apkFile.exists()) {
                icon = applicationInfo.loadIcon(context.packageManager)
                return icon
            } else {
                mounted = false
            }
        } else if (!mounted) {
            //先前程序未安装，现在安装了，需要重新加载icon
            if (apkFile.exists()) {
                mounted = true
                icon = applicationInfo.loadIcon(context.packageManager)
                return icon
            }
        } else {
            return icon
        }
        return context.resources.getDrawable(R.mipmap.ic_launcher)
    }

     fun loadLabel(context: Context) {
        if (appLabel == null || !mounted) {
            //若是apk路径不存在
            if (!apkFile.exists()) {
                mounted = false
                appLabel = applicationInfo.packageName
            } else {
                mounted = true
                val label = applicationInfo.loadLabel(context.packageManager)
                appLabel = if (label!=null){label.toString()}else {applicationInfo.packageName}
            }
        }
         Log.i(tag,"AppModel存储的信息  标签： $appLabel  包名： $applicationPackageName")
    }

}
