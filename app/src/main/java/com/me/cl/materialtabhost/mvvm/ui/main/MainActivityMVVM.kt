package com.me.cl.materialtabhost.mvvm.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.me.cl.materialtabhost.R
import com.me.cl.materialtabhost.mvvm.di.base.Injectable
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivityMVVM:AppCompatActivity(),HasSupportFragmentInjector, Injectable {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() =dispatchingAndroidInjector
//    @Inject
//    lateinit var testDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container_activity)
//        Completable.create {
//            testDatabase.clearAllTables()
//        }.subscribeOn(Schedulers.io()).subscribe()

        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                    .add(R.id.fl_container, CityListFragment.newInstance(), CityListFragment.TAG).commit()
        }
    }

}