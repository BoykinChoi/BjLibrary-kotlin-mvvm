package com.boykinchoi.star.design_pattern.state;

/**
 * 状态模式，状态接口
 * 《阿里巴巴 Java 开发手册》：当超过 3 层的 if-else 的逻辑判断代码，推荐用状态模式来重构代码。
 * 或需要大量if else 时，也可用此模式
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 14:46
 */
public interface IState {
    void register();
    void apply();
    void draw(double money);
}
