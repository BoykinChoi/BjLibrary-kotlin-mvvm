package com.boykinchoi.star.design_pattern.abstract_factory.wheel_factory;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 10:23
 */
public class MichelinWheel implements WheelMade {
    @Override
    public void madeWheel() {
        System.out.println("制造了米其林轮胎");
    }
}
