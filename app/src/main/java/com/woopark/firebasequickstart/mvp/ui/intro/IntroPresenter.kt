package com.woopark.firebasequickstart.mvp.ui.intro

import android.app.Activity
import android.os.SystemClock
import android.widget.Toast
import com.woopark.firebasequickstart.mvp.utils.ActivityUtils
import com.woopark.firebasequickstart.mvp.utils.SleepObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class IntroPresenter(private val view: IntroContract.View)
    : IntroContract.Presenter {
    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }

    private fun getActivity(): Activity {
        return view as Activity
    }

    override fun start() {
        startSignInActivityDelayed()
    }

    private fun startSignInActivityDelayed() {
        SleepObservable.sleepIfBeforeTargetTime(SystemClock.uptimeMillis() + DELAY_TIME)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {}
                .doAfterTerminate {}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    ActivityUtils.startSignInActivity(getActivity())
                }, {
                    Toast.makeText(getActivity(), it.message, Toast.LENGTH_SHORT).show()
                }).let { compositeDisposable.add(it) }
    }

    companion object {
        val TAG: String = IntroPresenter::class.java.simpleName
        private const val DELAY_TIME = 3000
    }
}