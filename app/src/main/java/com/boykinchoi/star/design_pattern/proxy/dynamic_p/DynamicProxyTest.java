package com.boykinchoi.star.design_pattern.proxy.dynamic_p;

import java.lang.reflect.Proxy;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2022/6/30 14:04
 */
public class DynamicProxyTest {

    public void test() {
        DynamicProxy inter = new DynamicProxy(new Vendor());
        //加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        Sell sell = (Sell) (Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[]{Sell.class}, inter));
        sell.ad();
        sell.sell();
    }
}
