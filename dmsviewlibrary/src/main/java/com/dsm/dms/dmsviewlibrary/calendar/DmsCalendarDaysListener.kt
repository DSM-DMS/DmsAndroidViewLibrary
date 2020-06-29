package com.dsm.dms.dmsviewlibrary.calendar

import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.dsm.dms.dmsviewlibrary.user.DmsCalendarUserListener
import java.text.SimpleDateFormat
import java.util.*

interface DmsCalendarDaysListener {
    var beforeImv: ImageView
    var afterImv: ImageView
    var titleTv: TextView
    var daysGrid: GridView

    var selectedTv: TextView?

    var eventListener: DmsCalendarUserListener?

    var calendar: Calendar
    val sdf: SimpleDateFormat
    var today: String

    var year: Int
    var month: Int
    var selectedDay: Int

    val eventDays: ArrayList<String>

    val isBig: Boolean

    fun selectedDay(day: Int)
}