package com.redix.testingapp.timer

class TickTimer:BaseTickTimerHelper() {
    fun start() {
        if (!isRunning) {
            uiUpdaterStart()
            startTime = System.currentTimeMillis() - pauseTime
            isRunning = true
        }
        trigertimer()
    }

    fun pause() {
        if (isRunning) {
            uiUpdaterStop()
            pauseTime = System.currentTimeMillis() - startTime
            isRunning = false
        }
        trigertimer()
    }

    fun reset() {
        uiUpdaterStop()
        startTime = 0L
        pauseTime = 0L
        isRunning = false
        trigertimer()
    }
}
