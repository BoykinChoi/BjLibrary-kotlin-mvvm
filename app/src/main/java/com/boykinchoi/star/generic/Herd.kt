package com.boykinchoi.star.generic

/**
 * 牲畜群，一个牲畜群包含10个动物
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/7/25 16:27
 */
class Herd<out T : Animal> {
    // T上界是Animal so,底层保存的也是Animal类型
    val animal = Animal()

    fun animalFeed(){
        animal.feed()
    }

    /**
     * 运行时泛型擦除，getVa()会报java.lang.ClassCastException: Animal cannot be cast to Cat
     */
    fun getVa(): T {
        return animal as T
    }

    /**
     * Kotlin可以实化类型参数，啥意思呢 也就是这个类型参数在运行时不会被擦除
     * 函数声明为inline函数，且泛型参数使用reified来修饰
     */
    inline fun <reified T> getVa2(): T {
        return animal as T
    }

    // 一个牲畜群包含10个动物
    val size = 10
}