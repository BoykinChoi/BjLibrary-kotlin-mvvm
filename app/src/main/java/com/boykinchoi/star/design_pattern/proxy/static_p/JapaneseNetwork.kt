package com.boykinchoi.star.design_pattern.proxy.static_p

/**
 * kotlin 代理属性
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/10/28 13:55
 */
class JapaneseNetwork(private val internet: Internet) : Internet by internet {
    override fun access(domain: String?): String {
        return internet.access(domain)
    }
}