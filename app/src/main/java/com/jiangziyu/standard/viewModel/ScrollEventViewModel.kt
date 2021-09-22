package com.jiangziyu.standard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hj.doctor.utils.TurnDataUtil
import com.jiangziyu.httpmodule.HttpRequest
import com.jiangziyu.httpmodule.ResponseCallBack
import com.jiangziyu.standard.model.ChapterData

/**
 * Desc:
 * Created by jiangziyu on 2021/8/12 15:08.
 */
class ScrollEventViewModel : ViewModel() {
    private val _chapter: MutableLiveData<List<ChapterData>> by lazy { MutableLiveData<List<ChapterData>>() }

    // TODO: 2021/8/10 搞明白这个写法的意思，查看viewmodle中的使用方式？
    val chapter: MutableLiveData<List<ChapterData>> get() = _chapter

    fun getChapters() {
        HttpRequest.getChapter(object : ResponseCallBack {
            override fun onSuccess(response: String) {
                _chapter.value = TurnDataUtil.turnToList(response, ChapterData::class.java)
            }

            override fun onFail(code: Int, errorMessage: String) {

            }
        })
    }
}