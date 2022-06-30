package com.boykinchoi.star.design_pattern.proxy.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2021/11/23 17:05
 */
class Delegate2 : ReadWriteProperty<Any, String> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return ""
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {

    }
}