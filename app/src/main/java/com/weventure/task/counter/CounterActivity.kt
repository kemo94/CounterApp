package com.weventure.task.counter

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.weventure.task.R
import com.weventure.task.base.BaseActivity
import com.weventure.task.databinding.ActivityCounterBinding
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : BaseActivity<CounterViewModel, ActivityCounterBinding>() {
    var seconds = 0
    override fun initialize() {
        mViewModel = ViewModelProvider(
            this,
            CounterViewModelFactory()
        ).get(CounterViewModel::class.java)
        mViewDataBinding.viewModel = mViewModel
    }

    override fun onSaveInstanceState(
        savedInstanceState: Bundle,
        outPersistentState: PersistableBundle
    ) {
        super.onSaveInstanceState(savedInstanceState, outPersistentState)
        savedInstanceState.putInt("seconds", mViewModel.passedSeconds)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey("seconds"))
            seconds = savedInstanceState.getInt("seconds")
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
            seconds = intent.getIntExtra("seconds", 0)
            mViewModel.runTimer(seconds)
        }
        observe()

        super.onResume()
    }
   // to prevent count from start when activity is paused
    override fun onPause() {
        intent.putExtra("seconds", mViewModel.passedSeconds)
        super.onPause()
    }


}