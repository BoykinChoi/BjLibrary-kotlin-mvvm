package com.boykinchoi.baselibrary.util

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.boykinchoi.baselibrary.R
import com.boykinchoi.baselibrary.base.app.BaseApplication
import kotlinx.android.synthetic.main.transient_notification.view.*
import java.lang.Error

/**
 * 缓存一个Toast 这种方式体验感觉最好，Toast消失的计时会从最后一次show之后才开始计算，
 * 还可以通过setText设置不同的内容
 * Created by BoykinChoi
 * on 2020/12/1
 **/
class ToastUtil private constructor() {
    init {
        throw Error("Do not need instantiate!")
    }

    companion object {
        private var toast: Toast? = null
        private var textContent: TextView? = null
        private fun show(msg: CharSequence?, duration: Int) {
            if (toast == null) {
                val context = BaseApplication.instance?.applicationContext
                toast = Toast(context)
                val inflater =
                    context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val rootView = inflater.inflate(R.layout.transient_notification, null)
                textContent = rootView.message
                textContent?.text = msg
                toast?.view = rootView
                toast?.duration = duration
            } else if (textContent != null) {
                textContent?.text = msg
            } else {
                toast = null
                show(msg, duration)
            }

            toast?.setGravity(Gravity.BOTTOM, 0, 300)
            toast?.show()
        }

        fun s(msg: String?) {
            show(msg, Toast.LENGTH_SHORT)
        }

        fun l(msg: String?) {
            show(msg, Toast.LENGTH_LONG)
        }

        fun s(res: Int) {
            s(BaseApplication.instance?.applicationContext?.resources?.getString(res))
        }

        fun l(res: Int) {
            l(BaseApplication.instance?.applicationContext?.resources?.getString(res))
        }
    }

}