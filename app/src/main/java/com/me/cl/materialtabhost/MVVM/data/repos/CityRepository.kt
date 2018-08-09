package com.me.cl.materialtabhost.MVVM.data.repos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.me.cl.materialtabhost.MVVM.data.base.DataResource
import com.me.cl.materialtabhost.MVVM.data.base.NetworkResponse
import com.me.cl.materialtabhost.MVVM.data.base.RemoteSource
import com.me.cl.materialtabhost.MVVM.data.transform.protocol.CityTransformer
import com.me.cl.materialtabhost.MVVM.data.local.protocol.CityLocalSource
import com.me.cl.materialtabhost.MVVM.data.remote.protocol.CityRemoteSource
import com.me.cl.materialtabhost.bean.entities.City
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CityRepository(val localSource: CityLocalSource, val remoteSource: CityRemoteSource, val transformer:CityTransformer){
        val title=MutableLiveData<DataResource<String>>()
        val cities:LiveData<DataResource<List<City>>>
        get() {
            return object: RemoteSource<List<City>>() {
                override fun obtainFromLocal(): LiveData<List<City>> {
                    return localSource.getCities()
                }
                override fun needFetch(data: List<City>?): Boolean {
                    return data==null
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
             val temp=MutableLiveData<DataResource<Array<List<City>>>>()
             Single.create<DataResource<*>> {
                 it.onSuccess(DataResource.success(transformer.divideIntoTwoList(totalList)))
             }.subscribeOn(Schedulers.computation()).subscribe { t1, t2 ->
                  (t1 as DataResource<Array<List<City>>>?)?.let{
                      temp.postValue(it)
                 }
             }
             return temp
     }

    fun getTitle():LiveData<DataResource<String>>{
        title.value= DataResource.success("test title")
        return title
    }

}