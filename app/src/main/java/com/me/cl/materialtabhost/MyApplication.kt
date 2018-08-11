package com.me.cl.materialtabhost

import android.app.Activity
import android.app.Application
import com.me.cl.materialtabhost.mvvm.di.DaggerAutoInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication:Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAutoInjector.inject(this)
    }
}