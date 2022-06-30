package com.boykinchoi.star.coroutine

import android.util.Log
import kotlinx.coroutines.*

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/6/27 10:23
 */
class CoroutineBase {
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
        // Dispatchers调度器它确定了相关的协程在哪个线程或哪些线程上执行。协程调度器可以将协程限制在一个特定的线程执行，
        // 或将它分派到一个线程池，亦或是让它不受限地运行
        val job: Job = GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                // 返回结果为 最后一行
                "我是网络请求结果"
                211
            }
            Log.d("launch withContext", "result=${result}")
        }
    }

}