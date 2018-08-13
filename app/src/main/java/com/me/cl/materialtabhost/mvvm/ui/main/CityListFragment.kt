package com.me.cl.materialtabhost.mvvm.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.cl.materialtabhost.R
import com.me.cl.materialtabhost.adapter.CityAdapterBind
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvvm.di.Injectable
import com.me.cl.materialtabhost.mvvm.utils.ViewStatePagerAdapter
import com.me.cl.materialtabhost.mvvm.utils.autoCleared
import com.me.cl.materialtabhost.mvvm.viewmodel.CityListViewModel
import kotlinx.android.synthetic.main.fragment_city_list.*
import javax.inject.Inject

class CityListFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: CityListViewModel
    private var viewPagerAdapter by autoCleared<CityListPageAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CityListViewModel::class.java)
        viewModel.getTwoCityList().observe(this, Observer {
            it?.original?.run {
                viewPagerAdapter=CityListPageAdapter(this)
                vp_city_list.adapter = viewPagerAdapter
            }
        })
        viewModel.getTitle().observe(this, Observer {
            titlebar.title = it?.original
        })
        tabLayout.setupWithViewPager(vp_city_list)
        vp_city_list.currentItem=0
    }

    companion object {
        const val TAG = "CityListFragment"
        fun newInstance(): CityListFragment {
            return CityListFragment().apply {
            }
        }
    }
}

class CityListPageAdapter(val twoList: Array<List<City>>) : ViewStatePagerAdapter() {
    override fun createView(container: ViewGroup?, position: Int): View {
        val baseContext = container?.context
        val rv = LayoutInflater.from(baseContext).inflate(R.layout.recyclerview, container, false) as RecyclerView
        rv.apply {
            rv.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
            if (position == 0) {
                rv.adapter = CityAdapterBind().apply {
                    submitList(twoList[position])
                }
            } else {
                rv.adapter = CityAdapterBind().apply {
                    submitList(twoList[position])
                }
            }
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

    override fun getCount(): Int {
        return twoList.size
    }

//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val baseContext=container.context
//        val rv = LayoutInflater.from(baseContext).inflate(R.layout.recyclerview, container, false) as RecyclerView
//        rv.apply {
//            rv.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
//            if (position == 0) {
//                rv.adapter = CityAdapterBind().apply {
//                    submitList(twoList[position])
//                }
//                rv.tag = 0
//                rv_state0?.let {
//                    rv.layoutManager.onRestoreInstanceState(it)
//                    rv_state0 = null
//                }
//            } else {
//                rv.adapter = CityAdapterBind().apply {
//                    submitList(twoList[position])
//                }
//                rv.tag = 1
//                rv_state1?.let {
//                    rv.layoutManager.onRestoreInstanceState(it)
//                    rv_state1 = null
//                }
//            }
//
//            container.addView(rv)
//        }
//
//        return rv
//    }

}