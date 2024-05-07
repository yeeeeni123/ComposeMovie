package com.yeen.movie

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.perf.metrics.AddTrace
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApplication : Application() {
    @AddTrace(name = "onCreateTrace", enabled = true /* optional */)
    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()
    }
}