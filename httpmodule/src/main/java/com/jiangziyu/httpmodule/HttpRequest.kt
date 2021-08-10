package com.jiangziyu.httpmodule

import android.app.Activity
import android.util.Log
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.SoftReference

/**
 * Desc:
 * Created by jiangziyu on 2021/5/27 14:59.
 */
object HttpRequest {

    val TAG = HttpRequest.javaClass.simpleName

    val CODE_SUCESS = 1 //与后台接口成功
    val CODE_FAILURE = -1001 //retrofit错误码
    val CODE_JSON_EXCEPTION = -1002 //json转换错误码

    private val retrofit by lazy {
//        val logInterceptor = OkHttpClient.Builder().addInterceptor(LogInterceptor()).build()
        return@lazy Retrofit.Builder().run {
//            client(logInterceptor)
            baseUrl(NetCenter.BaseUrl)
            addConverterFactory(GsonConverterFactory.create())
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

    fun getChapter(responseCallBack: ResponseCallBack){
        httpService.getWan().enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                dealResponse(response,responseCallBack)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val errorMsg = t.message.toString()
                responseCallBack.onFail(CODE_FAILURE, errorMsg)
                Log.e(TAG, errorMsg)
            }
        })
    }

    fun postMap(activity: Activity, param: BaseParameter, responseCallBack: ResponseCallBack) {
        val softReference: SoftReference<Activity> = SoftReference<Activity>(activity)
        httpService.postDataWithMap(param.parseParamToHashMap())
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    actIsRecycled(softReference)
                    dealResponse(response, responseCallBack)
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    actIsRecycled(softReference)
                    val errorMsg = t.message.toString()
                    responseCallBack.onFail(CODE_FAILURE, errorMsg)
                    Log.e(TAG, errorMsg)
                }

            })
    }

    private fun dealResponse(response: Response<ResponseBody>, responseCallBack: ResponseCallBack) {
        val responseResult = ResponseResult()
        try {
            if (response.isSuccessful) {
                val jsonObj = JSONObject(response.body()?.string())
                Log.e(TAG, jsonObj.toString())
                when (val retVal = jsonObj.getString("errorCode")) {
                    "0" -> {
                        responseResult.apply {
                            setResponseCode(CODE_SUCESS)
                            setResponseData(jsonObj.optString("data"))
                        }
                    }
                    else -> {
                        responseResult.apply {
                            setResponseCode(retVal.toInt())
                            setErrorMessage(jsonObj.optString("errorMsg"))
                        }
                    }
                }
            } else {
                responseResult.apply {
                    setResponseCode(CODE_FAILURE)
                    setErrorMessage(response.body().toString())
                }
            }
        } catch (e: Exception) {
            responseResult.apply {
                setResponseCode(CODE_JSON_EXCEPTION)
                setErrorMessage(e.message.toString())
            }

        } finally {
            if (responseResult.isSuccess()) {
                responseCallBack.onSuccess(responseResult.getResponseData())
            } else {
                responseCallBack.onFail(
                    responseResult.getResponseCode(),
                    responseResult.getErrorMessage()
                )
            }
        }
    }

    private fun actIsRecycled(softReference: SoftReference<Activity>) {
        if (softReference.get() == null || softReference.get()!!.isFinishing ||
            softReference.get()!!.isDestroyed
        ) {
            return
        }
    }

    class ResponseResult {

        private var responseCode = CODE_FAILURE
        private lateinit var responseData: String
        private lateinit var errorMessage: String

        fun isSuccess(): Boolean = responseCode == CODE_SUCESS

        fun getResponseCode(): Int = responseCode

        fun setResponseCode(responseCode: Int) {
            this.responseCode = responseCode
        }

        fun getResponseData(): String = responseData

        fun setResponseData(responseData: String) {
            this.responseData = responseData
        }

        fun getErrorMessage(): String = errorMessage

        fun setErrorMessage(errorMessage: String) {
            this.errorMessage = errorMessage
        }
    }
}