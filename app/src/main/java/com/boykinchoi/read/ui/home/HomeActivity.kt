package com.boykinchoi.read.ui.home

import android.content.SharedPreferences
import android.view.View
import androidx.lifecycle.Observer
import com.boykinchoi.baselibrary.base.BaseStatusActivity
import com.boykinchoi.baselibrary.util.ToastUtil
import com.boykinchoi.read.R
import com.boykinchoi.read.ui.widget.GridItemDecoration
import com.mirkowu.basetoolbar.ScreenUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by BoykinChoi
 * on 2021/1/30
 **/
class HomeActivity : BaseStatusActivity<HomeViewModel>() {
    private var bookAdapter: BookAdapter? = null

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override val stateRootView: View
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
                        this, 15f), false
                )
        )
    }

    override fun initData() {
        //开始请求数据
        viewModel?.checkVersion()
    }

    override fun observeData() {
        observeHomeData()
        observeVersionData()
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

    private fun observeVersionData() {
        viewModel?.versionData?.observe(this, Observer {
            ToastUtil.s(it.versionName)
        })
    }

}