package com.dsm.dms.dmsviewlibrary.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import com.dsm.dms.dmsviewlibrary.R


class DmsSettingSwitch(context: Context, attrs: AttributeSet): SwitchCompat(context, attrs) {

    init {
        trackDrawable = ContextCompat.getDrawable(context, R.drawable.track_selector)
        thumbDrawable = ContextCompat.getDrawable(context, R.drawable.thumb_selector)
    }

}
