package com.boykinchoi.star.myHandler;

import android.app.Activity;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/9/24 9:24
 */
public class ActivityThread {

    TestHandler testHandler  = new TestHandler();

    public static void main(String[] args) {
        MyLopper.prepare();
        ActivityThread thread = new ActivityThread();
        thread.attach(false);
        MyLopper.loop();

    }

    private void attach(boolean b){
        TestActivity testActivity = new TestActivity();
        testActivity.onCreate();
        MyMessage myMessage = new MyMessage();
        myMessage.obj  = testActivity;
        // 通过 Handler 执行生命周期
        testHandler.sendMessage(myMessage);
    }

    class TestHandler extends MyHandler{

        @Override
        public void handleMessage(MyMessage msg) {
            TestActivity activity = (TestActivity) msg.obj;
            activity.onResume();
        }
    }
}
