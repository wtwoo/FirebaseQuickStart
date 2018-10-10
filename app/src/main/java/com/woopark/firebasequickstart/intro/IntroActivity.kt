package com.woopark.firebasequickstart.intro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.woopark.firebasequickstart.R
import com.woopark.firebasequickstart.util.ActivityUtils

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        ActivityUtils.startSignInActivity(this)
    }
}
