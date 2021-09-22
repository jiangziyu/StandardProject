package com.jiangziyu.standard.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import com.jiangziyu.basemodule.base.BaseActivity
import com.jiangziyu.standard.R
import com.jiangziyu.standard.databinding.ActPointDrawBinding
import com.jiangziyu.standard.ui.fragment.PointDrawFrag

/**
 * Desc:
 * Created by jiangziyu on 2021/8/12 17:45.
 */
class PointDrawAct : BaseActivity<ActPointDrawBinding>() {

    val TAG = "jzy" + PointDrawAct::class.java.simpleName

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

        val fragment = PointDrawFrag()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
            .commit()
    }

    override fun initViewModel() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "start")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "resume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "pause")
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}