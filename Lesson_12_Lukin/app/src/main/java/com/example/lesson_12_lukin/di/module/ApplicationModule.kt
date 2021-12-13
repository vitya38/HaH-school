package com.example.lesson_12_lukin.di.module

import android.app.Application
import android.content.Context
import com.example.lesson_12_lukin.BridgesApplication
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule {

    @Provides
    fun provideContext(app: BridgesApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideApplication(app: BridgesApplication): Application {
        return app
    }
}