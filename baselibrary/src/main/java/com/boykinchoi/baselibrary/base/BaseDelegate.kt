package com.boykinchoi.baselibrary.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.boykinchoi.baselibrary.widget.LoadingDialog

/**
 * Created by BoykinChoi
 * on 2020/9/29
 **/
class BaseDelegate {
    private var activity: AppCompatActivity? = null
    private var orientationPortrait: Boolean = false
    private var nightMode: Boolean = false
    private var loadingDialog: LoadingDialog? = null
    private var loadingShowCount: Int = 0

    constructor(activity: AppCompatActivity?) {
        this.activity = activity
    }

    fun onCreate(savedInstanceState: Bundle?) {

    }

    fun onStart() {

    }

    fun onResume() {

    }

    fun onPause() {

    }

    fun onStop() {

    }

    fun onDestory() {

    }

    @Synchronized
    fun showLoading(context: Context, loadingTip: CharSequence) {
        if (loadingShowCount == 0) {
            if (loadingDialog == null) {
                loadingDialog = LoadingDialog(context, loadingTip)
            }
            /*
              ?.表示当前对象如果为空则不执行，
              !!.表示当前对象如果为空也执行，然后会抛出空异常
             */
            loadingDialog?.setOnCancelListener { loadingShowCount = 0 }
            loadingDialog?.show()
        }
        loadingShowCount++
    }

    @Synchronized
    fun dismissLoading() {
        if (loadingShowCount == 0) {
            return
        }
        loadingShowCount--
        if (loadingShowCount == 0) {
            loadingDialog?.dismiss()
            loadingDialog = null
        }

    }

}