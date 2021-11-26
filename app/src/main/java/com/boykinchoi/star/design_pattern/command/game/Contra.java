package com.boykinchoi.star.design_pattern.command.game;

import com.boykinchoi.star.design_pattern.command.gemecontrol.GameControl;

/**
 * 魂斗罗
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/22 15:21
 */
public class Contra implements GameControl {
    @Override
    public void up() {
        System.out.println("枪头向上");
    }

    @Override
    public void down() {
        System.out.println("蹲下");
    }

    @Override
    public void left() {
        System.out.println("往前走");
    }

    @Override
    public void right() {
        System.out.println("往后走");
    }

    @Override
    public void a() {
        System.out.println("发射子弹");
    }

    @Override
    public void b() {
        System.out.println("跳跃");
    }



    @Override
    public void start() {
        System.out.println("开始游戏");
    }

    @Override
    public void select() {
        System.out.println("单人/双人游戏");
    }
}
