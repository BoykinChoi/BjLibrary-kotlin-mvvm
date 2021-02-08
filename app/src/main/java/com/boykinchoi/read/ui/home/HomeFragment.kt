package com.boykinchoi.read.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.boykinchoi.baselibrary.base.BaseFragment
import com.boykinchoi.baselibrary.util.ToastUtil
import com.boykinchoi.read.R
import com.boykinchoi.read.ui.widget.GridItemDecoration
import com.mirkowu.basetoolbar.ScreenUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by BoykinChoi
 * on 2021/2/2
 **/
class HomeFragment : BaseFragment<HomeViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment {
            val homeFragment = HomeFragment()
            homeFragment.arguments = Bundle()
            return homeFragment
        }
    }

    private var bookAdapter: BookAdapter? = null
    override val layoutId: Int
        get() = R.layout.fragment_home

    override val stateRootView: View?
        get() = ll_root

    override fun initialize() {
        bookAdapter = BookAdapter()
        bookAdapter?.setOnItemClickListener { _, _, position ->
            val book = bookAdapter?.data?.get(position)
            ToastUtil.s("gan:${book?.bookChineseName}")
        }
        rvBook.adapter = bookAdapter
        rvBook.addItemDecoration(
                GridItemDecoration(3, ScreenUtil.dip2px(
                        activity, 15f), false
                )
        )
    }

    override fun initData() {
        //开始请求数据
        viewModel?.getBaseData()
    }

    override fun observeData() {
        observeHomeData()
    }

    private fun observeHomeData() {
        viewModel?.homeData?.observe(this, Observer {
            tv_username.text = it.userInfo?.studentName
            tv_username.setOnClickListener {
                viewModel?.checkVersion()
            }
            bookAdapter?.setNewInstance(it.bookList)
        })
    }

}