package com.boykinchoi.baselibrary.network

import com.boykinchoi.baselibrary.base.IBaseDisplay
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * RxJava2 转换器 用于网络加载数据 已实现功能有：
 * 1.检测有无网络
 * 2.加载网络时显示加载框 结束是隐藏
 * 3.控制RxJava生命周期，防止内存泄漏
 * Created by BoykinChoi
 * on 2020/12/3
 **/
class NetworkTransformer<T> constructor(
    private val view: IBaseDisplay?,
    private val showLoading: Boolean = true
) :
    ObservableTransformer<BaseBean<T>, T> {
    init {
        if (view == null) {
            throw RuntimeException("IBaseDisplay is not NULL")
        }
    }

    override fun apply(upstream: Observable<BaseBean<T>>): ObservableSource<T> {
        return upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
               if (showLoading) {
                    view?.showLoading()
                }
            }
            .doFinally {
                if (showLoading) {
                    view?.dismissLoading()
                }
                view?.onRequestFinish(true)
            }
            .map(filterData())
            .map(extractInnerData())
            .doOnError { error ->
                //RxJava2NullException 交给RxCallback处理
                if (error !is RxJava2NullException) {
                    view?.showError(error)
                }
            }
            .compose(view?.bindToLifecycle())
    }

    /**
     * 过滤异常
     */
    private fun filterData(): Function<in BaseBean<T>, BaseBean<T>> {
        val filterFunction: Function<BaseBean<T>, BaseBean<T>>?
        filterFunction = Function {
            if (StatusCode.SUCCESS == it.code) {
                return@Function it
            } else {
                throw ApiException(it.code, it.message)
            }
        }
        return filterFunction
    }

    /**
     * 提取BaseBean<T> 内真正需要的数据
     */
    private fun extractInnerData(): Function<in BaseBean<T>, T> {
        return Function {
            if (it.data != null) {
                it.data
            } else {
                throw RxJava2NullException()
            }
        }
    }
}