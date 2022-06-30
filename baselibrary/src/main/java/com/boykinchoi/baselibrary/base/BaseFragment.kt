package com.boykinchoi.baselibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * Fragment 基类
 * Created by BoykinChoi
 * on 2020/12/25
 **/
abstract class BaseFragment<V : BaseViewModel> : Fragment() {
    //和Activity共用同一个ViewModel,也可以viewModel = createViewModel()
    val viewModel: V? by lazy { (activity as BaseActivity<*>).viewModel as V }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewBinding = viewBind(inflater,container)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        initData()
        observeData()
    }

    protected fun createViewModel(): V? {
        // 反射获取model的真实类型
        val genericSuperclass = this.javaClass.genericSuperclass
        // kotlin 通过is判断类型后，若true,则后面会自动转为该类型
        if (genericSuperclass is ParameterizedType
            && genericSuperclass.actualTypeArguments.isNotEmpty()
        ) {
            // 获取第一个泛型参数
            val viewModelClass = genericSuperclass.actualTypeArguments[0] as Class<V>
//            //生成ViewModel实例,ps ViewModelProviders already Deprecated
//            return ViewModelProviders.of(this).get(viewModelClass)
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
//
//    @get:LayoutRes
//    abstract val layoutId: Int

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