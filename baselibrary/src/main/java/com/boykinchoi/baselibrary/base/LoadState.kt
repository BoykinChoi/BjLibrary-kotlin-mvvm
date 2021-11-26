package com.boykinchoi.baselibrary.base

/**
 * 加载状态密封类
 * Created by BoykinChoi
 * on 2021/1/30
 **/
sealed class LoadState(val msg: String? = null) {
    class Loading : LoadState()
    class Success : LoadState()
    class EmptyData : LoadState()
    class Fail(errorCode: String? = null, msg: String?) : LoadState(msg)
}