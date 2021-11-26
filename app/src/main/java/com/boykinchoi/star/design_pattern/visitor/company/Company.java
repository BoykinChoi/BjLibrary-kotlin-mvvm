package com.boykinchoi.star.design_pattern.visitor.company;

import com.boykinchoi.star.design_pattern.visitor.visitor.Visitor;

/**
 * 企业，被访问者
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/27 11:23
 */
public abstract class Company {
    public abstract void accept(Visitor visitor);
}
