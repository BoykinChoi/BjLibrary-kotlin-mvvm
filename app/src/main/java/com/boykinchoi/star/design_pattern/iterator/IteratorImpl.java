package com.boykinchoi.star.design_pattern.iterator;

/**
 * 迭代器实现类
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 11:21
 */
public class IteratorImpl implements Iiterator {

    private ILIst2 ilIst2;
    private int index;

    public IteratorImpl(ILIst2 ilIst2) {
        this.ilIst2 = ilIst2;
        this.index = 0;
    }

    @Override
    public Object next() {
        return ilIst2.get(index++);
    }

    @Override
    public boolean hasNext() {
        if (index < ilIst2.size()) {
            return true;
        }
        return false;
    }
}
