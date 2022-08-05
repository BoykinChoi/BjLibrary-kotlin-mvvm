package com.boykinchoi.star.ui.home

import android.os.Handler
import android.os.Message
import androidx.viewbinding.ViewBinding
import com.boykinchoi.baselibrary.base.BaseActivity
import com.boykinchoi.star.databinding.ActivityLeakBinding

class LeakActivity : BaseActivity<HomeJuHeViewModel>() {

    override fun bindView(): ViewBinding {
        return ActivityLeakBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mHandler.sendEmptyMessageDelayed(0, 24 * 60 * 60)
    }

    override fun initData() {

    }

    override fun observeData() {

    }

    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {}
    }
}




