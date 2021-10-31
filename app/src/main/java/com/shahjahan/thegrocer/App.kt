package com.shahjahan.thegrocer


import android.app.Application
import com.shahjahan.thegrocer.di.repositoryModule
import com.shahjahan.thegrocer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}