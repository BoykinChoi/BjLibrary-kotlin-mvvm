package com.boykinchoi.baselibrary.base

import androidx.lifecycle.ViewModelProvider

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/10/28 15:48
 */
object MyViewModelProvider {

    /**
     * 通过Activity创建ViewModel
     */
    @JvmStatic
    inline fun <reified V : BaseViewModel> createViewModel(owenr: BaseActivity<V>): V {
        return ViewModelProvider(owenr).get(V::class.java)
    }

    /**
     * 通过Fragment创建ViewModel
     */
    @JvmStatic
    inline fun <reified V : BaseViewModel> createViewModel(owenr: BaseFragment<V>): V {
        return ViewModelProvider(owenr).get(V::class.java)
    }
}