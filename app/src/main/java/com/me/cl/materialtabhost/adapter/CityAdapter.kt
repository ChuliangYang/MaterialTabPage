package com.me.cl.materialtabhost.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.me.cl.materialtabhost.R

import com.me.cl.materialtabhost.bean.CityBean

class CityAdapter(val context: Context,val cityBeanList: List<CityBean>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CityHolder(LayoutInflater.from(context).inflate(R.layout.item_city,parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CityHolder ){
            holder.apply {
                cityBeanList?.let {
                    tv_city.text = it[position].city
                    tv_state.text = it[position].state
                    tv_population.text = it[position].population
                }

            }

        }

    }

    override fun getItemCount(): Int {
        return cityBeanList?.size ?: 0
    }

    inner class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var tv_city: TextView
         var tv_state: TextView
         var tv_population: TextView

        init {
            tv_city = itemView.findViewById(R.id.tv_city)
            tv_state = itemView.findViewById(R.id.tv_state)
            tv_population = itemView.findViewById(R.id.tv_population)
        }
    }


}
