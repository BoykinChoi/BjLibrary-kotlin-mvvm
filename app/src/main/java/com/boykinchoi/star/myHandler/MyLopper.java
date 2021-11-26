package com.boykinchoi.star.myHandler;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/22 15:37
 */
public class MyLopper {
    static final ThreadLocal<MyLopper> sThreadLocal = new ThreadLocal<>();
    public MyMessageQueue mQueue;

    public MyLopper() {
        mQueue = new MyMessageQueue();
    }

    public static void prepareMainLooper(){

    }

    public static void prepare() {
        sThreadLocal.set(new MyLopper());
    }

    public static void loop() {
        MyLopper me = myLopper();
        MyMessage msg;
        for (; ; ) {
            msg = me.mQueue.next();
            if (msg == null || msg.target == null) {
                System.out.println("空消息");
                return;
            }
            msg.target.handleMessage(msg);
        }
    }

    /**
     * 获取到的是当前线程的MyLopper
     *
     * @return
     */
    public static MyLopper myLopper() {
        return sThreadLocal.get();
    }
}
