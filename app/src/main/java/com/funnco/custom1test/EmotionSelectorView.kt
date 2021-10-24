package com.funnco.custom1test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.lang.Math.abs

class EmotionSelectorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val painter = Paint()

    private var maxSectors = 4

    private var currentSector = 1

    val centerCircleRadius = 200
    val arcOffset = 10


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //Большой круг
        painter.color = 0x6500113E
        painter.alpha = 150
        canvas?.drawOval(
            RectF(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
            ), painter
        )
        painter.alpha = 255

        //Большой круг градиент
        painter.shader = RadialGradient(
            width / 2f,
            height / 2f,
            height / 2f,
            (0xFF2D67FF).toInt(),
            Color.TRANSPARENT,
            Shader.TileMode.MIRROR
        )
        canvas?.drawOval(
            RectF(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
            ), painter
        )

        //Маленький круг с описанием эмоции
        painter.shader = null
        canvas?.drawOval(
            RectF(
                width / 2f - centerCircleRadius,
                height / 2f - centerCircleRadius,
                width / 2f + centerCircleRadius,
                height / 2f + centerCircleRadius,
            ), painter
        )

        //Отрисовка выделения текущего сектора
        painter.color = Color.YELLOW
        painter.style = Paint.Style.STROKE
        painter.strokeWidth = 20f

        var fromAngle = -90f + (360f / maxSectors * (currentSector-1))
        var toAngle = 360f / maxSectors

        canvas?.drawArc(
            width / 2f - centerCircleRadius - arcOffset,
            height / 2f - centerCircleRadius - arcOffset,
            width / 2f + centerCircleRadius + arcOffset,
            height / 2f + centerCircleRadius + arcOffset,
            fromAngle,
            toAngle,
            false,
            painter
        )

        // Отображение текущего выделенного сектора
        painter.color = Color.WHITE
        painter.style = Paint.Style.FILL
        painter.textSize = 68f
        painter.strokeWidth = 1f
        canvas?.drawText(currentSector.toString() + "/" + maxSectors.toString(), width/2f - 45, height/2f + 30, painter)


    }

    // Пока не работает, надо делац
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return super.onTouchEvent(event)
        }

        val absEventX = abs(event.x)
        val absEventY = abs(event.y)

        return super.onTouchEvent(event)
    }

    fun setCurrentSector(sector: Int){
        currentSector = sector
        invalidate()
    }

    fun setMaxSectors(count: Int){
        maxSectors = count
        invalidate()
    }


}