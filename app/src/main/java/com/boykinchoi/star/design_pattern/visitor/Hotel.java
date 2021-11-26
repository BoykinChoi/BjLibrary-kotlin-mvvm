package com.boykinchoi.star.design_pattern.visitor;

import com.boykinchoi.star.design_pattern.visitor.company.Company;
import com.boykinchoi.star.design_pattern.visitor.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/27 11:41
 */
public class Hotel {
    private List<Company> companies = new ArrayList<>();

    /**
     * 接待访问者
     *
     * @param visitor
     */
    public void entertain(Visitor visitor) {
        for (Company company : companies) {
            company.accept(visitor);
        }
    }

    public void add(Company company) {
        companies.add(company);
    }
}
