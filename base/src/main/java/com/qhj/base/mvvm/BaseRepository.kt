package com.qhj.base.mvvm

import android.net.ParseException
import android.widget.Toast
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import com.qhj.base.manager.TopActivityManager
import com.qhj.base.net.ApiErrorResponse
import com.qhj.base.net.ApiFailureResponse
import com.qhj.base.net.ApiResponse
import com.qhj.base.net.ApiSuccessResponse
import com.qhj.base.widget.MyLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

/**
 * Created by 7in on 2021-09-28 9:12
 */
open class BaseRepository {

    suspend fun <T> request(block: suspend () -> T): Flow<T>{
        return flow {
            emit(block())
        }.flowOn(Dispatchers.IO)
    }

    //第二种网络请求封装方式
    suspend fun <T> request2(showLoading: Boolean = false, block: suspend () -> ApiResponse<T>): ApiResponse<T>{
        if (showLoading) MyLoading.instance.show()
        kotlin.runCatching {
            block.invoke()
        }.onSuccess {
            if (showLoading) MyLoading.instance.dismiss()
            return handleResponse(it)
        }.onFailure {
            if (showLoading) MyLoading.instance.dismiss()
            return handleException(it)
        }
        if (showLoading) MyLoading.instance.dismiss()
        return ApiResponse()
    }

    private fun <T> handleResponse(response: ApiResponse<T>): ApiResponse<T> {
        return if (response.code == 200) {
            ApiSuccessResponse(response.data)
        } else {
            if (response.code == 10014) {
                Toast.makeText(TopActivityManager.instance.getTopActivity(), "登录已过期", Toast.LENGTH_SHORT).show()
            }
            ApiFailureResponse(response.code,response.msg)
        }
    }

    private fun <T> handleException(e: Throwable): ApiErrorResponse<T>{
        when (e) {
            is HttpException -> {
                Toast.makeText(TopActivityManager.instance.getTopActivity(), "网络异常，请稍后再试", Toast.LENGTH_SHORT).show()
            }
            is ConnectException -> {
                Toast.makeText(TopActivityManager.instance.getTopActivity(), "网络连接错误，请稍后再试", Toast.LENGTH_SHORT).show()
            }
            is ConnectTimeoutException, is SocketTimeoutException -> {
                Toast.makeText(TopActivityManager.instance.getTopActivity(), "网络连接超时，请稍后再试", Toast.LENGTH_SHORT).show()
            }
            is UnknownHostException -> {
                Toast.makeText(TopActivityManager.instance.getTopActivity(), "网络连接错误，请稍后再试", Toast.LENGTH_SHORT).show()
            }
            is SSLException -> {
                Toast.makeText(TopActivityManager.instance.getTopActivity(), "证书出错，请稍后再试", Toast.LENGTH_SHORT).show()
            }
            is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                Toast.makeText(TopActivityManager.instance.getTopActivity(), "数据解析错误，请稍后再试", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(TopActivityManager.instance.getTopActivity(), "未知错误，请稍后再试", Toast.LENGTH_SHORT).show()
            }
        }
        return ApiErrorResponse(e)
    }

}