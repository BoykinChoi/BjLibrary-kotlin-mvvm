package com.boykinchoi.baselibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.boykinchoi.baselibrary.R
import com.boykinchoi.baselibrary.util.ToastUtil
import com.github.nukc.stateview.StateView

/**
 * Fragment 基类
 * Created by BoykinChoi
 * on 2022/7/21
 **/
abstract class BaseFragment2 : Fragment() {

    /**
     * 状态布局根View,不重写则不使用状态View
     */
    open val stateRootView: View? = null

    /**
     * 状态View
     */
    var stateView: StateView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewBinding = viewBind(inflater, container)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        (activity as BaseActivity<*>).showLoadingDialog()
    }

    fun dismissLoadingDialog() {
        (activity as BaseActivity<*>).dismissLoadingDialog()
    }

    fun showToast(toast:String?){
        ToastUtil.l(toast)
    }


    /**
     * ViewBinding
     */
    abstract fun viewBind(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean = false
    ): ViewBinding

    /**
     * 初始化
     */
    abstract fun initialize()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 数据变化监听
     */
    abstract fun observeData()


}