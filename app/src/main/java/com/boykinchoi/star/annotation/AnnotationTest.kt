package com.boykinchoi.star.annotation

import com.boykin.lib_annotation.BeforeClick
import com.boykin.lib_annotation.SuperField
import com.boykinchoi.baselibrary.util.BjLogger


/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/8/5 15:51
 */
class AnnotationTest(
    @SuperField
    val id: Int? = null,
    @SuperField
    val name: String? = null
) {
    @BeforeClick(name = "u can fuck me!")
    fun ganAnnotation() {
        BjLogger.i("gang hello")
    }
}