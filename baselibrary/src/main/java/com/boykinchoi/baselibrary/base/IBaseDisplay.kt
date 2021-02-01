package com.boykinchoi.baselibrary.base

import android.content.Context
import com.boykinchoi.baselibrary.network.ApiException
import com.trello.rxlifecycle2.LifecycleTransformer

/**
 * Created by BoykinChoi
 * on 2020/9/29
 **/
interface IBaseDisplay{
    fun getContext(): Context?
    fun getBaseActivity(): BaseActivity<*>?
    fun showLoading()
    fun showLoading(tips: CharSequence)
    fun dismissLoading()
    fun showError(t: Throwable)
    fun onRequestFinish(networkConnected: Boolean)
    fun onApiException(t: ApiException)
    fun <T> bindToLifecycle(): LifecycleTransformer<T>
}