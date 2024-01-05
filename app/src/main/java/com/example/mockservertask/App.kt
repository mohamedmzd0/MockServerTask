package com.example.mockservertask

import android.app.Application
import com.example.di.VARIANT_URL
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        VARIANT_URL="https://reqres.in/api/"
    }
}