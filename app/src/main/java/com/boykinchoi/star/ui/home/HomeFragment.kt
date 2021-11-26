package com.boykinchoi.star.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.boykinchoi.baselibrary.base.BaseFragment
import com.boykinchoi.baselibrary.base.BaseStatusFragment
import com.boykinchoi.baselibrary.widget.decoration.ColorItemDecoration
import com.boykinchoi.star.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.time.days

/**
 * Created by BoykinChoi
 * on 2021/2/2
 **/
class HomeFragment : BaseStatusFragment<HomeJuHeViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment {
            val homeFragment = HomeFragment()
            homeFragment.arguments = Bundle()
            return homeFragment
        }
    }

    private val bookAdapter: BookAdapter? by lazy { BookAdapter() }
    private val historyToadyAdapter: HistoryTodayAdapter? by lazy { HistoryTodayAdapter() }
    override val layoutId: Int
        get() = R.layout.fragment_home

    override val stateRootView: View?
        get() = ll_root

    override fun initialize() {
//        bookAdapter?.setOnItemClickListener { _, _, position ->
//            val book = bookAdapter?.data?.get(position)
//            ToastUtil.s("gan:${book?.bookChineseName}")
//        }
//        rvBook.adapter = bookAdapter
//        rvBook.addItemDecoration(
//                GridItemDecoration(3, ScreenUtil.dip2px(
//                        activity, 15f), false
//                )
//        )
        rvBook.adapter = historyToadyAdapter
        rvBook.addItemDecoration(ColorItemDecoration())
    }

    override fun initData() {
        //获取今天日期
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        //系统日期月份从0开始，需加1
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val today = "${year}年${month}月${day}日"
        tv_today.text = today
        val date = "${month}/${day}"
//        val date = "3/32"
        viewModel?.getHistoryToadyData(date)
    }

    override fun observeData() {
//        observeHomeData()
        observeJuHeData()
    }

    private fun observeHomeData() {
//        viewModel?.homeData?.observe(this, Observer {
//            tv_username.text = it.userInfo?.studentName
//            tv_username.setOnClickListener {
//                viewModel?.checkVersion()
//            }
//            bookAdapter?.setNewInstance(it.bookList)
//        })
    }

    private fun observeJuHeData() {
        viewModel?.historyTodayData?.observe(this, Observer {
            historyToadyAdapter?.setNewInstance(it)
        })

    }

}