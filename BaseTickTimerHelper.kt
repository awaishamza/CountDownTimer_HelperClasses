package com.awais.testingapp.timer

import android.os.Handler
import android.os.Looper


open class BaseTickTimerHelper {

    protected var startTime = 0L
    protected var pauseTime = 0L
    protected var isRunning = false
    private var timerCallBack: ((String) -> Unit)? = null
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            
            handler.postDelayed(this, 1000) // update every 1 second
            timerCallBack?.invoke(getFormattedTime())
        }
    }

    protected fun uiUpdaterStart() {
        handler.postDelayed(runnable, 0)
    }

    protected fun uiUpdaterStop() {
        handler.removeCallbacks(runnable)

    }
    fun trigertimer(){
        timerCallBack?.invoke(getFormattedTime())
    }

    fun setTimerUpdate(callback: ((String) -> Unit)?) {
        timerCallBack = callback
    }
    protected fun getElapsedTime(): Long {
        return if (isRunning) {
            System.currentTimeMillis() - startTime
        } else {
            pauseTime
        }
    }

    protected fun getFormattedTime(): String {
        val millis = getElapsedTime()
        val seconds = (millis / 1000) % 60
        val minutes = (millis / (1000 * 60)) % 60
        val hours = (millis / (1000 * 60 * 60))
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }


}
