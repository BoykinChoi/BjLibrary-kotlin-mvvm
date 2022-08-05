package com.boykinchoi.star.ui

import android.content.SharedPreferences
import android.widget.RadioGroup
import androidx.core.content.edit
import androidx.viewbinding.ViewBinding
import com.boykinchoi.baselibrary.base.BaseActivity
import com.boykinchoi.baselibrary.base.FragmentBasePagerAdapter
import com.boykinchoi.baselibrary.util.OnlyValueUtil
import com.boykinchoi.star.R
import com.boykinchoi.star.bean.BigBossImpl
import com.boykinchoi.star.bean.BigStaffImpl
import com.boykinchoi.star.bean.BossImpl
import com.boykinchoi.star.bean.StaffImpl
import com.boykinchoi.star.databinding.ActivityMainBinding
import com.boykinchoi.star.myHandler.MyHandler
import com.boykinchoi.star.myHandler.MyLopper
import com.boykinchoi.star.myHandler.MyMessage
import com.boykinchoi.star.ui.home.ClassFragment
import com.boykinchoi.star.ui.home.HomeFragment
import com.boykinchoi.star.ui.home.HomeJuHeViewModel
import com.boykinchoi.star.ui.home.MeFragment

class MainActivity : BaseActivity<HomeJuHeViewModel>(), RadioGroup.OnCheckedChangeListener {

//    override val layoutRes: Int
//        get() = R.layout.activity_main

    private lateinit var viewBinding: ActivityMainBinding

    override fun bindView(): ViewBinding {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        return viewBinding
    }

    private val sharedPreferences: SharedPreferences? = null

    override fun initialize() {
        val fragments = mutableListOf(
            HomeFragment.newInstance(),
            ClassFragment.newInstance(),
            MeFragment.newInstance()
        )
        val pagerAdapter = FragmentBasePagerAdapter(supportFragmentManager, fragments)
        viewBinding.run {
            vpHome.adapter = pagerAdapter
            vpHome.offscreenPageLimit = pagerAdapter.count
            rgTab.setOnCheckedChangeListener(this@MainActivity)
            rgTab.getChildAt(0).performClick()
        }
        sharedPreferences?.edit { putBoolean("key", false) }


        val staffA = StaffImpl("员工A")
        val bossA = BossImpl(staffA)

        val staffB = StaffImpl("员工B")
        val staffC = StaffImpl("员工C")
        val boosB = BossImpl(staffC)

        bossA.askHelp(boosB)

        staffB.askHelp(bossA)

        val staffD = BigStaffImpl("员工D")
        val bossC = BigBossImpl(staffD)

        val staffE = BigStaffImpl("员工E")
        val boosD = BigBossImpl(staffE)

        boosD.askHelp(bossC)

        bossC.askHelp(boosD)

        staffD.askHelp(boosD)
        staffE.askHelp(bossC)

        val mac1 = OnlyValueUtil.DeviceUtils.getMacAddressByInetAddress()
        val mac2 = OnlyValueUtil.DeviceUtils.getMacAddressByNetworkInterface()
//        ToastUtil.l("getMacAddressByInetAddress()=" + mac1 + "\n" + "getMacAddressByNetworkInterface()=" + mac2)

//        testMyHandler()
    }

    override fun initData() {

    }

    override fun observeData() {

    }

    private fun testMyHandler() {
        var testHandler: TestHandler? = null
        Thread {
            MyLopper.prepare()
            testHandler = TestHandler()
            MyLopper.loop()
        }.start()

        try {
            Thread.sleep(500)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Thread {
            val msg = MyMessage()
            msg.obj = "gan gan gan"
            testHandler?.sendMessage(msg)
            println("message send")
        }.start()
    }

    class TestHandler : MyHandler() {
        override fun handleMessage(msg: MyMessage?) {
            super.handleMessage(msg)
            println("receive message = ${msg?.obj}")
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_home -> viewBinding.vpHome.currentItem = 0
            R.id.rb_class -> viewBinding.vpHome.currentItem = 1
            R.id.rb_me -> viewBinding.vpHome.currentItem = 2
        }
    }
}




