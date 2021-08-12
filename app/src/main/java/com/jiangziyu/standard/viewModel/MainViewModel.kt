package com.jiangziyu.standard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Desc:
 * Created by jiangziyu on 2021/8/9 22:14.
 */
class MainViewModel() : ViewModel() {


    /**
     * 通过这个类的构造函数传值给MainViewModel的构造函数
     */
    class MainViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }
    }
}