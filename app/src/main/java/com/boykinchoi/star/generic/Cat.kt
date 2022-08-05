package com.boykinchoi.star.generic

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/7/25 16:39
 */
class Cat : Animal() {

    fun clean() {
        println("cat clean")
    }

    override fun feed(){
        println("cat feed")
    }

}