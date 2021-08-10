package com.jiangziyu.basemodule.base

import androidx.databinding.ViewDataBinding

/**
 * Desc:
 * Created by jiangziyu on 2021/5/12 11:28.
 */
abstract class LazyFragment<BindingType : ViewDataBinding> : BaseFragment<BindingType>() {

    private var isFirstLoad = false

    override fun onResume() {
        super.onResume()
        if (!isFirstLoad) {
            lazyLoad()
            isFirstLoad = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isFirstLoad = false
    }

    abstract fun lazyLoad()
}