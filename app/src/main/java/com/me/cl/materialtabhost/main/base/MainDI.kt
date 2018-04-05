package com.me.cl.materialtabhost.main.base

import android.content.Context
import com.me.cl.materialtabhost.GIST_HOST
import com.me.cl.materialtabhost.main.MainActivityRx
import com.me.cl.materialtabhost.main.MainInteractorImpl
import com.me.cl.materialtabhost.main.MainPresenterImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Component(modules = arrayOf(MainModule::class))
@ActivityScope
interface MainComponent {
    fun inject(mainActivity: MainActivityRx)
}

@Module
class MainModule(val activity: MainActivityRx) {

    @Provides
    fun provideMainPresenter(mainPresenterImpl: MainPresenterImpl): MainPresenter {
        return mainPresenterImpl
    }

    @Provides
    fun provideMainInteractor(mainInteractorImpl: MainInteractorImpl): MainInteractor {
        return mainInteractorImpl
    }

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