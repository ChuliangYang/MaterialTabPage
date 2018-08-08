package com.me.cl.materialtabhost.MVVM.data.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer

// Use local database as SSOT, local data will sync with remote data
abstract class RemoteSource<ResultType>{
    val result = MediatorLiveData<Data<ResultType,*>>()
    init {
        result.value=Data.loading()
        val dataSource=obtainFromLocal()
        val remoteSource=obtainFromRemote()
        result.addSource(dataSource) {
            if(needFetch(it)){

            }else{

            }
        }
    }

    fun toLiveData():LiveData<Data<ResultType,*>>{
        return result
    }

    abstract fun obtainFromRemote():LiveData<ResultType>
    abstract fun obtainFromLocal():LiveData<ResultType>
    abstract fun needFetch(data:ResultType?):Boolean

}