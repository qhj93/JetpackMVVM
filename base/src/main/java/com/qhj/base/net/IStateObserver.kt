package com.qhj.base.net

import androidx.lifecycle.Observer

/**
 * Created by 7in on 2021-12-20 15:20
 */
abstract class IStateObserver<T> : Observer<ApiResponse<T>> {

    override fun onChanged(response: ApiResponse<T>) {
        when(response) {
            is ApiSuccessResponse -> onSuccess(response.data!!)
            is ApiFailureResponse -> onFailure(response.code!!,response.msg!!)
            is ApiErrorResponse -> onError(response.error!!)
        }
    }

    abstract fun onSuccess(data: T)

    abstract fun onFailure(code: Int, msg: String)

    abstract fun onError(e: Throwable)

}