package com.qhj.base.net

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

/**
 * Created by 7in on 2021-12-20 16:04
 */
class StateLiveData<T> : MutableLiveData<ApiResponse<T>>(){

    fun observer(owner: LifecycleOwner, callbackBuilder: CallbackBuilder.() -> Unit) {
        val callback = CallbackBuilder().also(callbackBuilder)
        val value = object : IStateObserver<T>(){
            override fun onSuccess(data: T) {
                callback.successAction?.invoke(data)
            }

            override fun onFailure(code: Int, msg: String) {
                callback.failureAction?.invoke(code,msg)
            }

            override fun onError(e: Throwable) {
                callback.errorAction?.invoke(e)
            }
        }
        super.observe(owner,value)
    }

    inner class CallbackBuilder {
        internal var successAction: ((T) -> Unit)? = null
        internal var failureAction: ((Int,String) -> Unit)? = null
        internal var errorAction: ((Throwable) -> Unit)? = null

        fun onSuccess(action: (T) -> Unit) {
            successAction = action
        }

        fun onFailure(action: (Int,String) -> Unit) {
            failureAction = action
        }

        fun onError(action: (Throwable) -> Unit) {
            errorAction = action
        }
    }
}