package com.boykinchoi.star.design_pattern.static_proxy;

import java.util.HashSet;
import java.util.Set;

/**
 * 中国网络（就是代理）
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 17:12
 */
public class ChineseNetwork implements Internet {
    private Set<String> disable;
    private Internet internet;

    public ChineseNetwork(Internet internet) {
        this.internet = internet;
        this.disable = new HashSet<>();
        disable.add("www.google.com");
        disable.add("www.facebook.com");
        disable.add("www.ppp.com");
    }

    @Override
    public String access(String domain) {
        if (disable.contains(domain)) {
            System.out.println("在中国" + domain + "这个网站太反动，禁止访问哦");
            return domain + "禁止访问哦";
        }
        return internet.access(domain);
    }
}
