package com.qhj.jetpackmvvm.model

import com.qhj.base.mvvm.BaseRepository
import com.qhj.base.net.ApiResponse
import com.qhj.base.net.RetrofitFactory
import com.qhj.jetpackmvvm.bean.GankBean
import kotlinx.coroutines.flow.Flow

/**
 * Created by 7in on 2021-09-28 9:38
 */
class MainRepository : BaseRepository(){

    suspend fun getRepositoryData() : Flow<GankBean> {
        val api = RetrofitFactory.instance.create(MyApi::class.java)
        return request { api.getData() }
    }

    //第二种网络请求封装方式
    suspend fun getRepositoryData2() : ApiResponse<Any> {
        val api = RetrofitFactory.instance.create(MyApi::class.java)
        return request2(true) { api.getData2() }
    }

}