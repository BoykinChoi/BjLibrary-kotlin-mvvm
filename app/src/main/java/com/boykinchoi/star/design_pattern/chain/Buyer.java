package com.boykinchoi.star.design_pattern.chain;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 13:56
 */
public class Buyer {

    /**
     * 是否能按自己出的价钱购买
     */
    private boolean teamPermissionBuy;
    private boolean managerPermissionBuy;
    private boolean bossPermissionBuy;

    public boolean isTeamPermissionBuy() {
        return teamPermissionBuy;
    }

    public void setTeamPermissionBuy(boolean teamPermissionBuy) {
        this.teamPermissionBuy = teamPermissionBuy;
    }

    public boolean isManagerPermissionBuy() {
        return managerPermissionBuy;
    }

    public void setManagerPermissionBuy(boolean managerPermissionBuy) {
        this.managerPermissionBuy = managerPermissionBuy;
    }

    public boolean isBossPermissionBuy() {
        return bossPermissionBuy;
    }

    public void setBossPermissionBuy(boolean bossPermissionBuy) {
        this.bossPermissionBuy = bossPermissionBuy;
    }

    public void buyCarSuccess() {
        System.out.println("成功买到车了，笑了");
    }

    public int bidPrice() {
        System.out.println("小明说 188000 卖不卖");
        return 188000;
    }
}
