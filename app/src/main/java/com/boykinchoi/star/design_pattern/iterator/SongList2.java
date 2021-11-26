package com.boykinchoi.star.design_pattern.iterator;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 11:17
 */
public class SongList2 implements ILIst2 {
    private String[] songs;
    private int index;
    private int size;

    public SongList2(int size) {
        songs = new String[size];
        index = 0;
        size = 0;
    }

    @Override
    public Iiterator iterator() {
        return new IteratorImpl(this);
    }

    @Override
    public void add(Object object) {
        songs[index++] = (String) object;
        size++;
    }

    @Override
    public Object get(int index) {
        if (index < size) {
            return songs[index];
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
