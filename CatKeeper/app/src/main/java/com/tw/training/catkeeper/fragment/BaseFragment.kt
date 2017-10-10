package com.tw.training.catkeeper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {

    private var mRootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = mRootView ?: createView(inflater)
        (mRootView?.parent as ViewGroup?)?.removeView(mRootView)
        return mRootView
    }

    protected abstract fun createView(inflater: LayoutInflater): View
}