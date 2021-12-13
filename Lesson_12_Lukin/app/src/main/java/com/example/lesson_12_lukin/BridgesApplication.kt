package com.example.lesson_12_lukin

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import com.example.lesson_12_lukin.di.component.DaggerApplicationComponent

class BridgesApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(this)
    }
}