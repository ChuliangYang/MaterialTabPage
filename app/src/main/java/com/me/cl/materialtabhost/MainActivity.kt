package com.me.cl.materialtabhost

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.cl.materialtabhost.adapter.CityAdapter
import com.me.cl.materialtabhost.api.GistService
import com.me.cl.materialtabhost.bean.CityBean
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    val listOne:MutableList<CityBean> by lazy {
        mutableListOf<CityBean>()
    }
    val listTwo:MutableList<CityBean> by lazy {
        mutableListOf<CityBean>()
    }

    var vp: ViewPager?=null
    var myTabLayout: TabLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vp=vp_city_list
        myTabLayout=tabLayout
        myTabLayout?.setupWithViewPager(vp)
        vp?.currentItem = 0

        Retrofit.Builder()
                .baseUrl(GIST_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build().let {
                   val cityList= it.create(GistService::class.java).getCityList()
                    cityList.enqueue(object: Callback<List<CityBean>?> {
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
                                vp?.adapter = object: PagerAdapter() {
                                    override fun instantiateItem(container: ViewGroup, position: Int): Any {
                                        val rv = LayoutInflater.from(baseContext).inflate(R.layout.recyclerview, container, false) as RecyclerView
                                        rv.apply {
                                            if (position == 0) {
                                                rv.adapter = CityAdapter(baseContext, listOne)
                                            } else {
                                                rv.adapter = CityAdapter(baseContext, listTwo)
                                            }
                                            rv.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)

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
                    })
                }
    }
}
