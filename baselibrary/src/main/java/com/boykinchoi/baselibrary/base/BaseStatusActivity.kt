package com.boykinchoi.baselibrary.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.boykinchoi.baselibrary.R
import com.boykinchoi.baselibrary.util.ToastUtil
import com.github.nukc.stateview.StateView

/**
 * Activity带页面显示状态，加载数据
 * Created by BoykinChoi
 * on 2021/2/1
 **/
abstract class BaseStatusActivity<V : BaseViewModel> : BaseActivity<V>() {
    var stateView: StateView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStateView()
        initData()
        observeLoadState()
        //监听页面数据变化
        observeData()
    }

    private fun initStateView() {
        stateView = StateView.inject(stateRootView)
        stateView?.let {
            it.setEmptyResource(R.layout.layout_empty_data)
            it.setLoadingResource(R.layout.layout_loading)
            it.setRetryResource(R.layout.layout_load_retry)
            it.setOnRetryClickListener { initData() }
        }
    }

    /**
     * 监听viewModel页面加载状态变化
     */
    protected fun observeLoadState() {
        viewModel?.loadState?.observe(this, Observer {
            when (it) {
                is LoadState.Success -> stateView?.showContent()
                is LoadState.Fail -> {
                    stateView?.showRetry()
                    ToastUtil.l(it.msg)
                }
                is LoadState.Loading -> stateView?.showLoading()
            }
        })
    }

    abstract val stateRootView: View

    abstract fun initData()

    abstract fun observeData()

}