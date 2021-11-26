package com.boykinchoi.star.design_pattern.chain.carstore;

import com.boykinchoi.star.design_pattern.chain.Buyer;

import java.util.Random;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 13:53
 */
public class TeamLeader implements Manager {
    private Manager nextManager;

    public void bindNextManager(Manager nextManager) {
        this.nextManager = nextManager;
    }

    @Override
    public void permission(Buyer buyer) {
        boolean permission = new Random().nextBoolean();
        if (permission) {
            buyer.setTeamPermissionBuy(true);
            System.out.println("我是销售组长，您希望的价钱" + buyer.bidPrice() + "是不，我这边没问题，但要向上级请示一下哈");
            if (nextManager != null) {
                nextManager.permission(buyer);
            }
        } else {
            System.out.println("销售组长:这个价出不了");
        }

    }
}
