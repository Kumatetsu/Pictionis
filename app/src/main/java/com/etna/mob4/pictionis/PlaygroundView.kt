package com.etna.mob4.pictionis

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.etna.mob4.utils.Coord
import com.etna.mob4.utils.Draw
import com.etna.mob4.utils.FirebaseInstanceSingleton

class PlaygroundView  (context: Context, attrs: AttributeSet): View(context, attrs)  {
    private val fDbInstance = FirebaseInstanceSingleton.getDbInstance()
    private val drawingRef  = fDbInstance.getReference("drawing")
    private var drawing = Draw
    private var DRAWING_TAG = "DRAWING"
    private var draw = Paint()
    private var path = Path()

    private var currentX = 0f
    private var currentY = 0f
    private var startX = 0f
    private var startY = 0f

    init {
        draw.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 8f
            isAntiAlias = true
        }
    }

    fun setDrawing(drawing: Coord) {
        Log.d(DRAWING_TAG, "set Drawing")
        manageAction(drawing)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path, draw)
    }

    fun clearCanvas() {
        path.reset()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var coord = Coord(event.x, event.y, event.action)
        Log.d(DRAWING_TAG, "adding coords")
        drawing.coord?.add(coord)
        Log.d(DRAWING_TAG, "setValue to firebase?")
        drawingRef.setValue(coord)
        return true
    }

    private fun manageAction(coord: Coord): Boolean {
        Log.d(DRAWING_TAG, "manage action")
        val x = coord.x
        val y = coord.y

        when (coord.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = x
                startY = y
                actionDown(x, y)
            }
            MotionEvent.ACTION_MOVE -> actionMove(x, y)
            MotionEvent.ACTION_UP -> actionUp()
        }
        invalidate()
        return true
    }

    private fun actionDown(x: Float, y: Float) {
        path.moveTo(x, y)
        currentX = x
        currentY = y
    }

    private fun actionMove(x: Float, y: Float) {
        path.quadTo(currentX, currentY, (x + currentX) / 2, (y + currentY) / 2)
        currentX = x
        currentY = y
    }

    private fun actionUp() {
        path.lineTo(currentX, currentY)

        if (startX == currentX && startY == currentY) {
            path.lineTo(currentX, currentY + 2)
            path.lineTo(currentX + 1, currentY + 2)
            path.lineTo(currentX + 1, currentY)
        }
    }

}