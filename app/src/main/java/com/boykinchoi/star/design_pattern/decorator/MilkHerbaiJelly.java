package com.boykinchoi.star.design_pattern.decorator;

/**
 * 牛奶龟苓膏
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 16:36
 */
public class MilkHerbaiJelly extends Decorator {

    public MilkHerbaiJelly(HerbaiJelly herbaiJelly) {
        super(herbaiJelly);
    }

    @Override
    public void process() {
        super.process();
        System.out.println("加牛奶");
    }
}
