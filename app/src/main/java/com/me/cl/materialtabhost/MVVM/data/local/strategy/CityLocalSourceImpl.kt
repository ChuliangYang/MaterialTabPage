package com.me.cl.materialtabhost.MVVM.data.local.strategy

import android.arch.lifecycle.LiveData
import com.me.cl.materialtabhost.MVVM.data.local.base.room.AppDatabase
import com.me.cl.materialtabhost.MVVM.data.local.protocol.CityLocalSource
import com.me.cl.materialtabhost.bean.entities.City

class CityLocalSourceImpl(val roomDataBase:AppDatabase):CityLocalSource {
    val cityDao=roomDataBase.cityDao()

    override fun getCities(): LiveData<List<City>> {
        return cityDao.getAll()
    }

    override fun saveCities(cities:List<City>){
        cityDao.insertCities(cities)
    }
}