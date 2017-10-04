package com.tw.training.catkeeper.adapter

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class BannerAdapter(private val imageList: List<ImageView>) : PagerAdapter() {

    override fun getCount(): Int {
        return Integer.MAX_VALUE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = imageList[position % imageList.size]
        (container as ViewPager).addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(imageList[position % imageList.size])
    }
}
