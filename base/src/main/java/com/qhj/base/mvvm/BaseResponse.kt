package com.qhj.base.mvvm

/**
 * Created by 7in on 2021-09-28 9:11
 */
data class BaseResponse<T>(var code: Int, var msg: String, var data: T)