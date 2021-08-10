package com.jiangziyu.standard

import android.app.Application
import com.jiangziyu.httpmodule.HttpRequest

/**
 * Desc:
 * Created by jiangziyu on 2021/8/10 09:40.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        HttpRequest.init()
    }
}