package com.qhj.base.net

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by 7in on 2021-09-28 9:40
 */
class MyHeaderInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("token", "token").build()
        builder.addHeader("device", "Android").build()
        return chain.proceed(builder.build())
    }

}