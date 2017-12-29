package com.zongke.hapilolauncher.applist

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter


/**
 * Created by ${新根} on 2017/7/15.
 * blog ：http://blog.csdn.net/hexingen
 *
 * 监控运用程序变化的广播，用于及时通知Loader
 */

class PackageIntentReceiver(var  loader: AppsLoader):BroadcastReceiver(){
     init {
         //注册与运用安装，移除，变化相关的广播监听
         var filter=IntentFilter()
         filter.addAction(Intent.ACTION_PACKAGE_ADDED)
         filter.addAction(Intent.ACTION_PACKAGE_REMOVED)
         filter.addAction(Intent.ACTION_PACKAGE_CHANGED)
         filter.addDataScheme("package")
         loader.context.registerReceiver(this,filter)

         //注册与sdcard安装相关的广播监听
         var installfilter=IntentFilter()
         installfilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE)
         installfilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE)
         loader.context.registerReceiver(this,installfilter)
     }


    override fun onReceive(context: Context, intent: Intent) {
        //当运用程序发生改变的时候，通知Loader
       loader.onContentChanged()
    }

    /**
     * 取消注册监听
     */
    fun  unRegisterReceiver(){
        loader.context.unregisterReceiver(this)
    }
}
