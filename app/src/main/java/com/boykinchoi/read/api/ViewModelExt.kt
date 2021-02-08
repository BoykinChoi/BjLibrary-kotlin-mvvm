package com.boykinchoi.read.api

import androidx.lifecycle.viewModelScope
import com.boykinchoi.baselibrary.base.BaseViewModel
import com.boykinchoi.baselibrary.base.LoadState
import com.boykinchoi.baselibrary.network.ApiException
import com.boykinchoi.baselibrary.network.BaseBean
import com.boykinchoi.baselibrary.network.StatusCode
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/** BaseViewModel 扩展方法，launch 创建协程,统一处理LoadState
 * Created by BoykinChoi
 * on 2021/1/30
 **/
fun BaseViewModel.launch(
        block: suspend CoroutineScope.() -> Unit, //协程主体
        onError: (e: Throwable) -> Unit = {}, //错误回调
        onComplete: () -> Unit = {} //完成回调
) {
    //CoroutineExceptionHandler所有在协程中出现的错误都将回调这个方法
    viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        onError(e)
        loadState.value = LoadState.Fail(e.message ?: "http exception")
    }) {
        try {
            loadState.value = LoadState.Loading()
            block.invoke(this)
            loadState.value = LoadState.Success()
        } finally {
            onComplete()
        }
    }
}

/**
 * BaseViewModel 扩展方法，launch 创建协程,统一处理加载框
 **/
fun BaseViewModel.launchBase(
        showLoading: Boolean = true,
        block: suspend CoroutineScope.() -> Unit,
        onError: (e: Throwable) -> Unit = {},
        onComplete: () -> Unit = {}
) {
    viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        onError(e)
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

