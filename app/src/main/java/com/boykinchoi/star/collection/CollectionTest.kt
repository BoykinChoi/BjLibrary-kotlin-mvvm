package com.boykinchoi.star.collection

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/12/15 17:31
 */
class CollectionTest {
    fun testPlus() {
        val numbers = listOf("one", "two", "three", "four")
        val plusList = numbers + "five"

        println(plusList)

        val minusList = numbers - listOf("three", "four")
        println(plusList)

    }
}