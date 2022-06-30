package com.boykinchoi.star.design_pattern.proxy.static_p;

/**
 * 世界网络
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/26 17:10
 */
public class WorldNetwork implements Internet {
    @Override
    public String access(String domain) {
        System.out.println("访问网站：" + domain);
        return domain + "网站内容";
    }
}
