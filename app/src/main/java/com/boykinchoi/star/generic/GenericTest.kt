package com.boykinchoi.star.generic

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/7/25 16:34
 */
class GenericTest {

    fun feedCat(cats: Herd<Cat>) {
        for (i in 0..cats.size) {
            cats.animalFeed()
        }
        // 我们也想Herd< Cat >是Herd< Animal >的子类型，这样我就可以处理上面的问题了，
        // 所以这种变型的关系就叫做协变，只需要在Herd的泛型参数前加个out，就说明该类在该参数是协变的
        feedAll(cats)
    }

    fun feedAll(animals: Herd<Animal>) {
        for (i in 0..animals.size) {
            animals.getVa().feed()
        }
    }

}