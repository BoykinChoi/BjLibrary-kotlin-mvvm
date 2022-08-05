package com.boykinchoi.star.ui.tv

import android.view.View
import androidx.viewbinding.ViewBinding
import com.boykinchoi.baselibrary.base.BaseTvActivity
import com.boykinchoi.star.databinding.ActivityTvBinding
import com.boykinchoi.star.ui.home.HomeJuHeViewModel

class TvActivity : BaseTvActivity<HomeJuHeViewModel>() {

    private lateinit var viewBinding: ActivityTvBinding

    override val stateRootView: View
        get() = viewBinding.llRoot

    override fun bindView(): ViewBinding {
        viewBinding = ActivityTvBinding.inflate(layoutInflater)
        return viewBinding
    }

    override fun initialize() {
        // not to do sth
    }

    override fun initData() {
        // not to do sth
    }

    override fun observeData() {
        // not to do sth
    }
}




