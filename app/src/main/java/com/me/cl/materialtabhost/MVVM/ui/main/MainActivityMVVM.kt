package com.me.cl.materialtabhost.MVVM.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.me.cl.materialtabhost.R

class MainActivityMVVM:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container_activity)
        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                    .add(R.id.fl_container, CityListFragment.newInstance(), CityListFragment.TAG).commit()
        }
    }

}