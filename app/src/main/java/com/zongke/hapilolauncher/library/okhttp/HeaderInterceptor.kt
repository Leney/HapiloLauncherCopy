package com.zongke.hapilolauncher.library.okhttp

import com.zongke.hapilolauncher.library.retrofit.HttpConstance
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by ${xinGen} on 2017/11/15.
 */
class HeaderInterceptor : Interceptor {
    val appId = "appId"
    val CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8"
    override fun intercept(chain: Interceptor.Chain): Response {
        var builder = chain.request().newBuilder()
        builder.addHeader(appId, HttpConstance.APP_ID)
                .addHeader("User-Agent", "android")
                .addHeader("Content-Type", CONTENT_TYPE)
        return chain.proceed(builder.build())
    }
}