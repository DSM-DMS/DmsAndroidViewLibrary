package com.dsm.dms.dmsviewlibrary.calendar.small

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import com.dsm.dms.dmsviewlibrary.R
import com.dsm.dms.dmsviewlibrary.calendar.DmsCalendarDaysListener
import com.dsm.dms.dmsviewlibrary.calendar.DmsCalendarDaysAdapter
import com.dsm.dms.dmsviewlibrary.calendar.DmsCalendarView
import com.dsm.dms.dmsviewlibrary.user.DmsCalendarUserListener
import kotlinx.android.synthetic.main.dms_small_calendar_view.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import kotlin.collections.ArrayList


class DmsSmallCalendarView(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): DmsCalendarView(context, attrs, defStyleAttr) {

    override val isBig: Boolean = false

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.dms_small_calendar_view, this)
        assignUiElements()
    }

    override fun assignUiElements() {
        beforeImv = small_calendar_before_imv
        afterImv = small_calendar_after_imv
        titleTv = small_calendar_title_tv
        daysGrid = small_calendar_grid

        setOnClickEvent()
    }

}
