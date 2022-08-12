package com.boykinchoi.star.room.dao

import androidx.room.*
import com.boykinchoi.star.room.entity.DbUser

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/8/11 14:50
 */
@Dao
interface UserDao {

    // 查询所有
    @Query("SELECT * FROM user")
    suspend fun getAll(): MutableList<DbUser>

    //通id数组查询所有
    @Query("SELECT * FROM user WHERE id in(:ids)")
    suspend fun loadAllByIds(ids: IntArray): MutableList<DbUser>

    //通过name查询用户
    @Query("SELECT * FROM user WHERE name LIKE :name LIMIT 1 ")
    suspend fun findByName(name: String): DbUser

    //通过id查询用户
    @Query("SELECT * FROM user WHERE id =:id")
    suspend fun findByUid(id: Int): DbUser

    // 插入一条，返回的是插入的那条的rowId，插入多条返回的是数组
    @Insert
    suspend fun insert(vararg users: DbUser?)

    // 返回删除的行数
    @Delete
    suspend fun delete(user: DbUser?)

    // 返回更新的行数
    @Update
    suspend fun update(user: DbUser?)
}