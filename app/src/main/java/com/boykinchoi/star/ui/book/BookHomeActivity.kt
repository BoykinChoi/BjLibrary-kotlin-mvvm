package com.boykinchoi.star.ui.book

import androidx.viewbinding.ViewBinding
import com.boykinchoi.baselibrary.base.BaseActivity2
import com.boykinchoi.baselibrary.base.vm.MyViewModelProvider
import com.boykinchoi.baselibrary.util.ToastUtil
import com.boykinchoi.baselibrary.widget.decoration.GridItemDecoration
import com.boykinchoi.star.bean.BookBean
import com.boykinchoi.star.databinding.ActivityHomeBinding
import com.boykinchoi.star.ui.home.HomeViewModel
import com.mirkowu.basetoolbar.ScreenUtil

/**
 * Created by BoykinChoi
 * on 2021/1/30
 **/
class BookHomeActivity : BaseActivity2() {
    private val bookAdapter: BookAdapter2? by lazy { BookAdapter2() }

    private lateinit var viewBinding: ActivityHomeBinding

    private val viewModel by lazy { MyViewModelProvider.create<BookViewModel>(this) }

//    override val stateRootView: View
//        get() = viewBinding.llRoot

    override fun bindView(): ViewBinding {
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        return viewBinding
    }

    override fun initialize() {
        bookAdapter?.setOnItemClickListener { _, _, position ->
            val book = bookAdapter?.data?.get(position)
            ToastUtil.s("gan:${book?.bookChineseName}")
        }
        viewBinding.run {
            rvBook.adapter = bookAdapter
            rvBook.addItemDecoration(
                GridItemDecoration(
                    3, ScreenUtil.dip2px(
                        this@BookHomeActivity, 15f
                    ), false
                )
            )
            tvName.setOnClickListener {
                viewModel.checkVersion()
            }
        }

    }

    override fun initData() {
        viewModel.getBaseData()
    }

    override fun observeData() {
        observeHomeData()
        observeVersionData()
    }

    private fun observeHomeData() {
        viewModel.homeData.observe(this, {
            viewBinding.tvName.text = it.user?.studentName
            bookAdapter?.setNewInstance(it.bookList)
            viewBinding.rvBook.scrollToPosition(0)
        })
    }

    private fun observeVersionData() {
        viewModel.versionData.observe(this, {
            ToastUtil.s("发现版本${it.versionName}")
        })
    }

}