package com.boykinchoi.star.design_pattern.proxy.dynamic_p;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 中介类
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2022/6/30 13:47
 */
public class DynamicProxy implements InvocationHandler {

    /**
     * 委托类对象
     */
    private Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName() + ":代理前");
        Object result = method.invoke(obj, args);
        System.out.println(proxy.getClass().getName() + ":代理后");
        return result;
    }
}
