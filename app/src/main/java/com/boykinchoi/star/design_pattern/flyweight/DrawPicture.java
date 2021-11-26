package com.boykinchoi.star.design_pattern.flyweight;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/27 10:30
 */
public class DrawPicture {

    public void drawByXiaoMing(){
        ICrayon yellowCrayon = CrayonBag.getCrayon("yellow");
        yellowCrayon.draw("太阳");
        ICrayon blueCrayon = CrayonBag.getCrayon("blue");
        blueCrayon.draw("蓝天");
    }

    public void drawByXiaoHong(){
        ICrayon greenCrayon = CrayonBag.getCrayon("green");
        greenCrayon.draw("小树");
        ICrayon blueCrayon = CrayonBag.getCrayon("blue");
        blueCrayon.draw("河流");
        ICrayon yellowCrayon = CrayonBag.getCrayon("yellow");
        yellowCrayon.draw("小屋");
    }
}
