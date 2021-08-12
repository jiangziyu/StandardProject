package com.jiangziyu.standard.ui.activity

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.jiangziyu.basemodule.base.BaseActivity
import com.jiangziyu.basemodule.base.BaseFragmentAdapter
import com.jiangziyu.standard.R
import com.jiangziyu.standard.databinding.ActScrollEventBinding
import com.jiangziyu.standard.ui.fragment.ScrollEventFrag

/**
 * Desc:
 * Created by jiangziyu on 2021/8/12 14:38.
 */
class ScrollEventAct : BaseActivity<ActScrollEventBinding>() {

    private val fragList: List<ScrollEventFrag> by lazy {
        listOf(
            ScrollEventFrag.getFragment(1),
            ScrollEventFrag.getFragment(2),
            ScrollEventFrag.getFragment(3),
        )
    }

    private lateinit var fragAdapter: BaseFragmentAdapter

    override fun getLayoutId(): Int {
        return R.layout.act_scroll_event
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ScrollEventAct::class.java)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        initViewPager()
    }

    override fun initViewModel() {

    }

    private fun initViewPager() {
        fragAdapter = BaseFragmentAdapter(supportFragmentManager, fragList, null)
        binding.viewPager.run {
            adapter = fragAdapter
            offscreenPageLimit = fragList.size
        }
    }

}