package com.jiangziyu.basemodule.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Desc:
 * Created by jiangziyu on 2021/5/12 10:48.
 */
abstract class BaseActivity : AppCompatActivity() {

    // TODO: 2021/5/12 加入自定义的Activity栈 
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(getLayoutId())
        super.onCreate(savedInstanceState)
    }

    fun setOnClickListener(onClickListener: View.OnClickListener, vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.setOnClickListener(onClickListener)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}