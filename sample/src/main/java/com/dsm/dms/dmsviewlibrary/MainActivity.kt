package com.dsm.dms.dmsviewlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dms.dmsviewlibrary.user.DmsCalendarUserListener
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*

class MainActivity: AppCompatActivity(), DmsCalendarUserListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        big_calendar.setCalendar(Date(), this)
        big_calendar.setCalendarEventDays(arrayListOf(Date()))

        small_calendar.setCalendar(Date(), this)
    }

    override fun selectedEvent(dateString: String) {
        big_text.text = dateString
    }
}