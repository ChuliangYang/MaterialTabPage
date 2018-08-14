package com.me.cl.materialtabhost.mvp

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvp.MainInteractorImpl.Companion.STATE_RV_MODEL_0
import com.me.cl.materialtabhost.mvp.MainInteractorImpl.Companion.STATE_RV_MODEL_1
import com.me.cl.materialtabhost.mvp.base.MainInteractor
import com.me.cl.materialtabhost.mvp.base.MainPresenter
import com.me.cl.materialtabhost.mvp.base.MainView
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(val interactor: MainInteractor) : MainPresenter {

    var view: MainView? = null
    var provider: LifecycleProvider<Lifecycle.Event>? = null

    override fun init(provider: LifecycleProvider<Lifecycle.Event>, savedInstanceState: Bundle?) {
        this.provider = provider
        if (interactor.restoreState(savedInstanceState)) {
            view?.bindToViewPager(mutableListOf<List<City>>().apply {
                interactor.getCache(STATE_RV_MODEL_0)?.let {
                    add(it as List<City>)
                } ?: apply {
                    add(arrayListOf())
                }

                interactor.getCache(STATE_RV_MODEL_1)?.let {
                    add(it as List<City>)
                } ?: apply {
                    add(arrayListOf())
                }
            })
        } else {
            interactor.fetchCityList().subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).map {
                interactor.divideIntoTwoList(it)
            }.observeOn(AndroidSchedulers.mainThread()).compose(provider.bindToLifecycle()).subscribe {
                view?.bindToViewPager(it)
                interactor.cached(STATE_RV_MODEL_0, it[0])
                interactor.cached(STATE_RV_MODEL_1, it[1])
            }
        }
    }

    override fun manage(view: MainView) {
        this.view = view
    }

    override fun saveState(outState: Bundle) {
        interactor.saveState(outState)
    }

    override fun destroy() {
        interactor.release()
        view = null
    }

}