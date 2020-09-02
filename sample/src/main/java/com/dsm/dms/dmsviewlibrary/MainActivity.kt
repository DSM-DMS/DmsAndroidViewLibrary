package com.dsm.dms.dmsviewlibrary

import android.os.Bundle
import android.os.Handler
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

        button.setOnClickListener {
            button.onLoad("로딩")

            Handler().postDelayed(
                {
                    button.onError("에러")
                    Handler().postDelayed(
                        {
                            button.onLoad("로딩")
                            Handler().postDelayed(
                                {
                                    button.onSuccess("성공")
                                }, 4000)
                        }, 4000)
                }, 4000)
        }
    }

    override fun selectedEvent(dateString: String) {
        big_text.text = dateString
    }
}