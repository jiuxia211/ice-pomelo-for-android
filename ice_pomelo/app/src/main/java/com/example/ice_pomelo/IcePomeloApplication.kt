package com.example.ice_pomelo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

// 全局获取context
class IcePomeloApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}