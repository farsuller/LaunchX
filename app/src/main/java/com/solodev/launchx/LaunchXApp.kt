package com.solodev.launchx

import android.app.Application
import com.solodev.launchx.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LaunchXApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LaunchXApp) // 👈 THIS IS MANDATORY
            modules(appModule)
        }
    }
}