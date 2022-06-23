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

    /**
     *
     * 协程上下文
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

    /**
     * 协程启动模式
     */
    fun testCoroutineStart() {
        // DEFAULT 默认启动模式，我们可以称之为饿汉启动模式，因为协程创建后立即开始调度，虽然是立即调度，
        // 但不是立即执行，有可能在执行前被取消
        val defJob = GlobalScope.launch {
            Log.d("testCoroutineStart", "CoroutineStart.DEFAULT")
        }
        defJob.cancel()


        // LAZY 懒汉启动模式，启动后并不会有任何调度行为，直到我们需要它执行的时候才会产生调度。
        // 也就是说只有我们主动的调用Job的start、join或者await等函数时才会开始调度。
        val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY) {
            Log.d("testCoroutineStart", "CoroutineStart.LAZY")
        }
        lazyJob.start()


        // ATOMIC 一样也是在协程创建后立即开始调度，但是它和DEFAULT模式有一点不一样，
        // 通过ATOMIC模式启动的协程执行到第一个挂起点之前是不响应cancel 取消操作的，
        // ATOMIC一定要涉及到协程挂起后cancel 取消操作的时候才有意义。
        val atomicJob = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
            Log.d("testCoroutineStart", "CoroutineStart.ATOMIC 挂起前")
            delay(2000L)
            Log.d("testCoroutineStart", "CoroutineStart.ATOMIC 挂起后")
        }
        atomicJob.cancel()


        val unDispatchJob = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
            Log.d("testCoroutineStart", "CoroutineStart.UNDISPATCHED 挂起前")
            delay(2000L)
            Log.d("testCoroutineStart", "CoroutineStart.UNDISPATCHED 挂起后")
        }
        unDispatchJob.cancel()
    }

    /**
     * - 顶级作用域 --> 没有父协程的协程所在的作用域称之为顶级作用域。
     *
     * - 协同作用域 --> 在协程中启动一个协程，新协程为所在协程的子协程。子协程所在的作用域默认为协同作用域。
     * 此时子协程抛出未捕获的异常时，会将异常传递给父协程处理，如果父协程被取消，则所有子协程同时也会被取消。
     *
     * - 主从作用域 官方称之为监督作用域。与协同作用域一致，区别在于该作用域下的协程取消操作的单向传播性，
     * 子协程的异常不会导致其它子协程取消。但是如果父协程被取消，则所有子协程同时也会被取消。
     *
     * - 父协程需要等待所有的子协程执行完毕之后才会进入Completed状态，不管父协程自身的协程体是否已经执行完成。
     *
     */
    fun testCoroutineScope() {
        GlobalScope.launch(Dispatchers.Main) {
            Log.d("testCoroutineScope", "父协程上下文$coroutineContext")
            launch(CoroutineName("第一个子协程")) {
                Log.d("testCoroutineScope", "第一个子协程上下文$coroutineContext")
            }
            launch(Dispatchers.Unconfined + CoroutineName("第二个子协程")) {
                Log.d("testCoroutineScope", "第二个子协程上下文$coroutineContext")
            }
        }

    }

    /**
     * 协同作用域
     *  - 在协程中启动一个协程，新协程为所在协程的子协程。子协程所在的作用域默认为协同作用域。此时子协程抛出未捕获的异常时，
     *  会将异常传递给父协程处理，如果父协程被取消，则所有子协程同时也会被取消。
     */
    fun testCoroutineScope2() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("testCoroutineScope", "协程异常$throwable")
        }
        GlobalScope.launch(Dispatchers.Main + CoroutineName("Scope1") + coroutineExceptionHandler) {
            Log.d("testCoroutineScope", "-----Scope1-----")
            launch(CoroutineName("Scope2") + coroutineExceptionHandler) {
                Log.d("testCoroutineScope", "-----Scope2-1-----")
                throw NullPointerException("空空指针")
                Log.d("testCoroutineScope", "-----Scope2-2-----")
            }
            val scope3Job = launch(CoroutineName("Scope3") + coroutineExceptionHandler) {
                Log.d("testCoroutineScope", "-----Scope3-1-----")
                delay(1000L)
                Log.d("testCoroutineScope", "-----Scope3-2-----")
            }
            scope3Job.join()
        }
    }

    /**
     * 主从(监督)作用域与协同作用域一致，区别在于该作用域下的协程取消操作的单向传播性，
     * 子协程的异常不会导致其它子协程取消。分析主从(监督)作用域的时候，我们需要用到supervisorScope或者SupervisorJob
     */
    fun testCoroutineScope3() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("testCoroutineScope", "协程异常$throwable")
        }
        GlobalScope.launch(CoroutineName("父Scope") + exceptionHandler) {
            Log.d("testCoroutineScope", "父Scope1")
            supervisorScope {
                Log.d("testCoroutineScope", "-----supervisorScope1-----")
                launch(CoroutineName("scope2")) {
                    Log.d("testCoroutineScope", "-----scope2-1-----")
                    throw NullPointerException("空指针针")
                    Log.d("testCoroutineScope", "-----scope2-2-----")
                    launch(CoroutineName("scope3")) {
                        Log.d("testCoroutineScope", "-----scope3-1-----")
                        delay(2000L)
                        Log.d("testCoroutineScope", "-----scope3-2-----")
                    }
                }
                launch(CoroutineName("scope4")) {
                    Log.d("testCoroutineScope", "-----scope4-1-----")
                    delay(2000L)
                    Log.d("testCoroutineScope", "-----scope4-2-----")
                }
                Log.d("testCoroutineScope", "-----supervisorScope2-----")
            }
            Log.d("testCoroutineScope", "-----父Scope2-----")
        }
    }
}