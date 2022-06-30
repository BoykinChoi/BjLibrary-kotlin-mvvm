package com.boykinchoi.star.design_pattern.proxy.delegate

/** 约束类，约束是接口或者抽象类，它定义了通用的业务类型，也就是需要被代理的业务
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/11/23 16:32
 */
interface IGamePlayer {
    // 打排位
    fun rank()
    // 升段
    fun upgrade()
}