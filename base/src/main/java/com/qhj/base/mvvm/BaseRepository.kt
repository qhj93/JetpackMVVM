package com.qhj.base.mvvm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by 7in on 2021-09-28 9:12
 */
open class BaseRepository {

    suspend fun <T> request(block: suspend () -> T): Flow<T>{
        return flow {
            emit(block())
        }.flowOn(Dispatchers.IO)
    }

}