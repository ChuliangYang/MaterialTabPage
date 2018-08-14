package com.me.cl.materialtabhost.mvp

import android.content.Context
import android.os.Bundle
import com.me.cl.materialtabhost.api.GistService
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvp.base.MainInteractor
import io.reactivex.Observable
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class MainInteractorImpl @Inject constructor(val context: Context, val retrofit: Retrofit) : MainInteractor {

    val stateCache = HashMap<String, Any?>()

    override fun fetchCityList(): Observable<List<City>> {
        return retrofit.create(GistService::class.java).getCityListRx().toObservable()
    }

    override fun divideIntoTwoList(totalList: List<City>): List<List<City>> {
        val listOne = mutableListOf<City>()
        val listTwo = mutableListOf<City>()

        totalList.forEach {
            if (it.rank.toInt() < 500) {
                listOne.add(it)
            } else {
                listTwo.add(it)
            }
        }

        return mutableListOf<MutableList<City>>().apply {
            add(listOne)
            add(listTwo)
        }
    }

    override fun cached(key: String, state: Any?) {
        stateCache[key] = state
    }

    override fun getCache(key: String): Any? {
        return stateCache[key]
    }

    override fun restoreState(outState: Bundle?): Boolean {
        var isRestore = false

        outState?.apply {
            outState.getParcelableArrayList<City>(STATE_RV_MODEL_0).let {
                stateCache.put(STATE_RV_MODEL_0, it)
                if (size() > 0) isRestore = true

            }
            outState.getParcelableArrayList<City>(STATE_RV_MODEL_1).let {
                stateCache.put(STATE_RV_MODEL_1, it)
                if (size() > 0) isRestore = true
            }
        }
        return isRestore
    }

    override fun saveState(outState: Bundle) {
        stateCache.forEach {
            when (it.key) {
                STATE_RV_MODEL_0 -> outState.putParcelableArrayList(it.key, it.value as ArrayList<City>)
                STATE_RV_MODEL_1 -> outState.putParcelableArrayList(it.key, it.value as ArrayList<City>)
            }
        }
    }

    override fun release() {
        stateCache.clear()
    }

    companion object {
        val STATE_RV_MODEL_0 = "RV_MODEL_0"
        val STATE_RV_MODEL_1 = "RV_MODEL_1"
    }

}