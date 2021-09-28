package com.qhj.jetpackmvvm.model

import com.qhj.base.mvvm.BaseRepository
import com.qhj.base.net.RetrofitFactory
import com.qhj.jetpackmvvm.bean.GankBean
import kotlinx.coroutines.flow.Flow

/**
 * Created by 7in on 2021-09-28 9:38
 */
class MainRepository : BaseRepository(){
    suspend fun getRepositoryData() : Flow<GankBean> {
        val api = RetrofitFactory.getInstance().create(MyApi::class.java)
        return request { api.getData() }
    }
}