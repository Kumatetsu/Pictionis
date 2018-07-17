package com.etna.mob4.utils

import android.view.MotionEvent

object Coord {

    var action = MotionEvent.ACTION_CANCEL
    var x = 0f
    var y = 0f

    operator fun invoke(x: Float, y: Float, action: Int): Coord {
        this.x = x
        this.y = y
        this.action = action
        return this
    }

}

object Draw { var coord : MutableList<Coord>? = null }