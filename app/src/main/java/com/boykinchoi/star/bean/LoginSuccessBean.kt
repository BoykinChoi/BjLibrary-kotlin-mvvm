package com.boykinchoi.star.bean

/**
 * Created by BoykinChoi
 * on 2020/5/26
 */
data class LoginSuccessBean(
    val token: String? = null,
    val isBindingFlag: Boolean = false,//是否已绑定微信
    val isRegisterFlag: Boolean = false,//是否已注册了
    val isInvitationCodeFlag: Boolean = false,//是否有邀请码
    val mobile: String? = null, //用户手机
    val nickName: String? = null,//微信昵称
    val uuid: String? = null//微信uuid
)

