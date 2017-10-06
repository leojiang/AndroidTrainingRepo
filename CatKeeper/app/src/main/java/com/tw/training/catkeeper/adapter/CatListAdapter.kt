package com.tw.training.catkeeper.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.model.Cat

class CatListAdapter(context: Context, private val data: List<Cat>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return CatViewHolder(inflater.inflate(R.layout.cat_item_layout, null))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(holder == null) {
            return
        }

        holder.itemView.tag = position
    }

    inner class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onItemClickedListener?.onItemClicked(itemView, itemView.tag.toString().toInt()) }
            ButterKnife.bind(itemView)
        }
    }

    var onItemClickedListener: OnItemClickedListener? = null

    interface OnItemClickedListener {
        fun onItemClicked(view: View, position: Int)
    }
}