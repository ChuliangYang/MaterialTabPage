package com.me.cl.materialtabhost.main

import android.content.Context
import android.os.Bundle
import com.me.cl.materialtabhost.api.GistService
import com.me.cl.materialtabhost.bean.CityBean
import com.me.cl.materialtabhost.main.base.MainInteractor
import io.reactivex.Observable
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class MainInteractorImpl @Inject constructor(val context: Context, val retrofit: Retrofit) : MainInteractor {

    val stateCache = HashMap<String, Any?>()
    val KEY_WHOLE_STATE = "KEY_WHOLE_STATE"

    override fun fetchCityList(): Observable<List<CityBean>> {
        return retrofit.create(GistService::class.java).getCityListRx().toObservable()
    }

    override fun divideIntoTwoList(totalList: List<CityBean>): List<List<CityBean>> {
        val listOne = mutableListOf<CityBean>()
        val listTwo = mutableListOf<CityBean>()

        totalList.forEach {
            if (it.rank.toInt() < 500) {
                listOne.add(it)
            } else {
                listTwo.add(it)
            }
        }

        return mutableListOf<MutableList<CityBean>>().apply {
            add(listOne)
            add(listTwo)
        }
    }

    override fun saveState(key: String, state: Any?) {
        stateCache[key] = state
    }

    override fun getState(key: String): Any? {
        return stateCache[key]
    }

    override fun restoreFromState(outState: Bundle?): Boolean {
        var isRestore = false

        outState?.apply {
            outState.getParcelableArrayList<CityBean>(STATE_RV_MODEL_0).let {
                stateCache.put(STATE_RV_MODEL_0, it)
                if (size() > 0) isRestore = true

            }
            outState.getParcelableArrayList<CityBean>(STATE_RV_MODEL_1).let {
                stateCache.put(STATE_RV_MODEL_1, it)
                if (size() > 0) isRestore = true
            }
        }
        return isRestore
    }

    override fun saveWholeState(outState: Bundle) {
        stateCache.forEach {
            when (it.key) {
                STATE_RV_MODEL_0 -> outState.putParcelableArrayList(it.key, it.value as ArrayList<CityBean>)
                STATE_RV_MODEL_1 -> outState.putParcelableArrayList(it.key, it.value as ArrayList<CityBean>)
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