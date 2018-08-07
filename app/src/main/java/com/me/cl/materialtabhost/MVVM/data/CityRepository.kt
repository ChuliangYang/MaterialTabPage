package com.me.cl.materialtabhost.MVVM.data

import android.arch.lifecycle.LiveData
import com.me.cl.materialtabhost.MVVM.data.transform.protocol.CityTransformer
import com.me.cl.materialtabhost.MVVM.data.local.protocol.CityLocalSource
import com.me.cl.materialtabhost.MVVM.data.remote.protocol.CityRemoteSource
import com.me.cl.materialtabhost.bean.entities.City
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class CityRepository(val localSource: CityLocalSource, val remoteSource: CityRemoteSource, val transformer:CityTransformer){
        val cities:LiveData<List<City>>
        get() {
            remoteSource.getCities().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe{localSource.saveCities(it)}
            return localSource.getCities()
        }

}