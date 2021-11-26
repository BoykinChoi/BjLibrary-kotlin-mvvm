package com.boykinchoi.star.design_pattern.iterator;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 11:14
 */
public interface ILIst2 {
    Iiterator iterator();
    void add(Object object);
    Object get(int index);
    int size();
}
