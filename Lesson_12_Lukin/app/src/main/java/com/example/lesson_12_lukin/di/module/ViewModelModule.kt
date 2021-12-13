package com.example.lesson_12_lukin.di.module

import androidx.lifecycle.ViewModel
import com.example.lesson_12_lukin.di.util.ViewModelKey
import com.example.lesson_12_lukin.presentation.detail.BridgeDetailViewModel
import com.example.lesson_12_lukin.presentation.list.BridgeListViewModel
import com.example.lesson_12_lukin.presentation.map.MapsActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(BridgeListViewModel::class)
    abstract fun bridgeListViewModel(viewModel: BridgeListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BridgeDetailViewModel::class)
    abstract fun bridgeDetailViewModel(viewModel: BridgeDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapsActivityViewModel::class)
    abstract fun mapsActivityViewModel(viewModel: MapsActivityViewModel): ViewModel
}