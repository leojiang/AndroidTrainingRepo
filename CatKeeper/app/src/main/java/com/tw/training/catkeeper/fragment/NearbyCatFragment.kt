package com.tw.training.catkeeper.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.activity.CatDetailActivity
import com.tw.training.catkeeper.adapter.CatListAdapter
import com.tw.training.catkeeper.model.Cat
import com.tw.training.catkeeper.view.RecycleViewDivider

class NearbyCatFragment : BaseFragment(), CatListAdapter.OnItemClickedListener {

    @BindView(R.id.recycler_view)
    lateinit var mCatList: RecyclerView

    private lateinit var mAdapter: CatListAdapter

    override fun createView(inflater: LayoutInflater): View {
        val view = inflater.inflate(R.layout.fragment_nearby_cat, null)
        ButterKnife.bind(this, view)
        initRecyclerView()
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun initRecyclerView() {
        mCatList.layoutManager = LinearLayoutManager(activity)
        mCatList.addItemDecoration(RecycleViewDivider(
                LinearLayoutManager.HORIZONTAL, 30,
                ContextCompat.getColor(activity, R.color.paleGrey)))
    }

    private fun setData() {
        val list = ArrayList<Cat>()
        list.add(Cat("QIQI"))
        list.add(Cat("QIQI"))
        list.add(Cat("QIQI"))
        list.add(Cat("QIQI"))
        list.add(Cat("QIQI"))
        list.add(Cat("QIQI"))
        list.add(Cat("QIQI"))
        list.add(Cat("QIQI"))
        mAdapter = CatListAdapter(activity, list)
        mAdapter.onItemClickedListener = this
        mCatList.adapter = mAdapter
    }

    override fun onItemClicked(view: View, position: Int) {
        startActivity(Intent(activity, CatDetailActivity::class.java))
    }
}
