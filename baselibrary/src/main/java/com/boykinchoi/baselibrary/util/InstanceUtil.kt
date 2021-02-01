package com.boykinchoi.baselibrary.util

/**
 * 所有方法都为静态方法，如工具类、常量池、等，
 * 直接把类名前的class替换成object
 * Created by BoykinChoi
 * on 2020/11/24
 **/
object InstanceUtil {
    /**
     * 通过实例工厂去实例化相应类
     * 泛型方法，<T>表示调用此方法的实例的泛型类型
     * @param
     * @return T 返回实例的泛型类型
     */
    fun <T> getInstance(clazz: Class<*>): T? {
        try {
            return clazz.newInstance() as T
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return null
    }
}