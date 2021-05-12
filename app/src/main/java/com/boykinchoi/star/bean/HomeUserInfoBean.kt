package com.boykinchoi.star.bean

/**
 * Created by BoykinChoi
 * on 2020/6/3
 */
class HomeUserInfoBean(
    val accountId: Int,
    val headImgUrl: String? = null,
    val readWordsNumber: Int,
    val star: Int,
    val studentClass: Int,
    val studentGrade: Int,
    val studentName: String? = null,
    val studyScore: Int,
    val up: Boolean
)