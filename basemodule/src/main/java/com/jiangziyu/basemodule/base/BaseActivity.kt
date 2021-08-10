package com.jiangziyu.basemodule.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.greenrobot.eventbus.EventBus

/**
 * Desc:
 * Created by jiangziyu on 2021/5/12 10:48.
 */
abstract class BaseActivity<BindingType : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: BindingType

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
//        setContentView(getLayoutId())
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        super.onCreate(savedInstanceState)
        initView()
    }

    fun setOnClickListener(onClickListener: View.OnClickListener, vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.setOnClickListener(onClickListener)
            }
        }
    }

    protected fun register() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    private fun unRegister() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    protected fun postEvent(event: Any) {
        EventBus.getDefault().post(event)
    }

    protected fun postSticky(event: Any) {
        EventBus.getDefault().postSticky(event)
    }

    override fun onDestroy() {
        unRegister()
        super.onDestroy()
    }

    abstract fun initView()
}