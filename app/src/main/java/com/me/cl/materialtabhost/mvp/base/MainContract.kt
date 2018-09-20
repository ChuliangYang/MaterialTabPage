package com.me.cl.materialtabhost.mvp.base

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvp.CityModel
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable

interface MainViewMVP:MvpLceView<CityModel>