package com.jiangziyu.standard.model

/**
 * Desc:
 * Created by jiangziyu on 2021/8/10 11:15.
 */
data class ChapterData (val children: List<Any>,
                        val courseId: Int,
                        val id: Int,
                        val name: String,
                        val order: Int,
                        val parentChapterId: Int,
                        val userControlSetTop: Boolean,
                        val visible: Int){}