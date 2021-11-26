package com.boykinchoi.star.design_pattern.abstract_factory.wheel_factory;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 10:02
 */
public class BmwWheel implements WheelMade {
    @Override
    public void madeWheel() {
        System.out.println("制造了宝马牌轮胎");
    }
}
