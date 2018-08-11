package com.me.cl.materialtabhost.mvvm.data.local.base.room.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.me.cl.materialtabhost.data.entities.City


@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: City)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities: List<City>)

    @Update
    fun updateCity(city: City): Int

    @Update
    fun updateCities(cities: List<City>): Int

    @Update
    fun updateCities(vararg users: City)

    @Delete
    fun deleteCity(city: City): Int

    @Delete
    fun deleteCities(cities: List<City>): Int

    @Delete
    fun deleteCities(vararg cities: City): Int

    @Query("select * from cities")
    fun getAll(): LiveData<List<City>>

    @Query("select * from cities")
    fun getCities(): LiveData<Array<City>>

    @Query("delete from cities where city =:name")
    fun removeCity(name: String)
}