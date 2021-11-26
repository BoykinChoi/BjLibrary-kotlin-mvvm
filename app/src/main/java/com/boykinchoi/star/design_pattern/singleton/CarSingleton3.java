package com.boykinchoi.star.design_pattern.singleton;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 13:23
 */
public class CarSingleton3 {
    private static CarSingleton3 singleton;

    public static synchronized CarSingleton3 getInstance() {
        if (singleton == null) {
            singleton = new CarSingleton3();
        }
        return singleton;
    }
}
