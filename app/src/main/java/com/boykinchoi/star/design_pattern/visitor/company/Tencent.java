package com.boykinchoi.star.design_pattern.visitor.company;

import com.boykinchoi.star.design_pattern.visitor.visitor.Visitor;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/27 11:27
 */
public class Tencent extends Company {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String entertainBelowProvincialLeader(String leader) {
        return "Alibaba 接待" + leader + "：十菜一汤";
    }

    public String entertainAboveNationalLeader(String leader) {
        return "Alibaba 接待" + leader + "：十四菜两汤";
    }
}
