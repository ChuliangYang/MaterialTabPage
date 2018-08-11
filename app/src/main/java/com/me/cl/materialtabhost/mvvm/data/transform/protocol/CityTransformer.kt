package com.me.cl.materialtabhost.mvvm.data.transform.protocol

import com.me.cl.materialtabhost.data.entities.City

interface CityTransformer {
    fun divideIntoTwoList(totalList: List<City>): Array<List<City>>
}