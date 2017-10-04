package com.tw.training.catkeeper.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

object AnimationUtil {
    private val SCALE_RATIO = 0.8f
    private val ALPHA_RATIO = 0.3f
    private val DURATION= 200L

    fun moveContentUp(view: View, dist: Float) {
        val mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist)
        mAnimatorTranslateY.duration = DURATION
        mAnimatorTranslateY.start()
    }

    fun moveContentDown(view: View) {
        val mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.translationX, 0f)
        mAnimatorTranslateY.duration = DURATION
        mAnimatorTranslateY.start()
    }

    fun zoomIn(view: View, dist: Float) {
        view.pivotY = view.height.toFloat()
        view.pivotX = (view.width / 2).toFloat()

        val mAnimatorSet = AnimatorSet()
        val mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, SCALE_RATIO)
        val mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, SCALE_RATIO)
        val mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist)
        val mAnimatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 1.0f, ALPHA_RATIO)

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY).with(mAnimatorAlpha)
        mAnimatorSet.duration = DURATION
        mAnimatorSet.start()
    }

    fun zoomOut(view: View) {
        view.pivotY = view.height.toFloat()
        view.pivotX = (view.width / 2).toFloat()

        val mAnimatorSet = AnimatorSet()
        val mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", SCALE_RATIO, 1.0f)
        val mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", SCALE_RATIO, 1.0f)
        val mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.translationY, 0f)
        val mAnimatorAlpha = ObjectAnimator.ofFloat(view, "alpha", ALPHA_RATIO, 1.0f)

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY).with(mAnimatorAlpha)
        mAnimatorSet.duration = DURATION
        mAnimatorSet.start()
    }


}
