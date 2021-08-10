package com.jiangziyu.basemodule.delegate

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 * Desc:简化普通的Toast创建
 * Created by jiangziyu on 2021/8/9 09:06.
 */
fun Toast.makeText(context: Context, text: String): Toast {
    return Toast.makeText(context, text, Toast.LENGTH_SHORT)
}

fun Toast.makeText(context: Context, resId: Int): Toast {
    return Toast.makeText(context, resId, Toast.LENGTH_SHORT)
}

@SuppressLint("ShowToast")
fun Toast.makeCenterToast(context: Context, text: Int) {
    val result = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    result.setGravity(Gravity.CENTER, 0, 0)
}




