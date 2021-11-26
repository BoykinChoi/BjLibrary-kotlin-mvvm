package com.boykinchoi.star.design_pattern.visitor.visitor;

import com.boykinchoi.star.design_pattern.visitor.company.Alibaba;
import com.boykinchoi.star.design_pattern.visitor.company.Tencent;

/**
 * 访问者 —— 领导人
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/27 11:26
 */
public interface Visitor {
    void visit(Alibaba alibaba);
    void visit(Tencent tencent);
}
