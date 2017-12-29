package com.zongke.hapilolauncher.library.okhttp

import com.zongke.hapilolauncher.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by ${xingen} on 2017/7/10.
 */

object OkHttpProvider {
    /**
     * 自定义配置OkHttpClient
     * @return
     */
    fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        //拦截器
        builder.addInterceptor(HeaderInterceptor())
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            //打印一次请求的全部信息
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }
}
