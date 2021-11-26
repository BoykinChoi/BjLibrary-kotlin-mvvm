package com.boykinchoi.star.design_pattern.bridge.software;

import com.boykinchoi.star.design_pattern.bridge.phone.Phone;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 17:56
 */
public class FireFox implements Software {
    @Override
    public void open(Phone phone) {
        System.out.println("使用" + phone.getSys() + "系统手机，打开浏览器（FireFox）");
    }
}
