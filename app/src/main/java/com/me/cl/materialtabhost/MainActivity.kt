package com.me.cl.materialtabhost

import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evernote.android.state.State
import com.evernote.android.state.StateSaver
import com.me.cl.materialtabhost.adapter.CityAdapter
import com.me.cl.materialtabhost.api.GistService
import com.me.cl.materialtabhost.bean.CityBean
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class  MainActivity : AppCompatActivity() {

    @State
    var listOne: ArrayList<CityBean> = ArrayList()
    //            by lazy {
//        mutableListOf<CityBean>()
//    }
    @State
    var listTwo: ArrayList<CityBean> = ArrayList()
    //        by lazy {
//        mutableListOf<CityBean>()
//    }
    @State
    var rv_state0: Parcelable? = null
    @State
    var rv_state1: Parcelable? = null

    var vp: ViewPager?=null
    var myTabLayout: TabLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StateSaver.restoreInstanceState(this, savedInstanceState)
        setContentView(R.layout.activity_main)
        vp=vp_city_list
        myTabLayout=tabLayout
        myTabLayout?.setupWithViewPager(vp)
        vp?.currentItem = 0

        if (listOne.size * listTwo.size > 0) {
            configViewPager()
        } else {
            Retrofit.Builder()
                    .baseUrl(GIST_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().let {
                        val cityList = it.create(GistService::class.java).getCityList()
                        cityList.enqueue(object : Callback<List<CityBean>?> {
                            override fun onFailure(call: Call<List<CityBean>?>?, t: Throwable?) {
                            }

                            override fun onResponse(call: Call<List<CityBean>?>?, response: Response<List<CityBean>?>?) {
                                response?.body().apply {
                                    this?.forEach {
                                        if (it.rank.toInt()<500){
                                            listOne.add(it)
                                        }else{
                                            listTwo.add(it)
                                        }
                                    }
                                }

                                runOnUiThread {
                                    configViewPager()
                                }

                            }
                        })
                    }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let {
            vp?.childCount?.let {
                for (i in 0 until it) {
                    val child = vp?.getChildAt(i)
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

            StateSaver.saveInstanceState(this, it)
        }
    }

    private fun configViewPager() {
        vp?.adapter = object : PagerAdapter() {
            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val rv = LayoutInflater.from(baseContext).inflate(R.layout.recyclerview, container, false) as RecyclerView
                rv.apply {
                    rv.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
                    if (position == 0) {
                        rv.adapter = CityAdapter(baseContext, listOne)
                        rv.tag = 0
                        rv_state0?.let {
                            rv.layoutManager.onRestoreInstanceState(it)
                            rv_state0 = null
                        }
                    } else {
                        rv.adapter = CityAdapter(baseContext, listTwo)
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
}
