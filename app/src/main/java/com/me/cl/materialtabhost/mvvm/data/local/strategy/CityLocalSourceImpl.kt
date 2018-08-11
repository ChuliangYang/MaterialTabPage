package com.me.cl.materialtabhost.mvvm.data.local.strategy

import android.arch.lifecycle.LiveData
import com.me.cl.materialtabhost.mvvm.data.local.base.room.AppDatabase
import com.me.cl.materialtabhost.mvvm.data.local.protocol.CityLocalSource
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvvm.data.local.base.room.daos.CityDao
import javax.inject.Inject

class CityLocalSourceImpl @Inject constructor(val cityDao: CityDao):CityLocalSource {

    override fun getCities(): LiveData<List<City>> {
        return cityDao.getAll()
    }
    override fun saveCities(cities:List<City>){
        cityDao.insertCities(cities)
    }
}