package com.me.cl.materialtabhost.mvp

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvp.base.BaseRx2LcePresenter
import com.me.cl.materialtabhost.mvp.base.MainViewMVP
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(val mainRepo:MainRepository):BaseRx2LcePresenter<MainViewMVP,CityModel>() {
    fun loadCities(ptr:Boolean){
        subscribe(mainRepo.fetchCityList().subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).map {
            mainRepo.divideIntoTwoList(it)
        }.map {
            CityModel(it[0],it[1],0)
        },ptr)
    }
}