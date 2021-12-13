package com.example.lesson_12_lukin.di.component

import com.example.lesson_12_lukin.BridgesApplication
import com.example.lesson_12_lukin.di.module.ActivityModule
import com.example.lesson_12_lukin.di.module.ApiServiceModule
import com.example.lesson_12_lukin.di.module.FragmentModule
import com.example.lesson_12_lukin.di.module.ViewModelFactoryModule
import com.example.lesson_12_lukin.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApiServiceModule::class,
        ViewModelFactoryModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<BridgesApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: BridgesApplication): ApplicationComponent
    }
}