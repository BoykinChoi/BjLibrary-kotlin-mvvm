package com.boykinchoi.star.design_pattern.static_proxy.delegate

/**
 * 被委托对象，本场景的游戏代练
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/11/23 16:35
 */
class RealGamePlayer(private val name: String) : IGamePlayer {
    override fun rank() {
        println("$name 开始排位赛")
    }

    override fun upgrade() {
        println("$name 升段了！")
    }
}