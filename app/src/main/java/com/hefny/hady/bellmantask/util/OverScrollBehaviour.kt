package com.hefny.hady.bellmantask.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.forEach

// this class overrides a coordinator layout behaviour to achieve over-scroll bounce behaviour
class OverScrollBehaviour(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    private var overScrollY: Int = 0

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int
    ): Boolean {
        overScrollY = 0
        return true
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        if (dyUnconsumed == 0) {
            return
        }
        overScrollY -= dyUnconsumed
        val group = target as ViewGroup
        group.forEach { v: View ->
            v.translationY = overScrollY.toFloat()
        }
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View
    ) {
        val group = target as ViewGroup
        group.forEach {
            ViewCompat.animate(it).translationY(0f).start()
        }
    }
}