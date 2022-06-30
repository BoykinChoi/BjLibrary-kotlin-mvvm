package com.boykinchoi.star.design_pattern.adapter.animal;

/**
 * 万物拟人适配器
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2022/6/30 10:18
 */
public class AnimalFriendAdapter extends  FriendAdapter {

    private IAnimal animal;

    public AnimalFriendAdapter(IAnimal animal) {
        this.animal = animal;
    }

    @Override
    public void speak() {
        animal.makeSound();
        System.out.println();
    }
}
