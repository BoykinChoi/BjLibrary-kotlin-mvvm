package com.boykinchoi.star.design_pattern.strategy.strategy;

import com.boykinchoi.star.design_pattern.strategy.WashCar;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 13:36
 */
public class AclassStrategy implements WashCar {
    @Override
    public void wash() {
        System.out.println("A级流程：");
        System.out.println("[洗涤] 1 次，每次 10 分钟");
        System.out.println("[漂洗] 1 次，每次 10 分钟");
        System.out.println("[脱水] 5 分钟");
        System.out.println("总共耗时：45 分钟");
    }
}
