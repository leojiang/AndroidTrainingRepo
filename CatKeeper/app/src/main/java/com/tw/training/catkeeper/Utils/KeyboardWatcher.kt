package com.tw.training.catkeeper.Utils

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager

import java.util.LinkedList

class KeyboardWatcher @JvmOverloads constructor(private val activityRootView: View, private var isSoftKeyboardOpened: Boolean = false) : ViewTreeObserver.OnGlobalLayoutListener {

    interface SoftKeyboardStateListener {
        fun onSoftKeyboardOpened(keyboardHeightInPx: Int)

        fun onSoftKeyboardClosed()
    }

    private val listeners = LinkedList<SoftKeyboardStateListener>()
    /**
     * Default value is zero `0`.
     *
     * @return last saved keyboard height in px
     */
    private var lastSoftKeyboardHeightInPx: Int = 0
        private set

    private var statusBarHeight = -1

    private fun isFullScreen(activity: Activity): Boolean {
        return activity.window.attributes.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN == WindowManager.LayoutParams.FLAG_FULLSCREEN
    }

    init {
        activityRootView.viewTreeObserver.addOnGlobalLayoutListener(this)
        val resourceId = activityRootView.context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = activityRootView.context.resources.getDimensionPixelSize(resourceId)
        }
    }

    override fun onGlobalLayout() {
        val r = Rect()
        activityRootView.getWindowVisibleDisplayFrame(r)

        val heightDiff = activityRootView.rootView.height - (r.bottom - r.top)
        if (!isSoftKeyboardOpened && heightDiff > activityRootView.rootView.height / 4) {
            isSoftKeyboardOpened = true
            if (activityRootView.context is Activity && !isFullScreen(activityRootView.context as Activity)) {
                notifyOnSoftKeyboardOpened(heightDiff - statusBarHeight)
            } else {
                notifyOnSoftKeyboardOpened(heightDiff)
            }

        } else if (isSoftKeyboardOpened && heightDiff < activityRootView.rootView.height / 4) {
            isSoftKeyboardOpened = false
            notifyOnSoftKeyboardClosed()
        }
    }

    fun addSoftKeyboardStateListener(listener: SoftKeyboardStateListener) {
        listeners.add(listener)
    }

    fun removeSoftKeyboardStateListener(listener: SoftKeyboardStateListener) {
        listeners.remove(listener)
    }

    private fun notifyOnSoftKeyboardOpened(keyboardHeightInPx: Int) {
        lastSoftKeyboardHeightInPx = keyboardHeightInPx

        for (listener in listeners) {
            listener.onSoftKeyboardOpened(keyboardHeightInPx)
        }
    }

    private fun notifyOnSoftKeyboardClosed() {
        for (listener in listeners) {
            listener.onSoftKeyboardClosed()
        }
    }
}