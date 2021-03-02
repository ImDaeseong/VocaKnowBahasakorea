package com.daeseong.bahasakorea.Controls

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

object AnimatorUtil {

    //작아졌다 원래대로
    fun AnimatoScaleXY(view: View?) {
        val animatorSet = AnimatorSet()
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.8f, 1f)
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.8f, 1f)
        animatorSet.playTogether(scaleY, scaleX)
        animatorSet.duration = 1000
        animatorSet.start()
    }

    //왼쪽으로 swipe
    fun AnimatoSwipeLeft(view: View) {
        var mover = ObjectAnimator.ofFloat(view, "translationX", view.width.toFloat(), 0f)
        mover.duration = 0
        mover.start()
        mover = ObjectAnimator.ofFloat(view, "translationX", -view.width.toFloat(), 0f)
        mover.duration = 500
        mover.start()
    }

    //오른쪽으로 swipe
    fun AnimatoSwipeRight(view: View) {
        var mover = ObjectAnimator.ofFloat(view, "translationX", -view.width.toFloat(), 0f)
        mover.duration = 0
        mover.start()
        mover = ObjectAnimator.ofFloat(view, "translationX", view.width.toFloat(), 0f)
        mover.duration = 500
        mover.start()
    }

}
