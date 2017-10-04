package com.tw.training.catkeeper.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.Utils.KeyboardWatcher


class LoginActivity : AppCompatActivity(), KeyboardWatcher.SoftKeyboardStateListener {
    private lateinit var mBtnLogin: View
    private lateinit var mLogoLayout: View
    private lateinit var keyboardWatcher: KeyboardWatcher

    private lateinit var mContent: View
    private var screenHeight = 0
    private val scale = 0.8f
    private val alpha = 0.3f
    private val duration = 200L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLogoLayout = findViewById(R.id.logo_layout)
        mBtnLogin = findViewById(R.id.btn_login)
        mContent = findViewById(R.id.content)

        screenHeight = resources.displayMetrics.heightPixels

        keyboardWatcher = KeyboardWatcher(findViewById(Window.ID_ANDROID_CONTENT))
        keyboardWatcher.addSoftKeyboardStateListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        keyboardWatcher.removeSoftKeyboardStateListener(this)
    }

    override fun onSoftKeyboardOpened(keyboardHeightInPx: Int) {
        val location = IntArray(2)
        mContent.getLocationOnScreen(location)
        val bottom = screenHeight - (location[1] + mContent.height)

        if (keyboardHeightInPx > bottom) {
            val mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", 0.0f, -(keyboardHeightInPx - bottom).toFloat())
            mAnimatorTranslateY.duration = duration
            mAnimatorTranslateY.start()

            zoomIn(mLogoLayout, (keyboardHeightInPx - bottom).toFloat())
        }
    }

    override fun onSoftKeyboardClosed() {
        val mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", mContent.translationX, 0f)
        mAnimatorTranslateY.duration = duration
        mAnimatorTranslateY.start()

        zoomOut(mLogoLayout)
    }

    private fun zoomIn(view: View, dist: Float) {
        view.pivotY = view.height.toFloat()
        view.pivotX = (view.width / 2).toFloat()
        val mAnimatorSet = AnimatorSet()
        val mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale)
        val mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale)
        val mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist)
        val mAnimatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 1.0f, alpha)

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY).with(mAnimatorAlpha)
        mAnimatorSet.duration = duration
        mAnimatorSet.start()
    }

    private fun zoomOut(view: View) {
        if (view.translationY == 0f) {
            return
        }

        view.pivotY = view.height.toFloat()
        view.pivotX = (view.width / 2).toFloat()
        val mAnimatorSet = AnimatorSet()
        val mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", scale, 1.0f)
        val mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", scale, 1.0f)
        val mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.translationY, 0f)
        val mAnimatorAlpha = ObjectAnimator.ofFloat(view, "alpha", alpha, 1.0f)

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY).with(mAnimatorAlpha)
        mAnimatorSet.duration = duration
        mAnimatorSet.start()
    }
}
