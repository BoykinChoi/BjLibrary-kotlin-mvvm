package com.boykinchoi.star.flow

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * - 挂起函数可以异步的返回单个值，但是该如何异步返回多个计算好的值呢？这正是 Kotlin 流（Flow）的用武之地
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/7/18 13:16
 */
class FlowTest {
    fun test() {
        GlobalScope.launch {
            flow {
                for (i in 1..10){
                    delay(450L)
                    emit(i)
                }
            }.collect {
                Log.d("FlowTest","value=$it")
            }
        }

    }

    fun test2(){
        GlobalScope.launch {
            (1..6).asFlow().collect { Log.d("FlowTest","value=$it") }
        }
    }
}