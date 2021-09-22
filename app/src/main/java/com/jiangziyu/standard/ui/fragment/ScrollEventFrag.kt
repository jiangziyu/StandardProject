package com.jiangziyu.standard.ui.fragment

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jiangziyu.basemodule.base.LazyFragment
import com.jiangziyu.standard.R
import com.jiangziyu.standard.adapter.ChapterAdapter
import com.jiangziyu.standard.databinding.FragScrollEventBinding
import com.jiangziyu.standard.model.ChapterData
import com.jiangziyu.standard.viewModel.ScrollEventViewModel

/**
 * Desc:
 * Created by jiangziyu on 2021/8/12 15:47.
 */
class ScrollEventFrag : LazyFragment<FragScrollEventBinding>() {

    private val viewModel: ScrollEventViewModel by lazy {
        ViewModelProvider(this).get(
            ScrollEventViewModel::class.java
        )
    }
    private lateinit var chapterAdapter: ChapterAdapter
    private val list: ArrayList<ChapterData> by lazy { ArrayList() }
    private var type: Int = 0

    companion object {
        fun getFragment(type: Int): ScrollEventFrag {
            return ScrollEventFrag().apply { this.type = type }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.frag_scroll_event
    }

    override fun initView() {
        initRecycler()
    }

    override fun initViewModel() {
        viewModel.chapter.observe(this, Observer { result ->
            setData(result)
        })
    }


    override fun lazyLoad() {
        getData()
    }

    private fun getData() {
        viewModel.getChapters()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData(data: List<ChapterData>) {
        list.clear()
        list.addAll(data)
        chapterAdapter.notifyDataSetChanged()
    }


    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(activity)
        chapterAdapter = ChapterAdapter(list)
        binding.rcChapter.run {
            this.layoutManager = layoutManager
            this.adapter = chapterAdapter
        }
    }
}