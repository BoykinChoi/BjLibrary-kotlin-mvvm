package com.boykinchoi.star.bean

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/8/24 14:18
 */
abstract class Staff {
    open lateinit var name:String
    abstract fun service()
    abstract fun askHelp(boss: Boss)
}