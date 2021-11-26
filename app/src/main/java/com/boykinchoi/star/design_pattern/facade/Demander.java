package com.boykinchoi.star.design_pattern.facade;

/**
 * 需求输出人1，直接对开发和测试提需求
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 17:31
 */
public class Demander {

    private Developer developer = new Developer();
    private Tester tester = new Tester();

    public void deman(String name) {
        System.out.println("产品提出需求：" + name);
        developer.develop(name);
        tester.test(name);
    }
}
