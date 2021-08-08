package com.jiangziyu.basemodule.delegate

import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 * Desc:基于Context的拓展函数
 * Created by jiangziyu on 2021/8/8 22:17.
 */

fun Context.showToast(text: String,gravity: Gravity) {
    val result = Toast.makeText(this, text, Toast.LENGTH_SHORT)
    result.show()
}

fun Context.showCenterToast(text:String,gravity: Gravity){
    val result=Toast.makeText(this,text,Toast.LENGTH_SHORT)
    result.setGravity(Gravity.CENTER,0,0)
    result.show()
}