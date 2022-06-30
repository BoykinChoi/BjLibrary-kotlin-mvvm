package com.boykinchoi.star.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.boykinchoi.baselibrary.base.BaseFragment
import com.boykinchoi.star.R
import com.boykinchoi.star.coroutine.CoroutineTest
import com.boykinchoi.star.databinding.FragmentMeBinding
import kotlinx.android.synthetic.main.fragment_me.*

/**
 * Created by BoykinChoi
 * on 2021/2/2
 **/
class MeFragment : BaseFragment<HomeJuHeViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance(): MeFragment {
            val meFragment = MeFragment()
            meFragment.arguments = Bundle()
            return meFragment
        }
    }

    override fun viewBind(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): ViewBinding = FragmentMeBinding.inflate(inflater, container, attachToRoot)

    override fun initialize() {
        tv_home.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
        }
    }

    override fun initData() {
        val coroutineTest = CoroutineTest()
    }

    override fun observeData() {
//        viewModel?.versionData?.observe(this, Observer {
//            tv_info.text = "app version:${it.versionName} app no:${it.versionNo} desc:${it.updateContent}"
//        })
    }

}