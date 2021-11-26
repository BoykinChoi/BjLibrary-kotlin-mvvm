package com.boykinchoi.star.design_pattern.factory.car;

import com.boykinchoi.star.design_pattern.factory.CarInstall;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 9:22
 */
public class BenzCar implements CarInstall {
    @Override
    public void installEngine() {
        System.out.println("奔驰车安装发动机");
    }

    @Override
    public void installWheel() {
        System.out.println("奔驰车安装轮胎");
    }
}
