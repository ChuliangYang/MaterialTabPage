package com.me.cl.materialtabhost.mvvm.di

import android.app.Application
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.android.example.github.util.LiveDataCallAdapterFactory
import com.me.cl.materialtabhost.MyApplication
import com.me.cl.materialtabhost.api.GistService
import com.me.cl.materialtabhost.data.GIST_HOST
import com.me.cl.materialtabhost.mvvm.data.local.base.room.AppDatabase
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(GIST_HOST).client(OkHttpClient.Builder().apply {
            addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(LiveDataCallAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideGistService(retrofit: Retrofit): GistService {
        return retrofit.create(GistService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "material-tab").build()
    }
}

@Suppress("unused")
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    MainActivitySubComponentModule::class
])
interface AppComponent{
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application:Application):Builder
        fun build():AppComponent
    }

    fun inject(application:MyApplication)
}
