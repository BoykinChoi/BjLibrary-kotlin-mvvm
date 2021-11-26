package com.boykinchoi.star.design_pattern.observer;

/**
 * 可被观察接口
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 13:23
 */
public interface Observable {
    void addObserver(Observer observable);
    void removeObserver(Observer observable);
    void notifyObservers(String message);
}
