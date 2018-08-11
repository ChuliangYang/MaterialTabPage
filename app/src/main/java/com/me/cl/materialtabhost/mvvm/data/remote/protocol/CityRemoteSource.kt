package com.me.cl.materialtabhost.mvvm.data.remote.protocol

import android.arch.lifecycle.LiveData
import com.me.cl.materialtabhost.mvvm.data.base.NetworkResponse
import com.me.cl.materialtabhost.data.entities.City

interface CityRemoteSource {
    fun getCities(): LiveData<NetworkResponse<List<City>>>
}