package com.boykinchoi.star.design_pattern.factory;

import com.boykinchoi.star.design_pattern.factory.factory.BenzFactory;
import com.boykinchoi.star.design_pattern.factory.factory.BmwFactory;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 9:33
 */
public class Car4Store {
    public static final int BRAND_BMW = 1;
    public static final int BRAND_BENZ = 2;

    public void saleCar(int brand) throws Exception {
        CarProductionLine car = null;
        switch (brand) {
            case BRAND_BMW:
                car = new BmwFactory();
                break;
            case BRAND_BENZ:
                car = new BenzFactory();
                break;
            default:
        }
        if (car == null) {
            throw new Exception("请指定汽车品牌");
        }
        car.installCar();
    }
}
