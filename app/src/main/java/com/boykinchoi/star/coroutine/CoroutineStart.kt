package com.boykinchoi.star.coroutine

import android.util.Log
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/6/27 10:24
 */
class CoroutineStart {

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
        val atomicJob = GlobalScope.launch(start = kotlinx.coroutines.CoroutineStart.ATOMIC) {
            Log.d("testCoroutineStart", "CoroutineStart.ATOMIC 挂起前")
            delay(2000L)
            Log.d("testCoroutineStart", "CoroutineStart.ATOMIC 挂起后")
        }
        atomicJob.cancel()


        // UNDISPATCHED 协程在这种模式下会直接开始在当前线程下执行，直到运行到第一个挂起点。这听起来有点像 ATOMIC，
        // 不同之处在于UNDISPATCHED是不经过任何调度器就开始执行的。当然遇到挂起点之后的执行，
        // 将取决于挂起点本身的逻辑和协程上下文中的调度器。
        val unDispatchJob = GlobalScope.launch(start = kotlinx.coroutines.CoroutineStart.UNDISPATCHED) {
            Log.d("testCoroutineStart", "CoroutineStart.UNDISPATCHED 挂起前")
            delay(2000L)
            Log.d("testCoroutineStart", "CoroutineStart.UNDISPATCHED 挂起后")
        }
        unDispatchJob.cancel()
    }

}