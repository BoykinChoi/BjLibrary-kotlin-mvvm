package com.boykinchoi.star.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.boykinchoi.star.room.dao.BookDao
import com.boykinchoi.star.room.dao.UserDao
import com.boykinchoi.star.room.entity.DbBook
import com.boykinchoi.star.room.entity.DbUser

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/8/12 9:22
 */
@Database(entities = [DbUser::class,DbBook::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userData(): UserDao

    abstract fun bookData(): BookDao
}