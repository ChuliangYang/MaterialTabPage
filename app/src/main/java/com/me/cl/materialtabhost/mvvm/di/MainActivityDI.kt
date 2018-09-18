package com.me.cl.materialtabhost.mvvm.di

import android.content.Context
import com.me.cl.materialtabhost.mvvm.ui.main.MainActivityMVVM
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivitySubComponentModule{
    @ContributesAndroidInjector(modules = [MainActivityMVVMModule::class,CityListFragmentSubComponentModule::class])
    abstract fun mainActivityInjector(): MainActivityMVVM
}

@Module
abstract class MainActivityMVVMModule{
    @Binds
    abstract fun bindCityLocalSource(mainActivity: MainActivityMVVM): Context
}