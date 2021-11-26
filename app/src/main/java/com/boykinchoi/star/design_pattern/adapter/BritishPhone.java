package com.boykinchoi.star.design_pattern.adapter;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 14:08
 */
public class BritishPhone {
    private ChargerAdapter chargerAdapter;

    public BritishPhone(ChargerAdapter chargerAdapter) {
        this.chargerAdapter = chargerAdapter;
    }

    public void chargeing() throws Exception {
        if (this.chargerAdapter == null) {
            throw new Exception("插头不适合，请使用插头适配器充电");
        }
//        chargerAdapter.chargeByChineseStandard();
    }
}
