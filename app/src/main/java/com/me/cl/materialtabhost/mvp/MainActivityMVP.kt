package com.me.cl.materialtabhost.mvp

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateActivity
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.ParcelableDataLceViewState
import com.me.cl.materialtabhost.R
import com.me.cl.materialtabhost.adapter.CityAdapter
import com.me.cl.materialtabhost.adapter.CityPageAdapter
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvp.base.DaggerMainMVPComponent
import com.me.cl.materialtabhost.mvp.base.MainMVPModule
import com.me.cl.materialtabhost.mvp.base.MainViewMVP
import kotlinx.android.synthetic.main.activity_main_mvp.*
import javax.inject.Inject

class MainActivityMVP : MvpLceViewStateActivity<ViewPager, CityModel, MainViewMVP, MainPresenter>(), MainViewMVP {
    @Inject
    lateinit var mainPresenter: MainPresenter

    var cityModel = CityModel(emptyList(), emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainMVPComponent.builder().mainMVPModule(MainMVPModule(this)).build().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_mvp)
        contentView?.apply {
            adapter = CityPageAdapter(arrayListOf())
            currentItem = 0
        }.let {
            tabLayout?.setupWithViewPager(it)
        }
    }

    override fun setData(data: CityModel?) {
        bindToViewPager(data)
    }

    override fun loadData(pullToRefresh: Boolean) {
        mainPresenter.loadCities(pullToRefresh)
    }

    override fun createPresenter(): MainPresenter {
        return mainPresenter
    }

    override fun createViewState(): LceViewState<CityModel, MainViewMVP> {
        return ParcelableDataLceViewState<CityModel, MainViewMVP>()
    }

    override fun getData(): CityModel {
        return cityModel
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        return e.toString()
    }

    fun bindToViewPager(data: CityModel?) {
        data?.let {
            cityModel = it
            (contentView?.adapter as? CityPageAdapter)?.apply {
                setData(arrayListOf<List<City>>().apply {
                    add(it.city1)
                    add(it.city2)
                })
            }
            contentView.currentItem = it.selected?:0
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        cityModel.selected = contentView?.currentItem
    }


}

//class MainActivityMVP : AppCompatActivity(), MainView {
//
//    var mtitlebar: Toolbar? = null
//    var mtabLayout: TabLayout? = null
//    var mfabAdd: FloatingActionButton? = null
//    var mvpCityList: ViewPager? = null
//    val provider: LifecycleProvider<Lifecycle.Event> = AndroidLifecycle.createLifecycleProvider(this)
//
//    @Inject
//    lateinit var presenter: MainPresenter
//
//    @State
//    var rv_state0: Parcelable? = null
//    @State
//    var rv_state1: Parcelable? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        DaggerMainComponent.builder().mainModule(MainModule(this)).build().inject(this)
//        StateSaver.restoreInstanceState(this, savedInstanceState)
//        setContentView(R.layout.activity_main)
//        mtitlebar = titlebar
//        mtabLayout = tabLayout
//        mfabAdd = fab_add
//        mvpCityList = vp_city_list
//        mtabLayout?.setupWithViewPager(mvpCityList)
//        mvpCityList?.currentItem = 0
//
//        presenter.manage(this)
//        presenter.init(provider, savedInstanceState)
//    }
//
//    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
//        outState?.let {
//            mvpCityList?.childCount?.let {
//                for (i in 0 until it) {
//                    val child = mvpCityList?.getChildAt(i)
//                    if (child is RecyclerView) {
//                        val state = child.layoutManager.onSaveInstanceState()
//                        if (child.getTag() == 0) {
//                            rv_state0 = state
//                        } else if (child.getTag() == 1) {
//                            rv_state1 = state
//                        }
//                    }
//                }
//            }
//            presenter.saveState(it)
//            StateSaver.saveInstanceState(this, it)
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        presenter.destroy()
//    }
//
//    override fun bindToViewPager(twoList: List<List<City>>) {
//        mvpCityList?.adapter = object : PagerAdapter() {
//            override fun instantiateItem(container: ViewGroup, position: Int): Any {
//                val rv = LayoutInflater.from(baseContext).inflate(R.layout.recyclerview, container, false) as RecyclerView
//                rv.apply {
//                    rv.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
//                    if (position == 0) {
//                        rv.adapter = CityAdapter(baseContext, twoList[0])
//                        rv.tag = 0
//                        rv_state0?.let {
//                            rv.layoutManager.onRestoreInstanceState(it)
//                            rv_state0 = null
//                        }
//                    } else {
//                        rv.adapter = CityAdapter(baseContext, twoList[1])
//                        rv.tag = 1
//                        rv_state1?.let {
//                            rv.layoutManager.onRestoreInstanceState(it)
//                            rv_state1 = null
//                        }
//                    }
//
//                    container.addView(rv)
//                }
//
//                return rv
//            }
//
//            override fun getPageTitle(position: Int): CharSequence? {
//                return if (position == 0) {
//                    "NO.ONE"
//                } else {
//                    "TWO"
//                }
//            }
//
//            override fun isViewFromObject(view: View, `object`: Any): Boolean {
//                return view == `object`
//            }
//
//            override fun getCount(): Int {
//                return 2
//            }
//        }
//    }
//}

