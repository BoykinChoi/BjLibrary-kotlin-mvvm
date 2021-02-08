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
    var baseDelegate: BaseDelegate? = null
    var viewModel: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        baseDelegate = BaseDelegate(this)
        baseDelegate?.onCreate(savedInstanceState)
        viewModel = createViewModel()
        initialize()
        observeLoadingDialogState()
    }

    /**
     * 监听加载框显示/隐藏
     */
    protected fun observeLoadingDialogState() {
        viewModel?.showLoadingDialog?.observe(this, Observer {
            if (it) showLoadingDialog() else dismissLoadingDialog()
        })
    }

    /**
     * 根据泛型V 创建ViewModel实例 此处已重写，需要时可重写
     *
     * @return
     */
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