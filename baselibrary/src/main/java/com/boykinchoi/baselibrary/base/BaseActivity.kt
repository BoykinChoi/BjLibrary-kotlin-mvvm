package com.boykinchoi.baselibrary.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.boykinchoi.baselibrary.R
import com.boykinchoi.baselibrary.util.ToastUtil
import com.github.nukc.stateview.StateView
import com.gyf.immersionbar.ImmersionBar
import java.lang.reflect.ParameterizedType

/**
 * Activity基类 MVVM 实现，加载数据,动态创建ViewModel
 * Created by BoykinChoi
 * on 2020/9/29
 **/
abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity() {
    var baseDelegate: BaseDelegate? = null
    var stateView: StateView? = null
    var viewModel: V? = null
    var needStateView: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        baseDelegate = BaseDelegate(this)
        baseDelegate?.onCreate(savedInstanceState)
        ImmersionBar.with(this)
                //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
                .autoStatusBarDarkModeEnable(true, 0.2f)
                .statusBarDarkFont(true, 0.2f)
                .init()
        viewModel = createViewModel()
        initialize()
        initStateView()
        initData()
        observeLoadState()
    }

    private fun initStateView() {
        stateView = StateView.inject(stateRootView)
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
                is LoadState.Fail -> {
                    stateView?.showRetry()
                    ToastUtil.l(it.msg)
                }
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

    //抽象属性
    @get:LayoutRes
    abstract val layoutRes: Int

    abstract val stateRootView: View

    abstract fun initialize()

    abstract fun observeBaseData()

}