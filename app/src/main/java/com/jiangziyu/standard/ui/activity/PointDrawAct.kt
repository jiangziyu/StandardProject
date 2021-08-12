package com.jiangziyu.standard.ui.activity

import android.content.Context
import android.content.Intent
import com.jiangziyu.basemodule.base.BaseActivity
import com.jiangziyu.standard.R
import com.jiangziyu.standard.databinding.ActPointDrawBinding
import com.jiangziyu.standard.ui.fragment.PointDrawFrag

/**
 * Desc:
 * Created by jiangziyu on 2021/8/12 17:45.
 */
class PointDrawAct : BaseActivity<ActPointDrawBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, PointDrawAct::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.act_point_draw
    }

    override fun initView() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, PointDrawFrag())
            .commit()
    }

    override fun initViewModel() {
    }
}