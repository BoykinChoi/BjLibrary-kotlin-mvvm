package com.boykinchoi.star.bean

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/8/24 14:25
 */
class BossImpl(override var staff: Staff) : Boss() {

    override fun support() {
        staff.service()
    }

    override fun askHelp(boss: Boss) {
        boss.support()
    }
}