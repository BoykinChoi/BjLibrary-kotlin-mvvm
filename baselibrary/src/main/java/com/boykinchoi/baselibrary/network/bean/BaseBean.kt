package com.boykinchoi.baselibrary.network.bean

/**
 * Created by BoykinChoi
 * on 2020/12/3
 **/
open class BaseBean<T> {
    var code: String? = null
    var message: String? = null
    var data: T? = null
    var time: Long? = null
    override fun toString(): String {
        return "BaseBean{code=$code msg= $message data=$data time $time }"
    }

}