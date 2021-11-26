package com.boykinchoi.star.design_pattern.abstract_factory.factory;

import com.boykinchoi.star.design_pattern.abstract_factory.wheel_factory.BmwWheel;
import com.boykinchoi.star.design_pattern.abstract_factory.CarInstall;
import com.boykinchoi.star.design_pattern.abstract_factory.engine_factory.V16Engine;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 10:05
 */
public class BmwFactory implements CarInstall {
    @Override
    public void installEngine() {
        V16Engine engine = new V16Engine();
        engine.madeEngine();
        System.out.println("宝马工厂组装了V16发动机");
    }

    @Override
    public void installWheel() {
        BmwWheel wheel = new BmwWheel();
        wheel.madeWheel();
        System.out.println("宝马工厂组装了宝马牌轮胎");

    }
}
