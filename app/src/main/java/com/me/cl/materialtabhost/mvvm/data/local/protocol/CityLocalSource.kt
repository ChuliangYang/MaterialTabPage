package com.me.cl.materialtabhost.mvvm.data.local.protocol

import android.arch.lifecycle.LiveData
import com.me.cl.materialtabhost.data.entities.City

interface CityLocalSource{
    fun getCities():LiveData<List<City>>
    fun saveCities(cities: List<City>)
}