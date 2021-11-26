package com.boykinchoi.star.design_pattern.singleton;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 13:23
 */
public class CarSingleton2 {
    private static CarSingleton2 singleton;

    public static CarSingleton2 getInstance() {
        if (singleton == null) {
            singleton = new CarSingleton2();
        }
        return singleton;
    }
}
