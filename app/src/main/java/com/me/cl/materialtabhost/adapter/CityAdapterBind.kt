package com.me.cl.materialtabhost.adapter

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.me.cl.materialtabhost.R
import com.me.cl.materialtabhost.data.entities.City
import com.me.cl.materialtabhost.databinding.ItemCityBindBinding

class CityAdapterBind: ListAdapter<City, DataBoundViewHolder<ItemCityBindBinding>>(object:DiffUtil.ItemCallback<City>(){
    override fun areItemsTheSame(oldItem: City?, newItem: City?): Boolean {
       return oldItem?.uid==newItem?.uid
    }

    override fun areContentsTheSame(oldItem: City?, newItem: City?): Boolean {
        return oldItem?.city==newItem?.city
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<ItemCityBindBinding> {
        val binding = DataBindingUtil.inflate<ItemCityBindBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_city_bind,
                parent,
                false
        )
        return DataBoundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<ItemCityBindBinding>, position: Int) {
            holder.binding.city=getItem(position)
            holder.binding.executePendingBindings()
    }
}