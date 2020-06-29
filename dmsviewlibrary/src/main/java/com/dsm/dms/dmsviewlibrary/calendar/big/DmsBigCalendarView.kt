package com.dsm.dms.dmsviewlibrary.calendar.big

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import com.dsm.dms.dmsviewlibrary.R
import com.dsm.dms.dmsviewlibrary.calendar.DmsCalendarDaysListener
import com.dsm.dms.dmsviewlibrary.user.DmsCalendarUserListener
import kotlinx.android.synthetic.main.dms_big_calendar_view.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import kotlin.collections.ArrayList


class DmsBigCalendarView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs),
    DmsCalendarDaysListener {

    override lateinit var beforeImv: ImageView
    override lateinit var afterImv: ImageView
    override lateinit var titleTv: TextView
    override lateinit var daysGrid: GridView

    override var selectedTv: TextView? = null

    override var calendar: Calendar = getInstance()
    override var today: String = ""
    override val sdf: SimpleDateFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.KOREAN)

    override var year: Int = 0
    override var month: Int = 0
    override var selectedDay: Int = 0

    override val eventDays: ArrayList<String> = arrayListOf()

    override var eventListener: DmsCalendarUserListener? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.dms_big_calendar_view, this)
        assignUiElements()
    }

    override fun selectedDay(day: Int) {
        val daySdf = SimpleDateFormat("yyyy년 M월 d일 E요일", Locale.KOREAN)
        calendar.set(DAY_OF_MONTH, day)
        titleTv.text = daySdf.format(calendar.time)

        selectedDay = day
    }

    fun setBigCalendar(date: Date, listener: DmsCalendarUserListener) {
        val today = SimpleDateFormat("d", Locale.KOREA).format(date).toInt()
        year = SimpleDateFormat("yyyy", Locale.KOREA).format(date).toInt()
        month = SimpleDateFormat("M", Locale.KOREA).format(date).toInt()

        this.today = "${year}년 ${month}월 ${today}일"

        eventListener = listener

        daysGrid.adapter = DmsBigCalendarDaysAdapter(
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

        daysGrid.adapter = DmsBigCalendarDaysAdapter(
            this, context, createCells(year, month)
        )
    }

    private fun assignUiElements() {
        beforeImv = big_calendar_before_imv
        afterImv = big_calendar_after_imv
        titleTv = big_calendar_title_tv
        daysGrid = big_calendar_grid

        setOnClickEvent()
    }

    private fun setOnClickEvent() {
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

        (1 until calendar.get(DAY_OF_WEEK)).forEach {
            cells.add(0)
        }

        for (i in 1 until calendar.getActualMaximum(DAY_OF_MONTH)) {
            cells.add(i)
        }

        return cells
    }

    private fun updateBigCalendar(year: Int, month: Int) {
        daysGrid.adapter = DmsBigCalendarDaysAdapter(
            this, context, createCells(year, month)
        )
        selectedDay(1)
    }

}
