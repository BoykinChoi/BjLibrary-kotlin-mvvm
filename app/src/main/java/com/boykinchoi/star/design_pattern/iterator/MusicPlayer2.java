package com.boykinchoi.star.design_pattern.iterator;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/25 11:24
 */
public class MusicPlayer2 {
    private SongList2 songList2;

    public MusicPlayer2() {
        songList2 = new SongList2(3);
        songList2.add("千支针刺在心");
        songList2.add("泽田研二");
        songList2.add("海市蜃楼");
    }

    public void playByLoop() {
        Iiterator iiterator = songList2.iterator();
        while (iiterator.hasNext()) {
            System.out.println("MusicPlayer2开始播放：" + iiterator.next());
        }
    }
}
