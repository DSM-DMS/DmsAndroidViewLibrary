package com.dsm.dms.dmsviewlibrary.calendar.big

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.dsm.dms.dmsviewlibrary.R
import com.dsm.dms.dmsviewlibrary.calendar.DmsCalendarDaysListener
import java.util.ArrayList

class DmsBigCalendarDaysAdapter(private val listener: DmsCalendarDaysListener, context: Context, days: ArrayList<Any>) :
    ArrayAdapter<Any>(context, R.layout.item_big_calendar_view, days) {
    private val inflater= LayoutInflater.from(context)

    override fun getView(position: Int, getView: View?, parent: ViewGroup): View {
        with(listener) {
            val view =
                getView?.let { it }
                    .let { inflater.inflate(R.layout.item_big_calendar_view, parent, false) }

            if (view is TextView) {
                val day = getItem(position)

                if (day != 0) {
                    view.setTextColor(context.resources.getColor(R.color.black_900, null))
                    eventDays.forEach { eventDay ->
                        if ("${year}년 ${month}월 ${day}일" == eventDay) {
                            view.setTextColor(context.resources.getColor(R.color.main_900, null))
                        }
                    }
                    view.background = context.getDrawable(R.drawable.calendar_day_normal)
                    view.gravity = Gravity.CENTER
                    view.text = day.toString()
                }

                if (day is Int && day != 0) {
                    if ("${year}년 ${month}월 ${day}일" == today)
                        view.background = context.getDrawable(R.drawable.calendar_day_stroke)

                    view.setOnClickListener {
                        selectedTv?.let { tv ->
                            if ("${year}년 ${month}월 ${tv.text}일" == today) {
                                tv.background = context.getDrawable(R.drawable.calendar_day_stroke)
                            }
                            else {
                                tv.background = context.getDrawable(R.drawable.calendar_day_normal)
                            }
                            tv.setTextColor(context.resources.getColor(R.color.black_900, null))
                            eventDays.forEach { eventDay ->
                                if ("${year}년 ${month}월 ${tv.text}일" == eventDay) {
                                    tv.setTextColor(context.resources.getColor(R.color.main_900, null))
                                }
                            }
                        }

                        view.setTextColor(context.resources.getColor(R.color.black_100, null))
                        view.background = context.getDrawable(R.drawable.calendar_day_solid)

                        selectedDay(day)

                        selectedTv = view

                        eventListener?.let { it.selectedEvent("${year}년 ${month}월 ${selectedDay}일") }
                    }
                }
            }

            return view
        }
    }
}
