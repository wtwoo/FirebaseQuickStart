package com.wtwoo.girlsinger.worldcup.util;

import android.os.SystemClock
import io.reactivex.Observable

/**
 * Created by wtwoo on 2017-02-17.
 */

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