package com.dsm.dms.dmsviewlibrary

import android.app.Application
import androidx.preference.PreferenceManager


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themePref = sharedPreferences.getString("themePref", ThemeHelper.DEFAULT_MODE)!!
        ThemeHelper.applyTheme(themePref)
    }
}
