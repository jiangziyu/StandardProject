package com.jiangziyu.httpmodule

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Desc:
 * Created by jiangziyu on 2021/5/27 15:05.
 */
interface HttpService {
    @FormUrlEncoded
    @GET("wxarticle/chapters/json")
    fun postDataWithMap(@FieldMap fields: Map<String, String>): Call<ResponseBody>

    @GET("wxarticle/chapters/json")
    fun getWan():Call<ResponseBody>

}