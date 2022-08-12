package com.boykinchoi.star.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/8/11 14:42
 */
@Entity(tableName = "user")
data class DbUser(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val accountId: Int = 1,
    @ColumnInfo(name = "head_img")
    val headImgUrl: String? = null,
    @ColumnInfo(name = "read_words_num")
    val readWordsNumber: Int = 0,
    val star: Int = 0,
    @ColumnInfo(name = "class")
    val studentClass: Int = 1,
    @ColumnInfo(name = "grade")
    val studentGrade: Int = 1,
    @ColumnInfo(name = "name")
    var studentName: String? = null,
    @ColumnInfo(name = "score")
    val studyScore: Int = 0,
    val up: Boolean = false
) : Serializable