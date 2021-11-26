package com.boykinchoi.star.design_pattern.command.command;

import com.boykinchoi.star.design_pattern.command.gemecontrol.GameControl;

/**
 * 控制上下命名
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 9:51
 */
public class UpDownCommand implements Command {
    private GameControl gameControl;

    public UpDownCommand(GameControl gameControl) {
        this.gameControl = gameControl;
    }

    @Override
    public void exe() {
        gameControl.up();
    }

    @Override
    public void unexe() {
        gameControl.down();
    }
}
