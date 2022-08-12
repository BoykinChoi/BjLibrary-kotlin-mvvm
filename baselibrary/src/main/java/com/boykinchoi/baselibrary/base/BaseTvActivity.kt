package com.boykinchoi.baselibrary.base

import android.content.Context
import android.os.Bundle
import android.view.View
import com.boykin.focushandler.FocusHandler
import com.boykin.focushandler.adapter.BaseFocusAdapter
import com.boykin.focushandler.border.FocusBorder
import com.boykinchoi.baselibrary.base.vm.BaseViewModel
import java.lang.ref.WeakReference

/**
 * TV版Activity基类，统一处理焦点View
 * Created by BoykinChoi
 * on 2022/7/19
 **/
abstract class BaseTvActivity<M : BaseViewModel> : BaseActivity<M>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFocusHandler()
    }

    private fun initFocusHandler() {
        FocusHandler.bind(this, object : BaseFocusAdapter() {
            lateinit var borderView: WeakReference<View>
            override fun getFocusBolder(context: Context): FocusBorder {
                borderView = WeakReference(View(context))
                return FocusBorder.Builder()
                    .setBorder(borderView)
                    .setTagIgnore("focus_ignore")
                    .setTagOnlyScale("focus_scale")
                    .setTagOnlyVisible("focus_no_scale")
                    .setPadding(20)
                    .build()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        FocusHandler.unbind(this)
    }
}