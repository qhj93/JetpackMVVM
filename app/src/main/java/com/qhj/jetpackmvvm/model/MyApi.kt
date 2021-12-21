package com.qhj.jetpackmvvm.model

import com.qhj.base.net.ApiResponse
import com.qhj.jetpackmvvm.bean.GankBean
import retrofit2.http.GET

/**
 * Created by 7in on 2021-09-28 9:42
 */
interface MyApi {
    @GET("random/data/福利/20")
    suspend fun getData() : GankBean

    @GET("xxx")
    suspend fun getData2() : ApiResponse<Any>
}