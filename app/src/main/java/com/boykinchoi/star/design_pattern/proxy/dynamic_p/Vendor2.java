package com.boykinchoi.star.design_pattern.proxy.dynamic_p;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2022/6/30 13:36
 */
public class Vendor2 implements Sell {
    @Override
    public String sell() {
      System.out.println("vendor2 do sell");
      return "vendor2 sell";
    }

    @Override
    public void ad(String slogan) {
        System.out.println("vendor2 do ad");
    }
}
