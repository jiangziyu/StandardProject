package com.jiangziyu.standard.ui.fragment

import android.animation.ValueAnimator
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.jiangziyu.basemodule.base.BaseFragment
import com.jiangziyu.standard.R
import com.jiangziyu.standard.adapter.PointDrawAdapter
import com.jiangziyu.standard.databinding.FragPointDrawBinding

/**
 * Desc:
 * Created by jiangziyu on 2021/8/12 17:04.
 */
class PointDrawFrag : BaseFragment<FragPointDrawBinding>() {

    private val REPEAT_NUM = 4 //抽奖默认循环次数
    private val GRID_NUM = 9 //默认rc的数量
    private var aimNum: Int = 0 //中奖位置

    private lateinit var list: List<String>
    private lateinit var pointDrawAdapter: PointDrawAdapter

    override fun getLayoutId(): Int {
        return R.layout.frag_point_draw
    }

    override fun initView() {
        initRecycler()
        setOnClickListener(onClickListener, binding.btnPointDraw)
    }

    override fun initViewModel() {

    }

    private fun pointDraw() {
        aimNum = (0..8).random()
        val totalNum = REPEAT_NUM * GRID_NUM + aimNum
        val valueAnimator = ValueAnimator.ofInt(0, totalNum)
        valueAnimator.duration = 4000
        //默认的差值器就是这个，如果需要再设置，加入参就好，或者替换
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.addUpdateListener { animation ->
            val position = animation!!.animatedValue as Int
            pointDrawAdapter.setChosenPosition(position % GRID_NUM)
            if (position == totalNum) {
                showResult()
            }
        }
        valueAnimator.start()
    }

    private fun showResult() {
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(activity, list.get(aimNum), Toast.LENGTH_SHORT).show()
        }, 1000)
    }


    private fun initRecycler() {
        list = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
        val flexBoxLayoutManager = FlexboxLayoutManager(activity).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.CENTER
            alignItems = AlignItems.FLEX_START
        }
        pointDrawAdapter = PointDrawAdapter(list)
        binding.rcPointDraw.run {
            layoutManager = flexBoxLayoutManager
            adapter = pointDrawAdapter
        }
    }

    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.btnPointDraw -> {
                pointDraw()
            }
        }
    }
}