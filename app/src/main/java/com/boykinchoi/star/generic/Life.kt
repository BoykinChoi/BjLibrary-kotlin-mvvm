package com.boykinchoi.star.generic

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/7/25 18:02
 */
interface Life<out T:Animal> {
    fun getVa(): T
}