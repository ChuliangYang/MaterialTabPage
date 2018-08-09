package com.me.cl.materialtabhost.MVVM.data.remote.protocol

import android.arch.lifecycle.LiveData
import com.me.cl.materialtabhost.MVVM.data.base.NetworkResponse
import com.me.cl.materialtabhost.bean.entities.City
import io.reactivex.Observable

interface CityRemoteSource {
    fun getCities(): LiveData<NetworkResponse<List<City>>>
}