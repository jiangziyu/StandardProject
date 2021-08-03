package com.jiangziyu.basemodule.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Desc:
 * Created by jiangziyu on 2021/5/12 15:02.
 */
abstract class BaseRcAdapter : RecyclerView.Adapter<BaseRcHolder>() {
    // TODO: 2021/5/12 再写一个适合多样化item的Adapter ，写到CommonModule中

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRcHolder {
        // TODO: 2021/5/14 根据不同的itemType返回不同的View 
        val v: View = LayoutInflater.from(parent.context).inflate(getItemLayoutId(), parent, false)
        return BaseRcHolder(v)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return itemCount()
    }

    override fun onBindViewHolder(holder: BaseRcHolder, position: Int) {
        itemAction(holder, position)
        holder.executePendingBindings()
    }

    abstract fun getItemLayoutId(): Int

    abstract fun itemCount(): Int

    abstract fun itemAction(holder: BaseRcHolder?, position: Int)
}