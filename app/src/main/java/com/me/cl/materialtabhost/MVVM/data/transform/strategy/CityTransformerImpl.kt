package com.me.cl.materialtabhost.MVVM.data.transform.strategy

import com.me.cl.materialtabhost.MVVM.data.transform.protocol.CityTransformer
import com.me.cl.materialtabhost.bean.entities.City

class CityHandlerImpl:CityTransformer {
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
}