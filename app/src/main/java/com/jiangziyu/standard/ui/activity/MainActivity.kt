package com.jiangziyu.standard.ui.activity


import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jiangziyu.basemodule.base.BaseActivity
import com.jiangziyu.standard.R
import com.jiangziyu.standard.databinding.ActivityMainBinding
import com.jiangziyu.standard.viewModel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var viewModel: MainViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initViewModel()
        setOnClickListener(onClickListener, binding.btnMain,binding.btnPointDraw)
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
            }
            R.id.btnPointDraw->{
                PointDrawAct.start(this)
            }
        }
    }

}