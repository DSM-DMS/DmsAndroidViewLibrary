package com.dsm.dms.dmsviewlibrary.calendar

import android.content.Context
import android.util.AttributeSet
import android.widget.GridView

class DmsCalendarGridView(context: Context, attr: AttributeSet): GridView(context, attr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightSpec =
            if (layoutParams.height == LayoutParams.WRAP_CONTENT)
                MeasureSpec.makeMeasureSpec(
                    Int.MAX_VALUE shr 2, MeasureSpec.AT_MOST
                )
            else heightMeasureSpec

        super.onMeasure(widthMeasureSpec, heightSpec)
    }
}