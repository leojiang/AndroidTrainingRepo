package com.tw.training.catkeeper.activity

import android.widget.TextView
import com.tw.training.catkeeper.BuildConfig
import com.tw.training.catkeeper.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MainActivityTestRobolectric {

    lateinit var activity: MainActivity

    @Before
    @Throws(Exception::class)
    fun setup() {
        activity = Robolectric.setupActivity(MainActivity::class.java)
    }


    @Test
    @Throws(Exception::class)
    fun clickLeftTabTest() {
        var rightTab = activity.findViewById<TextView>(R.id.right_tab)
        var leftTab = activity.findViewById<TextView>(R.id.left_tab)

        assert(rightTab.isEnabled)
        assert(!leftTab.isEnabled)

        rightTab.performClick()

        assert(leftTab.isEnabled)
        assert(!rightTab.isEnabled)

        //TODO: should check fragment
    }


    @Test
    @Throws(Exception::class)
    fun clickLRightTabTest() {
        var rightTab = activity.findViewById<TextView>(R.id.right_tab)
        var leftTab = activity.findViewById<TextView>(R.id.left_tab)

        rightTab.performClick()

        assert(leftTab.isEnabled)
        assert(!rightTab.isEnabled)

        leftTab.performClick()

        assert(rightTab.isEnabled)
        assert(!leftTab.isEnabled)

        //TODO: should check fragment
    }
}