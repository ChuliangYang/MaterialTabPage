package com.me.cl.materialtabhost.api

import com.me.cl.materialtabhost.bean.CityBean
import retrofit2.Call
import retrofit2.http.GET

interface GistService {
    @GET("c1f004cb7f447ee5ccd6433bcb56d5af/raw/df3a570c9a0976e43b799be96da59186fc918ea7/CityList.json")
    fun getCityList():Call<List<CityBean>>
}