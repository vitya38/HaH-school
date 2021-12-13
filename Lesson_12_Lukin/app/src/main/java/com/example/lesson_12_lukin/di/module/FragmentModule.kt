package com.example.lesson_12_lukin.di.module

import com.example.lesson_12_lukin.presentation.detail.BridgeDetailFragment
import com.example.lesson_12_lukin.presentation.list.BridgeListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bridgeListFragment(): BridgeListFragment

    @ContributesAndroidInjector
    abstract fun bridgeDetailFragment(): BridgeDetailFragment
}