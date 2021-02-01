package com.boykinchoi.read.api

import androidx.annotation.NonNull
import com.boykinchoi.baselibrary.util.Logger
import com.boykinchoi.read.BuildConfig
import okhttp3.*
import okio.Buffer
import org.json.JSONObject
import java.io.IOException

/**
 * 参数格式为Json格式的拦截器
 * Created by BoykinChoi
 * on 2021/1/4
 **/
class ParameterInterceptor : Interceptor {
    companion object {
        const val METHOD_GET = "GET"
        const val METHOD_POST = "POST"
        const val AUTHORIZATION = "zuulAuthorization"
        const val TIMESTAMP = "timestamp"
        const val SIGNATURE = "signature"
        const val TEST_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1Iiwic3ViIjoie1wiYWNjb3VudElkXCI6NSxcImRldmljZVR5cGVcIjoxLFwibG9naW5GbGFnXCI6MSxcIm1vYmlsZVwiOlwiMTgzMDIwMTUxMDVcIixcInN0dWRlbnRJZFwiOjUsXCJzdHVkZW50UmVhZEdyYWRlXCI6NSxcInN0dWRlbnRTZXhcIjowfSIsImlhdCI6MTYxMTg4NzQyMX0.zV0K1ZieMVSsSsjmxHJEsXiDWotZbmIhTq78a0rLySk"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        var newRequest: Request? = null
        if (METHOD_GET == oldRequest.method()) {
            newRequest = oldRequest.newBuilder()
                .addHeader(TIMESTAMP, System.currentTimeMillis().toString())
                .addHeader(AUTHORIZATION, TEST_TOKEN)
                .build()
        } else if (METHOD_POST == oldRequest.method()) {
            //POST方式，参数用json形式提交,重make json参数RequestBody
            val httpUrl = oldRequest.url().newBuilder().build()
            val requestBody = makeRequestBody(oldRequest)
            val tempRequest = oldRequest.newBuilder()
                .url(httpUrl)
                .addHeader(AUTHORIZATION, TEST_TOKEN)
                .post(requestBody!!)
                .build()
            val timeStamp = System.currentTimeMillis().toString()
            newRequest = tempRequest.newBuilder()
                .url(httpUrl)
                .addHeader(AUTHORIZATION, TEST_TOKEN)
                .addHeader(TIMESTAMP, timeStamp)
                //签名用的timeStamp 必须和Header里的timeStamp一致
                //.addHeader(SIGNATURE, apiSignature(tempRequest, bodyToString(requestBody), timeStamp))
                .post(requestBody)
                .build()

        }
        //打印返回数据
        var response = chain.proceed(newRequest!!)
        if (BuildConfig.DEBUG) {
            val resultBody = response.body()
            val result = resultBody!!.string()
            try {
                Logger.json(result)
            } catch (e: Exception) {
                Logger.w(result)
            }
            //因为调用ResponseBody.string()后即关闭，后续无法获取内容,so，再次newBuilder
            response = response.newBuilder()
                .body(ResponseBody.create(resultBody.contentType(), result))
                .build()
        }
        return response
    }

    @NonNull
    private fun makeRequestBody(oldRequest: Request): RequestBody? {
        val oldUrl = oldRequest.url()
        var jsonData = JSONObject()
        try {
            when (oldRequest.body()) {
                //当参数以 @Field @FieldMap 提交时
                is FormBody -> {
                    val formBody = oldRequest.body() as FormBody
                    //相当于int i = body.size() - 1; i >= 0; i-- ，downTo相当于i-
                    for (i in formBody.size() - 1 downTo 0) {
                        val name = formBody.name(i)
                        val value = formBody.value(i)
                        jsonData.put(name, value)
                    }
                }
                //当参数以 @MultipartBody 提交时
                is MultipartBody ->
                    Logger.i("is MultipartBody")
                //当参数以 @Body 提交时
                else -> {
                    val bodyString = bodyToString(oldRequest.body())
                    if (bodyString.isNotEmpty()) {
                        //若以"["开头，则是以RequestBoy，JsonArray参数形式。则直接返回oldRequest.body()
                        if (bodyString.startsWith("[")) {
                            return oldRequest.body()
                        }
                        jsonData = JSONObject(bodyString)
                    }
                }
            }
            //请求参数
            for (i in oldUrl.querySize() - 1 downTo 0) {
                val queryName = oldUrl.queryParameterName(i)
                val queryValue = oldUrl.queryParameterValue(i)
                jsonData.put(queryName, queryValue)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //json形式的请求body
        val newBodyBuilder = FormBody.Builder()
        newBodyBuilder.add("data", jsonData.toString())
        //添加Sign参数
        //newBodyBuilder.add("apisign", MD5Util.ToMD5(Constants.MD5_KEY, data.toString()));
        //Logger.d("请求参数Params=========", apiSign);//打印请求log
        Logger.d("请求地址RequestUrl=====", oldUrl.url().toString())
        Logger.d("请求参数Params=========", jsonData.toString())
        //打印请求log
        Logger.json(jsonData.toString())

        val requestBody = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"), jsonData.toString()
        )
        return requestBody
    }

    /**
     * requestBody 转为String
     */
    private fun bodyToString(requestBody: RequestBody?): String {
        try {
            val buffer = Buffer()
            return if (requestBody != null) {
                requestBody.writeTo(buffer)
                buffer.readUtf8()
            } else ""
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

}