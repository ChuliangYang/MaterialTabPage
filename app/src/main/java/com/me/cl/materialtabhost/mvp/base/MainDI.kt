package com.me.cl.materialtabhost.mvp.base

import android.content.Context
import com.me.cl.materialtabhost.data.GIST_HOST
import com.me.cl.materialtabhost.mvp.MainActivityMVP
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope


@Component(modules = arrayOf(MainMVPModule::class))
@ActivityScope
interface MainMVPComponent {
    fun inject(mainActivity: MainActivityMVP)
}

@Module
class MainMVPModule(val activity: MainActivityMVP) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(GIST_HOST).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    }
}


@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScope