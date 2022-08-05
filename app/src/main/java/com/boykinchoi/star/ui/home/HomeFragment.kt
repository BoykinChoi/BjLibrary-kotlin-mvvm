package com.boykinchoi.star.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.boykinchoi.baselibrary.base.BaseFragment2
import com.boykinchoi.baselibrary.base.vm.MyViewModelProvider
import com.boykinchoi.baselibrary.widget.decoration.ColorItemDecoration
import com.boykinchoi.star.coroutine.CoroutineTest
import com.boykinchoi.star.databinding.FragmentHomeBinding
import java.util.*

/**
 * Created by BoykinChoi
 * on 2021/2/2
 **/
class HomeFragment : BaseFragment2() {

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment {
            val homeFragment = HomeFragment()
            homeFragment.arguments = Bundle()
            return homeFragment
        }
    }

    private val viewModel by lazy { MyViewModelProvider.create<HomeJuHeViewModel>(this) }

    private lateinit var viewBinding: FragmentHomeBinding

    private val historyToadyAdapter: HistoryTodayAdapter? by lazy { HistoryTodayAdapter() }

    override val stateRootView: View
        get() = viewBinding.llRoot

    override fun viewBind(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): ViewBinding {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, attachToRoot)
        return viewBinding
    }

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
        viewBinding.rvBook.adapter = historyToadyAdapter
        viewBinding.rvBook.addItemDecoration(ColorItemDecoration())

        // 协程测试
        CoroutineTest().apply {
            //testCoroutineStart()
//            testCoroutineScope()
//            testCoroutineScope2()
        }
    }

    override fun initData() {
        //获取今天日期
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        //系统日期月份从0开始，需加1
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val today = "${year}年${month}月${day}日"
        viewBinding.tvToday.text = today
        val date = "${month}/${day}"
//        val date = "3/32"
        viewModel.getHistoryToadyData(date)
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
        viewModel.historyTodayData.observe(this, Observer {
            historyToadyAdapter?.setNewInstance(it)
        })

    }

}