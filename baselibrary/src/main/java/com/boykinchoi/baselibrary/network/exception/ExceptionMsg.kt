package com.boykinchoi.baselibrary.network.exception

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/7/23 10:08
 */
class ExceptionMsg {
    companion object {
        const val CODE_DEFAULT = 0
        const val ERROR_SERVICE_500 = "服务器异常，请稍后再试"
        const val ERROR_CONNECT = "网络开小差啦，请检查网络"
        const val ERROR_TIME_OUT = "您的网络好像有问题哦~请稍后再试"
        const val MESSAGE_NON_NET = "无网络连接，请先连接网络"
        const val ERROR_DATA_DECODE = "数据解析错误"
        const val ERROR_SSL_HANDSHAKE = "证书验证错误，请检查网络"
        const val ERROR_UNKNOWN = "未知错误"
    }
}