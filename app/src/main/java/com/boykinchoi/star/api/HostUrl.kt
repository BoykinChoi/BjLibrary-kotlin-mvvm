package com.boykinchoi.star.api

import com.boykinchoi.star.BuildConfig

/**
 * Created by BoykinChoi
 * on 2020/1/4
 */

interface HostUrl {
    companion object {
//        const val HOST_URL = BuildConfig.HOST_URL
        const val HOST_URL = "http://v.juhe.cn/"
        //String HOST_URL = "https://pre-app.digitspower.cn/";
        //String HOST_URL = "https://app.digitspower.cn/";
        const val HOST_H5_URL = BuildConfig.HOST_H5_URL

        const val AMBASSADOR_EQUITIES = HostUrl.HOST_H5_URL + "/app-ambassador-inside/new_rights"
        const val NORMAL_INVITE_RULE = HostUrl.HOST_H5_URL + "/app-ambassador-inside/invite-rule"
        const val AMBASSADOR_INVITE_RULE =
            HostUrl.HOST_H5_URL + "/app-ambassador-inside/invite-ambassador-detail"
        const val COIN_EXPLAIN = HostUrl.HOST_H5_URL + "/app-integral/explain"
        const val KNOWLEDGE_PUZZLE_EXPLAIN = HostUrl.HOST_H5_URL + "/app-discover/puzzle"
        const val WX_APPLET_ASSISTANCE = HostUrl.HOST_H5_URL + "/app-share/activity"

        /* const val SERVICE_PROTOCOL_URL = BuildConfig.HOST_PROTOCOL_URL + "user_agreement/"
        const val PRIVATE_PROTOCOL_URL = BuildConfig.HOST_PROTOCOL_URL + "privacy_protocol/"
        const val ABOUT_PROTOCOL_URL = BuildConfig.HOST_PROTOCOL_URL + "about_us/"
        const val PROTOCOL_PAY_URL = BuildConfig.HOST_PROTOCOL_URL + "pay/"*/
    }
}