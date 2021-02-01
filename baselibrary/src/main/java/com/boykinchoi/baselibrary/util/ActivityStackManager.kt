package com.boykinchoi.baselibrary.util

import android.app.Activity
import java.util.*

/**
 * Activity管理类
 * Created by BoykinChoi
 * on 2020/11/28
 * 添加/删除 建议在{@link Application#registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks)}中统一处理
 * (此方法比在BaseActivity中处理要好)
 **/
class ActivityStackManager  {
    companion object {
        var actStack = Stack<Activity>()
        //kotlin 单例实现方法之一（双重校验锁式（Double Check)）
        val instance: ActivityStackManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ActivityStackManager()
        }
    }

    /**
     * 加入栈
     */
    fun add(activity: Activity?) {
        actStack.add(activity)
    }

    /**
     * 移出栈
     */
    fun remove(activity: Activity?) {
        if (actStack.contains(activity)) {
            actStack.remove(activity)
        }
    }

    /**
     *
     */
    fun getCount(): Int {
        return actStack.size
    }

    /**
     * 栈内是否包含指定类型Activity
     */
    fun isContains(cls: Class<*>): Boolean {
        for (act in actStack) {
            if (act.javaClass == cls) {
                return true
            }
        }
        return false
    }

    /**
     * 查找指定Activity 默认第一个
     */
    fun <T : Activity> findActivity(cls: Class<*>): T? {
        return findFirst(cls)
    }

    /**
     * 查找栈内第一个Activity
     */
    fun <T : Activity> findFirst(cls: Class<*>): T? {
        if (actStack.empty()) {
            return null
        }
        for (i in actStack.indices) {
            val activity = actStack[i]
            if (activity.javaClass == cls) {
                return activity as T
            }
        }
        return null
    }

    /**
     * 获取当前（即最后一个） Activity
     */
    fun <T : Activity> getCurrent(): T? {
        if (actStack.empty()) {
            return null
        }
        return actStack.lastElement() as T
    }

    /**
     * 结束指定Activity（具体某个）
     */
    fun finish(activity: Activity) {
        if (!activity.isFinishing) {
            activity.finish()
        }
        //这个可以不用的 记得在Application中调用
        //remove(activity)
    }

    /**
     * 结束指定Activity
     * 注：当栈中可能包含多个该Activity时，该方法会将所有的该Activity都finish
     */
    fun finish(cls: Class<*>) {
        if (actStack.empty()) {
            return
        }
        for (act in actStack) {
            if (act.javaClass == cls) {
                finish(act)
            }
        }
    }

    /**
     * 结束此Activity之前的所有Activity（不包括当前的 ，结束当前需手动） 最终显示此Activity
     * 1-2-3  * 4-5
     * 1-2  * 4-5
     * 1  * 4-5
     * * 4-5
     *
     * @param activity
     */
    fun finishBefore(activity: Activity?) {
        if (actStack.contains(activity)) {
            val index = actStack.indexOf(activity)
            if (index != -1) {
                //TODO 此处kotlin for循环相当于，再研究
                /*
                java code
                for (int i = index - 1; i >= 0; i--) {
                     finish(mActStack.get(i));
                 }*/
                for (i in index - 1 downTo 0) {
                    finish(actStack[i])
                }
            }
        }
    }
}