package com.woopark.rxfirebase.auth;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Single;

public class RxFirebaseAuth {
    @CheckResult
    @NonNull
    public static Single<FirebaseUser> signInWithCredential(
            @NonNull FirebaseAuth firebaseAuth, @NonNull AuthCredential authCredential) {
        return new SignInWithCredentialObserver(firebaseAuth, authCredential);
    }
}
