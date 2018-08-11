package com.me.cl.materialtabhost.mvvm.di

import com.me.cl.materialtabhost.mvvm.ui.main.MainActivityMVVM
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivitySubComponentModule{
    @ContributesAndroidInjector(modules = [CityListFragmentSubComponentModule::class])
    abstract fun mainActivityInjector(): MainActivityMVVM
}