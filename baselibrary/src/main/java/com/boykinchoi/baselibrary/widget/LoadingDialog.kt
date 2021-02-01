package com.boykinchoi.baselibrary.widget

import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.boykinchoi.baselibrary.R

/**
 * 加载中Dialog
 * Created by BoykinChoi
 * on 2020/10/19
 **/
class LoadingDialog constructor(context: Context, loadingTip: CharSequence = "") :
    ProgressDialog(context, R.style.LoadingDialogStyle) {
    private var rootView: View
    private var loadingTextView: TextView

    init {
        rootView = View.inflate(context, R.layout.dialog_loading, null)
        loadingTextView = rootView.findViewById(R.id.tv_loading_text)
        if (loadingTip.isNotEmpty()) {
            loadingTextView.text = loadingTip
            loadingTextView.visibility = View.VISIBLE
        }
    }

    override fun show() {
        try {
            if (this.isShowing) {
                hide()
            } else {
                super.show()
                //setContentView（）一定要在show之后调用
                this.setContentView(rootView)
            }
            super.show()
        } catch (e: WindowManager.BadTokenException) {
                e.printStackTrace()
        }

    }


}