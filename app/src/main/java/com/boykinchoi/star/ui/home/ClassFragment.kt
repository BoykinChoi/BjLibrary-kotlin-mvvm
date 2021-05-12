package com.boykinchoi.star.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.boykinchoi.baselibrary.base.BaseFragment
import com.boykinchoi.baselibrary.util.ToastUtil
import com.boykinchoi.star.R
import kotlinx.android.synthetic.main.fragment_class.*

/**
 * Created by BoykinChoi
 * on 2021/2/2
 **/
class ClassFragment : BaseFragment<HomeViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(): ClassFragment {
            val classFragment = ClassFragment()
            classFragment.arguments = Bundle()
            return classFragment
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_class

    override val stateRootView: View?
        get() = ll_class_root

    override fun initialize() {
        tv_info.setOnClickListener { ToastUtil.s(viewModel?.homeData?.value?.bookList?.get(0)?.bookChineseName) }
    }

    override fun initData() {
        //开始请求数据
        viewModel?.checkVersion()
    }

    override fun observeData() {
        viewModel?.versionData?.observe(this, Observer {
            tv_info.text = "app version:${it.versionName} app no:${it.versionNo} desc:${it.updateContent}"
        })
    }

}