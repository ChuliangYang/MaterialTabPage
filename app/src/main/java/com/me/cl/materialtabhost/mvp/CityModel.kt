package com.me.cl.materialtabhost.mvp

import android.os.Parcel
import android.os.Parcelable
import com.me.cl.materialtabhost.data.entities.City

data class CityModel(var city1:List<City>, var city2:List<City>,var selected:Int?=null) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.createTypedArrayList(City),
            parcel.createTypedArrayList(City),
            parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(city1)
        parcel.writeTypedList(city2)
        parcel.writeValue(selected)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CityModel> {
        override fun createFromParcel(parcel: Parcel): CityModel {
            return CityModel(parcel)
        }

        override fun newArray(size: Int): Array<CityModel?> {
            return arrayOfNulls(size)
        }
    }
}