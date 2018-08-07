package com.me.cl.materialtabhost.MVVM.ui.main

import android.support.v4.app.Fragment

class CityListFragment:Fragment(){
   companion object {
       const val TAG="CityListFragment"
       @JvmStatic
       fun newInstance():CityListFragment{
           return CityListFragment().apply {
           }
       }
   }
}