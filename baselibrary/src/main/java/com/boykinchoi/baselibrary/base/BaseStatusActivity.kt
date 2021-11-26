package com.boykinchoi.baselibrary.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.boykinchoi.baselibrary.R
import com.boykinchoi.baselibrary.util.ToastUtil
import com.github.nukc.stateview.StateView

/**
 * Activity基类 带页面显示状态stateView，需要覆盖stateRootView属性
 * Created by BoykinChoi
 * on 2021/2/1
 **/
abstract class BaseStatusActivity<V : BaseViewModel> : BaseActivity<V>() {

    /**
     * 状态布局根View
     */
    open val stateRootView: View? = null

    /**
     * 状态View
     */
    private var stateView: StateView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStateView()
        initData()
        observeData()
    }

    private fun initStateView() {
        stateRootView?.let {
            stateView = StateView.inject(it).apply {
                setEmptyResource(R.layout.layout_empty_data)
                setLoadingResource(R.layout.layout_loading)
                setRetryResource(R.layout.layout_load_retry)
                setOnRetryClickListener { initData() }
            }
            observeLoadState()
        }
    }

    /**
     * 监听stateView展示状态
     */
    protected fun observeLoadState() {
        viewModel?.loadState?.observe(this, Observer {
            when (it) {
                is LoadState.Success -> stateView?.showContent()
                is LoadState.Fail -> {
                    stateView?.showRetry()
                    ToastUtil.l(it.msg)
                }
                is LoadState.EmptyData -> stateView?.showEmpty()
                is LoadState.Loading -> stateView?.showLoading()
                else -> {
                }
            }
        })
    }

    abstract fun initData()

    abstract fun observeData()

}