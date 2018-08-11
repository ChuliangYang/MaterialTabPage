package com.me.cl.materialtabhost.mvvm.data.remote.strategy

import android.arch.lifecycle.LiveData
import com.me.cl.materialtabhost.api.GistService
import com.me.cl.materialtabhost.mvvm.data.base.NetworkResponse
import com.me.cl.materialtabhost.mvvm.data.remote.protocol.CityRemoteSource
import com.me.cl.materialtabhost.data.entities.City
import retrofit2.Retrofit
import javax.inject.Inject

class CityRemoteSourceImpl @Inject constructor(val gistService: GistService):CityRemoteSource {
    override fun getCities(): LiveData<NetworkResponse<List<City>>> {
        return gistService.getCityListLive()
    }
}