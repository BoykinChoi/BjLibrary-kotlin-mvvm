package com.boykinchoi.star.design_pattern.facade;

/**
 * 技术Leader
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 17:37
 */
public class TLeader {
    private Developer developer = new Developer();
    private Developer2 developer2 = new Developer2();
    private Tester tester = new Tester();

    public void projectStart(String name) {
        System.out.println("技术经理分配任务：");
        developer2.develop(name);
        developer.develop(name);
        tester.test(name);
    }
}
