package com.tw.training.catkeeper.fragment


import android.view.LayoutInflater
import android.view.View
import com.tw.training.catkeeper.R


class MyCatFragment : BaseFragment() {
    override fun createView(inflater: LayoutInflater): View {
        return inflater.inflate(R.layout.fragment_fragment_my_cat, null)
    }
}
