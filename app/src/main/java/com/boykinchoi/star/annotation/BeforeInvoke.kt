package com.boykinchoi.star.annotation

/**
 * kotlin注解：点击前
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/8/5 11:41
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class BeforeInvoke(val name: String)
