package com.boykinchoi.star.bean

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/8/24 14:18
 */
abstract class Boss {
    open lateinit var staff: Staff
    abstract fun support()
    abstract fun askHelp(boss: Boss)
}