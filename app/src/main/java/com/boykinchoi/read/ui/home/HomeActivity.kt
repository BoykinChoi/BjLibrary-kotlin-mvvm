package com.boykinchoi.read.ui.home

import android.view.View
import androidx.lifecycle.Observer
import com.boykinchoi.baselibrary.base.BaseActivity
import com.boykinchoi.baselibrary.util.ToastUtil
import com.boykinchoi.read.R
import com.boykinchoi.read.bean.HomeDataBean
import com.boykinchoi.read.ui.widget.GridItemDecoration
import com.mirkowu.basetoolbar.ScreenUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by BoykinChoi
 * on 2021/1/30
 **/
class HomeActivity : BaseActivity<HomeViewModel>() {
    var bookAdapter: BookAdapter? = null

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override val stateRootView: View
        get() = ll_content

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

    override fun observeBaseData() {
        viewModel?.homeData?.observe(this, Observer {
            updateUI(it)
        })
    }

    private fun updateUI(homeData: HomeDataBean) {
        tv_username.text = homeData.userInfo?.studentName
        tv_username.setOnClickListener {
            ToastUtil.l("id=${homeData.userInfo?.accountId}")
        }
        bookAdapter?.setNewInstance(homeData.bookList)
    }

}