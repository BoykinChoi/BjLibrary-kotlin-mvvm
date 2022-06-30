package com.boykinchoi.star.design_pattern.proxy.delegate

import kotlin.reflect.KProperty

/**
 * thisRef： 必须与 属性所有者 类型（对于扩展属性——指被扩展的类型）相同或者是它的超类型；
 * property： 必须是类型 KProperty<*>或其超类型。
 * value： 必须与属性同类型或者是它的子类型
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/11/23 16:50
 */
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }

}