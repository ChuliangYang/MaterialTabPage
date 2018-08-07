package com.me.cl.materialtabhost.MVVM.data.remote.strategy

import android.arch.lifecycle.LiveData
import com.me.cl.materialtabhost.MVVM.data.remote.protocol.CityRemoteSource
import com.me.cl.materialtabhost.api.GistService
import com.me.cl.materialtabhost.bean.entities.City
import io.reactivex.Observable
import retrofit2.Retrofit

class CityRemoteSourceImpl(val retrofit: Retrofit):CityRemoteSource {
    val GistService= retrofit.create(com.me.cl.materialtabhost.api.GistService::class.java)
    override fun getCities(): Observable<List<City>> {
        return GistService.getCityListRx().toObservable()
    }
}