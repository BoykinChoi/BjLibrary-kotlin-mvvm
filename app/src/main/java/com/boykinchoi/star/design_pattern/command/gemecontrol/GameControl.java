package com.boykinchoi.star.design_pattern.command.gemecontrol;

import com.boykinchoi.star.design_pattern.command.gemecontrol.BaseGameControl;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/22 15:15
 */
public interface GameControl extends BaseGameControl {
    void up();

    void down();

    void left();

    void right();

    void a();

    void b();
}
