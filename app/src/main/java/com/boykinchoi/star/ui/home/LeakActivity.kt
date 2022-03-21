package com.boykinchoi.star.ui.home

import android.os.Handler
import android.os.Message
import com.boykinchoi.baselibrary.base.BaseActivity
import com.boykinchoi.star.R

class LeakActivity : BaseActivity<HomeJuHeViewModel>() {
    override val layoutRes: Int
        get() = R.layout.activity_leak

    override fun initialize() {
        mHandler.sendEmptyMessageDelayed(0, 24 * 60 * 60)
    }

    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {}
    }
}




