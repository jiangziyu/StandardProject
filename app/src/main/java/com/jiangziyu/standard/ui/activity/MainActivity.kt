package com.jiangziyu.standard.ui.activity


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jiangziyu.basemodule.base.BaseActivity
import com.jiangziyu.standard.R
import com.jiangziyu.standard.databinding.ActivityMainBinding
import com.jiangziyu.standard.event.TestEvent
import com.jiangziyu.standard.viewModel.MainViewModel
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity<ActivityMainBinding>() {
    val TAG = "jzy" + MainActivity::class.java.simpleName

    private lateinit var viewModel: MainViewModel

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getEvent(event: TestEvent){
        Log.e(TAG,"hhh")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        register()
        initViewModel()
        setOnClickListener(onClickListener, binding.btnMain, binding.btnPointDraw)
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory()
        ).get(MainViewModel::class.java)
    }

    private val onClickListener = View.OnClickListener { v ->

        when (v.id) {
            R.id.btnMain -> {
                ScrollEventAct.start(this)
                Context.BIND_ABOVE_CLIENT
            }
            R.id.btnPointDraw -> {
                PointDrawAct.start(this)
            }
        }
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
        Log.e(TAG, "stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "destroy")
    }

}