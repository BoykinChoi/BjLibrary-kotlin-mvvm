package com.boykinchoi.baselibrary.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.boykinchoi.baselibrary.widget.LoadingDialog
import com.gyf.immersionbar.ImmersionBar

/**
 * Created by BoykinChoi
 * on 2020/9/29
 **/
class BaseDelegate {
    private var activity: AppCompatActivity
    private var orientationPortrait: Boolean = false
    private var nightMode: Boolean = false
    private var loadingDialog: LoadingDialog? = null
    private var loadingShowCount: Int = 0

    constructor(activity: AppCompatActivity) {
        this.activity = activity
    }

    fun onCreate(savedInstanceState: Bundle?) {
        ImmersionBar.with(activity)
                //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
                .autoStatusBarDarkModeEnable(true, 0.2f)
                .statusBarDarkFont(true, 0.2f)
                .init()
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

    /**
     * 重启Activity
     * 此方法会比 recreate() 效果更好
     */
    fun reload() {
        activity.run {
            val intent: Intent = intent
            overridePendingTransition(0, 0)
            intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
            finish()
            overridePendingTransition(0, 0)
            startActivity(intent)
            System.gc()
        }
    }

    fun showLoading() {
        showLoading("")
    }

    @Synchronized
    fun showLoading(loadingTip: CharSequence) {
        if (loadingShowCount == 0) {
            if (loadingDialog == null) {
                loadingDialog = LoadingDialog(activity, loadingTip)
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