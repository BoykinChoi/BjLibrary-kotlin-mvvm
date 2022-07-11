package com.boykinchoi.star.design_pattern.proxy.dynamic_p;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 中介类
 * - 中介类与委托类构成了静态代理关系，在这个关系中，中介类是代理类，委托类就是委托类;
 * 代理类与中介类也构成一个静态代理关系，在这个关系中，中介类是委托类，代理类是代理类。
 * - 也就是说，动态代理关系由两组静态代理关系组成，这就是动态代理的原理。
 * - 首先通过newProxyInstance方法获取代理类实例，而后我们便可以通过这个代理类实例调用代理类的方法，
 * 对代理类的方法的调用实际上都会调用中介类(调用处理器)的invoke方法，在invoke方法中我们调用委托类的相应方法，
 * 并且可以添加自己的处理逻辑。
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
        if (args != null && args[0] != null) {
            System.out.println("Entered " + obj.getClass().getName() + "-" + method.getName() + ",with arguments{" + args[0] + "}");
        } else {
            System.out.println("Entered " + obj.getClass().getName() + "-" + method.getName());
        }
        Object result = method.invoke(obj, args);
        System.out.println("Before return:" + result);
        return result;
    }
}
