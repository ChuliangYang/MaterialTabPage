package com.me.cl.materialtabhost.bean.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
@Entity(tableName = "cities")
data class City(val city: String,
                val growth_from_2000_to_2013: String,
                val latitude: Double,
                val longitude: Double,
                val population: String,
                val rank: String,
                val state: String) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var uid:Int=0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

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

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }
}
