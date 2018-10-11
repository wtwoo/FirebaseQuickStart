package com.woopark.firebasequickstart.mvp.ui.intro

import com.woopark.firebasequickstart.mvp.ui.base.BasePresenter
import com.woopark.firebasequickstart.mvp.ui.base.BaseView

interface IntroContract {

    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
    }
}