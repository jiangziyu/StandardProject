package com.jiangziyu.standard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hj.doctor.utils.TurnDataUtil
import com.jiangziyu.httpmodule.HttpRequest
import com.jiangziyu.httpmodule.ResponseCallBack
import com.jiangziyu.standard.model.ChapterData


/**
 * Desc:
 * Created by jiangziyu on 2021/8/9 22:14.
 */
class MainViewModel() : ViewModel() {
    private val _chapter: MutableLiveData<List<ChapterData>> by lazy { MutableLiveData<List<ChapterData>>() }

    // TODO: 2021/8/10 搞明白这个写法的意思 
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


    /**
     * 通过这个类的构造函数传值给MainViewModel的构造函数
     */
    class MainViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }
    }
}