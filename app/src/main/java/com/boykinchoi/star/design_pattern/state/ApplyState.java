package com.boykinchoi.star.design_pattern.state;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 14:49
 */
public class ApplyState implements IState {
    @Override
    public void register() {
        System.out.println("授信状态，不可以注册");
    }

    @Override
    public void apply() {
        System.out.println("授信状态，不可以申请授信");
    }

    @Override
    public void draw(double money) {
        System.out.println("授信状态，可以申请借贷:" + money + "元");
    }
}
