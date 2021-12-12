package ru.intelligency.scholarship.presentation.di

import android.content.Context
import dagger.Module

@Module
class ContextModule(private val context: Context) {

    fun provideContext(): Context {
        return context
    }
}
