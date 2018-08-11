package com.me.cl.materialtabhost.mvvm.data.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

// Use local database as SSOT, local data will sync with remote data
abstract class RemoteSource<ResultType>{
    val result = MediatorLiveData<DataResource<ResultType>>()
    init {
        result.value=DataResource.loading()
        val dataSource=obtainFromLocal()
        result.addSource(dataSource) {
            if(needFetch(it)){
                fetchFromRemote(it)
            }else{
                result.value=DataResource.success(it)
            }
        }
    }

    private fun fetchFromRemote(dbResult:ResultType?) {
        val temp = MediatorLiveData<NetworkResponse<ResultType>>()
        val remoteSource=obtainFromRemote()
        temp.addSource(remoteSource) { response ->
            temp.removeSource(remoteSource)
            when(response){
                is ResponseSuccess->{
                    onRemoteFetchSuccess()
                    Completable.create {
                        saveRemoteResult(processResponse(response))
                        it.onComplete()
                    }.subscribeOn(Schedulers.io()).subscribe()
//                    result.value=DataResource.success(it.result)
                }
                is ResponseFailed->{
                    onRemoteFetchFailed()
                    result.value=DataResource.failed(response.errorMessage,dbResult)
                }
            }
        }
    }

    fun toLiveData():LiveData<DataResource<ResultType>>{
        return result
    }

    open fun onRemoteFetchSuccess(){

    }
    open fun onRemoteFetchFailed(){

    }

    open fun processResponse(response:ResponseSuccess<ResultType>):ResultType?{
        return response.result
    }

    abstract fun obtainFromRemote():LiveData<NetworkResponse<ResultType>>
    abstract fun obtainFromLocal():LiveData<ResultType>
    abstract fun saveRemoteResult(result:ResultType?)
    abstract fun needFetch(data:ResultType?):Boolean

}