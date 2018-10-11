package com.woopark.firebasequickstart.mvp.ui.intro

import android.os.Bundle
import com.woopark.firebasequickstart.R
import com.woopark.firebasequickstart.mvp.ui.base.BaseActivity

class IntroActivity : BaseActivity(), IntroContract.View {

    private lateinit var presenter: IntroContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        presenter = IntroPresenter(this).apply {
            start()
        }
    }
}
