package com.example.photosdemo.common.util

import android.animation.Animator
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

fun View.setVisibilityAnimated(newVisibility: Int, duration: Long = 500L) {
    animate()
        .alpha(if (newVisibility == View.VISIBLE) 1.0F else 0.0F)
        .setDuration(duration)
        .setListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animator: Animator?) {}

            override fun onAnimationEnd(animator: Animator?) {
                if (newVisibility != View.VISIBLE) visibility = View.GONE
            }

            override fun onAnimationCancel(animator: Animator?) {}

            override fun onAnimationStart(animator: Animator?) {
                if (newVisibility == View.VISIBLE) visibility = View.VISIBLE
            }
        })
}

fun RecyclerView.disableChangeAnimations() {
    (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
}