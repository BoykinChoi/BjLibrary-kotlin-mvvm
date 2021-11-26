package com.boykinchoi.star.design_pattern.abstract_factory.factory;

import com.boykinchoi.star.design_pattern.abstract_factory.CarInstall;
import com.boykinchoi.star.design_pattern.abstract_factory.engine_factory.HondaEngine;
import com.boykinchoi.star.design_pattern.abstract_factory.wheel_factory.MichelinWheel;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 10:26
 */
public class HondaFactory implements CarInstall {
    @Override
    public void installEngine() {
        HondaEngine engine = new HondaEngine();
        engine.madeEngine();
        System.out.println("本田工厂组装了本田发动机");
    }

    @Override
    public void installWheel() {
        MichelinWheel wheel = new MichelinWheel();
        wheel.madeWheel();
        System.out.println("本田工厂组装了米其林轮胎");
    }
}
