package com.boykinchoi.baselibrary.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

/**
 * FragmentStatePagerAdapter 基类 配合ViewPager使用 简单快捷
 */
class FragmentBasePagerAdapter : FragmentStatePagerAdapter {
    private var fragments: MutableList<*>? = null
    private var titles: Array<String>? = null
    private var bundles: List<Bundle>? = null

    constructor(fm: FragmentManager?, fragments: MutableList<*>?) : super(fm!!) {
        this.fragments = fragments
    }

    constructor(fm: FragmentManager?, titles: Array<String>?, fragments: MutableList<*>?) : super(fm!!) {
        if (titles == null) return
        this.titles = titles
        this.fragments = fragments
    }

 /*   constructor(fm: FragmentManager?, cls: Class<out Fragment>, titles: Array<String>?, bundles: List<Bundle>) : super(fm!!) {
        if (titles == null) return
        this.titles = titles
        this.bundles = bundles
        fragments = arrayOfNulls(titles.size)
        for (i in titles.indices) {
            try {
                fragments!![i] = cls.newInstance()
                fragments!![i]?.arguments = bundles[i]
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }*/

    override fun getItem(position: Int): Fragment {
        return fragments!![position] as Fragment
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, any: Any) {
        super.setPrimaryItem(container, position, any)
    }

    override fun setPrimaryItem(container: View, position: Int, any: Any) {
        super.setPrimaryItem(container, position, any)
    }

    override fun getCount(): Int {
        return if (fragments == null) 0 else fragments!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (titles != null && position < titles!!.size) {
            titles!![position]
        } else super.getPageTitle(position)
    }

    fun setTitle(orderMenu: Array<String>?) {
        titles = orderMenu
    }

    fun setBundles(bundles: List<Bundle>?) {
        this.bundles = bundles
    }

    /**
     * 使用这个方式，让页面不缓存，能够在清除fragment的时候对其做了删除
     *
     * @param any
     * @return
     */
    override fun getItemPosition(any: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}