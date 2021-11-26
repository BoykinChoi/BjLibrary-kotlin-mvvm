package com.boykinchoi.star.design_pattern.factory.factory;

import com.boykinchoi.star.design_pattern.factory.CarInstall;
import com.boykinchoi.star.design_pattern.factory.CarProductionLine;
import com.boykinchoi.star.design_pattern.factory.car.BenzCar;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 9:31
 */
public class BenzFactory extends CarProductionLine {
    @Override
    public CarInstall carInstall() {
        return new BenzCar();
    }
}
