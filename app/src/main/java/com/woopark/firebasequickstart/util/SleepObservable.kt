package com.woopark.firebasequickstart.util

import android.os.SystemClock
import io.reactivex.Observable

object SleepObservable {
    fun sleepIfBeforeTargetTime(targetTime: Long): Observable<String> {
        return Observable.create { subscriber ->
            val sleepMillis = targetTime - SystemClock.uptimeMillis()
            if (sleepMillis > 0) {
                try {
                    Thread.sleep(sleepMillis)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            subscriber.onNext("")
            subscriber.onComplete()
        }
    }
}