package com.boykinchoi.star.design_pattern.command.game;

import com.boykinchoi.star.design_pattern.command.gemecontrol.GameControl;

/**
 * 赛车游戏
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 9:44
 */
public class RacingCar implements GameControl {
    @Override
    public void up() {
        System.out.println("前进");
    }

    @Override
    public void down() {
        System.out.println("刹车");
    }

    @Override
    public void left() {
        System.out.println("左转");
    }

    @Override
    public void right() {
        System.out.println("右转");
    }

    @Override
    public void a() {
        System.out.println("伸他一脚");
    }

    @Override
    public void b() {
        System.out.println("氮气加速");
    }

    @Override
    public void start() {
        System.out.println("开始游戏");
    }

    @Override
    public void select() {
        System.out.println("切换模式：道具/竞速");
    }
}
