package com.boykinchoi.star.bean

//import me.jessyan.autosize.utils.LogUtils

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/8/24 14:27
 */
class StaffImpl(override var name: String) : Staff() {
    override fun service() {
//        LogUtils.d("${name}提供服务")
    }

    override fun askHelp(boss: Boss) {
        boss.support()
    }
}