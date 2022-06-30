package com.boykinchoi.star.design_pattern.adapter.charger;

/**
 * 中式充电
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 14:04
 */
public interface IChineseCharger {
    /**
     * @param l 火线live
     * @param n 零线null
     */
    void chargeByChineseStandard(int l,int n) throws Exception;
}
