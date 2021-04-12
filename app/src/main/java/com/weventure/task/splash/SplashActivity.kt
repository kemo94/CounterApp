package com.weventure.task.splash

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import com.weventure.task.R
import com.weventure.task.base.BaseActivity
import androidx.lifecycle.ViewModelProvider
import com.weventure.task.databinding.ActivitySplashBinding
import com.weventure.task.home.HomeActivity

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override fun initialize() {
        mViewModel = ViewModelProvider(
            this,
            SplashViewModelFactory()
        ).get(SplashViewModel::class.java)
    }

    fun observe() {
        mViewModel.isReadyToRedirect.observe(this, Observer {
            startActivity(
                Intent(
                    this,
                    HomeActivity::class.java
                )
            )
            finish()
        })
    }


    override fun getLayoutId(): Int = R.layout.activity_splash
    override fun onResume() {
        mViewModel.setTimeSplash()
        observe()
        super.onResume()
    }
}
