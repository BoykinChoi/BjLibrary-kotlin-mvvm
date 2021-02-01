package com.boykinchoi.read.api.service

import com.boykinchoi.baselibrary.network.BaseBean
import com.boykinchoi.read.api.ApiConstants
import com.boykinchoi.read.bean.BookBean
import com.boykinchoi.read.bean.HomeUserInfoBean
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by BoykinChoi
 * on 2021/1/5
 **/
interface TestService {
    @GET(ApiConstants.HOME_USER_INFO)
    suspend fun homeUserInfo(): BaseBean<HomeUserInfoBean>

    @GET(ApiConstants.HOME_BOOK_LIST)
    suspend fun homeBookList(): BaseBean<MutableList<BookBean>>
}