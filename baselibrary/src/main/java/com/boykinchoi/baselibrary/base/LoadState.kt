package com.boykinchoi.baselibrary.base

/**
 * 加载状态密封类
 * Created by BoykinChoi
 * on 2021/1/30
 **/
sealed class LoadState(val msg:String) {
    class Loading(msg: String = "") : LoadState(msg)
    class Success(msg: String = "") : LoadState(msg)
    class Fail(msg: String) : LoadState(msg)
}