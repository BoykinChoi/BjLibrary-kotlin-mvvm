package com.boykinchoi.baselibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.boykinchoi.baselibrary.R
import com.github.nukc.stateview.StateView
import com.trello.rxlifecycle2.components.RxFragment
import java.lang.reflect.ParameterizedType

/**
 * Fragment 基类
 * Created by BoykinChoi
 * on 2020/12/25
 **/
abstract class BaseFragment<V: BaseViewModel> : Fragment(){
    var activity: AppCompatActivity? = null
    var viewModel: V? = null
    var stateView: StateView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        initStateView()
        initData()
        observeLoadState()
    }

    private fun initStateView() {
        initStateViewRoot()
        stateView?.setEmptyResource(R.layout.layout_empty_data)
        stateView?.setLoadingResource(R.layout.layout_loading)
        stateView?.setRetryResource(R.layout.layout_load_retry)
        stateView?.setOnRetryClickListener { initData() }
    }

    private fun initData() {
        //开始请求数据
        viewModel?.getBaseData()
        //监听页面基础数据变化
        observeBaseData()
    }

    /**
     * 监听viewModel加载状态变化
     */
    protected fun observeLoadState() {
        viewModel?.loadState?.observe(this, Observer {
            when (it) {
                is LoadState.Success -> stateView?.showContent()
                is LoadState.Fail -> stateView?.showRetry()
                is LoadState.Loading -> stateView?.showLoading()
            }
        })
    }

    /**
     * 根据泛型V 创建ViewModel实例 此处已重写，需要时可重写
     *
     * @return
     */
    protected fun createViewModel(): V? {
        if (this.javaClass.genericSuperclass is ParameterizedType
                && (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.isNotEmpty()
        ) {
            //获取ViewModel的class
            val viewModelClass =
                    (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<V>
            //生成ViewModel实例
            return ViewModelProviders.of(this).get(viewModelClass)
        }
        return null
    }

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun initialize()

    abstract fun initStateViewRoot()

    abstract fun observeBaseData()


}