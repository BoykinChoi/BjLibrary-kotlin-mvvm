package com.boykinchoi.star.ui.home

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.boykinchoi.baselibrary.base.BaseFragment
import com.boykinchoi.star.databinding.FragmentClassBinding
import me.jessyan.autosize.utils.LogUtils
import java.util.*

/**
 * Created by BoykinChoi
 * on 2021/2/2
 **/
class ClassFragment : BaseFragment<HomeJuHeViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(): ClassFragment {
            val classFragment = ClassFragment()
            classFragment.arguments = Bundle()
            return classFragment
        }
    }

    private lateinit var viewBinding: FragmentClassBinding

    override fun viewBind(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): ViewBinding {
        viewBinding = FragmentClassBinding.inflate(inflater, container, attachToRoot)
        return viewBinding
    }

    override fun initialize() {
//        tv_info.setOnClickListener { ToastUtil.s(viewModel?.homeData?.value?.bookList?.get(0)?.bookChineseName) }
        viewBinding.btTest.setOnClickListener {
//            LogUtils.d("bt_test onclick")
            testScheme()
//            testLeak()
        }
        val rect = Rect()
//        bt_test.setOnTouchListener { v, event ->
//            LogUtils.d("bt_test onTouch ${event.action}")
//            return@setOnTouchListener true
//        }
    }

    override fun initData() {
        //开始请求数据
//        viewModel?.checkVersion()
    }

    override fun observeData() {
//        viewModel?.versionData?.observe(this, Observer {
//            tv_info.text = "app version:${it.versionName} app no:${it.versionNo} desc:${it.updateContent}"
//        })
    }

    private fun testLeak() {
        startActivity(Intent(activity, LeakActivity::class.java))
    }

    /**
     * 测试各类scheme跳转
     *   uriHosts = { product ->
    switch (product) {
    case products.glkt:
    case products.glkt_sharp:
    case products.glkt_misound:
    return "glkt"
    case products.glkt_yzd:
    return "glkt_yzd"
    case products.glkt_jtb:
    return "glkt_jtb"
    case products.zxxtb:
    case products.zxkt:
    return "zxxtb"
    case products.xxtb:
    case products.xxtb_duer:
    return "xxtb_all"
    case products.xxtb1:
    return "xxtb1"
    case products.xxtb2:
    return "xxtb2"
    case products.xxtb3:
    return "xxtb3"
    case products.xxtb4:
    return "xxtb4"
    case products.xxtb5:
    return "xxtb5"
    case products.xxtb6:
    return "xxtb6"
    case products.xxwkt:
    return "xxwkt"
    case products.xxkxas:
    return "xxkxas"
    case products.gsw:
    return "gsw"
    case products.gx:
    return "gx"
    case products.zjg:
    case products.zjg_huawei_hd:
    return "zjg"
    }
     */
    private fun testScheme() {
        val date = viewModel?.calendar?.let {
            val month = if (it.get(Calendar.MONTH) < 10) {
                "0${it.get(Calendar.MONTH) + 1}"
            } else {
                it.get(Calendar.MONTH) + 1
            }
            val day = if (it.get(Calendar.DAY_OF_MONTH) < 10) {
                "0${it.get(Calendar.DAY_OF_MONTH)}"
            } else {
                it.get(Calendar.DAY_OF_MONTH)
            }
            "${it.get(Calendar.YEAR)}${month}${day}"
        }
        LogUtils.d("date fuck=" + date)
        val url = "wyt://glkt/mod_course/player?course_id=3263"
//        val url = "wyt://glkt_yhzh/mod_home/main?index=${
//            et_input.text.toString().trim()
//        }&sys_date=${date}"
//        val url = "wyt://glkt_custom/mod_home/main?index=${et_input.text.toString().trim()}&sys_date=${date}"
//        val url = "wyt://glkt_custom/mod_home/main?index=${et_input.text.toString().trim()}"
//        val url = "wyt://glkt_custom/mod_course/subject?subject_id=15"
//        val url = "wyt://glkt_custom/mod_zxx/search_tv"
//        val url = "wyt://zxxtb/mod_course/sync?press_id=44"
//        val url = "wyt://glkt/mod_user/study?position=1"
//        val url = "wyt://glkt/mod_user/study"
//                val url = "wyt://glkt/mod_home/main?index=3&sys_date=20211207"
//        val url = "wyt://glkt_custom/mod_course/subject?subject_id=312&sys_date=20211130"
//          val url = "wyt://glkt_yhzh/mod_course/player?course_id=3263&sys_date=20211130"
//          val url = "wyt://glkt/mod_course/sync?nianji_id=9&xueke_id=29&press_id=263&xueduan_id=1"
//          val url = "wyt://glkt/mod_course/sync"
//          val url = "wyt://xxtb_all/mod_course/player?course_id=7918"
//          val url = "wyt://glkt/mod_course/sync?nianji_id=9"
//        val url = "wyt://glkt_jtb/mod_home/main"
//        val url = "xuelejia://main/module_user/login?" +
//                "ticket=NDJlMzUwNTYtNGE5Ny00Yzg0LWEzNTAtNTY0YTk3MWM4NDc2MTYzMTIzNzEzNDM0NQ==&sysCode=0"
        try {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
//            intent.flags = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}