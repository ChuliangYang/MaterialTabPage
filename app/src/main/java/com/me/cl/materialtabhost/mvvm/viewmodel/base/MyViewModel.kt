package com.me.cl.materialtabhost.mvvm.viewmodel.base

import android.arch.lifecycle.ViewModel

open class MyViewModel:ViewModel(){
    val cache= mutableMapOf<String?,Any?>()
}

inline fun getMethodName(index:Int):String{
    return Throwable().stackTrace[index].methodName
}

 inline fun <T> MyViewModel.reuseWhenAlive(additionKey:String?=null,dataProducer:()->T):T{
    val key="${getMethodName(0)}${additionKey?.let{
        ":$it"
    }}"
    return if(cache[key]!=null){
        cache[key] as T
    }else{
        dataProducer().apply {
            cache[key]=this
        }
    }
}
