package com.boykinchoi.star.design_pattern.mediator.boss;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 11:44
 */
public class Bank implements IJob {
    @Override
    public void supplyJob() {
        System.out.println("Bank有一份ceo工作");
    }
}
