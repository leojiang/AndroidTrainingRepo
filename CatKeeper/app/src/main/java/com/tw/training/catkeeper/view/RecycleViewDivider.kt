package com.tw.training.catkeeper.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class RecycleViewDivider(private val mOrientation: Int) : RecyclerView.ItemDecoration() {

    private var mPaint: Paint? = null
    private var mDivider: Drawable? = null
    private var mDividerHeight = 2

    init {
        if(mOrientation != LinearLayoutManager.VERTICAL && mOrientation != LinearLayoutManager.HORIZONTAL) {
            throw IllegalArgumentException("please give correct parameters！")
        }
    }

    constructor(orientation: Int, drawable: Drawable) : this(orientation) {
        mDivider = drawable
        mDividerHeight = mDivider!!.intrinsicHeight
    }

    constructor(orientation: Int, dividerHeight: Int, dividerColor: Int) : this(orientation) {
        mDividerHeight = dividerHeight
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.color = dividerColor
        mPaint!!.style = Paint.Style.FILL
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(0, 0, 0, mDividerHeight)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        if(mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.measuredWidth - parent.paddingRight
        val childSize = parent.childCount
        for(i in 0 until (childSize - 1)) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin
            val bottom = top + mDividerHeight
            if(mDivider != null) {
                mDivider!!.setBounds(left, top, right, bottom)
                mDivider!!.draw(canvas)
            }
            if(mPaint != null) {
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint!!)
            }
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.measuredHeight - parent.paddingBottom
        val childSize = parent.childCount
        for(i in 0 until childSize) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + layoutParams.rightMargin
            val right = left + mDividerHeight
            if(mDivider != null) {
                mDivider!!.setBounds(left, top, right, bottom)
                mDivider!!.draw(canvas)
            }
            if(mPaint != null) {
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint!!)
            }
        }
    }
}