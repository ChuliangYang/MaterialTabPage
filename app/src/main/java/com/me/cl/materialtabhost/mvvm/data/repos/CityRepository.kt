package com.me.cl.materialtabhost.mvvm.data.repos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvvm.data.base.DataResource
import com.me.cl.materialtabhost.mvvm.data.base.NetworkResponse
import com.me.cl.materialtabhost.mvvm.data.remote.base.RemoteSource
import com.me.cl.materialtabhost.mvvm.data.local.protocol.CityLocalSource
import com.me.cl.materialtabhost.mvvm.data.remote.protocol.CityRemoteSource
import com.me.cl.materialtabhost.mvvm.data.repos.base.RxReopsitory
import com.me.cl.materialtabhost.mvvm.data.repos.base.autoDispose
import com.me.cl.materialtabhost.mvvm.data.transform.base.ReactUtil
import com.me.cl.materialtabhost.mvvm.data.transform.protocol.CityTransformer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CityRepository @Inject constructor(val localSource: CityLocalSource, val remoteSource: CityRemoteSource, val transformer: CityTransformer): RxReopsitory() {
    val title = MutableLiveData<DataResource<String>>()
    val cities: LiveData<DataResource<List<City>>>
        get() {
            return object : RemoteSource<List<City>>() {
                override fun obtainFromLocal(): LiveData<List<City>> {
                    return localSource.getCities()
                }

                override fun needFetch(data: List<City>?): Boolean {
                    return data==null||data.isEmpty()
                }

                override fun obtainFromRemote(): LiveData<NetworkResponse<List<City>>> {
                    return remoteSource.getCities()
                }

                override fun saveRemoteResult(result: List<City>?) {
                    result?.let {
                        localSource.saveCities(it)
                    }
                }
            }.toLiveData()
        }

    fun divideIntoTwoList(totalList: List<City>): LiveData<DataResource<Array<List<City>>>> {
        return ReactUtil.toLiveDataResource {
            transformer.divideIntoTwoList(totalList)
        }
    }

    fun getTitle(): LiveData<DataResource<String>> {
        title.value = DataResource.success("test title")
        autoDispose(Observable.interval(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe {
            title.value = DataResource.success("test title${it}")
        })
        return title
    }
}