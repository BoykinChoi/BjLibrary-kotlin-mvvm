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
//        GlobalScope.launch {
//            for (index in 1 until 50) {
//                launch {
//                    Log.d("launch$index", "启动协程")
//                }
//            }
//        }
        //指定Dispatchers为Main,子协程将同步执行
//        GlobalScope.launch(context = Dispatchers.Main) {
//            for (index in 1 until 20) {
//                launch {
//                    Log.d("launch$index", "Dispatchers.Main 启动协程")
//                }
//            }
//        }

        // 通过withContext 切换线程
        val job: Job = GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                "我是网络请求结果"
            }
            Log.d("launch withContext", "result=${result}")
        }

    }

    /**
     *
     * testCoroutineContext
     */
    fun testCoroutineContext() {
        // 通过+号我们可以把多个Element整合到一个集合中,同时我们也发现
        // 三个上下文中的Job是同一个对象。
        // 第二个上下文在第一个的基础上增加了一个新的CoroutineName,新增的CoroutineName替换了第一个上下文中的CoroutineName。
        // 第三个上下文在第二个的基础上又增加了一个新的CoroutineName和Dispatchers,同时他们也替换了第二个上下文中的CoroutineName和Dispatchers。

        val coroutineContext1 = Job() + CoroutineName("这是第一个上下文")
        println("coroutineContext=$coroutineContext1")
        val coroutineContext2 = coroutineContext1 + Dispatchers.Default + CoroutineName("这是第二个上下文")
        println("coroutineContext=$coroutineContext2")
        val coroutineContext3 = coroutineContext2 + Dispatchers.Main + CoroutineName("这是第三个上下文")
        println("coroutineContext=$coroutineContext3")
    }
}