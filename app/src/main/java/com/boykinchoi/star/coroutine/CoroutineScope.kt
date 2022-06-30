package com.boykinchoi.star.coroutine

import android.util.Log
import kotlinx.coroutines.*
import java.lang.Exception

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/6/27 10:25
 */
class CoroutineScope {


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

    fun testCoroutineScope4() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("testCoroutineScope", "协程异常$throwable")
        }

        val coroutineScope = CoroutineScope(SupervisorJob() + CoroutineName("my coroutineScope"))
        GlobalScope.launch(Dispatchers.Main + CoroutineName("Scope1") + exceptionHandler) {
            Log.d("testCoroutineScope", "-----Scope1-----")
            with(coroutineScope){
                val scope2 = launch(CoroutineName("Scope2") +exceptionHandler) {
                    Log.d("testCoroutineScope", "-----Scope2-----")
                    throw Exception("空空")
                }
            }
        }
    }
}