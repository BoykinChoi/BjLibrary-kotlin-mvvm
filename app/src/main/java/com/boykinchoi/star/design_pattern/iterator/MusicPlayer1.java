package com.boykinchoi.star.design_pattern.iterator;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 11:04
 */
public class MusicPlayer1 {
    private SongList1 songList1;

    public MusicPlayer1() {
        songList1 = new SongList1(5);
        songList1.add("成吉思汗");
        songList1.add("男儿当自强");
        songList1.add("数字人生");
    }

    public void playMusicByLoop() {
        for (int i = 0; i < songList1.size(); i++) {
            System.out.println("MusicPlayer1开始播放：" + songList1.get(i));
        }
    }
}


