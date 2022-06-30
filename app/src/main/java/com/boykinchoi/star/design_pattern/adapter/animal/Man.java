package com.boykinchoi.star.design_pattern.adapter.animal;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2022/6/30 10:33
 */
public class Man {

    public void speakTo(IFriend iFriend){
        System.out.println("人：朋友，你好吗？");
        iFriend.speak();
    }
}
