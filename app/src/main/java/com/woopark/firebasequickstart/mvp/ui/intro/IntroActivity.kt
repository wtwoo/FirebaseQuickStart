package com.woopark.firebasequickstart.mvp.ui.intro

import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.woopark.firebasequickstart.R
import com.woopark.firebasequickstart.mvp.utils.ActivityUtils
import com.woopark.firebasequickstart.mvp.utils.SleepObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class IntroActivity : AppCompatActivity() {
    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        startMainActivityDelayed()
    }

    private fun startMainActivityDelayed() {
        SleepObservable.sleepIfBeforeTargetTime(SystemClock.uptimeMillis() + DELAY_TIME)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {}
                .doAfterTerminate {}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    ActivityUtils.startSignInActivity(this)
                }, {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }).let { compositeDisposable.add(it) }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    companion object {
        val TAG: String = IntroActivity::class.java.simpleName
        private const val DELAY_TIME = 3000
    }
}
