package com.boykinchoi.star.design_pattern.state;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 14:57
 */
public class NewUser {
    private IState state;

    public NewUser(IState state) {
        this.state = state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public void register() {
        state.register();
    }

    public void apply() {
        state.apply();
    }

    public void drawMoney(int money) {
        state.draw(money);
    }
}
