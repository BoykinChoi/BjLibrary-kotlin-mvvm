package com.boykinchoi.baselibrary.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.boykinchoi.baselibrary.R
import com.boykinchoi.baselibrary.base.vm.BaseViewModel
import com.boykinchoi.baselibrary.util.ToastUtil
import com.github.nukc.stateview.StateView
import java.lang.reflect.ParameterizedType

/**
 * Activity基类,动态创建ViewModel
 * Created by BoykinChoi
 * on 2021/1/29
 **/
abstract class BaseActivity<M : BaseViewModel> : AppCompatActivity() {
    private val baseDelegate: BaseDelegate? by lazy { BaseDelegate(this) }
    var viewModel: M? = null

    /**
     * 状态布局根View，需要使用状态布局时重写
     */
    open val stateRootView: View? = null

    /**
     * 状态View
     */
    private var stateView: StateView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindView().root)
        baseDelegate?.onCreate(savedInstanceState)
        viewModel = createViewModel().apply {
            (this as BaseViewModel).showLoading.observe(this@BaseActivity, {
                if (it) showLoadingDialog() else dismissLoadingDialog()
            })
        }
        initialize()
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
        } ?: throw Exception("use StatusActivity must be inject state root view")
    }

    /**
     * 监听stateView展示状态
     */
    protected fun observeLoadState() {
        viewModel?.loadState?.observe(this) {
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
        }
    }


    /**
     * 根据泛型V 创建ViewModel实例
     *
     * @return
     */
    private fun createViewModel(): M? {
        // 反射获取model的真实类型
        val genericSuperclass = this.javaClass.genericSuperclass
        // kotlin 通过is判断类型后，若true,则后面会自动转为该类型
        if (genericSuperclass is ParameterizedType
            && genericSuperclass.actualTypeArguments.isNotEmpty()
        ) {
            // 获取第一个泛型参数类型
            val viewModelClass = genericSuperclass.actualTypeArguments[0] as Class<M>
//            //生成ViewModel实例,ps ViewModelProviders already Deprecated
            return ViewModelProvider(this).get(viewModelClass)
//            return MyViewModelProvider.createViewModel(this)
        }
        return null
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

    //抽象属性
//    @get:LayoutRes
//    abstract val layoutRes: Int

    abstract fun bindView(): ViewBinding

    abstract fun initialize()

    abstract fun initData()

    abstract fun observeData()
}