package com.boykinchoi.star.design_pattern.strategy;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 13:34
 */
public class CarWashProcess {
    private WashCar washCar;

    public CarWashProcess(WashCar washCar) {
        this.washCar = washCar;
    }

    public void washingCar() {
        washCar.wash();
    }
}
