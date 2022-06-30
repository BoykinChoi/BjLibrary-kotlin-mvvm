package com.boykinchoi.star.design_pattern.adapter.animal;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2022/6/30 10:07
 */
public class Dog implements IAnimal {
    @Override
    public void makeSound() {
        System.out.println("狗狗:汪汪汪...");
    }
}
