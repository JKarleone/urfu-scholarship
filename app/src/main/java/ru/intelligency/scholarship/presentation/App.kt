package ru.intelligency.scholarship.presentation

import android.app.Application
import ru.intelligency.scholarship.presentation.di.AppComponent
import ru.intelligency.scholarship.presentation.di.ContextModule
import ru.intelligency.scholarship.presentation.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }
}
