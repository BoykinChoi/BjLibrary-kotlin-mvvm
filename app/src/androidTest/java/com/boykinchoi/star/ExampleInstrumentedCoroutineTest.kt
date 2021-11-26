package com.boykinchoi.star

import android.os.Handler
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.boykinchoi.baselibrary.util.ToastUtil
import com.boykinchoi.star.myHandler.MyHandler
import com.boykinchoi.star.myHandler.MyLopper
import com.boykinchoi.star.myHandler.MyMessage

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.lang.Exception

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedCoroutineTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.boykinchoi.playread", appContext.packageName)
    }

    @Test
    fun testMyHandler() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        class TestHandler : MyHandler() {
            override fun handleMessage(msg: MyMessage?) {
                super.handleMessage(msg)
                ToastUtil.l("message = ${msg?.obj}")
            }
        }
        MyLopper.prepare()
        val testHandler = TestHandler()
        MyLopper.loop()

        Thread {
            val msg = MyMessage()
            msg.obj = "gan gan gan"
            testHandler.sendMessage(msg)
            println("message send")
        }.start()
    }
}
