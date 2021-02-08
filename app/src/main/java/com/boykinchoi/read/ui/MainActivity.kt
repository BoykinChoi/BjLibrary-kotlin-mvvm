package com.boykinchoi.read.ui

import android.content.SharedPreferences
import android.widget.RadioGroup
import androidx.core.content.edit
import com.boykinchoi.baselibrary.base.BaseActivity
import com.boykinchoi.baselibrary.base.FragmentBasePagerAdapter
import com.boykinchoi.read.R
import com.boykinchoi.read.ui.home.ClassFragment
import com.boykinchoi.read.ui.home.HomeFragment
import com.boykinchoi.read.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<HomeViewModel>(), RadioGroup.OnCheckedChangeListener {
    override val layoutRes: Int
        get() = R.layout.activity_main

    val sharedPreferences: SharedPreferences? = null

    override fun initialize() {
        val fragments = mutableListOf(HomeFragment.newInstance(),
                ClassFragment.newInstance(),
                HomeFragment.newInstance())
        val pagerAdapter = FragmentBasePagerAdapter(supportFragmentManager, fragments)
        vp_home.adapter = pagerAdapter
        vp_home.offscreenPageLimit = pagerAdapter.count
        rg_tab.setOnCheckedChangeListener(this)
        rg_tab.getChildAt(0).performClick()
        sharedPreferences?.edit { putBoolean("key", false) }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_home -> vp_home.currentItem = 0
            R.id.rb_class -> vp_home.currentItem = 1
            R.id.rb_me -> vp_home.currentItem = 2
        }
    }
}




