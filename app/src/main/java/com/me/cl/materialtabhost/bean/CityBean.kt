package com.me.cl.materialtabhost.bean

import android.os.Parcel
import android.os.Parcelable

data class CityBean(val city: String,
                    val growth_from_2000_to_2013: String,
                    val latitude: Double,
                    val longitude: Double,
                    val population: String,
                    val rank: String,
                    val state: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
        parcel.writeString(growth_from_2000_to_2013)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(population)
        parcel.writeString(rank)
        parcel.writeString(state)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CityBean> {
        override fun createFromParcel(parcel: Parcel): CityBean {
            return CityBean(parcel)
        }

        override fun newArray(size: Int): Array<CityBean?> {
            return arrayOfNulls(size)
        }
    }
}
