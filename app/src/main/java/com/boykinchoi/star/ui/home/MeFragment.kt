package com.boykinchoi.star.ui.home

import android.os.Bundle
import com.boykinchoi.baselibrary.base.BaseFragment
import com.boykinchoi.star.R
import com.boykinchoi.star.coroutine.CoroutineTest

/**
 * Created by BoykinChoi
 * on 2021/2/2
 **/
class MeFragment : BaseFragment<HomeJuHeViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(): MeFragment {
            val meFragment = MeFragment()
            meFragment.arguments = Bundle()
            return meFragment
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_me

//    override val stateRootView: View?
//        get() = ll_class_root

    override fun initialize() {

    }

    override fun initData() {
        val coroutineTest = CoroutineTest()
        coroutineTest.testCoroutineContext()
    }

    override fun observeData() {
//        viewModel?.versionData?.observe(this, Observer {
//            tv_info.text = "app version:${it.versionName} app no:${it.versionNo} desc:${it.updateContent}"
//        })
    }

}