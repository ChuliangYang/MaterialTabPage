package com.me.cl.materialtabhost.mvp

import android.support.v4.view.ViewPager
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateActivity
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvp.base.MainViewMVP

class MainActivityN:MvpLceViewStateActivity<ViewPager,List<List<City>>,MainViewMVP,MainPresenter>() {

    override fun setData(data: List<List<City>>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData(pullToRefresh: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPresenter(): MainPresenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createViewState(): LceViewState<List<List<City>>, MainViewMVP> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getData(): List<List<City>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}