package com.me.cl.materialtabhost.mvvm.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evernote.android.state.State
import com.me.cl.materialtabhost.mvvm.viewmodel.CityListViewModel
import com.me.cl.materialtabhost.R
import com.me.cl.materialtabhost.adapter.CityAdapterBind
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.mvvm.di.Injectable
import com.me.cl.materialtabhost.mvvm.utils.ViewStatePagerAdapter
import kotlinx.android.synthetic.main.fragment_city_list.*
import javax.inject.Inject

class CityListFragment:Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory:ViewModelProvider.Factory
    lateinit var viewModel: CityListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_city_list,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(CityListViewModel::class.java)
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

class CityListPageAdapter(val twoList:Array<List<City>>): ViewStatePagerAdapter(){
     override fun createView(container: ViewGroup?, position: Int): View {
         val baseContext=container?.context
         val rv = LayoutInflater.from(baseContext).inflate(R.layout.recyclerview, container, false) as RecyclerView
         rv.apply {
             rv.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
             if (position == 0) {
                 rv.adapter = CityAdapterBind().apply {
                     submitList(twoList[position])
                 }
//                 rv.tag = 0
//                 rv_state0?.let {
//                     rv.layoutManager.onRestoreInstanceState(it)
//                     rv_state0 = null
//                 }
             } else {
                 rv.adapter = CityAdapterBind().apply {
                     submitList(twoList[position])
                 }
//                 rv.tag = 1
//                 rv_state1?.let {
//                     rv.layoutManager.onRestoreInstanceState(it)
//                     rv_state1 = null
//                 }
             }

//             container.addView(rv)
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