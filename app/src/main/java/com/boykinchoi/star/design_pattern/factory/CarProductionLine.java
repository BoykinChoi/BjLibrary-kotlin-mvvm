package com.boykinchoi.star.design_pattern.factory;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 9:24
 */
public abstract class CarProductionLine
{
    public abstract CarInstall carInstall();

    public void installCar() {
        CarInstall install = carInstall();
        install.installEngine();
        install.installWheel();
        System.out.println("组装完成一部" + install.getClass().getSimpleName());
    }
}
