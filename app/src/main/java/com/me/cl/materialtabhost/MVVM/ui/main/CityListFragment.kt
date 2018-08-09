package com.me.cl.materialtabhost.MVVM.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.cl.materialtabhost.MVVM.viewmodel.CityListViewModel
import com.me.cl.materialtabhost.R
import com.me.cl.materialtabhost.adapter.CityAdapterBind
import com.me.cl.materialtabhost.bean.entities.City
import kotlinx.android.synthetic.main.fragment_city_list.*

class CityListFragment:Fragment(){
    lateinit var viewModel: CityListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_city_list,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getTwoCityList().observe(this, Observer {
            it?.original?.run{
                vp_city_list.adapter=CityListPageAdapter(this)
            }
        })
        viewModel.getTitle().observe(this, Observer {
            titlebar.title=it?.original
        })
    }

   companion object {
       const val TAG="CityListFragment"
       fun newInstance():CityListFragment{
           return CityListFragment().apply {
           }
       }
   }
}

class CityListPageAdapter(val twoList:Array<List<City>>): PagerAdapter(){
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val baseContext=container.context
        val rv = LayoutInflater.from(baseContext).inflate(R.layout.recyclerview, container, false) as RecyclerView
        rv.apply {
            rv.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
            if (position == 0) {
                rv.adapter = CityAdapterBind().apply {
                    submitList(twoList[position])
                }
//                rv.tag = 0
//                rv_state0?.let {
//                    rv.layoutManager.onRestoreInstanceState(it)
//                    rv_state0 = null
//                }
            } else {
                rv.adapter = CityAdapterBind().apply {
                    submitList(twoList[position])
                }
//                rv.tag = 1
//                rv_state1?.let {
//                    rv.layoutManager.onRestoreInstanceState(it)
//                    rv_state1 = null
//                }
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