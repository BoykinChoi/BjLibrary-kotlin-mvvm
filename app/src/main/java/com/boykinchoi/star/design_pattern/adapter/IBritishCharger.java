package com.boykinchoi.star.design_pattern.adapter;

/**
 * 英式充电
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 14:04
 */
public interface IBritishCharger {
    // 参数分别为火线live，零线null，地线earth
    void chargeByBritishStandard(int l, int n, int e);
}
