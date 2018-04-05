package com.me.cl.materialtabhost.main

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evernote.android.state.State
import com.evernote.android.state.StateSaver
import com.me.cl.materialtabhost.R
import com.me.cl.materialtabhost.adapter.CityAdapter
import com.me.cl.materialtabhost.bean.CityBean
import com.me.cl.materialtabhost.main.base.DaggerMainComponent
import com.me.cl.materialtabhost.main.base.MainModule
import com.me.cl.materialtabhost.main.base.MainPresenter
import com.me.cl.materialtabhost.main.base.MainView
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle2.LifecycleProvider
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivityMVP : AppCompatActivity(), MainView {

    var mtitlebar: Toolbar? = null
    var mtabLayout: TabLayout? = null
    var mfabAdd: FloatingActionButton? = null
    var mvpCityList: ViewPager? = null
    val provider: LifecycleProvider<Lifecycle.Event> = AndroidLifecycle.createLifecycleProvider(this)

    @Inject
    lateinit var presenter: MainPresenter

    @State
    var rv_state0: Parcelable? = null
    @State
    var rv_state1: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainComponent.builder().mainModule(MainModule(this)).build().inject(this)
        StateSaver.restoreInstanceState(this, savedInstanceState)
        setContentView(R.layout.activity_main)
        mtitlebar = titlebar
        mtabLayout = tabLayout
        mfabAdd = fab_add
        mvpCityList = vp_city_list
        mtabLayout?.setupWithViewPager(mvpCityList)
        mvpCityList?.currentItem = 0

        presenter.manage(this)
        presenter.init(provider, savedInstanceState)
    }

    override fun bindToViewPager(twoList: List<List<CityBean>>) {
        mvpCityList?.adapter = object : PagerAdapter() {
            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val rv = LayoutInflater.from(baseContext).inflate(R.layout.recyclerview, container, false) as RecyclerView
                rv.apply {
                    rv.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
                    if (position == 0) {
                        rv.adapter = CityAdapter(baseContext, twoList[0])
                        rv.tag = 0
                        rv_state0?.let {
                            rv.layoutManager.onRestoreInstanceState(it)
                            rv_state0 = null
                        }
                    } else {
                        rv.adapter = CityAdapter(baseContext, twoList[1])
                        rv.tag = 1
                        rv_state1?.let {
                            rv.layoutManager.onRestoreInstanceState(it)
                            rv_state1 = null
                        }
                    }

                    container.addView(rv)
                }

                return rv
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return if (position == 0) {
                    "NO.ONE"
                } else {
                    "TWO"
                }
            }

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view == `object`
            }

            override fun getCount(): Int {
                return 2
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let {
            mvpCityList?.childCount?.let {
                for (i in 0 until it) {
                    val child = mvpCityList?.getChildAt(i)
                    if (child is RecyclerView) {
                        val state = child.layoutManager.onSaveInstanceState()
                        if (child.getTag() == 0) {
                            rv_state0 = state
                        } else if (child.getTag() == 1) {
                            rv_state1 = state
                        }
                    }
                }
            }
            presenter.saveState(it)
            StateSaver.saveInstanceState(this, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

}