package com.im.daeseong.bahasakorea.Controls;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class AnimatorUtil {

    //작아졌다 원래대로
    public static void AnimatoScaleXY(View view){
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.8f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.8f, 1f);
        animatorSet.playTogether(scaleY, scaleX);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    //왼쪽으로 swipe
    public static void AnimatoSwipeLeft(View view){

        ObjectAnimator mover = ObjectAnimator.ofFloat(view,"translationX", view.getWidth(), 0f);
        mover.setDuration(0);
        mover.start();
        mover = ObjectAnimator.ofFloat(view, "translationX", -view.getWidth(), 0f);
        mover.setDuration(500);
        mover.start();

        /*
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationX", 0f, -1000f);
        animator1.setDuration(500);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationX", 1000f, 0f);
        animator2.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1, animator2);
        animatorSet.start();
        */
    }

    //오른쪽으로 swipe
    public static void AnimatoSwipeRight(View view){

        ObjectAnimator mover = ObjectAnimator.ofFloat(view,"translationX", -view.getWidth(), 0f);
        mover.setDuration(0);
        mover.start();
        mover = ObjectAnimator.ofFloat(view, "translationX",view.getWidth(), 0f);
        mover.setDuration(500);
        mover.start();

        /*
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationX",  0f, 1000f);
        animator1.setDuration(500);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationX", -1000f, 0f);
        animator2.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1, animator2);
        animatorSet.start();
        */
    }
}
