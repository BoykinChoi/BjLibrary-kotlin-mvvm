package com.boykinchoi.star.design_pattern.facade;

/**
 * 需求输出人2,只需要向技术经理提需求
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 17:31
 */
public class Demander2 {

    private TLeader tLeader = new TLeader();

    public void deman(String name) {
        System.out.println("提出需求：" + name);
        tLeader.projectStart(name);
    }
}
