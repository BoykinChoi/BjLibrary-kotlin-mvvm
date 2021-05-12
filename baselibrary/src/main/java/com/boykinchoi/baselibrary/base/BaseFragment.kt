package com.boykinchoi.baselibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.boykinchoi.baselibrary.R
import com.boykinchoi.baselibrary.util.ToastUtil
import com.github.nukc.stateview.StateView
import java.lang.reflect.ParameterizedType

/**
 * Fragment 基类
 * Created by BoykinChoi
 * on 2020/12/25
 **/
abstract class BaseFragment<V : BaseViewModel> : Fragment() {
    //和Activity共用同一个ViewModel,也可以viewModel = createViewModel()
    val viewModel: V? by lazy {  (activity as BaseActivity<*>).viewModel as V }
    private var stateView: StateView? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStateView()
        initialize()
        initData()
        observeLoadState()
        //监听页面数据变化
        observeData()
    }

    private fun initStateView() {
        stateView = StateView.inject(stateRootView!!)
        stateView?.let {
            it.setEmptyResource(R.layout.layout_empty_data)
            it.setLoadingResource(R.layout.layout_loading)
            it.setRetryResource(R.layout.layout_load_retry)
            it.setOnRetryClickListener { initData() }
        }
    }

    /**
     * 监听viewModel加载状态变化
     */
    private fun observeLoadState() {
        //监听stateView展示状态
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
        //监听加载框显示/隐藏
        viewModel?.showLoadingDialog?.observe(this, Observer {
            if (it) showLoadingDialog() else dismissLoadingDialog()
        })
    }

    protected fun createViewModel(): V? {
        val genericSuperclass = this.javaClass.genericSuperclass
        //kotlin 通过is判断类型后，若true,则后面会自动转为该类型
        if (genericSuperclass is ParameterizedType
                && (genericSuperclass).actualTypeArguments.isNotEmpty()) {
            //获取ViewModel的class
            val viewModelClass = (genericSuperclass).actualTypeArguments[0] as Class<V>
            //生成ViewModel实例,ps ViewModelProviders already Deprecated
            //return ViewModelProviders.of(this).get(viewModelClass)
            return ViewModelProvider(this).get(viewModelClass)
        }
        return null
    }


    fun showLoadingDialog() {
        (activity as BaseActivity<*>).showLoadingDialog()
    }

    fun dismissLoadingDialog() {
        (activity as BaseActivity<*>).dismissLoadingDialog()
    }

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val stateRootView: View?

    abstract fun initialize()

    abstract fun initData()

    abstract fun observeData()


}