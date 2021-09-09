package com.ooooonly.onote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ONoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}