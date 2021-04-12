package com.weventure.task.home

import android.content.Context
import android.os.Looper
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.weventure.task.base.BaseViewModel


class HomeViewModel() : BaseViewModel() {

    var seconds = MutableLiveData<String>()
    val isReadyToRedirect = MutableLiveData<Boolean>()

    fun setSeconds() {
        if ( TextUtils.isEmpty(seconds.value)){
            message.postValue("Enter seconds please")
        }else{
            isReadyToRedirect.postValue(true)
        }
    }
}