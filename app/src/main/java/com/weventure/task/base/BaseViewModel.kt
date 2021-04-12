package com.weventure.task.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val message = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val hideLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Any>()
}