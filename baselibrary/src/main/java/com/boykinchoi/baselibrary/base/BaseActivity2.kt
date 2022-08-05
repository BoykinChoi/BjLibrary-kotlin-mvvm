package com.boykinchoi.baselibrary.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.boykinchoi.baselibrary.R
import com.boykinchoi.baselibrary.base.BaseDelegate
import com.boykinchoi.baselibrary.base.vm.BaseViewModel
import com.boykinchoi.baselibrary.util.ToastUtil
import com.github.nukc.stateview.StateView
import java.lang.reflect.ParameterizedType

/**
 * Activity基类第2版
 * Created by BoykinChoi
 * on 2022/7/21
 **/
abstract class BaseActivity2 : AppCompatActivity() {
    private val baseDelegate: BaseDelegate? by lazy { BaseDelegate(this) }

    /**
     * 状态View
     */
    var stateView: StateView? = null

    /**
     * 状态布局根View，需要使用状态布局时重写
     */
    open val stateRootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindView().root)
        baseDelegate?.onCreate(savedInstanceState)
        initStateView()
        initialize()
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
        }
    }

    fun showLoadingDialog() {
        baseDelegate?.showLoading()
    }

    fun dismissLoadingDialog() {
        baseDelegate?.dismissLoading()
    }

    fun reload() {
        baseDelegate?.reload()
    }

    abstract fun bindView(): ViewBinding

    abstract fun initialize()

    abstract fun initData()

    abstract fun observeData()
}