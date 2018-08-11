package com.me.cl.materialtabhost.api

import android.arch.lifecycle.LiveData
import com.me.cl.materialtabhost.mvvm.data.base.NetworkResponse
import com.me.cl.materialtabhost.data.entities.City
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface GistService {

    @GET("c1f004cb7f447ee5ccd6433bcb56d5af/raw/df3a570c9a0976e43b799be96da59186fc918ea7/CityList.json")
    fun getCityList():Call<List<City>>

    @GET("c1f004cb7f447ee5ccd6433bcb56d5af/raw/df3a570c9a0976e43b799be96da59186fc918ea7/CityList.json")
    fun getCityListRx(): Single<List<City>>

    @GET("c1f004cb7f447ee5ccd6433bcb56d5af/raw/df3a570c9a0976e43b799be96da59186fc918ea7/CityList.json")
    fun getCityListLive(): LiveData<NetworkResponse<List<City>>>
}