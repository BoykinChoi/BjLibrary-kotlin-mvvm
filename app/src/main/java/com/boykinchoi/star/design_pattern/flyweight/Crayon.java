package com.boykinchoi.star.design_pattern.flyweight;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/27 10:22
 */
public class Crayon implements ICrayon {

    private String color;

    public Crayon(String color) {
        System.out.println("新建【" + color + "】颜色的铅笔");
        this.color = color;
    }

    @Override
    public void draw(String piace) {
        System.out.println("【" + color + "】颜色的铅笔，我画了【" + piace + "】");
    }
}
