package com.boykinchoi.star.design_pattern.mediator;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 11:47
 */
public class Person {

    public Person(String name) {
        System.out.println("我是" + name + "，我找个了中介帮我找工作");
    }

    public void lookingForJob(Mediator mediator) {
        mediator.supplyJob();
    }
}
