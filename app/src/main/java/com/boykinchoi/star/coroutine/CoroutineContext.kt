package com.boykinchoi.star.coroutine

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/6/27 10:23
 */
class CoroutineContext {

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
}