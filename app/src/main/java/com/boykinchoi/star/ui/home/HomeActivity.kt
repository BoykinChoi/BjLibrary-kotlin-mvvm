package com.boykinchoi.star.ui.home

import android.view.View
import androidx.lifecycle.Observer
import com.boykinchoi.baselibrary.base.BaseStatusActivity
import com.boykinchoi.baselibrary.util.ToastUtil
import com.boykinchoi.star.R
import com.boykinchoi.baselibrary.widget.decoration.GridItemDecoration
import com.boykinchoi.star.bean.Boss
import com.boykinchoi.star.bean.BossImpl
import com.boykinchoi.star.bean.StaffImpl
import com.mirkowu.basetoolbar.ScreenUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by BoykinChoi
 * on 2021/1/30
 **/
class HomeActivity : BaseStatusActivity<HomeViewModel>() {
    private val bookAdapter: BookAdapter? by lazy { BookAdapter() }

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override val stateRootView: View
        get() = ll_root

    override fun initialize() {
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
            tv_today.text = it.userInfo?.studentName
            tv_today.setOnClickListener {
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