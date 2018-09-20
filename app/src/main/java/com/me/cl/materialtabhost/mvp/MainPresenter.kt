package com.me.cl.materialtabhost.mvp

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvp.base.BaseRx2LcePresenter
import com.me.cl.materialtabhost.mvp.base.MainViewMVP

class MainPresenter:BaseRx2LcePresenter<MainViewMVP,List<List<City>>>() {
}