package com.boykinchoi.star.design_pattern.mediator.boss;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 11:44
 */
public class Store implements IJob {
    @Override
    public void supplyJob() {
        System.out.println("Store有一份收银员工作");
    }
}
