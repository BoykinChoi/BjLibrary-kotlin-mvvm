package com.boykinchoi.star.bean

import com.boykinchoi.star.bean.`interface`.BigBoss
import com.boykinchoi.star.bean.`interface`.BigStaff

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/8/24 16:32
 */
class BigBossImpl(private val bigStaff: BigStaff) : BigBoss {
    override fun support() {
        bigStaff.service()
    }

    override fun askHelp(bigBoss: BigBoss) {
        bigBoss.support()
    }
}