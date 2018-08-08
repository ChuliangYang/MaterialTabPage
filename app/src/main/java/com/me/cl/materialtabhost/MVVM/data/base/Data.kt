package com.me.cl.materialtabhost.MVVM.data.base

// Encapsulate original data as well as loading status
data class Data<OriginalType,MessageType>(val loadingStatus:DataStatus, val original:OriginalType?, val extraMessage:MessageType?){
    companion object {
        fun <T> success(originalData:T?, extraMessage:String?=null):Data<T,String>{
            return Data(DataStatus.SUCCESS,originalData,extraMessage)
        }
        fun <T> failed(extraMessage:String?, originalData:T?=null):Data<T,String>{
            return Data(DataStatus.FAILED,originalData,extraMessage)
        }
        fun <T> loading(progress:Int?=null, originalData:T?=null):Data<T, Int>{
            return Data(DataStatus.LOADING,originalData,progress)
        }
    }
}

enum class DataStatus{
    SUCCESS,
    FAILED,
    LOADING
}