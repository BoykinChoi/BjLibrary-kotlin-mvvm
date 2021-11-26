package com.boykinchoi.star.design_pattern.singleton;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 13:23
 */
public class CarSingleton4 {
    private static CarSingleton4 singleton;

    public static CarSingleton4 getInstance() {
        if (singleton == null) {
            synchronized (CarSingleton4.class) {
                if (singleton == null) {
                    singleton = new CarSingleton4();
                }
            }
        }
        return singleton;
    }
}
