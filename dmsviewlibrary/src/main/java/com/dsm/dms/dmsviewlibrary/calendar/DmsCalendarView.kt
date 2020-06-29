package com.dsm.dms.dmsviewlibrary.calendar

import android.content.Context
import android.util.AttributeSet
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dsm.dms.dmsviewlibrary.user.DmsCalendarUserListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

abstract class DmsCalendarView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs), DmsCalendarDaysListener {

    override lateinit var beforeImv: ImageView
    override lateinit var afterImv: ImageView
    override lateinit var titleTv: TextView
    override lateinit var daysGrid: GridView

    override var selectedTv: TextView? = null

    override var calendar: Calendar = Calendar.getInstance()
    override var today: String = ""
    override val sdf: SimpleDateFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.KOREAN)

    override var year: Int = 0
    override var month: Int = 0
    override var selectedDay: Int = 0

    override val eventDays: ArrayList<String> = arrayListOf()

    override var eventListener: DmsCalendarUserListener? = null

    override fun selectedDay(day: Int) {
        val daySdf = SimpleDateFormat("yyyy년 M월 d일 E요일", Locale.KOREAN)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        titleTv.text = daySdf.format(calendar.time)

        selectedDay = day
    }

    abstract fun assignUiElements()

    fun setCalendar(date: Date, listener: DmsCalendarUserListener) {
        val today =
            if (isBig) SimpleDateFormat("d", Locale.KOREA).format(date).toInt()
            else 1
        year = SimpleDateFormat("yyyy", Locale.KOREA).format(date).toInt()
        month = SimpleDateFormat("M", Locale.KOREA).format(date).toInt()
        this.today = "${year}년 ${month}월 ${today}일"

        eventListener = listener

        daysGrid.adapter = DmsCalendarDaysAdapter(
            this, context, createCells(year, month)
        )

        selectedDay(today)
    }

    fun setBigCalendarEventDays(dates: ArrayList<Date>) {
        eventDays.clear()
        dates.forEach { date ->
            eventDays.add(
                sdf.format(date)
            )
        }

        daysGrid.adapter = DmsCalendarDaysAdapter(
            this, context, createCells(year, month)
        )
    }

    fun setOnClickEvent() {
        beforeImv.setOnClickListener {
            updateBigCalendar(year, --month)
        }

        afterImv.setOnClickListener {
            updateBigCalendar(year, ++month)
        }
    }

    private fun createCells(year: Int, month: Int): ArrayList<Any> {
        val cells= arrayListOf<Any>("일", "월", "화", "수", "목", "금", "토")

        if (month > 12) {
            this.year = year + 1
            this.month = 1
        }
        else if (month < 1) {
            this.year = year - 1
            this.month = 12
        }
        else {
            this.year = year
            this.month = month
        }

        calendar.time = sdf.parse("${year}년 ${month}월 ${1}일")

        (1 until calendar.get(Calendar.DAY_OF_WEEK)).forEach {
            cells.add(0)
        }

        for (i in 1 until calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            cells.add(i)
        }

        return cells
    }

    private fun updateBigCalendar(year: Int, month: Int) {
        daysGrid.adapter = DmsCalendarDaysAdapter(
            this, context, createCells(year, month)
        )
        selectedDay(1)
    }
}