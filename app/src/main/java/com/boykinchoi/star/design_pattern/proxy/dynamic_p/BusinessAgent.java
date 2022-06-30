package com.boykinchoi.star.design_pattern.proxy.dynamic_p;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2022/6/30 13:35
 */
public class BusinessAgent implements Sell {

    private Vendor vendor;

    public BusinessAgent(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public void sell() {
        System.out.println("代理：sell前");
        vendor.sell();
        System.out.println("代理：sell后");
    }

    @Override
    public void ad() {
        System.out.println("代理：广告前");
        vendor.ad();
        System.out.println("代理：广告后");
    }
}
