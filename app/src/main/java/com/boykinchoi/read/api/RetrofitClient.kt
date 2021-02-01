package com.boykinchoi.read.api

import com.boykinchoi.baselibrary.util.Logger
import com.boykinchoi.read.api.service.TestService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable
import java.util.concurrent.TimeUnit

/**
 * Retrofit 单例（DCL(double check lock)改造懒汉式单例）
 * 使用Companion Object + lazy属性代理
 * Created by BoykinChoi
 * on 2021/1/4
 **/
class RetrofitClient private constructor() : Serializable {

    //防止单例对象在反序列化时重新生成对象
    private fun readResolve(): Any {
        return instance
    }

    companion object {
        //通过@JvmStatic注解，使得在Java中调用instance直接是像调用静态函数一样，
        //类似KLazilyDCLSingleton.getInstance(),如果不加注解，在Java中必须这样调用: KLazilyDCLSingleton.Companion.getInstance().
        @JvmStatic
        //使用lazy属性代理，并指定LazyThreadSafetyMode为SYNCHRONIZED模式保证线程安全
        val instance: Retrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Retrofit.Builder().baseUrl(HostUrl.HOST_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                //设置超时
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                //错误重连
                .retryOnConnectionFailure(true)
                //自定义（对请求参数进行处理的时候添加的）拦截器
                .addInterceptor(ParameterInterceptor())
                //添加日志打印
                .addInterceptor(loggingInterceptor)
                .build()
        }

        private val loggingInterceptor: HttpLoggingInterceptor by lazy {
            HttpLoggingInterceptor {
                //打印retrofit日志
                Logger.w("RetrofitLog", it + "")
            }.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        fun <T> getApiService(clazz: Class<T>): T {
            return instance.create(clazz)
        }

        @JvmStatic
        val testService: TestService
            get() = getApiService(TestService::class.java)

    }
}