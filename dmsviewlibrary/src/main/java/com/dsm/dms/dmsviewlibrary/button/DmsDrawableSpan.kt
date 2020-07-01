package com.dsm.dms.dmsviewlibrary.button

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ImageSpan

class DmsDrawableSpan(
    drawable: Drawable,
    private val useTextAlpha: Boolean,
    private var paddingStart: Int = 0,
    private var paddingEnd: Int = 0
): ImageSpan(drawable) {

    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fontMetricsInt: Paint.FontMetricsInt?
    ): Int {
        val drawable = drawable
        val rect = drawable.bounds

        fontMetricsInt?.let {
            val fontMetrics = paint.fontMetricsInt
            val lineHeight = fontMetrics.bottom - fontMetrics.top
            val drHeight = Math.max(lineHeight, rect.bottom - rect.top)
            val centerY = fontMetrics.top + lineHeight / 2

            fontMetricsInt.apply {
                ascent = centerY - drHeight / 2
                descent = centerY + drHeight / 2
                top = ascent
                bottom = descent
            }

        }
        return rect.width() + paddingStart + paddingEnd
    }


    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int, end: Int,
        x: Float, top: Int, y: Int, bottom: Int,
        paint: Paint
    ) {

        val drawable = drawable

        canvas.save()

        val fontMetrics = paint.fontMetricsInt
        val lineHeight = fontMetrics.descent - fontMetrics.ascent
        val centerY = y + fontMetrics.descent - lineHeight / 2
        val transY = centerY - drawable.bounds.height() / 2

        if (paddingStart != 0)
            canvas.translate(x + paddingStart, transY.toFloat())
        else
            canvas.translate(x, transY.toFloat())

        if (useTextAlpha)
            drawable.alpha = Color.alpha(paint.color)

        drawable.draw(canvas)
        canvas.restore()
    }

}
