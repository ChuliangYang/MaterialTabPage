package com.me.cl.materialtabhost.MVVM.data.local.protocol

import android.arch.lifecycle.LiveData
import com.me.cl.materialtabhost.bean.entities.City

interface CityLocalSource{
    fun getCities():LiveData<List<City>>
    fun saveCities(cities: List<City>)
}