package com.boykinchoi.star.design_pattern.static_proxy.delegate

/** 委托人，本场景中的游戏菜B
 * 在kotlin 中，委托用关键字by 修饰，by后面就是你委托的对象，可以是一个表达式。因此在本例中，
 * 通过by player 委托给了具体的被委托对象
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/11/23 16:38
 */
class DelegateMan(private val player: IGamePlayer) : IGamePlayer by player