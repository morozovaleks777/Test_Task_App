package com.moralex.testtaskapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestTaskApplication: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}
