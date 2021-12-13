package com.example.lesson_12_lukin.di.module

import com.example.lesson_12_lukin.presentation.map.MapsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mapsActivity(): MapsActivity
}