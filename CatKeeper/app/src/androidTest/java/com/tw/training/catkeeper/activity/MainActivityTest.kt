package com.tw.training.catkeeper.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isEnabled
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.tw.training.catkeeper.R
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun clickLeftTab() {
        onView(withId(R.id.right_tab)).perform(click())
        onView(withId(R.id.right_tab)).check(matches(not(isEnabled())))
        onView(withId(R.id.left_tab)).check(matches(isEnabled()))

        //TODO: should check fragment
    }
}