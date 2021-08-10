package com.jiangziyu.httpmodule

import android.util.Log
import java.lang.reflect.Field

/**
 * Desc:
 * Created by jiangziyu on 2021/8/9 10:05.
 */
abstract class BaseParameter {
    /**
     * 这里参照GetPlantListParam的传入
     */
    abstract fun getApiName(): String

    /**
     * 设置默认的token信息
     */
    private fun setToken(params: HashMap<String, String>) {
//        if (!TextUtils.isEmpty(GlobalValues.userLoginId)) {
//            params.put("userId", GlobalValues.userLoginId)
//        }
//        if (!TextUtils.isEmpty(GlobalValues.userLoginToken)) {
//            params.put("userToken", GlobalValues.userLoginToken)
//        }
    }

    /**
     * 将类中申明的成员变量转成HashMap集合
     */
    fun parseParamToHashMap(): HashMap<String, String> {
        val fields: Array<Field> = this.javaClass.declaredFields
        val params: HashMap<String, String> = HashMap()
        params.put("operationCode", getApiName())
        for (field in fields) {
            try {
                field.isAccessible = true
                val name = field.name
                val value = field.get(this)
                if (value != null && value.toString().isNotEmpty()) {
                    params.put(name, value.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        setToken(params)
        logParams(params)
        return params
    }

    private fun logParams(params: HashMap<String, String>) {
        if (BuildConfig.DEBUG) {
            val body = StringBuilder()
            params.forEach {
                body.append(it.key + ":" + it.value + "&")
            }
            Log.e("HttpParameter", body.toString())
        }
    }
}