package com.jiangziyu.httpmodule

/**
 * Desc:
 * Created by jiangziyu on 2021/8/9 10:04.
 */
interface ResponseCallBack {
    fun onSuccess(response: String)
    fun onFail(code: Int, errorMessage: String)
}