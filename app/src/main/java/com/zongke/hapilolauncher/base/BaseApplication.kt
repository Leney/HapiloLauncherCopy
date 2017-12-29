package com.zongke.hapilolauncher.base

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

/**
 * Created by ${xingen} on 2017/7/13.
 */

class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        initMultiDex()
        instance = this
    }

    /**
     * 突破MultiDex的限制
     */
    private fun initMultiDex() {
        MultiDex.install(this)
    }

    companion object {
        var instance: BaseApplication? = null
            private set
    }
}
