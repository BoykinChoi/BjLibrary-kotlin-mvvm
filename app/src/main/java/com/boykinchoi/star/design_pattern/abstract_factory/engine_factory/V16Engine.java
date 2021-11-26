package com.boykinchoi.star.design_pattern.abstract_factory.engine_factory;

import com.boykinchoi.star.design_pattern.abstract_factory.engine_factory.EngineMade;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 10:02
 */
public class V16Engine implements EngineMade {

    @Override
    public void madeEngine() {
        System.out.println("制造了一个v16发动机");
    }
}
