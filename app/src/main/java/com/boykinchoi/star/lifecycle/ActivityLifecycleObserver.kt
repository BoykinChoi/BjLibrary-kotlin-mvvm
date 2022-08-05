package com.boykinchoi.star.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.boykinchoi.baselibrary.util.BjLogger

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/8/4 17:04
 */
class ActivityLifecycleObserver : ILifecycleObserver {

    private val TAG = javaClass.simpleName

    override fun onCreate(owner: LifecycleOwner) {
        BjLogger.i(TAG, "onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        BjLogger.i(TAG, "onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        BjLogger.i(TAG, "onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        BjLogger.i(TAG, "onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        BjLogger.i(TAG, "onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        BjLogger.i(TAG, "onDestroy")
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        BjLogger.i(TAG, "onLifecycleChanged owner=${owner}")
    }
}