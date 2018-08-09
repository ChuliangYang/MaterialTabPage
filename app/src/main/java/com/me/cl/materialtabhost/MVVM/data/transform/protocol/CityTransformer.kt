package com.me.cl.materialtabhost.MVVM.data.transform.protocol

import com.me.cl.materialtabhost.bean.entities.City

interface CityTransformer {
    fun divideIntoTwoList(totalList: List<City>): Array<List<City>>
}