package com.boykinchoi.star.coroutine

import android.util.Log
import kotlinx.coroutines.*

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/10/21 16:23
 */
open class CoroutineTest {
    fun test() {
        // runBlocking 启动新的协程并阻塞调用它的线程，直到runBlocking运行结束才继续往下执行
        val runBlockingJob = runBlocking { Log.d("coroutine", "runBlocking启动协程") }
        // launch async 启动新的协程不用阻塞线程
        Log.d("runBlockingJob", "$runBlockingJob")
        val launchJob = GlobalScope.launch { Log.d("coroutine", "GlobalScope.launch启动协程") }
        Log.d("launchJob", "$launchJob")
        val asyncJob = GlobalScope.async {
            Log.d("coroutine", "GlobalScope.async启动协程")
            "我是返回值"
        }
        Log.d("asyncJob", "$asyncJob")
    }

    fun test2() {
        //子协程并发执行
        GlobalScope.launch {
            for (index in 1 until 50) {
                launch {
                    Log.d("launch$index", "启动协程")
                }
            }
        }
        //指定Dispatchers为Main,子协程将同步执行
        GlobalScope.launch(Dispatchers.Main) {
            for (index in 1 until 20) {
                launch {
                    Log.d("launch$index", "Dispatchers.Main 启动协程")
                }
            }
        }
    }
}