package com.jiangziyu.basemodule.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Desc:
 * BEHAVIOR_SET_USER_VISIBLE_HINT:当frag对用户的可见状态发生改变时，setUserVisibleHint方法会被调用
 * BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT:当前frag在Lifecycle.state#RESUME时，其他的frag会被限制在started
 * Created by jiangziyu on 2021/8/4 14:24.
 */
class BaseFragmentAdapter(
    fm: FragmentManager,
    private val list: List<Fragment>,
    private val listTitle: List<String>?,
    behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) :
    FragmentPagerAdapter(fm, behavior) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return list.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (listTitle != null) {
            return listTitle.get(position)
        }
        return super.getPageTitle(position)
    }
}