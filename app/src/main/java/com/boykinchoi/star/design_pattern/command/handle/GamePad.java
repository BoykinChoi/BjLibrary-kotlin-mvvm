package com.boykinchoi.star.design_pattern.command.handle;

import com.boykinchoi.star.design_pattern.command.command.ABCommand;
import com.boykinchoi.star.design_pattern.command.command.LeftRightCommand;
import com.boykinchoi.star.design_pattern.command.command.MenuCommand;
import com.boykinchoi.star.design_pattern.command.command.UpDownCommand;
import com.boykinchoi.star.design_pattern.command.gemecontrol.GameControl;

/**
 * 命令发送方,游戏手柄
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/22 14:58
 */
public class GamePad {
    private LeftRightCommand leftRightCommand;
    private UpDownCommand upDownCommand;
    private ABCommand abCommand;
    private MenuCommand menuCommand;

    public void bindCommand(GameControl game) {
        setLeftRightCommand(game);
        setUpDownCommand(game);
        setAbCommand(game);
        setMenuCommand(game);
    }

    public void setLeftRightCommand(GameControl game) {
        this.leftRightCommand = new LeftRightCommand(game);
    }

    public void setUpDownCommand(GameControl game) {
        this.upDownCommand = new UpDownCommand(game);
    }

    public void setAbCommand(GameControl game) {
        this.abCommand = new ABCommand(game);
    }

    public void setMenuCommand(GameControl game) {
        this.menuCommand = new MenuCommand(game);
    }

    public void clickLeftBtn() {
        leftRightCommand.exe();
    }

    public void clickRightBtn() {
        leftRightCommand.unexe();
    }

    public void clickUpBtn() {
        upDownCommand.exe();
    }

    public void clickDownBtn() {
        upDownCommand.unexe();
    }

    public void clickABtn() {
        abCommand.exe();
    }

    public void clickBBtn() {
        abCommand.unexe();
    }

    public void clickStartBtn() {
        menuCommand.exe();
    }

    public void clickSelectBtn() {
        menuCommand.unexe();
    }
}
