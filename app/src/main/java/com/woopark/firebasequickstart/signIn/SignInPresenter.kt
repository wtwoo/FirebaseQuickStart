package com.woopark.firebasequickstart.signIn

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth

class SignInPresenter : SignInContract.Presenter {
    private val auth: FirebaseAuth  by lazy {
        FirebaseAuth.getInstance()
    }


    override fun firebaseAuthWith(acct: GoogleSignInAccount) {

    }


    override fun start() {
    }
}