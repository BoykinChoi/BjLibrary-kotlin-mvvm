package com.boykinchoi.star.design_pattern.state;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 14:49
 */
public class NoneState implements IState {
    @Override
    public void register() {
        System.out.println("游客状态，可以注册");
    }

    @Override
    public void apply() {
        System.out.println("游客状态，不能申请授信");
    }

    @Override
    public void draw(double money) {
        System.out.println("游客状态，不能申请借贷:" + money + "元");
    }
}
