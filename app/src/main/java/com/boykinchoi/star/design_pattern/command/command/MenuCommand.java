package com.boykinchoi.star.design_pattern.command.command;

import com.boykinchoi.star.design_pattern.command.gemecontrol.GameControl;

/**
 * 控制菜单键
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 9:51
 */
public class MenuCommand implements Command {
    private GameControl gameControl;

    public MenuCommand(GameControl gameControl) {
        this.gameControl = gameControl;
    }

    @Override
    public void exe() {
        gameControl.start();
    }

    @Override
    public void unexe() {
        gameControl.select();
    }
}
