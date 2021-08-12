package com.jiangziyu.standard.adapter

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.content.ContextCompat
import com.jiangziyu.basemodule.base.BaseRcAdapter
import com.jiangziyu.basemodule.base.BaseRcHolder
import com.jiangziyu.standard.R
import com.jiangziyu.standard.databinding.ItemPointDrawBinding

/**
 * Desc:
 * Created by jiangziyu on 2021/8/12 17:19.
 */
class PointDrawAdapter(val list: List<String>) : BaseRcAdapter() {

    private var currentPosition = -1

    @SuppressLint("NotifyDataSetChanged")
    fun setChosenPosition(pos: Int) {
        currentPosition = pos
        notifyDataSetChanged()
    }

    override fun getItemLayoutId(): Int {
        return R.layout.item_point_draw
    }

    override fun itemCount(): Int {
        return list.size
    }

    @SuppressLint("ResourceAsColor")
    override fun itemAction(holder: BaseRcHolder?, position: Int) {
        val binding = holder!!.getBinding<ItemPointDrawBinding>()
        val context=holder.itemView.context
        val item = list.get(position)
        binding.tvNumber.text = item
//        val param = llPointDrawItem.layoutParams
//        if (position % 4 == 0) {
//            param.width = DensityUtils.dpToPx(context, 80)
//        } else {
//            param.width = DensityUtils.dpToPx(context, 70)
//        }
//        llPointDrawItem.layoutParams = param
        if (currentPosition == position) {
            binding.tvNumber.setTextColor(ContextCompat.getColor(context,R.color.teal_200))
            binding.flParent.setBackgroundColor(ContextCompat.getColor(context,R.color.teal_700))
        } else {
            binding.tvNumber.setTextColor(ContextCompat.getColor(context,R.color.black))
            binding.flParent.setBackgroundColor(ContextCompat.getColor(context,R.color.purple_200))
        }

    }
}