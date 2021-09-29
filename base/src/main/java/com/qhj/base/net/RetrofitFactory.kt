package com.qhj.base.net

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by 7in on 2021-09-28 9:40
 */
class RetrofitFactory private constructor(){

    companion object{
        val instance: RetrofitFactory by lazy(LazyThreadSafetyMode.SYNCHRONIZED){ RetrofitFactory() }
        private lateinit var retrofit: Retrofit
    }

    init {
        retrofit = Retrofit.Builder()
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://gank.io/api/")
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(MyHeaderInterceptor())
            .readTimeout(15L, TimeUnit.SECONDS)
            .writeTimeout(15L, TimeUnit.SECONDS)
            .connectTimeout(15L, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(8, 15L, TimeUnit.SECONDS))
            .build()
    }

    fun <T> create(service: Class<T>): T = retrofit.create(service)
}