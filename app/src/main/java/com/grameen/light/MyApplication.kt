package com.grameen.light

import android.app.Application
import android.preference.PreferenceManager
import org.osmdroid.config.Configuration

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val config = Configuration.getInstance()
        config.load(
            this,
            PreferenceManager.getDefaultSharedPreferences(this)
        )
        config.userAgentValue = packageName
    }
}
