package com.jiangziyu.basemodule.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Desc:
 * Created by jiangziyu on 2021/5/12 14:50.
 */
class BaseRcHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!

    @Suppress("UNCHECKED_CAST")
    fun <T : ViewDataBinding> getBinding(): T {
        return binding as T
    }

    fun executePendingBindings() {
        binding.executePendingBindings()
    }
}