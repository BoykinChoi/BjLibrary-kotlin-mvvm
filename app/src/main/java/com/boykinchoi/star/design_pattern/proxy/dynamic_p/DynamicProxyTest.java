package com.boykinchoi.star.design_pattern.proxy.dynamic_p;

import java.lang.reflect.Proxy;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2022/6/30 14:04
 */
public class DynamicProxyTest {

    public void test() {
        //创建中介类
        DynamicProxy inter = new DynamicProxy(new Vendor2());
        //加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        //获取代理类实例sel
        Sell sell = (Sell) (Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[]{Sell.class}, inter));
        //通过代理类对象调用代理类方法，实际上会转到invoke方法调用
        sell.ad("我们这个代理价钱最低");
        sell.sell();
    }
}
