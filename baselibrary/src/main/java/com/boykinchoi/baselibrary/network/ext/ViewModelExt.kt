package com.boykinchoi.baselibrary.network.ext

import androidx.lifecycle.viewModelScope
import com.boykinchoi.baselibrary.base.BaseViewModel
import com.boykinchoi.baselibrary.base.LoadState
import com.boykinchoi.baselibrary.network.BaseBean
import com.boykinchoi.baselibrary.network.JuHeBaseBean
import com.boykinchoi.baselibrary.network.StatusCode
import com.boykinchoi.baselibrary.network.exception.ApiException
import com.boykinchoi.baselibrary.network.exception.ExceptionMsg
import com.google.gson.JsonParseException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.security.cert.CertPathValidatorException
import java.text.ParseException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

/** BaseViewModel 扩展方法，launch 创建协程进行网络请求,统一处理LoadState
 * Created by BoykinChoi
 * on 2021/1/30
 **/
fun BaseViewModel.launch(
    block: suspend CoroutineScope.() -> Unit, //协程主体
    onError: ((e: Throwable) -> Unit)? = null, //错误回调
    onComplete: () -> Unit = {} //完成回调
) {
    //CoroutineExceptionHandler所有在协程中出现的错误都将回调这个方法
    viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        val errorMsg =
            if (e is HttpException || e is ConnectException || e is UnknownHostException) {
                ExceptionMsg.ERROR_CONNECT
            } else if (e is ConnectTimeoutException || e is TimeoutException || e is SocketTimeoutException) {
                ExceptionMsg.ERROR_TIME_OUT
            } else if (e is JsonParseException || e is JSONException || e is ParseException) {
                ExceptionMsg.ERROR_DATA_DECODE
            } else if (e is SSLHandshakeException || e is CertPathValidatorException) {
                ExceptionMsg.ERROR_SSL_HANDSHAKE
            } else if (e is IllegalArgumentException) {
                ExceptionMsg.ERROR_SERVICE_500
            } else {
                e.message
            }
        //若需要具体请求错误处理，传入onError(e)
        onError?.invoke(e)
        //统一处理请求异常
        loadState.value = LoadState.Fail(msg = errorMsg)
    }) {
        try {
            loadState.value = LoadState.Loading()
            block.invoke(this)
        } finally {
            loadState.value = LoadState.Success()
            onComplete.invoke()
        }
    }
}

/**
 * BaseViewModel 扩展方法，launch创建协程进行网络请求,统一处理加载框
 **/
fun BaseViewModel.launchBase(
    showLoading: Boolean = true,
    block: suspend CoroutineScope.() -> Unit,
    onError: ((e: Throwable) -> Unit)? = null,
    onComplete: () -> Unit = {}
) {
    viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        onError?.invoke(e)
        if (showLoading) {
            showLoadingDialog.value = false
        }
    }) {
        try {
            if (showLoading) {
                showLoadingDialog.value = true
            }
            block.invoke(this)
        } finally {
            if (showLoading) {
                showLoadingDialog.value = false
            }
            onComplete()
        }
    }
}

/**
 * BaseBean扩展函数
 * 请求成功返回真正需要的数据实体类T，否则直接抛出异常让CoroutineExceptionHandler在协程里处理
 **/
fun <T> BaseBean<T>.dataConvert(): T? {
    if (code == StatusCode.SUCCESS) {
        return data
    } else throw ApiException(message ?: "api exception")
}

/**
 * 聚合接合，提取请求成功后真正需要的数据实际类T
 */
fun <T> JuHeBaseBean<T>.juHeDataConvert(): T? {
    if (reason == "success") {
        return result
    } else throw ApiException(reason)
}

