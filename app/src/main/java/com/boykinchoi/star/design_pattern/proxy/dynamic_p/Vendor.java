package com.boykinchoi.star.design_pattern.proxy.dynamic_p;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2022/6/30 13:36
 */
public class Vendor implements Sell {
    @Override
    public String sell() {
      System.out.println("vendor sell");
        return "vendor sell";
    }

    @Override
    public void ad(String slogan) {
        System.out.println("vendor ad");
    }
}
