package com.zongke.hapilolauncher.applist

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.support.v4.content.AsyncTaskLoader
import java.text.Collator
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by xinGen on 2017/7/15.
 *
 *  用于查询加载有效的启动的运用程序，
 *  即getLaunchIntentForPackage（）返回有效的运用程序。
 *
 */

class AppsLoader(context: Context) : AsyncTaskLoader<ArrayList<AppModel>>(context) {
    private var packageManager: PackageManager = context.packageManager
    private var instanlledAppsList: ArrayList<AppModel>? = null
    /**
     * 后台线程,进行监控`
     * @return
     */
    override fun loadInBackground(): ArrayList<AppModel> {
        //获取系统上安装的运用程序列表
        var infoList: List<ApplicationInfo>? = this.packageManager.getInstalledApplications(0)
        if (infoList == null) {
            infoList = ArrayList<ApplicationInfo>()
        }
        //创建相应的运用程序且加载他们的标签
        val items = ArrayList<AppModel>(infoList.size)
        for (i in infoList.indices) {
            val applicationInfo = infoList[i]
            val packageName = applicationInfo.packageName
            //检查相应的包名是否可以启动对应的运用程序
            if (context!!.packageManager.getLaunchIntentForPackage(packageName) != null) {
                val appModel = AppModel(context, applicationInfo)
                appModel.loadLabel(context)
                items.add(appModel)
            }
        }
        //分类list
        Collections.sort(items, comparator)
        return items
    }

    override fun deliverResult(data: ArrayList<AppModel>?) {
        if (isReset) {
            //当Loader是停止的时候，不需要传递查询结果
            if (data != null) {
                releaseResource(data)
            }
        }
        var oldApps = data
        instanlledAppsList = data
        //当Loader已经开始，立即传递结果
        if (isStarted) {
            super.deliverResult(data)
        }
        //当不需要使用旧数据时候，释放资源
        if (oldApps != null) {
            releaseResource(oldApps)
        }
    }
    override fun onStartLoading() {
        //若是当前的结果是可用的，立即传递结果
        if (instanlledAppsList != null) {
            deliverResult(instanlledAppsList)
        }
        //若是，从上次时间后，数据发生改变。或者现在数据不可用，则开始加载。
        if (takeContentChanged() || instanlledAppsList == null) {
            forceLoad()
        }
    }

    override fun onStopLoading() {
        //当需要停止时候，取消加载
        cancelLoad()
    }

    override fun onCanceled(data: ArrayList<AppModel>?) {
        super.onCanceled(data)
        //当需要时候，释放资源
        releaseResource(data)
    }

    override fun onReset() {
        //先关闭先前的加载
        onStartLoading()
        //释放原本的数据
        if (instanlledAppsList != null) {
            releaseResource(instanlledAppsList)
            instanlledAppsList = null
        }
    }

    /**
     * 释放资源
     */
    fun releaseResource(apps: ArrayList<AppModel>?) {

    }
    companion object {
        /**
         * 运用程序的名字比较
         */
        val comparator: Comparator<AppModel> = object : Comparator<AppModel> {
            private val sCollator = Collator.getInstance()
            override fun compare(appModel: AppModel, t1: AppModel): Int {
                return sCollator.compare(appModel.appLabel, t1.appLabel)
            }
        }
    }
}
