package com.boykinchoi.star.myHandler;

import android.os.SystemClock;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/22 15:40
 */
public class MyMessageQueue {

    private MyMessage myMessage;

    /**
     * 消息入队
     *
     * @param msg
     * @param when
     * @return
     */
    boolean enqueueMessage(MyMessage msg, long when) {
        synchronized (this) {
            msg.when = when;
            MyMessage p = myMessage;
            if (p == null || when == 0 || when < p.when) {
                // New head, wake up the event queue if blocked.
                msg.next = p;
                myMessage = msg;
            } else {
                // Inserted within the middle of the queue.  Usually we don't have to wake
                // up the event queue unless there is a barrier at the head of the queue
                // and the message is the earliest asynchronous message in the queue.
                MyMessage prev;
                for (;;) {
                    prev = p;
                    p = p.next;
                    if (p == null || when < p.when) {
                        break;
                    }
                }
                msg.next = p;
                // invariant: p == prev.next
                prev.next = msg;
            }
        }
        return true;
    }

    /**
     * 获取下个消息
     *
     * @return
     */
    public MyMessage next() {
        for (; ; ) {
            // Try to retrieve the next message.  Return if found.
            final long now = SystemClock.uptimeMillis();
            MyMessage prevMsg = null;
            MyMessage msg = myMessage;
            if (msg != null && msg.target == null) {
                do {
                    prevMsg = msg;
                    msg = msg.next;
                } while (msg != null);
            }
            if (msg != null) {
                if (now < msg.when) {
                  // Next message is not ready.  Set a timeout to wake up when it is ready.
                } else {
                    // Got a message.
                    if (prevMsg != null) {
                        prevMsg.next = msg.next;
                    } else {
                        myMessage = msg.next;
                    }
                    msg.next = null;
                    return msg;
                }
            }else {
                // no more message
            }
        }
    }
}
