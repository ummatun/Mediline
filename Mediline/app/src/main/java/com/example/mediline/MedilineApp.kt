package com.example.mediline

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class MedilineApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Dark mode off rakhar jonno
        // ThreeTenABP initialize kora (java.time kaj korbe)
        AndroidThreeTen.init(this)
    }
}