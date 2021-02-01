package com.boykinchoi.baselibrary.network

import io.reactivex.Observer

/**
 * Created by BoykinChoi
 * on 2020/12/4
 **/

interface Callback<T> : Observer<T> {
    /*** 请求网络开始前，UI线程  */
    fun onStart()

    /*** 对返回数据进行操作的回调， UI线程  */
    fun onSuccess(data: T?)


    /*** 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程  */
    override fun onError(throwable: Throwable)

    /*** API的异常信息，UI线程  */
    fun onApiException(e: ApiException)

    /*** 请求网络结束后，UI线程  */
    fun onFinish()
}