package com.weventure.task.counter

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.weventure.task.R
import com.weventure.task.base.BaseActivity
import com.weventure.task.databinding.ActivityCounterBinding
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : BaseActivity<CounterViewModel, ActivityCounterBinding>() {

    override fun initialize() {
        mViewModel = ViewModelProvider(
            this,
            CounterViewModelFactory()
        ).get(CounterViewModel::class.java)
        mViewDataBinding.viewModel = mViewModel
    }


    override fun getLayoutId(): Int = R.layout.activity_counter

    fun observe() {
        mViewModel.timer.observe(this, Observer {
            if (timer_txv != null)
                timer_txv.text = it
        })
    }

    override fun onResume() {
        if (intent.hasExtra("seconds")) {
            var seconds = intent.getIntExtra("seconds", 0)
            mViewModel.runTimer(seconds)
        }
        observe()

        super.onResume()
    }

    override fun onStop() {
        mViewModel.timerCountDown!!.cancel()
        super.onStop()
    }

}