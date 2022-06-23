package com.boykinchoi.baselibrary.network.exception

import java.lang.RuntimeException

/**
 * Created by BoykinChoi
 * on 2022/6/23
 **/
class EmptyDataException : RuntimeException {
    var status: String? = null
    override var message: String? = null

    constructor(message: String) : super(message) {
        this.message = message
    }

    constructor(status: String?, message: String?) : super(message) {
        this.status = status
        this.message = message
    }
}