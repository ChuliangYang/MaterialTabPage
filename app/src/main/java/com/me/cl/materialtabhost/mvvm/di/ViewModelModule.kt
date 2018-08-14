package com.me.cl.materialtabhost.mvvm.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.me.cl.materialtabhost.mvvm.data.local.base.room.AppDatabase
import com.me.cl.materialtabhost.mvvm.data.local.base.room.daos.CityDao
import com.me.cl.materialtabhost.mvvm.data.local.protocol.CityLocalSource
import com.me.cl.materialtabhost.mvvm.data.local.strategy.CityLocalSourceImpl
import com.me.cl.materialtabhost.mvvm.data.remote.protocol.CityRemoteSource
import com.me.cl.materialtabhost.mvvm.data.remote.strategy.CityRemoteSourceImpl
import com.me.cl.materialtabhost.mvvm.data.transform.protocol.CityTransformer
import com.me.cl.materialtabhost.mvvm.data.transform.strategy.CityTransformerImpl
import com.me.cl.materialtabhost.mvvm.di.base.ViewModelKey
import com.me.cl.materialtabhost.mvvm.viewmodel.CityListViewModel
import com.me.cl.materialtabhost.mvvm.viewmodel.base.MyViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class ViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(CityListViewModel::class)
    abstract fun bindCityListViewModel(cityViewModel:CityListViewModel): ViewModel

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindCityLocalSource(cityLocalSourceImpl:CityLocalSourceImpl): CityLocalSource
    @Binds
    abstract fun bindCityRemoteSource(cityRemoteSourceImpl: CityRemoteSourceImpl): CityRemoteSource
    @Binds
    abstract fun bindCityTransformer(cityTransformerImpl: CityTransformerImpl): CityTransformer



    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideCityDao(dataBase:AppDatabase): CityDao{
            return dataBase.cityDao()
        }
    }
}