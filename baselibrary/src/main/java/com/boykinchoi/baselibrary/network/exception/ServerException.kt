package com.boykinchoi.baselibrary.network.exception

import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.security.cert.CertPathValidatorException
import java.text.ParseException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

/**
 * 服务器自定义错误
 */
class ServerException : RuntimeException {
    var code: Int
        private set
    override var message: String? = null
        private set

    /**
     * 是否需要重新加载
     */
    var isNeedReload: Boolean
        private set

    /**
     * 是否需要重新登录
     */
    private val isNeedRelogin = false

    @JvmOverloads
    constructor(msg: String?, code: Int =  ExceptionMsg.CODE_DEFAULT) {
        message = msg
        this.code = code
        isNeedReload = false
    }

    @JvmOverloads
    constructor(cause: Throwable?, code: Int = 0) : super(cause) {
        this.code = code
        isNeedReload = true
        if (cause is HttpException || cause is ConnectException || cause is UnknownHostException) {
            message =  ExceptionMsg.ERROR_CONNECT
        } else if (cause is ConnectTimeoutException || cause is TimeoutException || cause is SocketTimeoutException) {
            message =  ExceptionMsg.ERROR_TIME_OUT
        } else if (cause is JsonParseException || cause is JSONException || cause is ParseException) {
            message =  ExceptionMsg.ERROR_DATA_DECODE
        } else if (cause is SSLHandshakeException || cause is CertPathValidatorException) {
            message =  ExceptionMsg.ERROR_SSL_HANDSHAKE
        } else if (cause is IllegalArgumentException) {
            message =  ExceptionMsg.ERROR_SERVICE_500
        } else {
            isNeedReload = false
            message = if (cause == null) ExceptionMsg.ERROR_UNKNOWN else cause.message
        }
    }

}