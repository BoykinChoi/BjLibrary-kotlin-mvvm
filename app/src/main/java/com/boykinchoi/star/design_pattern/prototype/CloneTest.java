package com.boykinchoi.star.design_pattern.prototype;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/27 13:58
 */
public class CloneTest {

    public void bookCreate() {
        CBook book = new CBook("七龙珠", "鸟山明", "xxxxx");
        System.out.println(book.getName() + "第1本");
        for (int i = 0; i < 8; i++) {
            CBook cloneBook = (CBook) book.clone();
            System.out.println(cloneBook.getName() + "第" + (i + 2) + "本");
        }
    }
}

