package com.boykinchoi.star.myHandler;

import android.os.SystemClock;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/22 15:40
 */
public class MyHandler {
    MyMessageQueue mQueue;

    public MyHandler() {
        MyLopper lopper = MyLopper.myLopper();
        if (lopper == null) {
            throw new RuntimeException(
                    "Can't create handler inside thread " + Thread.currentThread() + " that has not called Looper.prepare()"
            );
        }
        mQueue = lopper.mQueue;
    }

    /**
     * 处理消息
     * 子类必须实现才能接收消息
     *
     * @param msg
     */
    public void handleMessage(MyMessage msg) {

    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMessage(MyMessage msg) {
        sendMessageDelayed(msg, 0);
    }

    /**
     * 发送延时消息
     *
     * @param msg
     * @param delayMillis
     * @return
     */
    public boolean sendMessageDelayed(MyMessage msg, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return sendMessageAtTime(msg, SystemClock.uptimeMillis() + delayMillis);
    }

    public boolean sendMessageAtTime(MyMessage msg, long uptimeMillis) {
        MyMessageQueue queue = mQueue;
        return enqueueMessage(queue, msg, uptimeMillis);
    }

    /**
     * Handler.sendMessage 实际上是执行 MessageQueue.enqueueMessage 方法，将 Message 加入队列
     * @param queue
     * @param msg
     * @param uptimeMillis
     * @return
     */
    private boolean enqueueMessage(MyMessageQueue queue, MyMessage msg, long uptimeMillis) {
        msg.target = this;
        return queue.enqueueMessage(msg, uptimeMillis);
    }
}
