package com.tw.training.catkeeper.activity

import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.adapter.BannerAdapter
import com.tw.training.catkeeper.presenter.MainContract
import com.tw.training.catkeeper.presenter.MainPresenter
import com.tw.training.catkeeper.Utils.ViewPagerScroller

class MainActivity : AppCompatActivity(), MainContract.View, ViewPager.OnPageChangeListener {
    private val MSG_WHAT = 0

    @BindView(R.id.left_tab)
    lateinit var mLeftTab: TextView

    @BindView(R.id.right_tab)
    lateinit var mRightTab: TextView

    @BindView(R.id.ad_viewpager)
    lateinit var mViewPager: ViewPager

    @BindView(R.id.indicator)
    lateinit var mIndicator : ViewGroup

    private var imageResIds = arrayOf(R.mipmap.cat_1, R.mipmap.cat_2, R.mipmap.cat_3, R.mipmap.cat_4)

    private var presenter: MainContract.Presenter = MainPresenter(this)

    private var previousPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        ButterKnife.bind(this@MainActivity)

        setupViewPager()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        handler.sendEmptyMessageDelayed(MSG_WHAT, 2000)
    }

    override fun onStop() {
        super.onStop()
        handler.removeMessages(MSG_WHAT);
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }

    @OnClick(R.id.left_tab)
    fun onLeftTabClicked() = presenter.switchToNearbyCat()


    @OnClick(R.id.right_tab)
    fun onRightTabClicked() = presenter.switchToMyCat()

    override fun switchToNearbyCat() {
        mRightTab.isEnabled = true
        mLeftTab.isEnabled = false
    }

    override fun switchToMyCat() {
        mLeftTab.isEnabled = true
        mRightTab.isEnabled = false
    }

    private fun setupViewPager() {
        val mViewContainer = java.util.ArrayList<ImageView>()
        for (i in 0 until imageResIds.size) {
            val view = LayoutInflater.from(this).inflate(R.layout.viewpager_item, null) as ImageView
            view.setImageResource(imageResIds[i])
            mViewContainer.add(view)
        }

        mViewPager.adapter = BannerAdapter(mViewContainer)
        mViewPager.addOnPageChangeListener(this)
        mViewPager.currentItem = 1000

        val pagerScroller = ViewPagerScroller(this)
        pagerScroller.initViewPagerScroll(mViewPager)
    }

    override fun onPageScrollStateChanged(p0: Int) {
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(position: Int) {
        val newPosition = position % imageResIds.size
        updateIndicator(previousPosition, newPosition)
        previousPosition = newPosition
    }

    private fun updateIndicator(pre: Int, now: Int) {
        var dot = mIndicator.getChildAt(pre) as ImageView
        dot.setImageResource(R.drawable.dot_bg_white)
        dot = mIndicator.getChildAt(now) as ImageView
        dot.setImageResource(R.drawable.dot_bg_green)
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: android.os.Message) {
            mViewPager.currentItem = mViewPager.currentItem + 1
            this.sendEmptyMessageDelayed(MSG_WHAT, 4000)
        }
    }

}
