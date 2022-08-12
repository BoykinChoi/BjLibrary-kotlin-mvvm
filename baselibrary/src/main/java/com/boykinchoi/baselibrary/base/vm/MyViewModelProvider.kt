package com.boykinchoi.baselibrary.base.vm

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.boykinchoi.baselibrary.base.*
import com.boykinchoi.baselibrary.util.ToastUtil

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
    inline fun <reified V : BaseViewModel> create(owenr: BaseActivity2): V {
        return ViewModelProvider(owenr, ViewModelProvider.NewInstanceFactory()).get(V::class.java)
            .apply {
                application = owenr.application
                showLoading.observe(owenr) {
                    if (it) owenr.showLoadingDialog() else owenr.dismissLoadingDialog()
                }
                loadState.observe(owenr) {
                    when (it) {
                        is LoadState.Success -> owenr.stateView?.showContent()
                        is LoadState.Fail -> {
                            owenr.stateView?.showRetry()
                            ToastUtil.l(it.msg)
                        }
                        is LoadState.EmptyData -> owenr.stateView?.showEmpty()
                        is LoadState.Loading -> owenr.stateView?.showLoading()
                        else -> {
                        }
                    }
                }
            }
    }

    /**
     * 通过Fragment创建ViewModel
     */
    @JvmStatic
    inline fun <reified V : BaseViewModel> create(owenr: BaseFragment2): V {
        return ViewModelProvider(owenr, ViewModelProvider.NewInstanceFactory()).get(V::class.java)
            .apply {
                application = owenr.requireActivity().application
                showLoading.observe(owenr) {
                    if (it) owenr.showLoadingDialog() else owenr.dismissLoadingDialog()
                }
                loadState.observe(owenr) {
                    when (it) {
                        is LoadState.Success -> owenr.stateView?.showContent()
                        is LoadState.Fail -> {
                            owenr.stateView?.showRetry()
                            owenr.showToast(it.msg)
                        }
                        is LoadState.EmptyData -> owenr.stateView?.showEmpty()
                        is LoadState.Loading -> owenr.stateView?.showLoading()
                        else -> {
                        }
                    }
                }
            }
    }
}