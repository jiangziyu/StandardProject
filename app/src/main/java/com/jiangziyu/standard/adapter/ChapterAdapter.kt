package com.jiangziyu.standard.adapter

import com.jiangziyu.basemodule.base.BaseRcAdapter
import com.jiangziyu.basemodule.base.BaseRcHolder
import com.jiangziyu.standard.R
import com.jiangziyu.standard.databinding.ItemChapterBinding
import com.jiangziyu.standard.model.ChapterData

/**
 * Desc:
 * Created by jiangziyu on 2021/8/12 14:51.
 */
class ChapterAdapter(val list: List<ChapterData>) : BaseRcAdapter() {
    override fun getItemLayoutId(): Int {
        return R.layout.item_chapter
    }

    override fun itemCount(): Int {
        return list.size
    }

    override fun itemAction(holder: BaseRcHolder?, position: Int) {
        val binding = holder!!.getBinding<ItemChapterBinding>()
        val item = list.get(position)
        with(item) {
            binding.tvName.text = name
            binding.tvId.text = id.toString()
            binding.tvContent.text = this.toString()
        }
    }
}