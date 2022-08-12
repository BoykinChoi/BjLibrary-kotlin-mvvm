package com.boykinchoi.star.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/8/12 9:51
 */
@Entity(tableName = "book")
data class DbBook(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    @ColumnInfo(name = "book_id")
    val bookId: Int = 1,
    @ColumnInfo(name = "chinese_name")
    val bookChineseName: String,
    @ColumnInfo(name = "img_url")
    val hdImgUrl: String,
    val status: Int
)