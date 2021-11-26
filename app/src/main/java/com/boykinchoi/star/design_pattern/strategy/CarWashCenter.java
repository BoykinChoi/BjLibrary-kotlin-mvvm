package com.boykinchoi.star.design_pattern.strategy;

import com.boykinchoi.star.design_pattern.strategy.strategy.AclassStrategy;
import com.boykinchoi.star.design_pattern.strategy.strategy.BclassStrategy;
import com.boykinchoi.star.design_pattern.strategy.strategy.CclassStrategy;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 13:40
 */
public class CarWashCenter {
    public static final int A = 1;
    public static final int B = 2;
    public static final int C = 3;

    public void washCar(int mode) {
        WashCar washStrategy = null;
        switch (mode) {
            case A:
                washStrategy = new AclassStrategy();
                break;
            case B:
                washStrategy = new BclassStrategy();
                break;
            case C:
                washStrategy = new CclassStrategy();
                break;
            default:
        }
        CarWashProcess carWashProcess = new CarWashProcess(washStrategy);
        carWashProcess.washingCar();
    }
}
