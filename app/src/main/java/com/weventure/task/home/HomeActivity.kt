package com.weventure.task.home

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.weventure.task.R
import com.weventure.task.base.BaseActivity
import com.weventure.task.counter.CounterActivity
import com.weventure.task.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(), HomeActivityHandler {


    override fun initialize() {
        mViewModel = ViewModelProvider(
            this,
            HomeViewModelFactory()
        ).get(HomeViewModel::class.java)
        mViewDataBinding.viewModel = mViewModel
        mViewDataBinding.handler = this
    }

    override fun onResume() {
        observe()
        super.onResume()
    }

    fun observe() {
        mViewModel.isReadyToRedirect.observe(this, Observer {
            if (it) {
                var intent = Intent(
                    this,
                    CounterActivity::class.java
                )

                intent.putExtra("seconds", mViewModel.seconds.value!!.toInt())
                startActivity(
                    intent
                )
            }
            mViewModel.isReadyToRedirect.postValue(false)
        })

        mViewModel.message.observe(this, Observer {
            if (!TextUtils.isEmpty(it)) {
                showMessage(it)
            }
        })
    }


    override fun getLayoutId(): Int = R.layout.activity_home

    override fun onEnterSeconds() {
        mViewModel.setSeconds()
    }

}