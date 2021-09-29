package com.qhj.base.mvvm

import android.net.ParseException
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import com.qhj.base.manager.TopActivityManager
import com.qhj.base.widget.MyLoading
import kotlinx.coroutines.flow.*
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

/**
 * Created by 7in on 2021-09-28 9:20
 */
open class BaseViewModel : ViewModel(){

    fun <T> request(showLoading: Boolean = false, block: suspend () -> Flow<T>) = liveData {
        block().onStart {
            if (showLoading) MyLoading.instance.show()
        }.catch {
            handleException(it)
        }.onCompletion {
            if (showLoading) MyLoading.instance.dismiss()
        }.collect {
            emit(it)
        }
    }

    private fun handleException(e: Throwable) {
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
    }

}