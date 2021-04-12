package com.weventure.task.counter

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.weventure.task.base.BaseViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class CounterViewModel() : BaseViewModel() {

    var timer = MutableLiveData<String>()
    var timerCountDown: CountDownTimer? = null

    fun runTimer(seconds: Int) {
        var date = addTime(seconds)
        timerCountDown = object : CountDownTimer(date!!.time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.postValue(getDifferenceTime(date!!.time))

            }

            override fun onFinish() {
                timer.postValue("00:00:00:00")
            }
        }

        timerCountDown!!.start()

    }

    private fun getDifferenceTime(mills: Long): String? {

        var different: Long = mills - Date().time
        var totalSecondsLeft = different / 1000


        val days = totalSecondsLeft / (24 * 60 * 60 * 1000)
        var hours = totalSecondsLeft / 3600
        var minutes = totalSecondsLeft % 3600 / 60
        var seconds = totalSecondsLeft % 60

        return pad(days) + ":" + pad(hours) + ":" + pad(minutes) + ":" + pad(seconds)
    }

    private fun pad(num: Long): String? {
        if (num in 0..9)
            return "0$num"

        if (num < 0)
            return "00"
        return num.toString()
    }

    private fun addTime(seconds: Int): Date? {
        val sdf = SimpleDateFormat("yy:MM:dd:HH:mm:ss", Locale.getDefault())
        val currentDateAndTime: String = sdf.format(Date())

        var date: Date? = null
        try {
            date = sdf.parse(currentDateAndTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.SECOND, seconds)

        return calendar.time
    }

}