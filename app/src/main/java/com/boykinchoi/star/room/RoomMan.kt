package com.boykinchoi.star.room

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * 注意：如果您的应用在单个进程中运行，在实例化 AppDatabase 对象时应遵循单例设计模式。每个 RoomDatabase 实例的
 * 成本相当高，而您几乎不需要在单个进程中访问多个实例。
 * 如果您的应用在多个进程中运行，请在数据库构建器调用中包含 enableMultiInstanceInvalidation()。这样，
 * 如果您在每个进程中都有一个 AppDatabase 实例，可以在一个进程中使共享数据库文件失效，并且这种失效会自动传播到
 * 其他进程中 AppDatabase 的实例。
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/8/11 14:38
 */
object RoomMan {

    private var db: RoomDatabase? = null

    fun getDB(context: Application): AppDataBase {
        if (db == null) {
            db = Room.databaseBuilder(context, AppDataBase::class.java, "cbj_db")
                .enableMultiInstanceInvalidation()
                .build()

        }
        return db as AppDataBase
    }

}