package com.boykinchoi.star.design_pattern.adapter;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 14:06
 */
public class ChargerAdapter implements IChineseCharger {
    private BritishCharger britishCharger;

    public ChargerAdapter(BritishCharger britishCharger) {
        this.britishCharger = britishCharger;
    }

    @Override
    public void chargeByChineseStandard(int l, int n) {
        System.out.println("使用中英式插头转换器");
        britishCharger.chargeByBritishStandard(l, n, 0);
    }
}
