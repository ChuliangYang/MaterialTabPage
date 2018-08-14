package com.me.cl.materialtabhost.mvvm.di

import com.me.cl.materialtabhost.mvvm.di.base.PerFragment
import com.me.cl.materialtabhost.mvvm.ui.main.CityListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class CityListFragmentSubComponentModule{
    @PerFragment
    @ContributesAndroidInjector(modules = [CityListFragmentModule::class])
    abstract fun cityListFragmentInjector():CityListFragment
}

@Module
abstract class CityListFragmentModule{

}