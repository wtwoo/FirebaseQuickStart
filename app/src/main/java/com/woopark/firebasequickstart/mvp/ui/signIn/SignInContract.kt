package com.woopark.firebasequickstart.mvp.ui.signIn

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.woopark.firebasequickstart.mvp.ui.base.BasePresenter
import com.woopark.firebasequickstart.mvp.ui.base.BaseView

interface SignInContract {

    interface View : BaseView<Presenter> {
        fun updateUI(user: FirebaseUser?)
    }

    interface Presenter : BasePresenter {
        fun firebaseAuthWith(authCredential : AuthCredential)
        fun signIn()
        fun signOut()
        fun revokeAccess()
    }
}