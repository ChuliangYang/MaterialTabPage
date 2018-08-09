package com.me.cl.materialtabhost.MVVM.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations.switchMap
import android.arch.lifecycle.ViewModel
import com.me.cl.materialtabhost.MVVM.data.base.DataResource
import com.me.cl.materialtabhost.MVVM.data.base.NullLiveData
import com.me.cl.materialtabhost.MVVM.data.repos.CityRepository
import com.me.cl.materialtabhost.bean.entities.City

class CityListViewModel(val repo: CityRepository) : ViewModel() {
     fun getTwoCityList():LiveData<DataResource<Array<List<City>>>>{
         return switchMap(repo.cities) { input ->
             input.original?.let{
                 repo.divideIntoTwoList(it)
             }?:let{
                 NullLiveData.create<DataResource<Array<List<City>>>>()
             }
         }
     }

    fun getTitle():LiveData<DataResource<String>>{
        return repo.getTitle()
    }
}