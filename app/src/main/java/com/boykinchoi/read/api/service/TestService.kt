package com.boykinchoi.read.api.service

import com.boykinchoi.baselibrary.network.BaseBean
import com.boykinchoi.read.api.ApiConstants
import com.boykinchoi.read.bean.BookBean
import com.boykinchoi.read.bean.HomeUserInfoBean
import com.boykinchoi.read.bean.VersionBean
import io.reactivex.Observable
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
    suspend fun checkVersion(@Query("channel") channel: String,
                             @Query("version") version: String,
                             @Query("platformType") platformType: Int)
            : BaseBean<VersionBean>
}