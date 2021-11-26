package com.boykinchoi.star.design_pattern.singleton;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 13:23
 */
public class CarSingleton1 {
    private static CarSingleton1 singleton = new CarSingleton1();

    public static CarSingleton1 getInstance() {
        return singleton;
    }
}
