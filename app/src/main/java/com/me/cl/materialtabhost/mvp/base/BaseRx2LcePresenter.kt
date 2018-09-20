package com.me.cl.materialtabhost.mvp.base

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView

import org.reactivestreams.Subscriber

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseRx2LcePresenter<V : MvpLceView<M>, M> : com.hannesdorfmann.mosby3.mvp.MvpBasePresenter<V>(), com.hannesdorfmann.mosby3.mvp.MvpPresenter<V> {

    protected var disposal: Disposable? = null

    /**
     * Unsubscribes the disposal and set it to null
     */
    protected fun unsubscribe() {
        disposal?.dispose()
        disposal = null
    }

    /**
     * Subscribes the presenter himself as disposal on the observable
     *
     * @param observable The observable to subscribe
     * @param pullToRefresh Pull to refresh?
     */
    fun subscribe(observable: Observable<M>, pullToRefresh: Boolean) {

        if (isViewAttached) {
            view.showLoading(pullToRefresh)
        }

        unsubscribe()

        disposal = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onNext,{
                    onError(it,pullToRefresh)
                },{
                 onCompleted()
                })
    }

    protected fun onCompleted() {
        if (isViewAttached) {
            view.showContent()
        }
        unsubscribe()
    }

    protected fun onError(e: Throwable, pullToRefresh: Boolean) {
        if (isViewAttached) {
            view.showError(e, pullToRefresh)
        }
        unsubscribe()
    }

    protected fun onNext(data: M) {
        if (isViewAttached) {
            view.setData(data)
        }
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        if (!retainInstance) {
            unsubscribe()
        }
    }
}
