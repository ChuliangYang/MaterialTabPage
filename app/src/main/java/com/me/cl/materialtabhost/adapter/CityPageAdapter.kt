package com.me.cl.materialtabhost.adapter

import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.cl.materialtabhost.R
import com.me.cl.materialtabhost.data.entities.City

class CityPageAdapter(var cities: List<List<City>>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rv = LayoutInflater.from(container.context).inflate(R.layout.recyclerview, container, false) as RecyclerView
        rv.apply {
            layoutManager = LinearLayoutManager(container.context, LinearLayoutManager.VERTICAL, false)
            adapter = CityAdapter(container.context, cities[position])
            tag = position
            container.addView(rv)
        }

        return rv
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            "ONE"
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

    fun setData(newCities: List<List<City>>) {
        cities = newCities
        notifyDataSetChanged()
    }
}