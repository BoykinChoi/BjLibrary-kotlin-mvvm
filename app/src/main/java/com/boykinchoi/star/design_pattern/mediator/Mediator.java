package com.boykinchoi.star.design_pattern.mediator;

import com.boykinchoi.star.design_pattern.mediator.boss.Bank;
import com.boykinchoi.star.design_pattern.mediator.boss.Company;
import com.boykinchoi.star.design_pattern.mediator.boss.IJob;
import com.boykinchoi.star.design_pattern.mediator.boss.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 11:48
 */
public class Mediator implements IJob {
    private List<IJob> jobList;

    public Mediator() {
        this.jobList = new ArrayList<>();
        jobList.add(new Company());
        jobList.add(new Store());
        jobList.add(new Bank());
    }

    @Override
    public void supplyJob() {
        for (int i = 0; i < jobList.size(); i++) {
            jobList.get(i).supplyJob();
        }
    }
}
