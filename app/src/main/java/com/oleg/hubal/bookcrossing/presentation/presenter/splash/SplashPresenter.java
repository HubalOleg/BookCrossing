package com.oleg.hubal.bookcrossing.presentation.presenter.splash;


import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.oleg.hubal.bookcrossing.data.AppPrefs;
import com.oleg.hubal.bookcrossing.presentation.view.splash.SplashView;

@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {

    private final AppPrefs mAppPrefs;
    private final Handler mHandler = new Handler();

    private OnCompleteListener<AuthResult> mOnSignInCompleteListener = new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                getViewState().StartMainActivity();
            } else {
                getViewState().StartAuthActivity();
            }
        }
    };

    public SplashPresenter(AppPrefs appPrefs) {
        mAppPrefs = appPrefs;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                CheckIsUserLoggedIn();
            }
        }, 2000);
    }


    private void CheckIsUserLoggedIn() {
        String email = mAppPrefs.getEmail();
        String password = mAppPrefs.getPassword();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            getViewState().StartAuthActivity();
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(mOnSignInCompleteListener);
        }
    }

}
