package com.boykinchoi.star.api.service

import com.boykinchoi.baselibrary.network.bean.BaseBean
import com.boykinchoi.baselibrary.network.bean.JuHeBaseBean
import com.boykinchoi.star.api.ApiConstants
import com.boykinchoi.star.bean.*
import com.boykinchoi.star.room.entity.DbBook
import com.boykinchoi.star.room.entity.DbUser
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by BoykinChoi
 * on 2021/1/5
 **/
interface TestService {

    @GET(ApiConstants.HOME_USER_INFO)
    suspend fun homeUserInfo(): BaseBean<HomeUserInfoBean>

    @GET(ApiConstants.HOME_BOOK_LIST)
    suspend fun homeBookList(): BaseBean<MutableList<BookBean>>


    @GET(ApiConstants.HOME_USER_INFO)
    suspend fun homeUserInfo2(): BaseBean<DbUser>

    @GET(ApiConstants.HOME_BOOK_LIST)
    suspend fun homeBookList2(): BaseBean<MutableList<DbBook>>

    /**
     * 登录
     *
     * @param phone
     * @param deviceNumber 设备id
     * @param captcha      验证码
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.PWD_LOGIN)
    suspend fun login(
        @Field("phoneNumbers") phone: String?,
        @Field("password") password: String?,
        @Field("deviceNumber") deviceNumber: String? = "unknow",
        @Field("appVersion") appVersion: String? = "1.9.0"
    ): BaseBean<LoginSuccessBean>

    /**
     * 检查版本
     */
    @GET(ApiConstants.CHECK_APP_VERSION)
    suspend fun checkVersion(
        @Query("channel") channel: String,
        @Query("version") version: String,
        @Query("platformType") platformType: Int
    ): BaseBean<VersionBean>



    /**----------------------------聚合数据接口--------------------------------------**/
    /**
     * 历史上的今天
     * @param date 日期,格式:月/日 如:1/1,/10/1,12/12 如月或者日小于10,前面无需加0
     */
    @GET(ApiConstants.JUHE_HISTORY_TODAY)
    suspend fun historyToday(
        @Query("date") date: String,
        @Query("key") apiKey: String
    ): JuHeBaseBean<MutableList<HistoryTodayBean>>

}