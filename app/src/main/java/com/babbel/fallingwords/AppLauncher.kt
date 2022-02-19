package com.babbel.fallingwords

import android.app.Application
import android.content.Context

class AppLauncher : Application() {
    companion object {
        //Application context to be accessed by whole application
        var context : Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}