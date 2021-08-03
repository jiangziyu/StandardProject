package com.jiangziyu.httpmodule

import retrofit2.Retrofit

/**
 * Desc:
 * Created by jiangziyu on 2021/5/27 14:59.
 */
object HttpRequest {
    // TODO: 2021/5/27 拦截器通过注解拦截，同时提供列表实现baseurl的直接替换
    private val retrofit by lazy {
//        val logInterceptor = OkHttpClient.Builder().addInterceptor(LogInterceptor()).build()
        return@lazy Retrofit.Builder().run {
//            client(logInterceptor)
            baseUrl(NetCenter.BaseUrl)
//            addConverterFactory(GsonConverterFactory.create())
            build()
        }
    }

    private lateinit var httpService: HttpService

    private fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    /**
     * 必须在调用接口前初始化
     */
    fun init() {
        httpService = create(HttpService::class.java)
    }
}