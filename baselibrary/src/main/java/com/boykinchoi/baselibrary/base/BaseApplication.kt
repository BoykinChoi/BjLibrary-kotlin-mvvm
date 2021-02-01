package com.boykinchoi.baselibrary.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.boykinchoi.baselibrary.util.ActivityStackManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * Created by BoykinChoi
 * on 2020/11/28
 **/
open class BaseApplication : Application() {
    companion object {
        var instance: BaseApplication? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Logger.addLogAdapter(AndroidLogAdapter())
        registerActivityLifecycle()
    }

    /**
     * 全局管理Activity
     */
    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {//to do sth
            }

            override fun onActivityResumed(activity: Activity?) {//to do sth
            }

            override fun onActivityStarted(activity: Activity?) {//to do sth
            }


            override fun onActivitySaveInstanceState(
                activity: Activity?,
                outState: Bundle?
            ) {//to do sth
            }

            override fun onActivityStopped(activity: Activity?) {//to do sth
            }

            override fun onActivityCreated(
                activity: Activity?,
                savedInstanceState: Bundle?
            ) {
                ActivityStackManager.instance.add(activity)
            }

            override fun onActivityDestroyed(activity: Activity?) {
                ActivityStackManager.instance.remove(activity)
            }

        })
    }

}