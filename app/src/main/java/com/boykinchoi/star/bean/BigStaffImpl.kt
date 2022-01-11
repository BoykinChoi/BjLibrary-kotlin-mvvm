package com.boykinchoi.star.bean

import com.boykinchoi.star.bean.`interface`.BigBoss
import com.boykinchoi.star.bean.`interface`.BigStaff
import me.jessyan.autosize.utils.LogUtils

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/8/24 16:34
 */
class BigStaffImpl(private val name: String) : BigStaff {
    override fun service() {
        LogUtils.d("${name}提供大大的服务")
    }

    override fun askHelp(bigBoss: BigBoss) {
        bigBoss.support()
    }
}