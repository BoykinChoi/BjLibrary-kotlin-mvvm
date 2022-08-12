package com.boykinchoi.star.ui.book

import com.boykinchoi.star.room.entity.DbBook
import com.boykinchoi.star.room.entity.DbUser

/**
 * Created by BoykinChoi
 * on 2021/1/29
 **/
data class HomeDataBean2(var user: DbUser?, var bookList: MutableList<DbBook>?)