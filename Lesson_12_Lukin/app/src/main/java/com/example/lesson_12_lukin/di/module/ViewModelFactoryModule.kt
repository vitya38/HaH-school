package com.example.lesson_12_lukin.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.lesson_12_lukin.di.util.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}