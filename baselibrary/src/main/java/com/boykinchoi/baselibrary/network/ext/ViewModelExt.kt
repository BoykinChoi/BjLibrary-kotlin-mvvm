package com.boykinchoi.baselibrary.network.ext

import androidx.lifecycle.viewModelScope
import com.boykinchoi.baselibrary.base.BaseViewModel
import com.boykinchoi.baselibrary.base.LoadState
import com.boykinchoi.baselibrary.network.StatusCode
import com.boykinchoi.baselibrary.network.bean.BaseBean
import com.boykinchoi.baselibrary.network.bean.BaseList
import com.boykinchoi.baselibrary.network.bean.JuHeBaseBean
import com.boykinchoi.baselibrary.network.exception.ApiException
import com.boykinchoi.baselibrary.network.exception.EmptyDataException
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

/**
 * BaseViewModel 扩展方法:
 * - fetchLaunch 创建协程进行网络请求,统一处理LoadState
 * Created by BoykinChoi
 * on 2021/1/30
 **/
fun BaseViewModel.fetchLaunch(
    /**
     * 异常回调，若需要具体请求错误处理时传入
     */
    onError: ((e: Throwable) -> Unit)? = null,
    /**
     * 完成回调
     */
    onComplete: () -> Unit = {},
    /**
     * 协程主体
     */
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, e ->
        // CoroutineExceptionHandler所有在协程中出现的错误都将回调这个方法
        val errorMsg = when (e) {
            is HttpException,
            is ConnectException,
            is UnknownHostException -> ExceptionMsg.ERROR_CONNECT
            is ConnectTimeoutException,
            is TimeoutException,
            is SocketTimeoutException -> ExceptionMsg.ERROR_TIME_OUT
            is JsonParseException,
            is JSONException,
            is ParseException -> ExceptionMsg.ERROR_DATA_DECODE
            is SSLHandshakeException,
            is CertPathValidatorException -> ExceptionMsg.ERROR_SSL_HANDSHAKE
            is IllegalArgumentException -> ExceptionMsg.ERROR_SERVICE_500
            else -> e.message
        }
        onError?.invoke(e)
        // 统一处理请求异常
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
 * BaseViewModel 扩展方法
 * - fetchLaunchBase 创建协程进行网络请求,统一处理LoadingDialog
 *
 **/
fun BaseViewModel.fetchLaunchBase(
    /**
     * 是否显示加载框
     */
    showLoading: Boolean = true,
    /**
     * 异常回调
     */
    onError: ((e: Throwable) -> Unit)? = null,
    /**
     * 完成回调
     */
    onComplete: (() -> Unit)? = null,
    /**
     * 协程主体
     */
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, e ->
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
            onComplete?.invoke()
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
 * BaseBean<BaseList> 扩展函数
 */
fun <T> BaseBean<BaseList<T>>.listDataConvert(): BaseList<T>? {
    if (code == StatusCode.SUCCESS) {
        if (data?.isEmptyList == true) {
            throw EmptyDataException("暂没数据哦")
        }
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

