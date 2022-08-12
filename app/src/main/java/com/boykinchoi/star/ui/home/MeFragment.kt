package com.boykinchoi.star.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.boykinchoi.baselibrary.base.BaseFragment
import com.boykinchoi.baselibrary.ext.start
import com.boykinchoi.star.coroutine.CoroutineTest
import com.boykinchoi.star.databinding.FragmentMeBinding
import com.boykinchoi.star.flow.FlowTest
import com.boykinchoi.star.lifecycle.LifecycleActivity
import com.boykinchoi.star.ui.book.BookHomeActivity
import com.boykinchoi.star.ui.tv.TvActivity
import kotlinx.android.synthetic.main.fragment_me.*

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

    override fun viewBind(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): ViewBinding = FragmentMeBinding.inflate(inflater, container, attachToRoot)

    override fun initialize() {
        tv_home.setOnClickListener {
            activity?.start<BookHomeActivity>()
        }
        tv_tv.setOnClickListener {
            activity?.start<TvActivity>()
        }
        tv_lifecycle.setOnClickListener {
            activity?.start<LifecycleActivity>()
        }
        FlowTest().test2()

        val strings = arrayListOf("a", "b", "c")
        val strings2 = arrayListOf(1, 2, 3)
        printSum(strings2)
    }

    private fun printSum(c: Collection<*>) {
        val list = c as? List<Int> ?: throw IllegalArgumentException("期望是List")
        Log.i("aaaaaaaaaaaaa","size=${list.size}")
    }

    override fun initData() {
        val coroutineTest = CoroutineTest()
    }

    override fun observeData() {
//        viewModel?.versionData?.observe(this, Observer {
//            tv_info.text = "app version:${it.versionName} app no:${it.versionNo} desc:${it.updateContent}"
//        })
    }

}