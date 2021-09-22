package com.jiangziyu.basemodule.base

import androidx.databinding.ViewDataBinding

/**
 * Desc:
 * Created by jiangziyu on 2021/5/12 11:28.
 */
abstract class LazyFragment<BindingType : ViewDataBinding> : BaseFragment<BindingType>() {

    private var isLoaded = false

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            lazyLoad()
            isLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyLoad()
}