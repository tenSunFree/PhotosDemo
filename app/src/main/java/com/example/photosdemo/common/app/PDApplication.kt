package com.example.photosdemo.common.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PDApplication : Application() {

    companion object {
        lateinit var instance: PDApplication
            private set
    }

    override fun onCreate() {
        System.loadLibrary("keys")
        super.onCreate()
        instance = this
    }
}