package com.boykinchoi.star.design_pattern.adapter;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 14:00
 */
public class BritishCharger implements IBritishCharger {

    @Override
    public void chargeByBritishStandard(int l, int n, int e) {
        System.out.println("用英式充电器充电，火线零线地线");
    }
}
