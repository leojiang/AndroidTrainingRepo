package com.tw.training.catkeeper.utils

import android.content.Context
import android.support.v4.view.ViewPager
import android.view.animation.Interpolator
import android.widget.Scroller

class ViewPagerScroller : Scroller {
    private var mScrollDuration = 1000

    fun setScrollDuration(duration: Int) {
        this.mScrollDuration = duration
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, interpolator: Interpolator) : super(context, interpolator)

    constructor(context: Context, interpolator: Interpolator,
                flywheel: Boolean) : super(context, interpolator, flywheel)

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration)
    }

    fun initViewPagerScroll(viewPager: ViewPager) {
        try {
            val mScroller = ViewPager::class.java.getDeclaredField("mScroller")
            mScroller.isAccessible = true
            mScroller.set(viewPager, this)
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
}