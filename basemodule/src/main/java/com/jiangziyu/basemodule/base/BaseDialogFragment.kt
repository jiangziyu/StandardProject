package com.jiangziyu.basemodule.base

import android.os.Bundle
import android.view.*
import androidx.annotation.FloatRange
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.jiangziyu.basemodule.R

/**
 * Desc:
 * Created by jiangziyu on 2021/5/12 14:10.
 */
abstract class BaseDialogFragment : DialogFragment() {

    // TODO: 2021/5/12 尝试下别的设计模式？
    
    private var mDimAmount = 0.5f //背景昏暗度
    private var mAnimStyle = 0  //进出动画
    private var showBottom = false //是否显示在底部
    private var mWidth = 0 //宽度
    private var mHeight = 0 //高度
    private var mOutCancel = true //是否可以点击空白部分取消
    private var mPaddingLeft = 0 //左侧padding
    private var mPaddingRight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.MyDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutId(), container, false)
        initWindowParams()
        initView(rootView)
        return rootView
    }

    private fun initWindowParams() {
        val window: Window? = dialog!!.window
        if (window != null) {
            window.setBackgroundDrawableResource(0x00000000) //窗口的背景色为透明，这一步是必须的
            window.decorView.setPadding(mPaddingLeft, 0, mPaddingRight, 0) //窗口的 Padding 值
            if (mAnimStyle != 0) {
                window.setWindowAnimations(mAnimStyle)
            }
            val wlp: WindowManager.LayoutParams = window.attributes.apply {
                dimAmount = mDimAmount
                if (showBottom) {
                    gravity = Gravity.BOTTOM
                }
                width = if (mWidth == 0) {
                    WindowManager.LayoutParams.WRAP_CONTENT
                } else {
                    mWidth
                }
                height = if (mHeight == 0) {
                    WindowManager.LayoutParams.WRAP_CONTENT
                } else {
                    mHeight
                }
            }

            window.attributes = wlp
        }
        isCancelable = mOutCancel
    }

    /**
     * dialog背景阴影度
     *
     * @param dimAmount
     * @return
     */
    fun setDimAmount(@FloatRange(from = 0.0, to = 1.0) dimAmount: Float): BaseDialogFragment {
        mDimAmount = dimAmount
        return this
    }

    /**
     * 设置是否显示底部，可用于直接实现BottomSheetDialogFragment效果
     *
     * @param isShowBottom
     * @return
     */
    fun showBottom(isShowBottom: Boolean): BaseDialogFragment {
        showBottom = isShowBottom
        return this
    }

    /**
     * 设置dialog主体高度
     *
     * @param width  WindowManager.LayoutParams.MATCH_PARENT
     * @param height WindowManager.LayoutParams.WRAP_CONTENT
     * @return
     */
    fun setSize(width: Int, height: Int): BaseDialogFragment {
        mWidth = width
        mHeight = height
        return this
    }

    /**
     * 设置左右padding，这里主要作用与dialog主体距离屏幕左右的距离
     *
     * @param paddingLeft
     * @param paddingRight
     * @return
     */
    fun setPadding(paddingLeft: Int, paddingRight: Int): BaseDialogFragment {
        mPaddingLeft = paddingLeft
        mPaddingRight = paddingRight
        return this
    }

    /**
     * 设置dialog出入动画
     *
     * @param animStyle
     * @return
     */
    fun setAnimStyle(@StyleRes animStyle: Int): BaseDialogFragment {
        mAnimStyle = animStyle
        return this
    }

    /**
     * 是否可以点击屏幕其他地方取消
     *
     * @param outCancel
     * @return
     */
    fun setOutCancel(outCancel: Boolean): BaseDialogFragment {
        mOutCancel = outCancel
        return this
    }

    /**
     * 这里的tag可自定义
     *
     * @param manager
     * @return
     */
    fun show(manager: FragmentManager?): BaseDialogFragment {
        super.show(manager!!, System.currentTimeMillis().toString())
        return this
    }

    abstract fun getLayoutId(): Int

    abstract fun initView(rootView: View?)

    fun setOnclickListener(onClickListener: View.OnClickListener,vararg views:View){
        if (views.isNotEmpty()) {
            for (view in views) {
                view.setOnClickListener(onClickListener)
            }
        }
    }
}