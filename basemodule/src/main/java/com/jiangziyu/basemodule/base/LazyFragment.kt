package com.jiangziyu.basemodule.base

/**
 * Desc:
 * Created by jiangziyu on 2021/5/12 11:28.
 */
abstract class LazyFragment : BaseFragment() {

    private var isFirstLoad = false

    // TODO: 2021/5/12 关于setUserVisibleHint过时的问题待修改

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isFirstLoad = true
    }
}