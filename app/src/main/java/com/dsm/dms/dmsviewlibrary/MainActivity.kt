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
        calendar.setBigCalendar(Calendar.getInstance().time, this)
        calendar.setBigCalendarEventDays(arrayListOf(Calendar.getInstance().time))
    }

    override fun selectedEvent(dateString: String) {
        text.text = dateString
    }
}