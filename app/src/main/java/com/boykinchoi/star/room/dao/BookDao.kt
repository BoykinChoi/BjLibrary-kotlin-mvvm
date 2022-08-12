package com.boykinchoi.star.room.dao

import androidx.room.*
import com.boykinchoi.star.room.entity.DbBook

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/8/11 14:50
 */
@Dao
interface BookDao {

    // 查询所有
    @Query("SELECT * FROM book")
    suspend fun getAll(): MutableList<DbBook>

    // 通id数组查询所有
    @Query("SELECT * FROM book WHERE id in(:ids)")
    suspend fun loadAllByIds(ids: IntArray): MutableList<DbBook>

    // 通过name查询
    @Query("SELECT * FROM book WHERE chinese_name LIKE :chineseName LIMIT 1 ")
    suspend fun findByName(chineseName: String): DbBook

    // 通过id查询
    @Query("SELECT * FROM book WHERE id =:id")
    suspend fun findById(id: Int): DbBook

    // 插入一条，返回的是插入的那条的rowId，插入多条返回的是数组
    @Insert
    suspend fun insert(vararg books: DbBook?)

    // 返回删除的行数
    @Delete
    suspend fun delete(book: DbBook?)

    // 返回更新的行数
    @Update
    suspend fun update(book: DbBook?)
}