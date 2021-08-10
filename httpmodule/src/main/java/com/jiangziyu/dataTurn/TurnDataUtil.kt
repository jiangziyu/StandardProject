package com.hj.doctor.utils

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.text.DecimalFormat


/**
 * Desc:
 * Created by jiangziyu on 2020/7/1 12:05.
 */
object TurnDataUtil {
    fun turnToListString(list: List<*>): String? {
        val stringBuilder = StringBuilder()
        for (i in list.indices) {
            stringBuilder.append(list[i])
            if (i != list.size - 1) {
                stringBuilder.append(",")
            }
        }
        return stringBuilder.toString()
    }

    fun turnToJson(list: List<*>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    fun <T> turnToList(data: String?, clazz: Class<T>?): List<T>? {
        return if (!TextUtils.isEmpty(data)) {
            try {
                val modelList: MutableList<T> = ArrayList()
                val parser = JsonParser()
                val jsonArray = parser.parse(data).asJsonArray
                val gson = Gson()
                for (jsonModel in jsonArray) {
                    val model = gson.fromJson(jsonModel, clazz)
                    modelList.add(model)
                }
                modelList
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            null
        }
    }

    fun modifyFloat(f: Float): Float {
        val decimalFormat = DecimalFormat("0.00") //构造方法的字符格式这里如果小数不足2位,会以0补足.
        val p: String = decimalFormat.format(f) //format 返回的是字符串
        return java.lang.Float.valueOf(p)
    }
}