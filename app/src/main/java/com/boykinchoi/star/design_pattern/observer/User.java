package com.boykinchoi.star.design_pattern.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 13:34
 */
public class User implements Observable, Observer {
    private List<Observer> friends;
    private String name;

    public User(String name) {
        this.friends = new LinkedList<>();
        this.name = name;
    }

    @Override
    public void addObserver(Observer observable) {
        friends.add(observable);
    }

    @Override
    public void removeObserver(Observer observable) {
        friends.remove(observable);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer friend : friends) {
            friend.update(name, message);
        }
    }

    @Override
    public void update(String name, String message) {
        System.out.println("【" + this.name + "】看到【" + name + "】发的朋友圈：" + message);
    }

    public void sendMessage(String message) {
        this.notifyObservers(message);
    }
}
