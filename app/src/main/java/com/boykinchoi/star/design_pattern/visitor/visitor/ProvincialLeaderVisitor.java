package com.boykinchoi.star.design_pattern.visitor.visitor;

import com.boykinchoi.star.design_pattern.visitor.company.Alibaba;
import com.boykinchoi.star.design_pattern.visitor.company.Tencent;

/** 省领导访问
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/27 11:36
 */
public class ProvincialLeaderVisitor implements Visitor {
    @Override
    public void visit(Alibaba alibaba) {
        System.out.println(alibaba.entertainBelowProvincialLeader("省领导"));
    }

    @Override
    public void visit(Tencent tencent) {
        System.out.println(tencent.entertainBelowProvincialLeader("省领导"));
    }
}
