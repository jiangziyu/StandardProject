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
    @POST("drhua.ashx/")
    fun postDataWithMap(@FieldMap fields: Map<String, String>): Call<ResponseBody>

    @FormUrlEncoded
    @POST("mobile.ashx/")
    fun postDataToGardsenWithMap(@FieldMap fields: Map<String, String>): Call<ResponseBody>
}