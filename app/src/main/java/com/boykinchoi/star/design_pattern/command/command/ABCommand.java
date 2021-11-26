package com.boykinchoi.star.design_pattern.command.command;

import com.boykinchoi.star.design_pattern.command.gemecontrol.GameControl;

/**
 * 控制A B 键
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 9:51
 */
public class ABCommand implements Command {
    private GameControl gameControl;

    public ABCommand(GameControl gameControl) {
        this.gameControl = gameControl;
    }

    @Override
    public void exe() {
        gameControl.a();
    }

    @Override
    public void unexe() {
        gameControl.b();
    }
}
