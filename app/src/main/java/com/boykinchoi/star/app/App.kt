package com.boykinchoi.star.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.boykinchoi.baselibrary.base.BaseApplication
import com.boykinchoi.baselibrary.util.ActivityStackManager
import com.boykinchoi.star.ui.AdActivity
import com.boykinchoi.star.ui.MainActivity

/**
 * Created by BoykinChoi
 * on 2020/11/28
 **/
class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycle()
    }

    /**
     * 全局管理Activity
     */
    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {//to do sth
            }

            override fun onActivityResumed(activity: Activity?) {
//                val intent = Intent(activity, AdActivity::javaClass.javaClass)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                activity?.startActivity(intent)
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