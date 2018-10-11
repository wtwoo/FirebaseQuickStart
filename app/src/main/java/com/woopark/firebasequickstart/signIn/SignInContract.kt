package com.woopark.firebasequickstart.signIn

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.woopark.firebasequickstart.base.BasePresenter
import com.woopark.firebasequickstart.base.BaseView

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