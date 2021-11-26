package com.boykinchoi.star.design_pattern.abstract_factory.engine_factory;

import com.boykinchoi.star.design_pattern.abstract_factory.engine_factory.EngineMade;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/16 10:20
 */
public class HondaEngine implements EngineMade {

    @Override
    public void madeEngine() {
        System.out.println("制造了一个本田发动机");
    }
}
