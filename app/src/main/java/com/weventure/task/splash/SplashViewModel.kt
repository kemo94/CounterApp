package com.weventure.task.splash

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.weventure.task.base.BaseViewModel


class SplashViewModel : BaseViewModel() {


    val isReadyToRedirect = MutableLiveData<Boolean>()

    fun setTimeSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            isReadyToRedirect.postValue(true)
        }, 3000)
    }
}