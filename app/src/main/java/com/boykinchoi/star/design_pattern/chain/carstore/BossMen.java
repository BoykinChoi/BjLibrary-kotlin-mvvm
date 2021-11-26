package com.boykinchoi.star.design_pattern.chain.carstore;

import com.boykinchoi.star.design_pattern.chain.Buyer;

import java.util.Random;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 13:53
 */
public class BossMen implements Manager {
    private Manager nextManager;

    public void bindNextManager(Manager nextManager) {
        this.nextManager = nextManager;
    }

    @Override
    public void permission(Buyer buyer) {
        boolean permission = new Random().nextBoolean();
        if (permission) {
            buyer.setBossPermissionBuy(true);
            System.out.println("我是Boss，您希望的价钱" + buyer.bidPrice() + "是不，卖给你了，笑一个");
            buyer.buyCarSuccess();
        } else {
            System.out.println("Boss:滚犊子");
        }

    }
}
