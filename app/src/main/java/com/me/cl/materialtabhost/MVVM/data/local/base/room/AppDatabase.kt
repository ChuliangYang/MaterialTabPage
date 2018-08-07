package com.me.cl.materialtabhost.MVVM.data.local.base.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.me.cl.materialtabhost.MVVM.data.local.base.room.daos.CityDao
import com.me.cl.materialtabhost.bean.entities.City

@Database(entities= arrayOf(City::class),version=1)
abstract class AppDatabase: RoomDatabase() {
   abstract fun cityDao(): CityDao

   companion object {
      private lateinit var sInstance: AppDatabase
      @JvmStatic
      fun getInstance(context: Context): AppDatabase {
         if (sInstance == null) {
            synchronized(AppDatabase::class.java) {
               if (sInstance == null) {
                  sInstance = Room.databaseBuilder(context.applicationContext,
                          AppDatabase::class.java, "material-tab").build()
               }
            }
         }
         return sInstance
      }
   }
}