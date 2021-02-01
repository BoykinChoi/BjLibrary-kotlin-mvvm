package com.boykinchoi.baselibrary.network

/**
 * RxJava2不能发生null ,但数据有时会返回null ,
 * so,这里主动抛出异常并进行主动捕捉，将数据通过Callback正确返回
 * Created by BoykinChoi
 * on 2020/12/3
 **/
class RxJava2NullException : RuntimeException("RxJava2 cannot send null")