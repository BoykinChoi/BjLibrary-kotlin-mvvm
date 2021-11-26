package com.boykinchoi.star.design_pattern.decorator;

/**
 * 龟苓膏装饰器
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 16:31
 */
public abstract class Decorator extends HerbaiJelly {

    private HerbaiJelly herbaiJelly;

    public Decorator(HerbaiJelly herbaiJelly) {
        this.herbaiJelly = herbaiJelly;
    }

    @Override
    public void process() {
        this.herbaiJelly.process();
    }
}
