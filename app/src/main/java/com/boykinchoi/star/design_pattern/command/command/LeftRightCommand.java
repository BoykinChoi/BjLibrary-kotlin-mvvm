package com.boykinchoi.star.design_pattern.command.command;

import com.boykinchoi.star.design_pattern.command.gemecontrol.GameControl;

/**
 * 控制左右命名
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 9:51
 */
public class LeftRightCommand implements Command {
    private GameControl gameControl;

    public LeftRightCommand(GameControl gameControl) {
        this.gameControl = gameControl;
    }

    @Override
    public void exe() {
        gameControl.left();
    }

    @Override
    public void unexe() {
        gameControl.right();
    }
}
