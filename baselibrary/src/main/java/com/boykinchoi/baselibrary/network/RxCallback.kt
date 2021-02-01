package com.boykinchoi.baselibrary.network

import com.boykinchoi.baselibrary.base.IBaseDisplay
import com.boykinchoi.baselibrary.util.ToastUtil
import io.reactivex.disposables.Disposable

/**
 * 可直接订阅RxJava 的 Observer 继承类  已实现功能有：
 * 1.对应请求流程，可进行相应的操作
 * 2.捕捉异常，并进行后续操作
 * 3.解决RxJava2不能发生null的问题，当数据传回了Null 则主动进行捕捉并处理
 */
abstract class RxCallback<T> : Callback<T> {
    private val view: IBaseDisplay? = null

    // public RxCallback(IBaseDisplay view) {
    // this.view = view;
    // }
    override fun onSubscribe(d: Disposable) {
        onStart()
    }

    override fun onStart() {}
    override fun onNext(t: T) {
        onSuccess(t)
        onFinish()
    }

    override fun onError(t: Throwable) {
        //RxJava2不能发送null
        when (t) {
            is RxJava2NullException -> onSuccess(null)
            is ApiException -> {
                //通用的Api异常处理
                onApiException(t)
            }
            else -> {
                ToastUtil.s(t?.message)
                //t.printStackTrace();
            }
        }
    }

    override fun onApiException(e: ApiException) {
        //默认 需要根据业务逻辑 处理了
        //ToastUtil.s(e.getMessage())
        //e.printStackTrace()
    }

    override fun onComplete() {
        onFinish()
    }

    override fun onFinish() {
    }
}