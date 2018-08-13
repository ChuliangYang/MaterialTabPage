package com.me.cl.materialtabhost.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations.switchMap
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvvm.data.base.DataResource
import com.me.cl.materialtabhost.mvvm.data.base.NullLiveData
import com.me.cl.materialtabhost.mvvm.data.repos.CityRepository
import com.me.cl.materialtabhost.mvvm.viewmodel.base.MyViewModel
import com.me.cl.materialtabhost.mvvm.viewmodel.base.reuseWhenAlive
import javax.inject.Inject

class CityListViewModel @Inject constructor(val repo: CityRepository) : MyViewModel() {
    fun getTwoCityList(): LiveData<DataResource<Array<List<City>>>> {
        return reuseWhenAlive {
            switchMap(repo.cities) { input ->
                input.original?.let {
                    repo.divideIntoTwoList(it)
                } ?: let {
                    NullLiveData.create<DataResource<Array<List<City>>>>()
                }
            }
        }
    }

    fun getTitle(): LiveData<DataResource<String>> {
        return reuseWhenAlive("test") {
            repo.getTitle()
        }
    }
}