package com.boykinchoi.baselibrary.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * Activity基类,动态创建ViewModel
 * Created by BoykinChoi
 * on 2021/1/29
 **/
abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity() {
    private val baseDelegate: BaseDelegate? by lazy { BaseDelegate(this) }
    var viewModel: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        baseDelegate?.onCreate(savedInstanceState)
        viewModel = createViewModel()
        initialize()
        observeLoadingDialogState()
    }

    /**
     * 监听加载框显示/隐藏
     */
    private fun observeLoadingDialogState() {
        viewModel?.showLoadingDialog?.observe(this, Observer {
            if (it) showLoadingDialog() else dismissLoadingDialog()
        })
    }

    /**
     * 根据泛型V 创建ViewModel实例
     *
     * @return
     */
    private fun createViewModel(): V? {
        // 反射获取model的真实类型
        val genericSuperclass = this.javaClass.genericSuperclass
        // kotlin 通过is判断类型后，若true,则后面会自动转为该类型
        if (genericSuperclass is ParameterizedType
            && (genericSuperclass).actualTypeArguments.isNotEmpty()
        ) {
            // 获取第一个泛型参数
            val viewModelClass = (genericSuperclass).actualTypeArguments[0] as Class<V>
//            //生成ViewModel实例,ps ViewModelProviders already Deprecated
//            return ViewModelProviders.of(this).get(viewModelClass)
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
    @get:LayoutRes
    abstract val layoutRes: Int

    abstract fun initialize()

}