package com.woopark.rxfirebase.auth;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.woopark.rxfirebase.OnCompleteDisposable;

import io.reactivex.Single;
import io.reactivex.SingleObserver;

final class SignInWithCredentialObserver extends Single<FirebaseUser> {

    private final FirebaseAuth firebaseAuth;
    private final AuthCredential authCredential;

    SignInWithCredentialObserver(FirebaseAuth firebaseAuth, AuthCredential authCredential) {
        this.firebaseAuth = firebaseAuth;
        this.authCredential = authCredential;
    }

    @Override
    protected void subscribeActual(SingleObserver<? super FirebaseUser> observer) {
        Listener listener = new Listener(observer);
        observer.onSubscribe(listener);
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(listener);
    }

    static final class Listener extends OnCompleteDisposable<AuthResult> {
        private final SingleObserver<? super FirebaseUser> observer;
        Listener(@NonNull SingleObserver<? super FirebaseUser> observer) {
            this.observer = observer;
        }

        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (!isDisposed()) {
                if (!task.isSuccessful()) {
                    observer.onError(task.getException());
                } else {
                    observer.onSuccess(task.getResult().getUser());
                }
            }
        }
    }
}