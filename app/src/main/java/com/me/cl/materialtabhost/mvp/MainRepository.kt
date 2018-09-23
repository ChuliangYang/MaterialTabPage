package com.me.cl.materialtabhost.mvp

import android.content.Context
import com.me.cl.materialtabhost.api.GistService
import com.me.cl.materialtabhost.data.entities.City
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class MainRepository @Inject constructor(val retrofit: Retrofit) {
     fun fetchCityList(): Observable<List<City>> {
        return retrofit.create(GistService::class.java).getCityListRx().toObservable()
    }

     fun divideIntoTwoList(totalList: List<City>): List<List<City>> {
        val listOne = mutableListOf<City>()
        val listTwo = mutableListOf<City>()

        totalList.forEach {
            if (it.rank.toInt() < 500) {
                listOne.add(it)
            } else {
                listTwo.add(it)
            }
        }

        return arrayListOf<List<City>>().apply {
            add(listOne)
            add(listTwo)
        }
    }
}