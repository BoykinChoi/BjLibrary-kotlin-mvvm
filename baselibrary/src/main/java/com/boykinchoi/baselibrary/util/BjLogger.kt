package com.boykinchoi.baselibrary.util

import com.orhanobut.logger.Logger

/**
 * Created by BoykinChoi
 * on 2021/1/4
 */
object BjLogger {

    var isDebug = true//是否Debug 控制打印

    /**
     * Logger 工具打印
     */
    fun d(msg: String) {
        if (isDebug) Logger.d(msg)
    }

    fun i(msg: String) {
        if (isDebug) Logger.i(msg)
    }

    fun v(msg: String) {
        if (isDebug) Logger.v(msg)
    }

    fun w(msg: String) {
        if (isDebug) Logger.w(msg)
    }

    fun e(msg: String) {
        if (isDebug) Logger.e(msg)
    }

    fun json(json: String) {
        if (isDebug) Logger.json(json)
    }

    fun xml(xml: String) {
        if (isDebug) Logger.xml(xml)
    }


    /**
     * 以下是系统自带的
     * 打印简单的log
     */
    fun e(tag: String, msg: String) {
        if (isDebug) android.util.Log.e(tag, msg)
    }

    fun w(tag: String, msg: String) {
        if (isDebug) android.util.Log.w(tag, msg)
    }

    fun d(tag: String, msg: String) {
        if (isDebug) android.util.Log.d(tag, msg)
    }

    fun i(tag: String, msg: String) {
        if (isDebug) android.util.Log.i(tag, msg)
    }

    fun v(tag: String, msg: String) {
        if (isDebug) android.util.Log.v(tag, msg)
    }


}
