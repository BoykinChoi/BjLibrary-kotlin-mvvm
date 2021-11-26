package com.boykinchoi.star.design_pattern.iterator;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 10:57
 */
public class SongList1 implements IList1 {
    private String[] songs;
    private int index;
    private int size;

    public SongList1(int size) {
        this.songs = new String[size];
        this.index = 0;
        size = 0;
    }

    @Override
    public void add(Object object) {
        songs[index++] = (String) object;
        this.size++;
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
