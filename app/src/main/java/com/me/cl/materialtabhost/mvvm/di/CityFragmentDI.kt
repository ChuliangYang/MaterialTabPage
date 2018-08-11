package com.me.cl.materialtabhost.mvvm.di

import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.RoomDatabase
import com.me.cl.materialtabhost.mvvm.data.local.base.room.AppDatabase
import com.me.cl.materialtabhost.mvvm.data.local.base.room.daos.CityDao
import com.me.cl.materialtabhost.mvvm.data.local.protocol.CityLocalSource
import com.me.cl.materialtabhost.mvvm.data.local.strategy.CityLocalSourceImpl
import com.me.cl.materialtabhost.mvvm.data.remote.protocol.CityRemoteSource
import com.me.cl.materialtabhost.mvvm.data.remote.strategy.CityRemoteSourceImpl
import com.me.cl.materialtabhost.mvvm.data.transform.protocol.CityTransformer
import com.me.cl.materialtabhost.mvvm.data.transform.strategy.CityTransformerImpl
import com.me.cl.materialtabhost.mvvm.ui.main.CityListFragment
import com.me.cl.materialtabhost.mvvm.viewmodel.MyViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class CityListFragmentSubComponentModule{
    @PerFragment
    @ContributesAndroidInjector(modules = [CityListFragmentModule::class])
    abstract fun cityListFragmentInjector():CityListFragment
}

@Suppress("unused")
@Module
abstract class CityListFragmentModule{

}