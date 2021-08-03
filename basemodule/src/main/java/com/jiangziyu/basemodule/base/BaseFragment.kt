package com.jiangziyu.basemodule.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Desc:
 * Created by jiangziyu on 2021/5/12 11:01.
 */
abstract class BaseFragment : Fragment() {

    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun setOnClickListener(onClickListener: View.OnClickListener, vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.setOnClickListener(onClickListener)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}