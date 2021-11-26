package com.boykinchoi.star.api.service

import com.boykinchoi.baselibrary.network.BaseBean
import com.boykinchoi.baselibrary.network.JuHeBaseBean
import com.boykinchoi.star.api.ApiConstants
import com.boykinchoi.star.bean.*
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by BoykinChoi
 * on 2021/1/5
 **/
interface TestService {
    @GET(ApiConstants.HOME_USER_INFO)
    suspend fun homeUserInfo(): BaseBean<HomeUserInfoBean>

    @GET(ApiConstants.HOME_BOOK_LIST)
    suspend fun homeBookList(): BaseBean<MutableList<BookBean>>

    @GET(ApiConstants.CHECK_APP_VERSION)
    suspend fun checkVersion(
        @Query("channel") channel: String,
        @Query("version") version: String,
        @Query("platformType") platformType: Int
    )
            : BaseBean<VersionBean>

    /**----------------------------聚合数据接口--------------------------------------**/
    /**
     * 历史上的今天
     * @param date 日期,格式:月/日 如:1/1,/10/1,12/12 如月或者日小于10,前面无需加0
     */
    @GET(ApiConstants.JUHE_HISTORY_TODAY)
    suspend fun historyToday(
        @Query("date") date: String,
        @Query("key") apiKey: String
    )
            : JuHeBaseBean<MutableList<HistoryTodayBean>>

}