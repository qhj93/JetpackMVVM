package com.qhj.base.net

/**
 * Created by 7in on 2021-12-20 15:40
 */
open class ApiResponse<T> (
    open val code: Int? = null,
    open val msg: String? = null,
    open val data: T? = null,
    open val error: Throwable? = null
)

data class ApiSuccessResponse<T>(val response: T?) : ApiResponse<T>(data = response)

data class ApiFailureResponse<T>(override val code: Int?, override val msg: String?) : ApiResponse<T>(code,msg)

data class ApiErrorResponse<T>(val e: Throwable) : ApiResponse<T>(error = e)